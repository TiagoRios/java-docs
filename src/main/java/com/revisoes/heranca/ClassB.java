package com.revisoes.heranca;

public class ClassB extends ClassA{
    
    // This static method cannot hide the instance method from ClassA.
    // public static void methodOne(int i){}
    
    @Override
    public void methodTwo(int i){}
    
    // This instance method cannot override the static method from ClassA.
    // public void void methodThree(int i){}
    
    public static void methodFour(int i){}
}
