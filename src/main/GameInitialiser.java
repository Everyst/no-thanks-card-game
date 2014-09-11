package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import model.players.*;

public class GameInitialiser {
	
	private static Hashtable<Class<? extends Player>, Integer> gameStatistics = new Hashtable<Class<? extends Player>, Integer>();
	
	
	public static void main(String[] args) throws InterruptedException{
		Thread game;
		
		//gameStatistics.put(HumanPlayer.class, 0);
		//gameStatistics.put(RandomAI.class, 0);
		gameStatistics.put(GeoffFAI.class, 0);
		gameStatistics.put(NetValueLessThanXAI.class, 0);
		gameStatistics.put(NeverTakeAI.class, 0);
		
		for (int i = 0; i < 10000; i++){
			game = new Thread(new GameRunner());
			game.start();
			game.join();
		}
		
		System.out.println(gameStatistics);
	}
	
	
	public static List<Player> setUpPlayers(){
		List<Player> players = new ArrayList<Player>();
		
		//players.add(new HumanPlayer());
		
		//players.add(new RandomAI());
		
		players.add(new GeoffFAI());
		players.add(new NetValueLessThanXAI(11));
		players.add(new NeverTakeAI());
		
		
		Collections.shuffle(players); // shuffle the order of players
		
		return players;
	}
	
	
	public static void addScore(Class<? extends Player> class1){
		gameStatistics.put(class1, gameStatistics.get(class1)+1); // Increase by one
	}
	
}
