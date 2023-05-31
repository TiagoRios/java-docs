package com.revisoes.baralho;

public enum Rank {
    AS(1, "as"),
	DOIS(2, "dois"),
	TRES(3, "tres"),
	FOUR(4, "four"),
	FIVE(5, "five"),
	SIX(6, "six"),
	SEVEN(7, "seven"),
	EIGHT(8, "eight"),
	NINE(9, "nine"),
	TEN(10, "ten"),
	VALETE(11, "valete"),
	RAINHA(12, "rainha"),
	REI(13, "rei");

    private final int id;
    private final String name;
	public static final int NUM_RANKS = 13;


    Rank(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
