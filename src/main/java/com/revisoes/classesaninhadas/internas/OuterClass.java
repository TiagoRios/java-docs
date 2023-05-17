package com.revisoes.classesaninhadas.internas;

import java.util.logging.Logger;

// Classe muito simples não vou fazer testes separados.
public class OuterClass {

    Logger logger = Logger.getLogger(OuterClass.class.getName());

    String externa = "campo externo";
    static String externaEstatico = "campo externo estatico";

    class Interna {
        public void acessarMembros(){
            logger.info(externa);
            logger.info(externaEstatico);
        }
    }
    
    static class InternaEstatica {
        public void acessarMembros(OuterClass outerClass){
            Logger.getLogger(OuterClass.class.getName()).info(outerClass.externa);
            Logger.getLogger(OuterClass.class.getName()).info(externaEstatico);
        }
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();

        Logger.getLogger(OuterClass.class.getName()).info("-------------inner class-------------");
        OuterClass.Interna interna = outer.new Interna();
        interna.acessarMembros();
        
        Logger.getLogger(OuterClass.class.getName()).info("-------------outer class-------------");
        InternaEstatica internaEstatica = new InternaEstatica();
        internaEstatica.acessarMembros(outer);
    }
}
