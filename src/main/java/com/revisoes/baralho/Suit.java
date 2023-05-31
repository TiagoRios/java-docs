package com.revisoes.baralho;

public enum Suit {
    PAUS(1, "paus"),
	OUROS(2, "ouros"),
	COPAS(3, "copas"),
	ESPADAS(4, "espadas");

    private final int id;
    private final String name;
    public static final int NUM_SUITS = 4;

    Suit(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    
}
