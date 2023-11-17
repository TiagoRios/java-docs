package com.revisoes.basico.classesessenciais.platformenvironment;

/**
 * Exemplo que demonstra como listar uma variável específica.
 * <p>
 * Passe o nome da variável como argumento na linha de comando:
 * 
 * <pre>
 * $ java NomeArquivo.java nome_argumento.
 * </pre>
 * 
 * Windows ignora maiúsculas e minúsculas, enquando sistemas UNIX não.
 */
public class Env {
    public static void main(String[] args) {

        for (String env : args) {
            String value = System.getenv(env);

            if (value != null) {
                System.out.format("%s=%s%n%n",
                        env, value);
            } else {
                System.out.format("%s is"
                        + " not assigned.%n%n", env);
            }
        }
    }
}