package com.revisoes.basico.colecoes.implementacoes;

import java.util.*;
import java.io.*;

/**
 * Exercise:
 * Write a program that reads a text file, specified by the first command line
 * argument, into a List. The program should then print random lines from the
 * file, the number of lines printed to be specified by the second command line
 * argument. Write the program so that a correctly-sized collection is allocated
 * all at once, instead of being gradually expanded as the file is read in.
 * Hint: To determine the number of lines in the file, use java.io.File.length
 * to obtain the size of the file, then divide by an assumed size of an average
 * line.
 *
 * Answer:
 * Since we are accessing the List randomly, we will use ArrayList. We estimate
 * the number of lines by taking the file size and dividing by 50. We then
 * double that figure, since it is more efficient to overestimate than to
 * underestimate.
 * 
 * terminal: java FileList.java arquivo.txt 7
 */
public class FileList {
  public static void main(String[] args) {
    final int assumedLineLength = 50;
    File file = new File(args[0]);
    List<String> fileList = new ArrayList<String>((int) (file.length() / assumedLineLength) * 2);
    BufferedReader reader = null;
    int lineCount = 0;

    try {
      reader = new BufferedReader(new FileReader(file));

      for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        fileList.add(line);
        lineCount++;
      }
    } catch (IOException e) {
      System.err.format("Could not read %s: %s%n", file, e);
      System.exit(1);

    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
        }
      }
    }

    int repeats = Integer.parseInt(args[1]);
    Random random = new Random();

    for (int i = 0; i < repeats; i++) {
      System.out.format("%d: %s%n", i + 1,
          fileList.get(random.nextInt(lineCount - 1)));
    }
  }
}
