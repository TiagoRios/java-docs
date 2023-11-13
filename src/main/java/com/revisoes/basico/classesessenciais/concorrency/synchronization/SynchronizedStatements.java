package com.revisoes.basico.classesessenciais.concorrency.synchronization;

import java.util.ArrayList;
import java.util.List;

/**
 * Exemplo do site GeeksforGeeks.
 */
public class SynchronizedStatements {
    public static void main(String[] args) {

        Geek gk = new Geek();

        List<String> list = new ArrayList<>();

        gk.geekName("mohit", list);

        System.out.println(gk.name);
    }
}