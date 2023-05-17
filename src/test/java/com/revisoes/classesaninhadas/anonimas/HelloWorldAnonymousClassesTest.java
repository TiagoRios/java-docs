package com.revisoes.classesaninhadas.anonimas;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HelloWorldAnonymousClassesTest {

    HelloWorldAnonymousClasses classesAnonimas;

    @Before
    public void setup(){
        classesAnonimas = new HelloWorldAnonymousClasses();
    }

    @Test
    public void deveCumprimentarEmInglesClasseNormal(){
        String[] resultado = classesAnonimas.sayHelloEnglishComClasseNormal();
        assertEquals("Hello world", resultado[0]);
        assertEquals("Hello ENGLISH", resultado[1]);
    }
    
    @Test
    public void deveCumprimentarEmFrancesComInterface(){
        String[] resultado = classesAnonimas.sayHelloFrenchComInterface();
        assertEquals("Salut tout le monde", resultado[0]);
        assertEquals("Salut FRENCH", resultado[1]);
    }
    
    @Test
    public void deveCumprimentarComClasseAbstrata(){
        String[] resultado = classesAnonimas.sayHelloClasseAbstrata();
        assertEquals("Ola mundo", resultado[0]);
        assertEquals("Ola ABSTRATA", resultado[1]);
    }
    
    @Test
    public void deveCumprimentarClasseNormalSintaxeAnonima(){
        String[] resultado = classesAnonimas.sayHelloClasseNormalSintaxeAnonima();
        assertEquals("world world algumAdjetivo", resultado[0]);
        assertEquals("world NORMAL_ANONIMA algumAdjetivo", resultado[1]);
    }
}