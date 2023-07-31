package com.revisoes.basico.aprendendo.classesobjetos.baralho;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

// import com.revisoes.basico.aprendendo.classesobjetos.baralho.Card;
// import com.revisoes.basico.aprendendo.classesobjetos.baralho.Deck;

/*
 * Suite de teste para um deck de baralho.
 * 
 * Por facilidade testes possuem 2 assertivas.
 * Em cenários reais fazer apenas uma assertiva por teste.
 */
public class DeckTest {

    com.revisoes.basico.aprendendo.classesobjetos.baralho.Card card;
    com.revisoes.basico.aprendendo.classesobjetos.baralho.Deck deck;

    private static final String AS = "As";
    private static final String REI = "Rei";
    private static final String RAINHA = "Rainha";
    private static final String VALETE = "Valete";

    private static final String PAUS = "Paus";
    private static final String OUROS = "Ouros";
    private static final String COPAS = "Copas";
    private static final String ESPADAS = "Espadas";


    @Before
    public void setup() {
        deck = new Deck();
    }

    @Test
    public void deveRetornaNaipeOuros() {
        card = deck.getCard(Card.OUROS, Card.REI); 
        
        assertEquals(OUROS, Card.naipeToString(card.getNaipe()));
        assertEquals(
                REI + " de " + OUROS,
                Card.rankToString(card.getRank()) + " de " + Card.naipeToString(card.getNaipe()));
    }

    @Test
    public void deveRetornaNaipeCopas() {
        card = deck.getCard(Card.COPAS, Card.VALETE);
        assertEquals(COPAS, Card.naipeToString(card.getNaipe()));
        assertEquals(
            VALETE + " de " + COPAS,
            Card.rankToString(card.getRank()) + " de " + Card.naipeToString(card.getNaipe()));
    }

    @Test
    public void deveRetornaNaipeEspadas() {
        card = deck.getCard(Card.ESPADAS, Card.AS);
        
        assertEquals(ESPADAS, Card.naipeToString(card.getNaipe()));
        assertEquals(
            AS + " de " + ESPADAS,
            Card.rankToString(card.getRank()) + " de " + Card.naipeToString(card.getNaipe()));
    }
    
    @Test
    public void deveRetornaNaipePaus() {
        card = deck.getCard(Card.PAUS, Card.RAINHA);
        
        assertEquals(PAUS, Card.naipeToString(card.getNaipe()));
        assertEquals(
            RAINHA + " de " + PAUS,
            Card.rankToString(card.getRank()) + " de " + Card.naipeToString(card.getNaipe()));
    }
}
