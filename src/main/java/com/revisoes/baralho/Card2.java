package com.revisoes.baralho;

// Essa classe será melhorada utilizando "ENUMs"
public class Card2 {
	private final Rank rank;
	private final Suit suit;
	public static final int NUM_CARDS = Suit.NUM_SUITS * Rank.NUM_RANKS;	

	public Card2(Rank rank, Suit naipe) {
		isValidRank(rank);
		isValidSuit(naipe);
		this.rank = rank;
		this.suit = naipe;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	private static boolean isValidRank(Rank rank) {
		return Rank.AS.getId() <= rank.getId() && rank.getId() <= Rank.REI.getId();
	}

	private static boolean isValidSuit(Suit naipe) {
		return Suit.OUROS.getId() <= naipe.getId() && naipe.getId() <= Suit.ESPADAS.getId();
	}
	@Override
	public String toString() {
		return this.getRank() + " de " + this.getSuit();
	}
}