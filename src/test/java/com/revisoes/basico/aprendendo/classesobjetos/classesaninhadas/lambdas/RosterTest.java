package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.lambdas;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.Before;

public class RosterTest {

    private final int CATORZE_ANOS = 14;
    private final int DEZOITO_ANOS = 18;
    private final int TRINTA_ANOS = 30;
    private final int QUANTIDADE_MAIORES_DEZOITO_ANOS = 3;

    private final String BOB_AGE = "20";
    private final String BOB_NOME = "Bob";
    private final String BOB_EMAIL = "bob@example.com";

    List<Person> roster;
    List<Person> personsMaioresQue;
    List<Person> personsFaixaEtaria;
    List<Person> elegiveisServicoMilitar;

    Stream<Person> personStream;

    boolean emIdadeMilitar(Person p) {
        return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
    }

    @Before
    public void setup() {
        roster = Person.createRoster();
        
        personsMaioresQue = Roster.printPersonsOlderThan(roster, DEZOITO_ANOS);
        personsFaixaEtaria = Roster.printPersonsWithinAgeRange(roster, CATORZE_ANOS, TRINTA_ANOS);

        personStream = roster.stream().filter(p -> emIdadeMilitar(p));
    }

    // Approach 1: Create Methods that Search for Persons that Match One
    // Characteristic
    /* ================ Maiores de X anos +============== */

    @Test
    public void deveRetornarTodasPessoasMaioresQue() {
        assertEquals(QUANTIDADE_MAIORES_DEZOITO_ANOS, personsMaioresQue.size());
    }

    @Test
    public void deveRetornarEmailPrimeiraPessoaMaiorQue() {
        assertEquals(BOB_EMAIL, personsMaioresQue.get(0).getEmailAddress());
    }

    // Approach 2: Create More Generalized Search Methods
    /* ============= Idade entre X e Y anos ============= */

    @Test
    public void deveRetornarTodasPessoasFaixaEtaria() {
        assertEquals(2, personsFaixaEtaria.size());
    }

    @Test
    public void deveRetornarPrimeiraPessoaFaixaEtaria() {
        assertEquals(BOB_NOME, personsFaixaEtaria.get(0).getName());
    }

    // Approach 3: Specify Search Criteria Code in a Local Class
    /* ========= Elegiveis para serviço militar ========= */

    @Test
    public void deveRetornaNomePrimeiroElegivelServicoMilitar_ClasseLocal() {

        class CheckPersonEligibleForSelectiveService implements CheckPerson {
            public boolean test(Person p) {
                return emIdadeMilitar(p);
            }
        }

        elegiveisServicoMilitar = Roster.printPersons(roster, new CheckPersonEligibleForSelectiveService());

        assertEquals(BOB_NOME, elegiveisServicoMilitar.get(0).getName());
    }

    // Approach 4: Specify Search Criteria Code in an Anonymous Class

    @Test
    public void deveRetornaNomePrimeiroElegivelServicoMilitar_ClasseAnonima() {

        elegiveisServicoMilitar = Roster.printPersons(roster, new CheckPerson() {
            public boolean test(Person p) {
                return emIdadeMilitar(p);
            }
        });

        assertEquals(BOB_NOME, elegiveisServicoMilitar.get(0).getName());
    }

    // Approach 5: Specify Search Criteria Code with a Lambda Expression

    @Test
    public void deveRetornaNomePrimeiroElegivelServicoMilitar_ExpressaoLambda() {

        elegiveisServicoMilitar = Roster
                .printPersons(roster, p -> emIdadeMilitar(p));

        assertEquals(BOB_NOME, elegiveisServicoMilitar.get(0).getName());
    }

    // Approach 6: Use Standard Functional Interfaces with Lambda
    // Expressions

    @Test
    public void deveRetornaPrimeiroElegivelServicoMilitar_ParametroPredicate() {

        elegiveisServicoMilitar = Roster
                .printPersonsWithPredicate(roster, p -> emIdadeMilitar(p));

        assertEquals(BOB_NOME, elegiveisServicoMilitar.get(0).getName());
    }

    // Approach 7: Use Lamba Expressions Throughout Your Application

    @Test
    public void deveRetornaPrimeiroElegivelServicoMilitar_ParametrosPredicateEConsumer() {

        elegiveisServicoMilitar = Roster
                .processPersons(
                        roster,
                        p -> emIdadeMilitar(p),
                        p -> System.out.println(p.getName()));

        assertEquals(BOB_NOME, elegiveisServicoMilitar.get(0).getName());
    }

    // Approach 7, second example

    @Test
    public void deveRetornaPrimeiroElegiveLServicoMilitar_ParametrosPredicateEFunctionEConsumer() {

        elegiveisServicoMilitar = Roster
                .processPersonsWithFunction(
                        roster,
                        p -> emIdadeMilitar(p),
                        p -> p.getName(),
                        email -> System.out.println(email + " (essa parte na lambda)"));

        assertEquals(BOB_NOME, elegiveisServicoMilitar.get(0).getName());
    }

    // Approach 8: Use Generics More Extensively

    @Test
    public void deveRetornaPrimeiroElegiveLServicoMilitar_MaisGenerico() {

        elegiveisServicoMilitar = Roster
                .processElements(
                        roster,
                        p -> emIdadeMilitar(p),
                        p -> p.getEmailAddress(),
                        email -> System.out.println(email));

        assertEquals(BOB_NOME, elegiveisServicoMilitar.get(0).getName());
    }

    // Approach 9: Use Bulk Data Operations That Accept Lambda Expressions
    // as Parameters

    @Test
    public void deveRetornaPrimeiroElegiveLServicoMilitar_OperacoesDadosEmMassa() {
        // não há mapeamento, então retorna objeto completo.
        assertEquals(BOB_NOME, personStream.findFirst().get().getName());
    }

    @Test
    public void deveRetornaPrimeiroElegiveLServicoMilitar_OperacoesDadosEmMassa_Email() {
        // .map() retorna apenas o email de todos em formato de texto.
        String emailFirstPerson = personStream.map(p -> p.getEmailAddress()).findFirst().get();

        assertEquals(BOB_EMAIL, emailFirstPerson);
    }

    @Test
    public void deveRetornaPrimeiroElegiveLServicoMilitar_OperacoesDadosEmMassa_Idade() {
        // .map() retorna apenas a idade de todos em formato de texto.
        // String ageFirstPerson = personStream.map(p ->
        // p.getAge()).findFirst().get().toString();

        // Posso utilizar referência de método no lugar de lambdas.
        String ageFirstPerson = personStream.map(Person::getAge).findFirst().get().toString();

        assertEquals(BOB_AGE, ageFirstPerson);
    }
}
