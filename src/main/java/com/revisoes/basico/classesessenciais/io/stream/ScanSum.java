package com.revisoes.basico.classesessenciais.io.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import com.revisoes.basico.classesessenciais.io.MyPath;

public class ScanSum {
    public static void main(String[] args) throws IOException {

        Scanner scanner = null;
        double sum = 0;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(MyPath.IO_PATH + "usnumbers.txt")));
            scanner.useLocale(Locale.US);

            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    sum += scanner.nextDouble();
                } else {
                    scanner.next();
                }
            }
        } finally {
            scanner.close();
        }

        System.out.println("\nSoma igual a: " + sum + "\n");
    }
} 