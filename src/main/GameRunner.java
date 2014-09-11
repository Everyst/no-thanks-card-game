package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Card;
import model.Deck;
import model.players.Player;

public class GameRunner implements Runnable{
	 private final static Logger LOG = Logger.getLogger(GameRunner.class.getName());
	
	
	private int currentPlayer;
	private int numberOfPlayers;
	
	
	/*public public GameRunner(Hashtable<Class<? extends Player>, Integer>) {
		super();
		
	}*/
	
	
	@Override
	public void run() {
		LOG.setLevel(Level.OFF);
		
		// Set up the deck
		Deck deck = new Deck();
		
		List<Player> players = GameInitialiser.setUpPlayers();
		LOG.info("The player order is: " + players);
		numberOfPlayers = players.size();
		
		List<Player> otherPlayers = new ArrayList<Player>(numberOfPlayers-1); // Will store all players who aren't the current player.
		
		// Get the first card out
		Card currentCard = deck.nextCard();
		
		currentPlayer = 0;
		
		// Game loop
		boolean gameRunning = true;
		while(gameRunning){
			
			otherPlayers.clear();
			otherPlayers.addAll(players);
			otherPlayers.remove(currentPlayer);
			
			// While the current player is willing to take the cards, he keeps taking them.
			while (players.get(currentPlayer).takeTurn(currentCard, otherPlayers, deck.cardsRemaining())){
				LOG.info("Player " + players.get(currentPlayer).getName() + " took card " + currentCard);
				
				if ((currentCard = deck.nextCard()) == null){
					// No more cards, end the game
					gameRunning = false;
					break;
				}
			}
			
			if (currentCard != null){
				LOG.info("Player " + players.get(currentPlayer).getName() + " put a token on card " + currentCard);
				
				currentPlayer = nextPlayer();				
			}
		}
		
		Collections.sort(players);
		
		LOG.info("\nGAME OVER");
		
		LOG.severe(players.get(0) + " won!");
		
		GameInitialiser.addScore(players.get(0).getClass());
		
		for (int i = 0; i < numberOfPlayers; i++){
			LOG.info("Player " + players.get(i) + " got " + players.get(i).getStatus());
		}
	}
	
	private int nextPlayer(){
		currentPlayer++;
		if (currentPlayer % numberOfPlayers == 0){
			currentPlayer = 0;
		}
		return currentPlayer;
	}

}
