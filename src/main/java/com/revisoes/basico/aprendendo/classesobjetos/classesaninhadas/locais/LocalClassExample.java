package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.locais;

/**
 * Esta classe demonstra a utilização de classe local.
 * 
 * Uma classe local é definada dentro de um método.
 */
public class LocalClassExample {

    private LocalClassExample() {

    }

    // Deve ser estática para poder ser referenciado dentro de um contexto estático.
    // metodo estático validatePhoneNumber() somente referencia variáveis estáticas.
    static String notNumberCharacteres = "[^0-9]";

    /**
     * Valida um número de telefone
     * 
     * Não posso declarar interfaces dentro do corpo de um método porque elas
     * sãoinerentemente estáticas.
     * 
     * @param phoneNumber
     * @param numberLength
     * @return
     */
    public static String validatePhoneNumber(String phoneNumber, int numberLength) {

        // agora tenho que informar o tamanho ao chamar o método.
        // final int numberLength = 10;.

        /*
         * Valid in JDK 8 and later:
         * int numLenght = 10.
         * 
         * Obs: Desde que seja "efetivamente final" (valor não mais
         * alterado depois de inicializado).
         */

        /**
         * Classe local são classes que estão dentro de um método.
         * 
         * Classes locais não podem ser estáticos porque elas tem que acessar as
         * variáveis de instância da classe envolvente. consequentemente elas não podem
         * conter muitos dos tipos de declarações estáticas.
         * 
         * Classes locais não podem ter incializadores estáticos nem interfaces de
         * instância.
         */
        class PhoneNumber {

            String validationMessage = null;

            PhoneNumber(String phoneNumber) {
                // Dessa maneira o numberLength deixa de ser efetivamente final.
                // numberLength = 7.

                // Apenas os numeros da string fornecida são extraídos.
                String currentNumber = phoneNumber.replaceAll(notNumberCharacteres, "");

                // Apenas a quantidade de digitos é validada.
                if (currentNumber.length() == numberLength) {
                    validationMessage = "número válido";

                } else {
                    validationMessage = "número inválido";
                }
            }

            /**
             * Mostra uma mensagem sobre a validade de um número de telefone.
             * 
             * @return uma mensagem de validação
             */
            public String getMensagemNumeroValidoOuInvalido() {
                return validationMessage;
            }

            // Valid in JDK 8 and later:
            // Utilizar os paramentros do método diretamente.

            /**
             * Imprime no console uma mensagem sobre o número analisado.
             * 
             * <p>
             * Este método utiliza os parâmetros da função que o envolve.
             * </p>
             */
            public void printOriginalNumber() {
                System.out.println("Original numbers is: " + phoneNumber + " and it's " + validationMessage);
            }
        }

        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber);

        // Valid in JDK 8 and later:
        // Método utilizou os parâmetros da função que o envolve. FUNCIONOU

        myNumber1.printOriginalNumber();

        return myNumber1.getMensagemNumeroValidoOuInvalido();
    }
}
