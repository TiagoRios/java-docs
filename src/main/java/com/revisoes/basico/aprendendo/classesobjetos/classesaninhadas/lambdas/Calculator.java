package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.lambdas;

/**
 * example of lambda expressions that take more than one formal parameter
 */
public class Calculator {

    @FunctionalInterface
    interface IntegerMath {
        int operation(int a, int b);
    }

    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }

    public static void main(String... args) {

        Calculator myApp = new Calculator();

        IntegerMath addition = (a, b) -> a + b;
        IntegerMath subtraction = (a, b) -> a - b;

        System.out.println("40 + 2 = " +
                myApp.operateBinary(40, 2, addition));

        System.out.println("20 - 10 = " +
                myApp.operateBinary(20, 10, subtraction));

        System.out.println("2 * 19 = " +
                myApp.operateBinary(2, 19, (a, b) -> a * b));

        System.out.println("20 / 5 = " +
                myApp.operateBinary(20, 5, (a, b) -> a / b));

        System.out.println("2 ** 8 = " +
                myApp.operateBinary(2, 8, (a, b) -> (int) Math.pow(a, b)));
    }
}