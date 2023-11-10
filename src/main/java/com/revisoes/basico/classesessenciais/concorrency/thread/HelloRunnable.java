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
 * <strong>First way:</strong> Provide a <i>Runnable</i> Object.
 * </p>
 * 
 * <p>
 * The Runnable interface defines a single method, run, meant to contain the
 * code executed in the thread.
 * </p>
 */
public class HelloRunnable implements Runnable {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new Thread(new HelloRunnable())).start(); // Iniciando a Thread.
    }

}
