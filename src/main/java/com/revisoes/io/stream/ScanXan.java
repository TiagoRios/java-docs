package com.revisoes.io.stream;

import java.io.*;
import java.util.Scanner;

import com.revisoes.io.Path;

public class ScanXan {
    public static void main(String[] args) throws IOException {

        String valorTemporario;
        Scanner scanner = null;
        PrintWriter printWriter = null;
        
        String uriOrigem = Path.IO_PATH + Path.XANADU;
        String uriDestino = Path.IO_PATH + "stream/" + ScanXan.class.getSimpleName().toLowerCase() + ".txt";

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(uriOrigem)));
            printWriter = new PrintWriter(new FileWriter(uriDestino));

            System.out.println("\n"); // separar saida no console.

            while (scanner.hasNext()) {
                valorTemporario = scanner.nextLine();
                printWriter.println(valorTemporario);
                System.out.println(valorTemporario); // como é gravado no arquivo de texto
            }

            System.out.println("\n"); // separar saida no console.

        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}