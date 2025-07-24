package com.revisoes.design.adapter;

public class ClasseExistente {
  public void imprimirTexto(String texto) {
    System.out.println(texto);
  }

  public String paraMaiusculo(String texto) {
    return texto.toUpperCase();
  }

  public String paraMinusculo(String texto) {
    return texto.toLowerCase();
  }

}