package com.revisoes.basico.classesessenciais.concorrency.thread;

/**
 * <p>
 * Classe que demonstra alguns conceitos sobre Threads.
 * </p>
 *
 * <p>
 * A classe consiste em duas threads.
 * </p>
 * 
 * <p>
 * Todo aplicativo Java possui a Thread main.
 * A Thread main cria uma nova thread a partir do objeto Runnable (MessageLoop)
 * e espera a sua finalização (thread criada).
 * </p>
 * 
 * <p>
 * Se a thread MessageLoop demorar muito para terminar, a thread main o
 * interrompe.
 * </p>
 * 
 * <p>
 * A thread MessageLoop imprime uma série de mensagens.
 * Se for interrompida antes de imprimir todas as suas mensagens, a thread
 * MessageLoop imprime uma mensagem e sai.
 * </p>
 */
public class SimpleThreads {

    // Display a message, preceded by the name of the current thread
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();

        System.out.format("%s: %s%n", threadName, message);
    }

    // Runnable que posso utilizar dentro do construtor da thread.
    private static class MessageLoop implements Runnable {
        public void run() {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };

            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    // Pause for 4 seconds
                    Thread.sleep(4000);
                    // Print a message
                    threadMessage(importantInfo[i]);
                }

            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    // Este método estático main é minha Thread princial
    public static void main(String args[]) throws InterruptedException {

        // Delay, in milliseconds before we interrupt MessageLoop thread.
        long patience = 1000 * 60 * 60; // (default one hour)

        // If command line argument present, gives patience in seconds.
        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;

            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();

        // cria a Thread passa o objeto runnable ao construtor.
        Thread minhaThread = new Thread(new MessageLoop());
        minhaThread.start();

        threadMessage("Waiting for MessageLoop thread to finish");

        // loop until MessageLoop thread exits
        while (minhaThread.isAlive()) {
            threadMessage("Still waiting...");

            // Wait maximum of 1 second for MessageLoop thread to finish.
            minhaThread.join(1000); // aguarda por 1seg a thread terminar.

            if (((System.currentTimeMillis() - startTime) > patience)
                    && minhaThread.isAlive()) {

                threadMessage("Tired of waiting!");
                minhaThread.interrupt();

                // Shouldn't be long now -- wait indefinitely
                minhaThread.join();
            }
        }

        threadMessage("Finally!");
    }
}