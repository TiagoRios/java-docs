package com.revisoes.genericos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * CuringaTest
 */
public class CuringaTest {

    private static final int QUANTIDADE_ELEMENTOS = 10;

    private Curinga curinga;

    List<Number> numberList; // funciona para ambos inferior e superior.

    List<? extends Number> doubleList; // Limite superior.    
    List<? super Integer> integerList; // Limite inferior.
    List<? super Integer> integerList2; // Limite inferior definir com Arrays.asList.

    @Before
    public void setup() {
        curinga = new Curinga();

        doubleList = Arrays.asList(1d, 4d, 6d); // soma 11.
        integerList = new ArrayList<>();
        integerList2 = Arrays.asList(); // UnsupportedOperationException
    }

    /*
     * ============================================================
     * --------------- Curinga Limite SUPERIOR
     * ============================================================
     */

    @Test
    public void deveSomaElementos() {
        double d = curinga.sumOfListSuperior(doubleList).doubleValue();
        assertEquals(d, 11, 0);
    }

    /*
     * ============================================================
     * --------------- Curinga Limite INFERIOR
     * ============================================================
     */

    @Test
    public void deveAdicionarElementos() {
        curinga.addNumbersInferior(integerList, QUANTIDADE_ELEMENTOS);
        assertEquals(integerList.size(), QUANTIDADE_ELEMENTOS);
    }

    /*
     * ============================================================
     * --------------- Posso utilizar com ambos
     * ============================================================
     */

    @Test
    public void deveSomaElementosEmNumberList() {
        numberList = Arrays.asList(1, 2, 3);
        double d = curinga.sumOfListSuperior(numberList).doubleValue();
        assertEquals(d, 6, 0);
    }

    @Test
    public void deveAdicionarElementosEmNumberList() {
        numberList = new ArrayList<>();
        curinga.addNumbersInferior(numberList, QUANTIDADE_ELEMENTOS);
        assertEquals(numberList.size(), QUANTIDADE_ELEMENTOS);
    }

    /*
     * ============================================================
     * --------------- UnsupportedOperationException
     * ============================================================
     */

    /**
     * Por que acontece esse erro?
     * it's a "fixed-size list", means you cannot change the list's size.
     */
    @Test
    public void throwsUnsupportedOperationException_Arrays_asList() {
        numberList = Arrays.asList(); // UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class,
                () -> curinga.addNumbersInferior(numberList, QUANTIDADE_ELEMENTOS));
    }
}