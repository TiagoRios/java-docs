package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.lambdas;

import java.util.List;
import java.util.ArrayList;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import java.util.logging.Logger;

/**
 * Classe que mostra o funcionamento de expressões lambdas.
 * Favor verificar o tutorial no site da Oracle sobre o tema.
 * 
 * Essa classe possui um método principal main.
 * Esta classe possui uma suite de teste separada. Modificações no código fonte
 * podem quebrar os testes
 * 
 * OBS: Os métodos definidos dentro da classe estão na ordem correta de
 * aprendizagem e/ou revisões.
 */
public class Roster {

	// Approach 1: Create Methods that Search for Persons that Match One
	// Characteristic

	/**
	 * Busca pessoas maiores ou igual ao parametro age.
	 * 
	 * @param roster Lista de persons.
	 * @param age    idade para busca.
	 * @return Lista de persons
	 */
	public static List<Person> printPersonsOlderThan(List<Person> roster, int age) {
		
		List<Person> lista = new ArrayList<>();

		for (Person p : roster) {
			if (p.getAge() >= age) {
				lista.add(p);
				p.printPerson();
			}
		}
		
		return lista;
	}

	// Approach 2: Create More Generalized Search Methods

	/**
	 * Busca pessoas entre uma faixa de idade.
	 * 
	 * @param roster Lista de persons.
	 * @param low    idade mais baixa.
	 * @param high   idade mais alta.
	 * @return Lista de persons.
	 */
	public static List<Person> printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
		
		List<Person> lista = new ArrayList<>();

		for (Person p : roster) {
			if (low <= p.getAge() && p.getAge() < high) {
				p.printPerson();
				lista.add(p);
			}
		}

		return lista;
	}

	// Approach 3: Specify Search Criteria Code in a Local Class
	// Approach 4: Specify Search Criteria Code in an Anonymous Class
	// Approach 5: Specify Search Criteria Code with a Lambda Expression

	// Utiliza a minha propria interface funcional.
	public static List<Person> printPersons(List<Person> roster, CheckPerson tester) {

		List<Person> lista = new ArrayList<>();

		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
				lista.add(p);
			}
		}

		return lista;
	}

	// Approach 6: Use Standard Functional Interfaces with Lambda Expressions

	// Utiliza interface funcional padrão da linguagem.
	public static List<Person> printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {

		List<Person> lista = new ArrayList<>();

		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
				lista.add(p);
			}
		}

		return lista;
	}

	// Approach 7: Use Lambda Expressions Throughout Your Application

	/**
	 * Método que processa persons, ele utiliza duas interfaces funcionais padrões.
	 * 
	 * @see Predicate
	 * @see Consumer
	 * 
	 * @param roster Lista de persons.
	 * @param tester Uma função que faz uma verificação das pessoas da lista.
	 * @param block  faz algo, mas não retorna valores.
	 * @return Lista de persons.
	 */
	public static List<Person> processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {

		List<Person> lista = new ArrayList<>();

		for (Person p : roster) {
			if (tester.test(p)) {
				block.accept(p);
				lista.add(p);
			}
		}

		return lista;
	}

	// Approach 7, second example

	/**
	 * Método que processa persons, ele utiliza três interfaces funcionais padrões.
	 * 
	 * @see Predicate
	 * @see Function
	 * @see Consumer
	 * 
	 * @param roster Lista de persons.
	 * @param tester Uma função que faz uma verificação das pessoas da lista.
	 * @param mapper retorna um objeto Person do tipo String.
	 * @param block  faz algo, mas não retorna valores.
	 * @return Lista de persons.
	 */
	public static List<Person> processPersonsWithFunction(
			List<Person> roster,
			Predicate<Person> tester,
			Function<Person, String> mapper,
			Consumer<String> block) {

		List<Person> lista = new ArrayList<>();

		for (Person p : roster) {
			if (tester.test(p)) {
				String data = mapper.apply(p);
				block.accept(data + " ---- (isso na definicao do metodo) ---- ");
				lista.add(p);
			}
		}
		
		return lista;
	}

	// Approach 8: Use Generics More Extensively

	/**
	 * Método mais amplo que processa um elemento generico, ele utiliza interfaces
	 * funcionais padrões.
	 * 
	 * @param <X>    Tipo de dado
	 * @param <Y>    Tipo retorno.
	 * @param source Algum objeto iterável do tipo <X>
	 * @param tester Uma função que faz uma verificação do tipo <X> da iterável.
	 * @param mapper retorna um objeto Person do tipo <Y>.
	 * @param block  faz algo, mas não retorna valores.
	 * @return Lista do tipo <X>.
	 */
	public static <X, Y> List<X> processElements(
			Iterable<X> source,
			Predicate<X> tester,
			Function<X, Y> mapper,
			Consumer<Y> block) {

		List<X> lista = new ArrayList<>();

		for (X p : source) {
			if (tester.test(p)) {
				Y data = mapper.apply(p);
				block.accept(data);
				lista.add(p);
			}
		}

		return lista;
	}

	// Veja o teste de unidade para esta classe no arquivo correspondente.
	public static void main(String... args) {

		Logger logger = Logger.getLogger("RosterTest main");

		final String SEPARADOR = " \n===================================================================\n";
		final String ELEGIVEIS_SERVICO_MILITAR = "Persons who are eligible for Selective Service:";

		List<Person> roster = Person.createRoster();

		/* ============================================================ */
		// Approach 1: Create Methods that Search for Persons that Match One
		// Characteristic
		logger.info("Persons older than 20:");
		printPersonsOlderThan(roster, 20);
		logger.info(SEPARADOR);

		// /* ============================================================ */
		// // Approach 2: Create More Generalized Search Methods

		logger.info(SEPARADOR);
		logger.info("Persons between the ages of 14 and 30:");
		printPersonsWithinAgeRange(roster, 14, 30);
		logger.info(SEPARADOR);

		/* ============================================================ */
		// Approach 3: Specify Search Criteria Code in a Local Class

		logger.info(SEPARADOR);
		logger.info(ELEGIVEIS_SERVICO_MILITAR);

		class CheckPersonEligibleForSelectiveService implements CheckPerson {
			public boolean test(Person p) {
				return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
			}
		}

		printPersons(roster, new CheckPersonEligibleForSelectiveService());

		logger.info(SEPARADOR);

		/* ============================================================ */
		// Approach 4: Specify Search Criteria Code in an Anonymous Class

		logger.info(SEPARADOR);
		logger.info(() -> ELEGIVEIS_SERVICO_MILITAR + " (anonymous class):");

		printPersons(roster, new CheckPerson() {
			public boolean test(Person p) {
				return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
			}
		});

		logger.info(SEPARADOR);

		/* ============================================================ */
		// Approach 5: Specify Search Criteria Code with a Lambda Expression

		logger.info(SEPARADOR);
		logger.info(() -> ELEGIVEIS_SERVICO_MILITAR + " (lambda expression):");

		printPersons(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);

		logger.info(SEPARADOR);

		/* ============================================================ */
		// Approach 6: Use Standard Functional Interfaces with Lambda
		// Expressions

		logger.info(SEPARADOR);
		logger.info(() -> ELEGIVEIS_SERVICO_MILITAR + " (with Predicate parameter):");

		printPersonsWithPredicate(roster,
				p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);

		logger.info(SEPARADOR);

		/* ============================================================ */
		// Approach 7: Use Lamba Expressions Throughout Your Application

		logger.info(SEPARADOR);
		logger.info(() -> ELEGIVEIS_SERVICO_MILITAR + " (with Predicate and Consumer parameters):");

		processPersons(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,
				Person::printPerson);

		logger.info(SEPARADOR);

		/* ============================================================ */
		// Approach 7, second example

		logger.info(SEPARADOR);
		logger.info(() -> ELEGIVEIS_SERVICO_MILITAR + " (with Predicate, Function, and Consumer parameters):");

		processPersonsWithFunction(roster,
				p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,
				p -> p.getEmailAddress(),
				logger::info);

		logger.info(SEPARADOR);

		/* ============================================================ */
		// Approach 8: Use Generics More Extensively

		logger.info(SEPARADOR);
		logger.info(() -> ELEGIVEIS_SERVICO_MILITAR + " (generic version):");

		processElements(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,
				Person::getEmailAddress, logger::info);
		// Outra forma p -> p.getEmailAddress(), name.

		logger.info(SEPARADOR);

		/* ============================================================ */
		// Approach 9: Use Bulk Data Operations That Accept Lambda Expressions
		// as Parameters

		logger.info(SEPARADOR);
		logger.info("Persons who are eligible for Selective Service " + "(with bulk data operations):");

		roster.stream().filter(p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25)
				.map(Person::getEmailAddress).forEach(logger::info);
	}
}