package com.revisoes.basico.aprendendo.classesobjetos.baralho;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ParaTestar {
    public static void main(String[] args) {
        Deck deck = new Deck();
        for (int suit = Card.OUROS; suit <= Card.ESPADAS; suit++) {
            for (int rank = Card.AS; rank <= Card.REI; rank++) {
                Card card = deck.getCard(suit, rank);

                // Lint pedia para trocar por um Logger.
                Logger.getLogger(ParaTestar.class.getName())
                        .log(Level.INFO, "----- {0}",
                                String.format("%s de %s%n",
                                        Card.rankToString(card.getRank()),
                                        Card.naipeToString(card.getNaipe())));
            }
        }
    }
}