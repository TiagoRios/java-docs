package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.internas;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

// import com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.internas.DataStructure;

public class DataStructureTest {

    private static final int SIZE = 33;

    DataStructure dataStructure;
    List<Integer> listaNumerosPares;

    @Before
    public void setup() {

        dataStructure = new DataStructure(SIZE);
        listaNumerosPares = new ArrayList<Integer>();

        for (int i = 0; i < SIZE; i++) {
            if ((i % 2) == 0)
                listaNumerosPares.add(i);
        }
    }

    @Test
    public void listasDevemSerIguais() {
        assertEquals(listaNumerosPares, dataStructure.printEven());
    }
}