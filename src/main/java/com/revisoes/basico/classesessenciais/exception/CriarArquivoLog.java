package com.revisoes.basico.classesessenciais.exception;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.FileHandler;

/**
 * Exemplo do tutorial Oracle, Registrar os erros em um arquivo de registro.
 */
public class CriarArquivoLog {
    public static void main(String[] args) {
        try {
            Handler handler = new FileHandler("OutFile.log");

            Logger.getLogger("").addHandler(handler);
   
            throw new IOException(); // somente para analisar o relatório gerado.
   
        } catch (IOException e) {
            Logger logger = Logger.getLogger("package.name");

            StackTraceElement elements[] = e.getStackTrace();
   
            for (int i = 0, n = elements.length; i < n; i++) {
                logger.log(Level.WARNING, "Alguma msg sobre o erro aqui.");
            }
        }
    }
}