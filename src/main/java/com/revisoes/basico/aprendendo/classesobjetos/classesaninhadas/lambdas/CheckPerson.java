package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.lambdas;

/**
 * Interface utilizada para verificar persons.
 * 
 * Esta é uma Interface funcional. Tais interfaces possuem apenas um método
 * abstrato.
 */
@FunctionalInterface
public interface CheckPerson {

    /**
     * Método utilizado para fazer verificações no objeto.
     * 
     * @param person Objeto que será testado.
     * @return boolean
     */
    boolean test(Person person);
}
