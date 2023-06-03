package com.revisoes.baralho;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

/*
 * Suite de teste para um deck de baralho.
 * 
 * Por facilidade testes possuem 2 assertivas.
 * Em cenários reais fazer apenas uma assertiva por teste.
 */
public class DeckTest2 {

    Card2 card;
    Deck2 deck;
    
    @Before
    public void setup() {
        deck = new Deck2();
    }

    @Test
    public void deveRetornaNaipeOuros() {
        card = deck.getCard(Suit.OUROS, Rank.REI); 
        
        assertEquals(Suit.OUROS, card.getSuit());
        assertEquals(Rank.REI + " de " + Suit.OUROS, card.toString());
    }

    @Test
    public void deveRetornaNaipeCopas() {
        card = deck.getCard(Suit.COPAS, Rank.VALETE); 
        
        assertEquals(Suit.COPAS, card.getSuit());
        assertEquals(Rank.VALETE + " de " + Suit.COPAS, card.toString());
    }

    @Test
    public void deveRetornaNaipeEspadas() {
        card = deck.getCard(Suit.ESPADAS, Rank.AS); 
        
        assertEquals(Suit.ESPADAS, card.getSuit());
        assertEquals(Rank.AS + " de " + Suit.ESPADAS, card.toString());
    }
    
    @Test
    public void deveRetornaNaipePaus() {
        card = deck.getCard(Suit.PAUS, Rank.RAINHA); 
        
        assertEquals(Suit.PAUS, card.getSuit());
        assertEquals(Rank.RAINHA + " de " + Suit.PAUS, card.toString());
    }
}
