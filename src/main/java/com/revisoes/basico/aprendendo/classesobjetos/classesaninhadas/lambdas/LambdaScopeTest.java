package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.lambdas;

import java.util.function.Consumer;

/**
 * Like local and anonymous classes, lambda expressions can capture variables;
 * they have the same access to local variables of the enclosing scope. However,
 * unlike local and anonymous classes, lambda expressions do not have any
 * shadowing issues (see Shadowing for more information). Lambda expressions are
 * lexically scoped. This means that they do not inherit any names from a
 * supertype or introduce a new level of scoping. Declarations in a lambda
 * expression are interpreted just as they are in the enclosing environment.
 */
public class LambdaScopeTest {
    public int x = 0;

    class FirstLevel {
        public int x = 1;

        void methodInFirstLevel(int x) {
            int z = 2;

            Consumer<Integer> myConsumer = (y) -> {
                // The following statement causes the compiler to generate
                // the error "Local variable z defined in an enclosing scope
                // must be final or effectively final"
                // z = 99;

                System.out.println("x = " + x);
                System.out.println("y = " + y);
                System.out.println("z = " + z);
                System.out.println("this.x = " + this.x);
                System.out.println("LambdaScopeTest.this.x = " +
                        LambdaScopeTest.this.x);
            };

            myConsumer.accept(x);
        }
    }

    public static void main(String... args) {
        
        LambdaScopeTest st = new LambdaScopeTest();
        
        LambdaScopeTest.FirstLevel fl = st.new FirstLevel();
        
        fl.methodInFirstLevel(23);
    }
}