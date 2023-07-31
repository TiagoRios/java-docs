package com.revisoes.basico.aprendendo.classesobjetos.baralho;

// Essa classe será melhorada utilizando "ENUMs"
public class Card {
	private final int rank;
	private final int naipe;

	// Kinds of suits
	public static final int PAUS = 1;
	public static final int OUROS = 2;
	public static final int COPAS = 3;
	public static final int ESPADAS = 4;

	// Kinds of ranks
	public static final int AS = 1;
	public static final int DOIS = 2;
	public static final int TRES = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NINE = 9;
	public static final int TEN = 10;
	public static final int VALETE = 11;
	public static final int RAINHA = 12;
	public static final int REI = 13;

	public Card(int rank, int naipe) {
		isValidRank(rank);
		isValidNaipe(naipe);
		this.rank = rank;
		this.naipe = naipe;
	}

	public int getNaipe() {
		return naipe;
	}

	public int getRank() {
		return rank;
	}

	public static boolean isValidRank(int rank) {
		return AS <= rank && rank <= REI;
	}

	public static boolean isValidNaipe(int naipe) {
		return OUROS <= naipe && naipe <= ESPADAS;
	}

	public static String rankToString(int rank) {
		switch (rank) {
		case AS:
			return "As";
		case DOIS:
			return "Deuce";
		case TRES:
			return "Three";
		case FOUR:
			return "Four";
		case FIVE:
			return "Five";
		case SIX:
			return "Six";
		case SEVEN:
			return "Seven";
		case EIGHT:
			return "Eight";
		case NINE:
			return "Nine";
		case TEN:
			return "Ten";
		case VALETE:
			return "Valete";
		case RAINHA:
			return "Rainha";
		case REI:
			return "Rei";
		default:
			// Handle an illegal argument. There are generally two
			// ways to handle invalid arguments, throwing an exception
			// (see the section on Handling Exceptions) or return null
			return null;
		}
	}

	public static String naipeToString(int naipe) {
		switch (naipe) {
		case OUROS:
			return "Ouros";
		case PAUS:
			return "Paus";
		case COPAS:
			return "Copas";
		case ESPADAS:
			return "Espadas";
		default:
			return null;
		}
	}
}