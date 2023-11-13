package com.revisoes.basico.classesessenciais.concorrency.sincronizacao;

/**
 * Exemplo do site GeeksforGeeks.
 */
class Train extends Thread {
    // Reference variable of type Line.
    Line line;

    Train(Line line) {
        this.line = line;
    }

    @Override
    public void run() {
        line.getLine();
    }
}
