package com.revisoes.basico.classesessenciais.io.stream;

import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;

import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;

import java.io.IOException;

import com.revisoes.basico.classesessenciais.io.MyPath;

import java.io.EOFException;

/**
 * Os fluxos de dados suportam E/S <strong>binária</strong> de valores de tipo
 * de dados <strong>primitivos</strong> (boolean, char, byte, short, int, long,
 * float e double),
 * bem como valores de String.
 * <p>
 * Todos os fluxos de dados implementam a interface {@link java.io.DataInput
 * DataInput } ou a interface {@link java.io.DataOutput DataOutput}.
 * 
 * Para trabalhar com objetos utilize fluxo de objetos (Objects Streams), ao
 * invés de fluxo de dados (Data Streams).
 * 
 * @see DataInputStream
 * @see DataOutputStream
 * @see java.math.BigDecimal
 */
public class DataStreams {
    static final String dataFile = MyPath.IO_PATH + "stream/" + "my_file";
    // java.Math.BigDecimal é mais adequado para valores monetários.
    // Porém ele é um objeto e não vai funcionar aqui para fluxo de dados.
    static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
    static final int[] units = { 12, 8, 13, 29, 50 };
    static final String[] descs = { "Java T-shirt",
            "Java Mug",
            "Duke Juggling Dolls",
            "Java Pin",
            "Java Key Chain" };

    public static void main(String[] args) throws IOException {
        DataOutputStream out = null;

        try {
            // DataOutputStream foi construído como um wrapper para um fluxo de bytes.
            out = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(dataFile)));

            for (int i = 0; i < prices.length; i++) {
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }

        } finally {
            out.close();
        }

        DataInputStream in = null;
        double total = 0.0;

        try {
            // DataInputStream foi construído como um wrapper para um fluxo de bytes.
            in = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(dataFile)));

            double price;
            int unity;
            String description;
   
            try {
                while (true) {
                    price = in.readDouble();
                    unity = in.readInt();
                    description = in.readUTF();
                    System.out.format("You ordered %d units of %s at $%.2f%n",
                            unity, description, price);
                    total += unity * price;
                }
   
            } catch (EOFException e) {
            }
   
            System.out.format("For a TOTAL of: $%.2f%n", total);
   
        } finally {
            in.close();
        }
    }
}