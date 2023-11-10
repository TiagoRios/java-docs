package com.revisoes.basico.aprendendo.classesobjetos.classesaninhadas.lambdas;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Person {

	Logger logger = Logger.getLogger(Person.class.getName());

	public enum Sex {
		MALE, FEMALE
	}

	Sex gender;
	String name;
	LocalDate birthday;
	String emailAddress;

	public Person() {
	}

	public Person(String nameArg, LocalDate birthdayArg, Sex genderArg, String emailArg) {
		name = nameArg;
		birthday = birthdayArg;
		gender = genderArg;
		emailAddress = emailArg;
	}

	public int getAge() {
		return birthday.until(IsoChronology.INSTANCE.dateNow()).getYears();
	}

	public Sex getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void printPerson() {
		logger.log(Level.INFO, "{0}", name + ", " + this.getAge());
	}

	/**
	 * Compara dois objetos pela idade de person.
	 * 
	 * @param firstPerson  primeiro objeto
	 * @param secondPerson segundo objeto
	 * 
	 * @return inteiro com a comparação.
	 */
	public static int compareByAge(Person firstPerson, Person secondPerson) {
		return firstPerson.birthday.compareTo(secondPerson.birthday);
	}

	/**
	 * Cria uma lista de pessoas já preenchidas com valores simulados.
	 * 
	 * @return lista de Person
	 */
	public static List<Person> createRoster() {

		List<Person> roster = new ArrayList<>();
		roster.add(new Person("Bob", IsoChronology.INSTANCE.dateNow().minusYears(20), Person.Sex.MALE,
				"bob@example.com"));
		roster.add(new Person("Fred", IsoChronology.INSTANCE.dateNow().minusYears(40), Person.Sex.MALE,
				"fred@example.com"));
		roster.add(new Person("Jane", IsoChronology.INSTANCE.dateNow().minusYears(31), Person.Sex.FEMALE,
				"jane@example.com"));
		roster.add(new Person("George", IsoChronology.INSTANCE.dateNow().minusYears(15), Person.Sex.MALE,
				"george@example.com"));

		return roster;
	}
}