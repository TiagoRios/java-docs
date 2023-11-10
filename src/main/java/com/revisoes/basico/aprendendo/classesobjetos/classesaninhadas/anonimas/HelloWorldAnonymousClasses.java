package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.anonimas;

/**
 * Classe que demonstra a utilização de classes anônimas.
 */
public class HelloWorldAnonymousClasses {

    /* ================== Interface ===================== */

    /**
     * <p>
     * Interface para demonstrar a criação de classes anînimas.
     * </p>
     * 
     * <p>
     * Classes anònimas. Não é preciso instânciar, porém devo implementar a
     * interface ou a classe abstrata.
     * </p>
     * 
     * <p>
     * Novos métodos NÃO podem ser utilizados fora da criação das classes anônimas!
     * </p>
     * 
     * <p>
     * Elas são expressão, então, no final é necessário "ponto e virgula".
     * </p>
     */
    interface HelloWorld {
        /**
         * Faz uma saudação geral.
         * 
         * @return String com a saudação.
         */
        public String greet();

        /**
         * Faz uma saudação a alguem específico.
         * 
         * @param someone
         * @return String com a saudação.
         */
        public String greetSomeone(String someone);
    }

    /* ================= Classe Normal ================== */

    /**
     * Classe normal que implementa uma interface.
     */
    class EnglishGreeting implements HelloWorld {
        String name = "world";

        public String greet() {
            return greetSomeone(name);
        }

        public String greetSomeone(String someone) {
            name = someone;
            return "Hello " + name;
        }
    }

    /* ================= Classe abstrata ================ */

    /**
     * Classe abstrata para poder criar uma classe anônima.
     */
    abstract class SaudacaoClasseAbstrata {

        String name = "mundo"; // Sem propriedade, Lint pede para transformar em uma interface.

        String greet() {
            return greetSomeone(name);
        }

        public abstract String greetSomeone(String someone);
    }

    /*
     * ==================================================
     * ----- Métodos com responsabilidades únicas
     * ==================================================
     */

    /**
     * Este método não utiliza o mecanismo de classes anônimas.
     * 
     * @return Array<String> Array com as duas saudações.
     */
    public String[] sayHelloEnglishComClasseNormal() {

        EnglishGreeting englishGreeting = new EnglishGreeting();

        return new String[] {
                englishGreeting.greet(), // Hello world
                englishGreeting.greetSomeone("ENGLISH") // Hello ENGLISH
        };
    }

    /* ========= Classe anônima com interface =========== */

    public String[] sayHelloFrenchComInterface() {

        // Criando classe anônima com a interface.
        HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde";

            public String greet() {
                return greetSomeone("tout le monde");
            }

            public String greetSomeone(String someone) {
                name = someone;
                return ("Salut " + name);
            }
        };

        return new String[] {
                frenchGreeting.greet(),
                frenchGreeting.greetSomeone("FRENCH")
        };
    }

    /* ======== Classe anônima com classe abstrata ====== */

    public String[] sayHelloClasseAbstrata() {

        SaudacaoClasseAbstrata saudacaoClasseAbstrata = new SaudacaoClasseAbstrata() {

            @Override
            public String greet() {
                return greetSomeone("mundo");
            }

            @Override
            public String greetSomeone(String alguem) {
                name = alguem;
                return "Ola " + name;
            }
        };

        return new String[] {
                saudacaoClasseAbstrata.greet(), // Ola mundo adjetivo
                saudacaoClasseAbstrata.greetSomeone("ABSTRATA") // Ola ABSTRATA
        };
    }

    /* ======== Sintaxe com Classe anônima usando classe Normal ======== */

    public String[] sayHelloClasseNormalSintaxeAnonima() {

        // Utiliza a classe normal para criar classe anônima.
        // Mesma coisa que instânciar normalmente. não faz sentido fazer isso.
        EnglishGreeting englishGreetingSintaxeAnonima = new EnglishGreeting() {

            // Pode ser estática mas tem que ser "final". regra vista em classes locais
            static final String ADJETIVO = " algumAdjetivo";

            // Esté método só pode ser usado dentro desse bloco de código.
            public String adjetivo() {
                return ADJETIVO;
            }

            @Override
            public String greetSomeone(String someone) {
                return ("Hello " + someone + adjetivo());
            }
        };

        return new String[] {
                englishGreetingSintaxeAnonima.greet(), // Hello world algumAdjetivo
                englishGreetingSintaxeAnonima.greetSomeone("NORMAL_ANONIMA"), // Hello NORMAL_ANONIMA algumAdjetivo
        };
    }
}
