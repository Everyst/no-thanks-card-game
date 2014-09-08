package model.players;

import java.util.List;
import java.util.Random;

import model.Card;

public class RandomAI extends Player{
	private static final String AI_NAME = "RandomAI";
	
	public RandomAI() {
		super(AI_NAME);
	}
	
	public RandomAI(String name) {
		super(name);
	}

	Random random = new Random(System.nanoTime());
	
	@Override
	protected boolean chooseMove(Card currentCard, List<Player> otherPlayers) {
		// Ignore the inputs and choose to take the card with a 50% chance.
		if (random.nextInt(2) == 0){
			return false;
		} else {
			return true;
		}
	}

}
