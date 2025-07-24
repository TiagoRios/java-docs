package com.revisoes.design.adapter;

import com.revisoes.design.adapter.classadapter.ClasseExistenteAdapter;
import com.revisoes.design.adapter.classadapter.AlvoInterface;
import com.revisoes.design.adapter.objectadapter.AlvoAbstract;
import com.revisoes.design.adapter.objectadapter.ClasseExistenteObjAdpater;

// Testa os padrões class adapter e object adapter.
// poderia criar testes mais é um saco.
public class Cliente {
  AlvoInterface alvoInterface;
  AlvoAbstract alvosAbstract;

  public Cliente() {
    alvoInterface = new ClasseExistenteAdapter();
    alvosAbstract = new ClasseExistenteObjAdpater();
  }

  public void executaAlvos() {
    alvoInterface.operacao();
    alvosAbstract.operacao();
  }

  public static void main(String[] args) {
    Cliente c = new Cliente();
    c.executaAlvos();
  }
}