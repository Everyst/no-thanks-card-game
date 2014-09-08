package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.*;
import model.players.*;

public class MainGame {
	private static int numberOfPlayers = 5;
	
	private static int currentPlayer = 0;
	
	
	public static void main(String[] args){
		// Set up the deck
		Deck deck = new Deck();
		
		// Set up the players
		List<Player> players = new ArrayList<Player>(numberOfPlayers);
		
		// One PVLTT AI and others all RandomAI
		players.add(new PointValueLessThanTenAI());
		for (int i = 0; i < numberOfPlayers-1; i++){
			players.add(new RandomAI("RandomAI_"+i));
		}
		
		Collections.shuffle(players); // shuffle the order of players
		System.out.println("The player order is: " + players);
		
		List<Player> otherPlayers = new ArrayList<Player>(numberOfPlayers-1); // Will store all players who aren't the current player.
		
		// Get the first card out
		Card currentCard = deck.nextCard();
		
		// Game loop
		boolean gameRunning = true;
		while(gameRunning){
			
			otherPlayers.clear();
			otherPlayers.addAll(players);
			otherPlayers.remove(currentPlayer);
			
			// While the current player is willing to take the cards, he keeps taking them.
			while (players.get(currentPlayer).takeTurn(currentCard, otherPlayers)){
				System.out.println("Player " + players.get(currentPlayer).getName() + " took card " + currentCard);
				
				if ((currentCard = deck.nextCard()) == null){
					// No more cards, end the game
					gameRunning = false;
					break;
				}
			}
			
			if (currentCard != null){
				System.out.println("Player " + players.get(currentPlayer).getName() + " put a token on card " + currentCard);
				
				nextPlayer();
			}
		}
		
		Collections.sort(players);
		
		for (int i = 0; i < numberOfPlayers; i++){
			System.out.println("Player " + players.get(i) + " got " + players.get(i).getStatus());
		}
		
	}
	
	private static void nextPlayer(){
		currentPlayer++;
		if (currentPlayer % numberOfPlayers == 0){
			currentPlayer = 0;
		}
	}
	
}
