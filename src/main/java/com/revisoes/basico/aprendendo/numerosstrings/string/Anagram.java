package com.revisoes.basico.aprendendo.numerosstrings.string;

/**
 * Programa que analisa e verifica se textos são anagramas.
 * 
 * <p>
 * Esta classe demonstra a utilização de strings.
 * </p>
 * 
 * <p>
 * Este exemplo pode ser encontrado nos tutorias site da oracle. 
 * </p>
 */
public class Anagram {

    /**
     * Verifica se dos conjuntos de texto são anagramas.
     * 
     * <p>
     * Anagramas são palavras diferentes formadas com mesmas letras em ordens
     * diferentes
     * </p>
     * 
     * @param string1
     * @param string2
     * @return
     */
    public static boolean areAnagrams(String string1,
            String string2) {

        String workingCopy1 = removeJunk(string1);
        String workingCopy2 = removeJunk(string2);

        workingCopy1 = workingCopy1.toLowerCase();
        workingCopy2 = workingCopy2.toLowerCase();

        workingCopy1 = sort(workingCopy1);
        workingCopy2 = sort(workingCopy2);

        return workingCopy1.equals(workingCopy2);
    }

    /**
     * Método que remove a pontuação de uma string.
     * 
     * @param string a string para ser analisada
     * @return a string sem pontuação
     */
    protected static String removeJunk(String string) {

        int i, length = string.length();

        StringBuilder withoutPunctuation = new StringBuilder(length);

        char c;

        for (i = (length - 1); i >= 0; i--) {

            c = string.charAt(i);

            if (Character.isLetter(c)) {
                withoutPunctuation.append(c);
            }
        }

        return withoutPunctuation.toString();
    }

    /**
     * Método para ordenar uma string.
     * 
     * @param string o texto para ser ordenado
     * @return uma string ordenada.
     */
    protected static String sort(String string) {

        char[] charArray = string.toCharArray();

        java.util.Arrays.sort(charArray);

        return new String(charArray);
    }

    public static void main(String[] args) {
        String string1 = "Cosmo and Laine:";
        String string2 = "Maid, clean soon!";

        System.out.println();
        System.out.println("Testing whether the following "
                + "strings are anagrams:");

        System.out.println("    String 1: " + string1);
        System.out.println("    String 2: " + string2);
        System.out.println();

        if (areAnagrams(string1, string2)) {
            System.out.println("They ARE anagrams!");

        } else {
            System.out.println("They are NOT anagrams!");
        }

        System.out.println();
    }
}