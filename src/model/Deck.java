package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Deck {

	List<Card> cards = new ArrayList<Card>(33);
	
	public Deck(){
		// Add the cards 3-35
		for (int i = 3; i <= 35; i++){
			cards.add(new Card(i));
		}
		
		shuffleDeck();
		removeNineCards();
	}
	
	
	public Card nextCard(){
		if (cards.size() > 0){
			return cards.remove(cards.size()-1);
		} else {
			return null;
		}
	}
	
	
	private void shuffleDeck(){
		Collections.shuffle(cards);
	}
	
	private void removeNineCards(){
		// Check that we are starting a new game.
		if (cards.size() != 33){
			throw new RuntimeException("You may not remove nine cards from a deck that does not have cards 3-35 in it.");
		}
		
		// Remove 9 cards from the end of the deck
		for (int i = 0; i < 9; i++){
			cards.remove(cards.size()-1);
		}
	}
	
	
	@Override
	public String toString(){
		return ""+cards;
	}
	
}
