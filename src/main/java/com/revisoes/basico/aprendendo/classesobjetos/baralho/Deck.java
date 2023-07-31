package com.revisoes.basico.aprendendo.classesobjetos.baralho;

public class Deck {

	public static final int NUM_SUITS = 4;
	public static final int NUM_RANKS = 13;
	public static final int NUM_CARDS = NUM_SUITS * NUM_RANKS;

	private Card[][] cards;

	public Deck() {
		cards = new Card[NUM_SUITS][NUM_RANKS];
		for (int naipe = 1; naipe <= NUM_SUITS; naipe++) {
			for (int rank = Card.AS; rank <= Card.REI; rank++) {
				cards[naipe - 1][rank - 1] = new Card(rank, naipe);
			}
		}
	}

	public Card getCard(int naipe, int rank) {
		return cards[naipe - 1][rank - 1];
	}
}