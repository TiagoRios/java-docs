package com.revisoes.basico.aprendendo.classesobjetos.blocosinicializadores;

/**
 * Classe obtida do site da documentação oracle. 
 */
public class Bicycle {
	private int cadence;
	private int gear;
	private int speed;
	private int outraVariavelInstancia = inicializaVariavelInstacia(999);

	private int id;

	// Inicializando com metodo estatico e privado.
	private static int numberOfBicycles = numberOfBicyclesInitializer(0);

	public Bicycle(int startCadence, int startSpeed, int startGear) {
		gear = startGear;
		cadence = startCadence;
		speed = startSpeed;

		id = ++numberOfBicycles;
	}

	// Lint dá problemas. Não pode ser utilizado para inicializar variáveis.
	// Essa construção é raramente usada. inicializar das formas convencionais.
	// {.
		// Esse bloco � compartilhado entre todos os construtores.
	//}.

	// Inicializando variaveis de classe
	static {
		// Usado para inicialiozar codigos mais complexos com algum tipo de valida��o ou
		// trotamento de erros
	}

	// Inicializa variaveis de classe.
	private static int numberOfBicyclesInitializer(int numeroInicialBicicletas) {
		return numeroInicialBicicletas;
	}

	// Inicializa variaveis de instancia.
	protected final int inicializaVariavelInstacia(int valorInicial) {
		return valorInicial;
	}

	public int getID() {
		return id;
	}

	public int getOtherInstanceVar() {
		return outraVariavelInstancia;
	}

	public void setOutraVariavelInstancia(int outraVariavelInstancia) {
		this.outraVariavelInstancia = outraVariavelInstancia;
	}

	public static int getNumberOfBicycles() {
		return numberOfBicycles;
	}

	public int getCadence() {
		return cadence;
	}

	public void setCadence(int newValue) {
		cadence = newValue;
	}

	public int getGear() {
		return gear;
	}

	public void setGear(int newValue) {
		gear = newValue;
	}

	public int getSpeed() {
		return speed;
	}

	public void applyBrake(int decrement) {
		speed -= decrement;
	}

	public void speedUp(int increment) {
		speed += increment;
	}
}