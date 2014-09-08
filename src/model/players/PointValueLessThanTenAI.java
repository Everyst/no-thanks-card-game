package model.players;

import java.util.List;

import model.Card;

public class PointValueLessThanTenAI extends Player{
	private static final String AI_NAME = "PointValueLessThanTenAI";
	
	public PointValueLessThanTenAI() {
		super(AI_NAME);
	}
	
	public PointValueLessThanTenAI(String name) {
		super(name);
	}
	
	@Override
	protected boolean chooseMove(Card currentCard, List<Player> otherPlayers) {
		// Take the card if it's net value is less than 10 points.
		if (currentCard.getValue() - currentCard.getTokens() < 10){
			return true;
		} else {
			return false;
		}
	}

}
