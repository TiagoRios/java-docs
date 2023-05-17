package com.revisoes.classesaninhadas.locais;

public class LocalClassExample {

    private LocalClassExample() {

    }

    // Deve ser estática para poder ser referenciado dentro de um contexto estático.
    // metodo estático validatePhoneNumber() somente referencia variáveis estáticas.
    static String regularExpression = "[^0-9]";
    
     // Não posso declarar interfaces dentro do corpo de um método porque elas são
     // inerentemente estáticas.
    public static String validatePhoneNumber(String phoneNumber1, int numberLength) {
        /*
        // agora tenho que informar o tamanho ao chamar o método.
        // final int numberLength = 10;.

        // Valid in JDK 8 and later:
        // int numLenght = 10. Desde que seja "efetivamente final" (valor não mais
        // alterado depois de inicializado).
        */
        /**
         * Classe local são classes que estão dentro de um método.
         * 
         * Classes locais não podem ser estáticos porque elas tem que acessar as
         * variáveis de instância da classe envolvente. consequentemente elas não podem
         * conter muitos dos tipos de declarações estáticas.
         * 
         * Classes não podem ter incializadores estáticos nem interfaces de instância.
         */
        class PhoneNumber {

            String formattedPhoneNumber = null;

            PhoneNumber(String phoneNumber) {
                // Dessa maneira o numberLength deixa de ser efetivamente final.
                // numberLength = 7.

                // Extrai apenas os numeros da string fornecida
                String currentNumber = phoneNumber.replaceAll(regularExpression, "");

                // isso somente valida a quantidade de digitos.
                if (currentNumber.length() == numberLength)
                    formattedPhoneNumber = "número válido";
                else
                    formattedPhoneNumber = "número inválido";
            }

            public String getMensagemNumeroValidoOuInvalido() {
                return formattedPhoneNumber;
            }

            // Valid in JDK 8 and later:
            // Utilizar os paramentros do método diretamente.

            // public void printOriginalNumbers() {.
            // System.out.println("Original numbers are " + phoneNumber1 +
            // " and " + phoneNumber2);.
            // }.
        }

        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);

        // Valid in JDK 8 and later: método que utiliza os paramentros da função que
        // o envolve.
        // myNumber1.printOriginalNumbers();.

        return myNumber1.getMensagemNumeroValidoOuInvalido();

    }
}
