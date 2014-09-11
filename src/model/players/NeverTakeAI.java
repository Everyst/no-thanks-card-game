package model.players;

import java.util.List;

import model.Card;

public class NeverTakeAI extends Player{
	private static final String AI_NAME = "NeverTakeAI";
	
	public NeverTakeAI() {
		super(AI_NAME);
	}
	
	public NeverTakeAI(String name) {
		super(name);
	}
	
	@Override
	protected boolean chooseMove(Card currentCard, List<Player> otherPlayers, int numberOfCardsRemaining) {
		// Never take a card you don't have to.
		return false;
	}

}
