package model.players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import model.Card;

public class HumanPlayer extends Player{
	private static final String AI_NAME = "Human";
	
	public HumanPlayer() {
		super(AI_NAME);
	}
	
	public HumanPlayer(String name) {
		super(name);
	}
	
	@Override
	protected boolean chooseMove(Card currentCard, List<Player> otherPlayers, int numberOfCardsRemaining) {
		System.out.println("\nPlayer " + this.getName() + " takes their turn...");
		
		for (Player otherPlayer : otherPlayers){
			System.out.println("Player " + otherPlayer.getName() + " has cards " + otherPlayer.getCards());
		}
		
		System.out.println("There are " + numberOfCardsRemaining + " cards remaining.");
		
		System.out.println("You have cards " + getCards() + " and " + getTokens() + " tokens.");
		
		System.out.println("Current card is " + currentCard.getValue() + " with " + currentCard.getTokens() + " tokens on it.");
		
		System.out.println("Do you take the card? Y/N");
		
		BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
		try {
			if (reader.readLine().equalsIgnoreCase("y")){
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			return false;
		}
	}
}
