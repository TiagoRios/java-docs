package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.internas;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import java.util.NoSuchElementException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Este exemplo demonstra a utilização de classe interna.
 * 
 * Uma classe interna é definida dentro de outra classe, diferente
 * de classes locais. que são definidas dentro de um método
 * 
 * A classe interna EvenIterator implementa a interface Iterator<T>
 * 
 * @see java.util.Iterator<T>
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
        Iterator<Integer> evenIterator = this.new EvenIterator();
        
        while (evenIterator.hasNext()) {
 
            int numeroInteiro = evenIterator.next();
 
            logger.log(Level.INFO, "{0}", numeroInteiro);
 
            lista.add(numeroInteiro);
        }
        
        return lista;
    }

    private class EvenIterator implements Iterator<Integer> {

        private int nextIndex = 0;

        public boolean hasNext() {
            return (nextIndex < size);
        }

        @Override
        public Integer next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Integer intValue = Integer.valueOf(arrayOfInts[nextIndex]);

            nextIndex += 2;

            return intValue;
        }
    }

}
