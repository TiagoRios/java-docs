package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.internas;

import java.util.logging.Logger;

/**
 * Classe que demonstra a utilização de classes internas.
 * 
 * <p>
 * Classes internas são classes definidas no corpo de uma classe envolvente.
 * </p>
 */
public class OuterClass {

    Logger logger = Logger.getLogger(OuterClass.class.getName());

    String externa = "campo externo";
    static String externaEstatico = "campo externo estatico";

    /**
     * Classe interna (NORMAL)
     */
    class Interna {

        /**
         * Acessar as propriedades da classe OuterClass e os imprime com um logger.
         * 
         * <p>
         * Acessa as propriedades diretamente.
         * </p>
         */
        public void acessarMembros() {
            logger.info(externa);
            logger.info(externaEstatico);
        }
    }

    /**
     * Classe interna (ESTÁTICA)
     */
    static class InternaEstatica {

        /**
         * Acessar as propriedades da classe OuterClass e os imprime com um logger.
         * 
         * <p>
         * Precisa de um objeto da classe externa para acessar as propriedades.
         * </p>
         * 
         * @param outerClass uma instância da classe externa.
         */
        public void acessarMembros(OuterClass outerClass) {
            Logger.getLogger(OuterClass.class.getName()).info(outerClass.externa);
            Logger.getLogger(OuterClass.class.getName()).info(externaEstatico);
        }
    }

    public static void main(String[] args) {

        OuterClass outer = new OuterClass(); // Para utilizada

        Logger.getLogger(OuterClass.class.getName()).info("-------------inner class-------------");

        // Sintaxe estranha para criar classe interna.
        OuterClass.Interna interna = outer.new Interna();
        interna.acessarMembros();

        Logger.getLogger(OuterClass.class.getName()).info("-------------outer class-------------");

        // Criar classe interna estática é igual instanciar normalmente.
        InternaEstatica internaEstatica = new InternaEstatica();
        internaEstatica.acessarMembros(outer);
    }
}
