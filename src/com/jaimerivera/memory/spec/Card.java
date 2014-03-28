package com.jaimerivera.memory.spec;

public class Card {
	
	public static enum Suit {
		CLUBS, SPADES, HEARTS, DIAMONDS
	}
	
	public static enum Face {
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
	}
	
	public static final int NUM_SUITS = Suit.values().length;
	public static final int NUM_FACES = Face.values().length;
	public static final int NUM_CARDS = NUM_SUITS * NUM_FACES;
	private static final Card[] CARDS = new Card[NUM_CARDS];
	
	static {		
		int index = 0;;
		for (Suit suit : Suit.values()) {
			for (Face face : Face.values()) {
				CARDS[index++] = new Card(face, suit);
			}
		}
	}
	
	public final Face face;
	public final Suit suit;
	
	private Card(Face face, Suit suit) {
		this.face = face;
		this.suit = suit;
	}
	
	public static final Card getCard(Face face, Suit suit) {
		return CARDS[suit.ordinal() * NUM_FACES + face.ordinal()];
	}

}
