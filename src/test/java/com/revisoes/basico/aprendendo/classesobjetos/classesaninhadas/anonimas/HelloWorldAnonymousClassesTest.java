package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.anonimas;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

// import com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.anonimas.HelloWorldAnonymousClasses;

public class HelloWorldAnonymousClassesTest {

    String[] resultado = null;
    HelloWorldAnonymousClasses classesAnonimas;

    @Before
    public void setup() {
        classesAnonimas = new HelloWorldAnonymousClasses();
    }

    @Test
    public void deveCumprimentarEmInglesClasseNormal() {

        resultado = classesAnonimas.sayHelloEnglishComClasseNormal();

        assertEquals("Hello world", resultado[0]);
        assertEquals("Hello ENGLISH", resultado[1]);
    }

    @Test
    public void deveCumprimentarEmFrancesComInterface() {

        resultado = classesAnonimas.sayHelloFrenchComInterface();

        assertEquals("Salut tout le monde", resultado[0]);
        assertEquals("Salut FRENCH", resultado[1]);
    }

    @Test
    public void deveCumprimentarComClasseAbstrata() {

        resultado = classesAnonimas.sayHelloClasseAbstrata();

        assertEquals("Ola mundo", resultado[0]);
        assertEquals("Ola ABSTRATA", resultado[1]);
    }

    @Test
    public void deveCumprimentarClasseNormalSintaxeAnonima() {

        resultado = classesAnonimas.sayHelloClasseNormalSintaxeAnonima();

        assertEquals("Hello world algumAdjetivo", resultado[0]);
        assertEquals("Hello NORMAL_ANONIMA algumAdjetivo", resultado[1]);
    }
}