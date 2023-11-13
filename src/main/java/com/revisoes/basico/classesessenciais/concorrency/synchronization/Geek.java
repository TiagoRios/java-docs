package com.revisoes.basico.classesessenciais.concorrency.synchronization;

import java.util.List;

/**
 * <p>
 * Exemplo do site GeeksforGeeks.
 * </p>
 * <p>
 * Classe demonstra a utilização de instruções/blocos sincronizados.
 * </p>
 */
public class Geek {

    String name = "";
    public int count = 0;

    /**
     * Define a propriedade name e adiciona o nome na lista fornecida.
     * 
     * @param geek nome do geek (nome próprio).
     * @param list lista para adicionar o nome do geek
     */
    public void geekName(String geek, List<String> list) {
        // Only one thread is permitted to change geek's name at a time.
        synchronized (this) {
            name = geek;
            count++; // how many threads change geek's name.
        }

        // All other threads are permitted to add geek name into list.
        list.add(geek);
    }
}