package com.revisoes.basico.classesessenciais.io.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import com.revisoes.basico.classesessenciais.io.MyPath;

/**
 * Classe que demonstra E/S orientada a linha e fluxo E/S com buffer.
 * 
 * <p>
 * E/S <strong>SEM</strong> buffer significa que cada solicitação de leitura ou
 * gravação é
 * tratada <strong>diretamente</strong> pelo sistema operacional subjacente.
 * Isso pode tornar um
 * programa <strong>MUITO MENOS eficiente</strong>, pois cada uma dessas
 * solicitações geralmente
 * aciona acesso ao disco, atividade de rede ou alguma outra operação
 * relativamente cara.
 * 
 * <p>
 * Fluxos de entrada com buffer leem dados de uma área de memória conhecida como
 * buffer.
 * 
 * @see Reader
 * @see FileReader
 * @see BufferedReader
 * 
 * @see Writer
 * @see FileWriter
 * @see PrintWriter
 * @see BufferedWriter
 * 
 */
public class CopyLines {
    public static void main(String[] args) throws IOException {

        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        String uriOrigem = MyPath.IO_PATH + MyPath.XANADU;
        String uriDestino = MyPath.IO_PATH + "stream/" + CopyLines.class.getSimpleName().toLowerCase() + ".txt";

        try {
            // Encapsulando fluxo sem buffer em um fluxo com buffer.
            inputStream = new BufferedReader(new FileReader(uriOrigem));
            outputStream = new PrintWriter(new FileWriter(uriDestino));

            String l;
            // retorna uma linha de texto.
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);
            }
            System.out.println("\n\nCopiado com sucesso!\n\n");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}