package model.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import model.Card;

public abstract class Player implements Comparable<Player> {
	private String name;
	
	int tokens = 11;
	
	List<Card> cards = new ArrayList<Card>();
	
	public Player(String name){
		this.name = name;
	}
	
	/**
	 * This method should be overwritten to provide different AI.
	 * @param cardValue The current card value
	 * @return whether you want to take the card or not.
	 */
	protected abstract boolean chooseMove(Card currentCard, List<Player> otherPlayers);
	
	public final boolean takeTurn(Card currentCard, List<Player> otherPlayers){
		boolean takeCard = true; // Assume the player must take the card
		
		// A player may only decide whether or not to take the card if they have at least one token.
		if (getTokens() > 0){
			takeCard = chooseMove(currentCard, otherPlayers);
		}
		
		if (takeCard){
			addTokens(currentCard.getTokens());
			cards.add(currentCard);
		} else {
			// Put a token on the card
			spendToken();
			currentCard.addToken();
		}
		
		return takeCard;
	}
	
	
	public final int calculatePoints(){
		int points = 0;
		
		if (cards.size() > 0){
			// Sort the cards into order
			Collections.sort(cards);
			
			// Previous card seen is the first card
			int previousCard = cards.get(0).getValue();
			
			for (Card card : cards){
				// Only count subsequent cards which aren't exactly one more than the previous card seen
				// Note that in the first iteration the current card will equal the previous card, 
				// and therefore will be counted.
				if (card.getValue() != previousCard+1){
					points += card.getValue();
					previousCard = card.getValue();
				}
			}
		}
		
		// Subtract the number of tokens the player has 
		points -= getTokens();
		
		return points;
	}
	
	
	public final String getName(){
		return name;
	}
	
	
	
	private final void addTokens(int tokens){
		this.tokens += tokens;
	}
	
	private final void spendToken(){
		if (getTokens() <= 0){
			throw new RuntimeException("Tried to spend a token when tokens = " + getTokens());
		}
		tokens--;
	}
	
	protected final int getTokens(){
		return tokens;
	}
	
	@Override
	public String toString(){
		return "Cards: " + cards + ", Tokens: " + tokens + ", Points: " + calculatePoints();
	}
	
	/**
	 * This method compares two player's scores and puts them in descending order (lowest first)
	 */
	@Override
	public final int compareTo(Player otherPlayer) {
		// Descending order
		return this.calculatePoints() - otherPlayer.calculatePoints();
	}
}
