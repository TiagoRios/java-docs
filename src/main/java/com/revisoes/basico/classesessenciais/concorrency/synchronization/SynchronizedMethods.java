package com.revisoes.basico.classesessenciais.concorrency.synchronization;

/**
 * Exemplo do site GeeksforGeeks.
 */
class SynchronizedMethods {
    public static void main(String[] args) {
        Line obj = new Line();

        // we are creating two threads which share same Object.
        Train train1 = new Train(obj);
        Train train2 = new Train(obj);

        // both threads start executing .
        train1.start();
        train2.start();
    }
}