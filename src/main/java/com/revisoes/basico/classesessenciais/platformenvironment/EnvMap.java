package com.revisoes.basico.classesessenciais.platformenvironment;

import java.util.Map;

/**
 * Exemplo que demonstra como listar todas as variáveis de ambiente.
 */
public class EnvMap {
    public static void main (String[] args) {
        Map<String, String> env = System.getenv();

        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n", envName, env.get(envName));
        }
    }
}