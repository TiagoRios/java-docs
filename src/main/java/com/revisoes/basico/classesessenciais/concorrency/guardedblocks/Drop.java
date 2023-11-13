package com.revisoes.basico.classesessenciais.concorrency.guardedblocks;

/**
 * Class that demonstrates guarded blocks.
 * <p>
 * This Class shares data between a producer and a consumidor.
 * The data is a series of text messages.
 * </p>
 */
public class Drop {

    private String message; // to consumer.

    // True if consumer should wait for producer to send message,
    // false if producer should wait for consumer to retrieve message.
    private boolean empty = true;

    /**
     * Recupera/consome a mensagem que foi produzida.
     * 
     * @return a mensagem produzida.
     */
    public synchronized String take() {

        while (empty) { // Wait until message is available.
            try {
                wait();

            } catch (InterruptedException e) {
            }
        }

        empty = true; // Toggle status.

        notifyAll(); // Notify producer that status has changed.

        return message;
    }

    /**
     * Criar/definir a mensagem para ser consumida.
     * 
     * @param message A mensagem que será consumida.
     */
    public synchronized void put(String message) {

        while (!empty) { // Wait until message has been retrieved.
            try {
                wait();

            } catch (InterruptedException e) {
            }
        }

        empty = false; // Toggle status.

        this.message = message; // Store message.

        notifyAll(); // Notify consumer that status has changed.
    }
}
