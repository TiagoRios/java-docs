package com.revisoes.basico.classesessenciais.io.stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.revisoes.basico.classesessenciais.io.MyPath;

/**
 * Demonstra como copiar um arquivos usando fluxo de bytes.
 * <p>
 * Faz a copia do arquivo um byte por vez.
 * 
 * Todas as classes de fluxo de bytes são descendentes de
 * {@link java.io.InputStream InputStream} e {@link java.io.OutputStream
 * OutputStream }
 */
public class CopyBytes {

    private static final String COMPLEMENTO_PATH = "stream/";
    
    private static final String ORIGEM_ARQUIVO = MyPath.IO_PATH + MyPath.XANADU;
    private static final String DESTINO_ARQUIVO = MyPath.IO_PATH + COMPLEMENTO_PATH
            + CopyBytes.class.getSimpleName().toLowerCase() + ".txt";

    // mais fácil para testar dessa maneira.
    public static void copiarArquivo(String arquivoOrigem, String arquivioDestino) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(arquivoOrigem);
            out = new FileOutputStream(arquivioDestino);
            int c;
            System.out.println("");
            while ((c = in.read()) != -1) {
                out.write(c);
                System.out.print((char) c); // Cast int in char.
            }
            System.out.println("\n");
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        copiarArquivo(ORIGEM_ARQUIVO, DESTINO_ARQUIVO);
    }
}