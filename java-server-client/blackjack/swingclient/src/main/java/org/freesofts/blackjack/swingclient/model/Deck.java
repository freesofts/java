package org.freesofts.blackjack.swingclient.model;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private ArrayList<Card> usedcards = new ArrayList<Card>();
	public Deck() {
		for(int i=0;i<52;i++) {
			cards.add(new Card(i));
		}
	}
	
	public void shuffle() {
		
	}
	
	public Card pull() {
		Card used = cards.get(0);
		cards.remove(0);
		usedcards.add(used);
		return used;
	}
	
	public void reset() {
		cards.addAll(usedcards);
		usedcards.clear();
	}
}

