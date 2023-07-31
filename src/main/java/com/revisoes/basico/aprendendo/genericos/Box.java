package com.revisoes.basico.aprendendo.genericos;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Classe genérica que demonstra a utilização de parâmetros de limites únicos e
 * limites multiplos, construtor genérica, métodos genéricos, inferência na
 * instanciação e na invocação de métodos.
 * 
 * <p>{@code NomeClasse<T [ extends outraClasse e/ou interface's']> }
 */
public class Box<T extends Number> {

    private T t;
    private String toStringDoObjeto;

    Box() {
    }

    // Nessa implementação não importa o tipo, ele será transformado em string.
    <U> Box(U u) {
        this.toStringDoObjeto = u.toString();
    }

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public String getToStringDoObjeto() {
        return toStringDoObjeto;
    }

    // Aplicando parâmetros de tipo limitado. Limites multiplos.
    public <U extends Number & Serializable> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {

        // Inferência na instanciação.
        Box<Short> shortBox = new Box<>();
        shortBox.set(Short.valueOf("10")); // java.lang.Short

        // Sem inferência.
        shortBox.<Double>inspect(99.22); // java.lang.Double

        // Inferência em método genérico.
        shortBox.inspect(99); // java.lang.Integer

        // error: método está limitado: <U extends Number & Serializable>
        // integerBox.inspect("some text");

        /* Chamando construtor genèrico */
        Box<Byte> byteBox = new Box<>(Calendar.getInstance().getTime());
        System.out.println(byteBox.getToStringDoObjeto()); // Data e hora em String.
        byteBox.set(Byte.valueOf("-128")); // Faixa entre -128 e 127
        byteBox.inspect(3D); // Double

    }
}