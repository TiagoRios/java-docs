package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.lambdas;

/**
 * Interface utilizada para verifar persons.
 * 
 * Esta é uma Interface funcional. Tais interfaces possuem apenas um método
 * abstrato.
 */
public interface CheckPerson {
    /**
     * Método utilizado para fazer verificações no objeto.
     * 
     * @param person Objeto que será testado.
     * @return boleano.
     */
    boolean test(Person person);
}
