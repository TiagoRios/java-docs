package com.revisoes.basico.aprendendo.genericos;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe NÃO generica que demonstra inferência de tipo em:
 * 1 - Métodos genericos;
 * 2 - Instanciação de classe genérica;
 * 
 */
public class BoxDemo {

    /**
     * Método que adiciona Box do tipo <E> dentro da lista.
     * 
     * @param <E>   Tipo do elemento
     * @param e     O elemento a ser adicionado em Box a qual será adicionado na
     *              lista.
     * @param boxes A lista que será manipulada.
     */
    public static <E extends Number> void addBox(E e, List<Box<E>> boxes) {
        Box<E> box = new Box<>(); // Inferência de tipo na instanciação.
        box.set(e);
        boxes.add(box);
    }

    /**
     * Método que imprime no console todos os itens da lista com uma formatação
     * personalizada.
     * 
     * @param <E> Tipo do elemento. 
     * @param boxes A lista que será percorrida.
     */
    public static <E extends Number> void outputBoxes(List<Box<E>> boxes) {
        int counter = 0;
        for (Box<E> box : boxes) {
            E boxContents = box.get();
            System.out.println("Box #" + counter + " contains [" +
                    boxContents.toString() + "]");
            counter++;
        }
    }

    public static void main(String[] args) {
        // Inferência na instanciação.
        ArrayList<Box<Integer>> listOfIntegerBoxes = new ArrayList<>();

        // Sem inferência.
        BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);

        // Inferência de tipo em métodos genéricos.
        BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes);
        BoxDemo.addBox(Integer.valueOf(30), listOfIntegerBoxes);

        BoxDemo.outputBoxes(listOfIntegerBoxes);
    }
}