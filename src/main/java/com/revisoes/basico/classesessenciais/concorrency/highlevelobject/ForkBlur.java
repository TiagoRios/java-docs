package com.revisoes.basico.classesessenciais.concorrency.highlevelobject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import javax.imageio.ImageIO;

/** 
 * ForkBlur implements a simple horizontal image blur. It averages pixels in the
 * source array and writes them to a destination array. The sThreshold value
 * determines whether the blurring will be performed directly or split into two
 * tasks.
 *
 * This is not the recommended way to blur images; it is only intended to
 * illustrate the use of the Fork/Join framework.
 */
public class ForkBlur extends RecursiveAction {

    private int[] mSource;
    private int mStart;
    private int mLength;
    private int[] mDestination;
    private int mBlurWidth = 15; // Processing window size, should be odd.

    public ForkBlur(int[] src, int start, int length, int[] dst) {
        mSource = src;
        mStart = start;
        mLength = length;
        mDestination = dst;
    }

    // Average pixels from source, write results into destination.
    protected void computeDirectly() {
        int sidePixels = (mBlurWidth - 1) / 2;

        for (int index = mStart; index < mStart + mLength; index++) {

            // Calculate average.
            float redt = 0, greent = 0, bluet = 0;

            for (int mi = -sidePixels; mi <= sidePixels; mi++) {

                int mindex = Math.min(Math.max(mi + index, 0), mSource.length - 1);
                int pixel = mSource[mindex];

                redt += (float) ((pixel & 0x00ff0000) >> 16) / mBlurWidth;
                greent += (float) ((pixel & 0x0000ff00) >> 8) / mBlurWidth;
                bluet += (float) ((pixel & 0x000000ff) >> 0) / mBlurWidth;
            }

            // Re-assemble destination pixel.
            int dpixel = (0xff000000)
                    | (((int) redt) << 16)
                    | (((int) greent) << 8)
                    | (((int) bluet) << 0);

            mDestination[index] = dpixel;
        }
    }

    protected static int sThreshold = 10000; // limit

    @Override
    protected void compute() {
        if (mLength < sThreshold) {
            computeDirectly();
            return;
        }

        int split = mLength / 2;

        invokeAll(
                new ForkBlur(mSource, mStart, split, mDestination),
                new ForkBlur(mSource, mStart + split, mLength - split, mDestination));
    }

    // Plumbing follows.
    public static void main(String[] args) throws Exception {

        BufferedImage image = ImageIO.read(new File("a.jpg"));

        System.out.println(image.getData());

        BufferedImage blurredImage = blur(image);

        System.out.println(blurredImage.getData());

        // não sei como escrever. tentei muitas maneiras.
        ImageIO.write(blurredImage, "jpg", new File("a-blur.jpg"));

    }

    public static BufferedImage blur(BufferedImage srcImage) {
        int imageWidth = srcImage.getWidth();
        int imageHeight = srcImage.getHeight();

        int[] source = srcImage.getRGB(0, 0, imageWidth, imageHeight, null, 0, imageWidth);
        int[] destiny = new int[source.length];

        System.out.println("Array size is " + source.length);
        System.out.println("Threshold is " + sThreshold);

        int processors = Runtime.getRuntime().availableProcessors();

        System.out.println(Integer.toString(processors) + " processor"
                + (processors != 1 ? "s are " : " is ")
                + "available");

        ForkBlur forkBlur = new ForkBlur(source, 0, source.length, destiny);

        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.currentTimeMillis();
        pool.invoke(forkBlur);
        long endTime = System.currentTimeMillis();

        System.out.println("Image blur took " + (endTime - startTime) +
                " milliseconds.");

        BufferedImage destinyImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);

        destinyImage.setRGB(0, 0, imageWidth, imageHeight, destiny, 0, imageWidth);

        return destinyImage;
    }
}