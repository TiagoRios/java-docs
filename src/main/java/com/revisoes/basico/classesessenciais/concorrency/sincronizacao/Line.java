package com.revisoes.basico.classesessenciais.concorrency.sincronizacao;

/**
 * <p>
 * Exemplo do site GeeksforGeeks.
 * </p>
 * <p>
 * Example that shows multiple threads can execute the same method but in
 * synchronized way.
 * </p>
 */
class Line {

    /**
     * If multiple threads(trains) trying to access this synchronized method on the
     * same Object but only one thread will be able to execute it at a time.
     */
    public void getLine() {

        for (int i = 0; i < 5; i++) {
            System.out.println(i);

            try {
                Thread.sleep(400);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
