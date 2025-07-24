package com.revisoes.design.adapter.objectadapter;

import com.revisoes.design.adapter.ClasseExistente;

public class ClasseExistenteObjAdpater extends AlvoAbstract {

  ClasseExistente classeExistente = new ClasseExistente();

  @Override
  public void operacao() {
    String texto = classeExistente.paraMaiusculo("Operação Realizada com: " + this.getClass().getSimpleName());
    classeExistente.imprimirTexto(texto);
  }
}
