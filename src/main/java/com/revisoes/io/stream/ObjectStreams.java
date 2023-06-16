package com.revisoes.io.stream;

import java.io.*;
import java.math.BigDecimal;
import java.util.Calendar;

import com.revisoes.io.Path;

/**
 * Os fluxos de objetos suportam E/S de objetos ou uma mistrura de tipos
 * primitivos e de objetos.
 * <p>
 * Este exemplo recria o mesmo exemplo que {@link DataStreams DataStreams} com
 * algumas alterações:
 * <ul>
 * <li>Preços agora são melhor representandos com {@link java.math.BigDecimal
 * BigDecimal}.</li>
 * <li>Um objeto {@link java.util.Calendar Calendar} é gravado no arquivo de
 * dados indicando a data e hora da fatura.</li>
 * <li>E claro, usa classes específicas para trabalhar com fluxo de
 * objetos.</li>
 * </ul>
 * 
 * @see ObjectInput
 * @see ObjectOutput
 * 
 * @see ObjectInputStream
 * @see ObjectOutputStream
 * 
 * @see java.math.BigDecimal
 */
public class ObjectStreams {
    static final String dataFile = Path.IO_PATH + "stream/" + "objectstreams";

    // Primeira modificação.
    static final BigDecimal[] prices = {
            new BigDecimal("19.99"),
            new BigDecimal("9.99"),
            new BigDecimal("15.99"),
            new BigDecimal("3.99"),
            new BigDecimal("4.99") };
    static final int[] unities = { 12, 8, 13, 29, 50 };
    static final String[] descriptions = { "Java T-shirt",
            "Java Mug",
            "Duke Juggling Dolls",
            "Java Pin",
            "Java Key Chain" };

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(dataFile)));

            // Segunda modificação.
            out.writeObject(Calendar.getInstance());
            for (int i = 0; i < prices.length; i++) {
                out.writeObject(prices[i]); // write object
                out.writeInt(unities[i]); // and, write primitive too.
                out.writeUTF(descriptions[i]);
            }
        } finally {
            out.close();
        }

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(dataFile)));

            Calendar date = null;
            BigDecimal price;
            int unity;
            String description;
            BigDecimal total = new BigDecimal(0);

            // Ainda na Segunda modificação.
            date = (Calendar) in.readObject();

            System.out.format("%nOn %tA, %<tB %<te, %<tY:%n%n", date);

            try {
                while (true) { // Serio isso ? kkks
                    price = (BigDecimal) in.readObject();
                    unity = in.readInt();
                    description = in.readUTF();
                    System.out.format("You ordered %d units of %s at $%.2f%n",
                            unity, description, price);
                    total = total.add(price.multiply(new BigDecimal(unity)));
                }
            } catch (EOFException e) {
            }
            System.out.format("%nFor a TOTAL of: $%.2f%n%n", total);
        } finally {
            in.close();
        }
    }
}