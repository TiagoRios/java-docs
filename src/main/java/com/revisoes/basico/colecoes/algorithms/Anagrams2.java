package com.revisoes.basico.colecoes.algorithms;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.*;
import java.io.*;

/**
 * Rode com: java Anagramas.java dictionary.txt 8
 * Programa verifica quais palavras do dictionary são anagramas
 * 
 */

public class Anagrams2 {
  public static void main(String[] args) {
    int minGroupSize = Integer.parseInt(args[1]);

    // Read words from file and put into simulated multimap
    Map<String, List<String>> mapa = new HashMap<String, List<String>>();
    try {
      Scanner arquivoEntrada = new Scanner(new File(args[0]));

      while (arquivoEntrada.hasNext()) {
        String word = arquivoEntrada.next();
        String alpha = alphabetize(word);
        List<String> listaPalavrasAnagramas = mapa.get(alpha);

        if (listaPalavrasAnagramas == null)
          mapa.put(alpha, listaPalavrasAnagramas = new ArrayList<String>());
        listaPalavrasAnagramas.add(word);
      }

      arquivoEntrada.close();

    } catch (IOException e) {
      System.err.println(e);
      System.exit(1);
    }

    // Make a List of all permutation groups above size threshold
    List<List<String>> winners = new ArrayList<List<String>>();

    for (List<String> listOfAllPermutationGroups : mapa.values()) {
      if (listOfAllPermutationGroups.size() >= minGroupSize) {
        winners.add(listOfAllPermutationGroups);
      }
    }

    // Sort permutation groups according to size
    Collections.sort(winners, new Comparator<List<String>>() {
      public int compare(List<String> o1, List<String> o2) {
        return o2.size() - o1.size();
      }
    });

    // Print permutation groups
    for (List<String> l : winners) {
      System.out.println(l.size() + ": " + l);
    }
  }

  private static String alphabetize(String s) {
    char[] a = s.toCharArray();
    Arrays.sort(a);
    return new String(a);
  }
}