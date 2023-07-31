package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.internas;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta classe utiliza uma classe interna chamanda EvenIterator.class 
 */
public class DataStructure {

    private int size;
    private int[] arrayOfInts; // usado na classe EvenIterator
    Logger logger = Logger.getLogger(DataStructure.class.getName());

    public DataStructure(int size) {
        this.size = size;
        arrayOfInts = new int[size];
        for (int i = 0; i < size; i++) {
            arrayOfInts[i] = i;
        }
    }

    public List<Integer> printEven() {

        List<Integer> lista = new ArrayList<>();
    
        java.util.Iterator<Integer> evenIterator = this.new EvenIterator();

        while (evenIterator.hasNext()) {
            int numeroInteiro = evenIterator.next();
            logger.log(Level.INFO, "{0}", numeroInteiro);
            lista.add(numeroInteiro);
        }

        return lista;
    }

    /**
     * Classe interna que implementa classe iterator<Integer>
     */
    private class EvenIterator implements java.util.Iterator<Integer> {
        private int nextIndex = 0;

        public boolean hasNext() {
            return (nextIndex < size);
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            nextIndex += 2;
            return retValue;
        }
    }

}