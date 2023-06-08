package com.revisoes.genericos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Classe que demonstra a utilização de curigas. O Curinga é representado pelo
 * sinal de interrogação "?".
 * 
 * <h3>Curingas:</h3
 * <ul>
 * <li>Ilimitado: {@code <?> }. Lista de tipo desconhecido.
 * <blockquote>
 * 
 * <pre>
 *     List{@code<?>}
 * </pre>
 * 
 * </blockquote>
 * 
 * <li>Com limite superior: {@code <? extends SuperClasse> }
 * <blockquote>
 * 
 * <pre>
 *     List{@code<? extends Number>}
 * </pre>
 * 
 * </blockquote>
 * 
 * <li>Com limite inferior: {@code <? super SubClasse> }
 * <blockquote>
 * 
 * <pre>
 *     List{@code<? super Integer>}
 * </pre>
 * 
 * </blockquote>
 * </ul>
 * 
 * Nota: não pode ser definido ao mesmo tempo um limite inferior e superior.
 */
public class Curinga {

    /**
     * Método que imprime os elementos da lista de qualquer tipo.
     * <p>
     * Utiliza Curinga Ilimitado
     * <p>
     * Aceita list com qualquer tipo.
     * 
     * @param list Lista que será impressa.
     */
    public void printListIlimitado(List<?> list) {
        list.forEach(System.out::println); // Referência de método. Lambda.
    }

    /**
     * Método que soma os elementos da lista.
     * <p>
     * Utiliza Curinga com limite superior.
     * <p>
     * Aceita subclasses que {@code extends Number} ou o próprio {@code Number}
     * 
     * @param list com elementos que serão somados.
     * @return A soma de todos os elementos da lista.
     */
    public Double sumOfListSuperior(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        // maior liberdade na formatação.
        System.out.format(Locale.ENGLISH, "sum = %.2f %n", sum);
        return sum;
    }

    /**
     * Método que adiciona dez números na lista.
     * <p>
     * Utiliza Curinga com limite inferior.
     * <p>
     * Aceita superClasses do tipo passado ou o próprio tipo.
     * 
     * @param list            A lista para ser preenchida.
     * @param numeroElementos quantidade números para inserir na lista.
     */
    public String addNumbersInferior(List<? super Integer> list, int numeroElementos) {
        for (int i = 1; i <= numeroElementos; i++) {
            list.add(i);
        }
        return numeroElementos + " Inserções";
    }

    public static void main(String[] args) {
        Curinga curinga = new Curinga();

        List<Number> numberList = new ArrayList<>(); // funciona para ambos
        List<? super Integer> integerList = new ArrayList<>();
        List<? extends Number> doubleList = Arrays.asList(1d, 4d, 6d);

        // Arrays.asList() Cria uma lista de tamanho fixo, significa que não pode
        // modificar o seu tamanho.
        // posso fazer uma copia dela com new ArrayList<>(Arrays.asList(...))

        List<String> stringList = Arrays.asList("um", "dois", "tres");

        // Coringa Ilimitado
        curinga.printListIlimitado(doubleList);
        curinga.printListIlimitado(stringList);

        // Coringa Limite superior
        curinga.sumOfListSuperior(doubleList);
        // sumOfList(stringList); // String não e subclasse de Number

        // Coringa Limite inferior
        // addNumbersLimiteInferior(integerList);
        curinga.addNumbersInferior(integerList, 10);
        curinga.addNumbersInferior(numberList, 10);

        // addNumbersLimiteInferior(doubleList); // não aceita Lista do tipo Double
        curinga.printListIlimitado(integerList);
    }
}
