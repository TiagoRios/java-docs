package com.revisoes.basico.classesessenciais.concorrency.sincronizacao;

/**
 * <p>
 * Classe que demonstra como sincronizar métodos.
 * </p>
 * <p>
 * A sincronização de métodos ajuda a evitar interferência de thread e erros de
 * consistência de memória.
 * </p>
 * <p>
 * Adicione a palavra chave {@code synchronized} na declaração do método para
 * torná-lo síncrono.
 * </p>
 * <p>
 * <strong>Obs:</strong> Construtores não podem ser sincronizados.
 * </p>
 */
public class SynchronizedCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized int value() {
        return count;
    }
}