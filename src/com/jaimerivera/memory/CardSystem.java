package com.jaimerivera.memory;

import java.io.File;
import java.io.IOException;

import com.jaimerivera.memory.Card.Face;
import com.jaimerivera.memory.Card.Suit;

public class CardSystem extends System<Integer, Card> {
	
	public CardSystem(File file) throws IOException {
		super(file);
	}

	@Override
	public Integer getType1(String line) {
		int index = line.indexOf(" ");
		return Integer.parseInt(line.substring(0, index));
	}

	@Override
	public Card getType2(String line) {
		int index = line.indexOf(" ");
		String[] faceAndSuit = line.substring(index + 1).split(" ");
		
		return Card.getCard(Face.valueOf(faceAndSuit[0]), Suit.valueOf(faceAndSuit[1]));
	}
	
	public int get(Card card) {
		Object value = super.get(card);
		
		return (value == null) ? -1 : (Integer) value;
	}
	
	public Card get(int num) {
		Object value = super.get(num);
		
		return (value == null) ? null : (Card) value;
	}
}
