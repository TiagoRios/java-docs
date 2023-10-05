package com.revisoes.basico.aprendendo.genericos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que demonstra o aviso do compilador sobre poluição de heap.
 * <p>
 * Monstra também como utilizar a anotação {@link SafeVarargs @SafeVarargs} para
 * eliminar esses avisos.
 * </p>
 * 
 * <h2>Tipos não reificáveis</h2>
 * <p>
 * Tipos não reificáveis ​​são tipos em que as informações foram removidas em
 * tempo de compilação por exclusão de tipo — invocações de tipos genéricos que
 * não são definidos como curingas ilimitados. Um tipo não reificável não tem
 * todas as suas informações disponíveis em tempo de execução. Exemplos de tipos
 * não reificáveis ​​são {@code List<String>} e {@code List<Number>}; a JVM não
 * pode dizer a
 * diferença entre esses tipos em tempo de execução. Conforme mostrado em
 * Restrictions on Generics , existem certas situações em que tipos não
 * reificáveis ​​não podem ser usados: em uma instância de expressão, por
 * exemplo, ou como um elemento em um array.
 */
public class TipoNaoReificavel {

    /**
     * Classe usada somente para observar tipos <strong><i>NÃO</i></strong>
     * Reificáveis.
     */
    class ArrayBuilder {

        /**
         * If you declare a varargs method that has parameters of a parameterized type,
         * and you ensure that the body of the method does not throw a
         * {@code ClassCastException} or other similar exception due to improper
         * handling of the
         * varargs formal parameter, you can prevent the warning that the compiler
         * generates for these kinds of varargs methods by adding the following
         * annotation to static and non-constructor method declarations:
         * 
         * {@link SafeVarargs @SafeVarargs}
         * 
         * @param <T>       Tipo do elemento
         * @param listArg   A lista que será manipulada.
         * @param elementos Os elementos que serão adicionados na lista
         */
        @SafeVarargs
        public static <T> void addToList(List<T> listArg, T... elements) { // potencial poluição de heap via varArgs.
            for (T x : elements) {
                listArg.add(x);
            }
        }

        /**
         * Método falha lançando exceção {@code ClassCastException}.
         * <p>
         * Na documentação da anotação {@code @SafeVarargs} tem o mesmo exemplo.
         * <p>
         * Usar esta anotação somente quando você garantir que o corpo do método não
         * lance exceções.
         * 
         * @param listaString lista de Strings
         */
        @SafeVarargs // Não é realmente segura. o corpo do método lança exceção.
        public static void failedMethod(List<String>... listaString) { // potencial poluição de heap via varArgs.
            try {
                Object[] array = listaString; // Válido
                List<Integer> listaTemporaria = Arrays.asList(42);

                // inserindo a lista temporária no index 0 do array.
                array[0] = listaTemporaria; // Semantically invalid, but compiles without warnings
                String s = listaString[0].get(0); // ClassCastException lançada aqui
                System.out.println("index 0 = "+ s);
            } catch (Exception e) {
                System.out.println("\n\nErro capturado: FAILED_METHOD - " + e.getMessage() + "\n\n");
            }
        }

        @SafeVarargs // Não é realmente segura. o corpo do método lança exceção.
        public static void failedMethodResolvido(List<String>... listaString) { // potencial poluição de heap
            try {
                String s = listaString[0].get(0);
                System.out.println("index 0 = "+ s);
            } catch (Exception e) {
                System.out.println("\n\nErro capturado: FAILED_METHOD_RESSOLVIDO - " + e.getMessage() + "\n\n");
            }
        }
    }

    public static void main(String[] args) {
        // Cria
        List<String> stringListA = new ArrayList<String>();
        List<String> stringListB = new ArrayList<String>();
        List<List<String>> listaComListaDeString = new ArrayList<List<String>>();

        // Preenche
        ArrayBuilder.addToList(stringListA, "um", "dois");
        ArrayBuilder.addToList(stringListB, "tres", "quatro");
        ArrayBuilder.addToList(listaComListaDeString, stringListA, stringListB);

        // Método que falha
        ArrayBuilder.failedMethod(Arrays.asList("Olá!"), Arrays.asList("Mundo!"));
        ArrayBuilder.failedMethod(stringListA, stringListB);

        // Método que falha resolvido
        ArrayBuilder.failedMethodResolvido(Arrays.asList("Olá!"), Arrays.asList("Mundo!"));
        ArrayBuilder.failedMethodResolvido(stringListA, stringListB);

        RestricoesGenerics.imprimirRestricoes();
    }
}