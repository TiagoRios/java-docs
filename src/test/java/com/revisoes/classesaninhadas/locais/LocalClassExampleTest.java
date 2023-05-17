package com.revisoes.classesaninhadas.locais;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LocalClassExampleTest {
    
    private static int TAMANHO_VALIDO = 10;
    private static String NUMERO_TELEFONE_VALIDO = "123-456-7890";
    private static String NUMERO_TELEFONE_INVALIDO = "123-456-78";

    private static String MSG_NUMERO_VALIDO = "número válido";
    private static String MSG_NUMERO_INVALIDO = "número inválido";
    
    @Test
    public void deveRetornarMensagemNumeroValido(){
        assertEquals(MSG_NUMERO_VALIDO, LocalClassExample.validatePhoneNumber(NUMERO_TELEFONE_VALIDO, TAMANHO_VALIDO));
    }
    
    //Poderia ter lançado uma excessão e capturado no teste.
    @Test
    public void deveRetornarMensagemNumeroInvalido(){
        assertEquals(MSG_NUMERO_INVALIDO, LocalClassExample.validatePhoneNumber(NUMERO_TELEFONE_INVALIDO, TAMANHO_VALIDO));
    }
}