package com.revisoes.basico.aprendendo.classesobjetos.baralho;

public class Deck2 {

	private static Card2[] cards = new Card2[Card2.NUM_CARDS];

	public Deck2() {
		int i = 0;
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards[i++] = new Card2(rank, suit);
			}	
		};
	}

	public Card2 getCard(Suit suit, Rank rank) {
		for (Card2 card2 : cards) {
			if(card2.getSuit() == suit && card2.getRank() == rank){
				return card2;
			}
		}
		return null;
	}

}