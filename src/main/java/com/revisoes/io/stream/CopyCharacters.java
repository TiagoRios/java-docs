package com.revisoes.io.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.revisoes.io.MyPath;

/**
 * All character stream classes are descended from {@link java.io.Reader Reader}
 * and {@link java.io.Writer Writer}.
 * 
 * <p>
 * A program that uses character streams in place of byte streams automatically
 * adapts to the local character set and is ready for internationalization — all
 * without extra effort by the programmer.
 * 
 * <p>
 * There are two general-purpose byte-to-character "bridge" streams:
 * {@link java.io.InputStreamReader InputStreamReader} and
 * {@link java.io.OutputStreamWriter OutputStreamWriter}. Use them to create
 * character streams when there are no prepackaged character stream classes that
 * meet your
 * needs.
 * 
 * @see FileReader
 * @see FileWriter
 * 
 * @see BufferedInputStream
 * @see BufferedOutputStream 
 */
public class CopyCharacters {
    public static void main(String[] args) throws IOException {

        FileReader inputStream = null; // usa FileInputStream
        FileWriter outputStream = null; // usa FileOutputStream
        
        String uriOrigem = MyPath.IO_PATH + MyPath.XANADU;
        String uriDestino = MyPath.IO_PATH + "stream/" + CopyCharacters.class.getSimpleName().toLowerCase() + ".txt";

        try {
            inputStream = new FileReader(uriOrigem);
            outputStream = new FileWriter(uriDestino);

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
            System.out.println("\n\nCopiado com sucesso!\n\n");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
