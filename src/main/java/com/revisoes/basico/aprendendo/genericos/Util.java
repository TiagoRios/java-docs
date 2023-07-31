package com.revisoes.basico.aprendendo.genericos;

/**
 * Classe que demonstra a utilização de métodos genéricos e parâmetros de tipo
 * limitado.
 * 
 * @see Pair
 */
public class Util {

    /**
     * Método que compara se key e value de dois pares são iguais.
     * 
     * @param <K> key
     * @param <V> value
     * @param p1  elemento para comparar
     * @param p2  elemento para comparar
     * @return true se key e value forem iguais.
     */
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

    /**
     * Método que calcula quantos elementos são maiores que o elemento fornecido.
     * Método generico com parâmetro de tipo limitado.
     * 
     * @param <T>     Tipo dos dados que serão usados.
     * @param anArray Array com elementos do tipo T que serão analisados.
     * @param elem    o elemento para fazer a comparação.
     * @return A quantidade de elementos que são maiores que o elemento comparado.
     */
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int contagem = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++contagem;
        return contagem;
    }

    public static void main(String[] args) {
        Pair<Integer, String> pair1 = new Pair<>(1, "maçã");
        Pair<Integer, String> pair2 = new Pair<>(2, "pêra");

        boolean iguals = Util.<Integer, String>compare(pair1, pair2);
        System.out.println(iguals); // false

        // Aqui ocorre inferência de tipo.
        boolean iguals2 = Util.compare(pair1, pair1);
        System.out.println(iguals2); // true
    }
}