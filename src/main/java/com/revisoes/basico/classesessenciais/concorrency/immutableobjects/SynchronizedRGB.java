package com.revisoes.basico.classesessenciais.concorrency.immutableobjects;

/**
 * The class, SynchronizedRGB, defines objects that represent colors. Each
 * object represents the color as three integers that stand for primary color
 * values and a string that gives the name of the color.
 * <p>
 * SynchronizedRGB must be used carefully to avoid being seen in an inconsistent
 * state. Suppose, for example, a thread executes the following code:
 * </p>
 * 
 * <blockquote>
 * 
 * <pre>
 * SynchronizedRGB color = new SynchronizedRGB(0, 0, 0, "Pitch Black");
 * // ...
 * int myColorInt = color.getRGB(); // Statement 1
 * String myColorName = color.getName(); // Statement 2
 * </pre>
 * 
 * </blockquote>
 * 
 * <p>
 * If another thread invokes color.set after Statement 1 but before Statement 2,
 * the value of myColorInt won't match the value of myColorName. To avoid this
 * outcome, the two statements must be bound together:
 * </p>
 * 
 * <blockquote>
 * 
 * <pre>
 * synchronized (color) {
 *     int myColorInt = color.getRGB();
 *     String myColorName = color.getName();
 * }
 * </pre>
 * 
 * </blockquote>
 * 
 * This kind of inconsistency is only possible for mutable objects — it will not
 * be an issue for the immutable version of SynchronizedRGB.
 */
public class SynchronizedRGB {

    // Values must be between 0 and 255.
    private int red;
    private int green;
    private int blue;
    private String name;

    private void check(int red,
            int green,
            int blue) {
        if (red < 0 || red > 255
                || green < 0 || green > 255
                || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public SynchronizedRGB(int red, int green, int blue, String name) {
        check(red, green, blue);

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public void set(int red, int green, int blue, String name) {
        check(red, green, blue);

        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }
    }

    public synchronized int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void invert() {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
        name = "Inverse of " + name;
    }
}
