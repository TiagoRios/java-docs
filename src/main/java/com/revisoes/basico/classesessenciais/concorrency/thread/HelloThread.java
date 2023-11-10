package com.revisoes.basico.classesessenciais.concorrency.thread;

/**
 * Classe que demonstra como definir e iniciar uma thread.
 * 
 * <p>
 * An application that creates an instance of Thread must provide the code that
 * will run in that thread. There are two ways to do this:
 * </p>
 * 
 * <p>
 * <strong>Second way:</strong> Provide a Subclass <i>Thread</i>.
 * </p>
 * 
 * <p>
 * The Thread class itself implements Runnable, though its run method does
 * nothing. An application can subclass Thread, providing its own implementation
 * of run.
 * </p>
 */
public class HelloThread extends Thread {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new HelloThread()).start(); // Iniciando a Thread.
    }

}