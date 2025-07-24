package com.revisoes.design.adapter.classadapter;

import com.revisoes.design.adapter.ClasseExistente;

public class ClasseExistenteAdapter extends ClasseExistente implements AlvoInterface {

  @Override
  public void operacao() {
    String texto = paraMinusculo("Operação Realizada com: " + this.getClass().getSimpleName());
    imprimirTexto(texto);
  }
}
