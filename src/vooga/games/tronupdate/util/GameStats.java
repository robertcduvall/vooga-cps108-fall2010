package vooga.games.tronupdate.util;

import vooga.engine.overlay.Stat;

public class GameStats {
	private static Stat<Integer> NumMatches = new Stat<Integer>(0);
	private static Stat<Integer> Level = new Stat<Integer>(0);
	private static Stat<Integer> originalState = new Stat<Integer>(0);
	private int[] loses = new int[2];
	
	
	
	public static void setNumMatches(int i){
		NumMatches=new Stat<Integer>(i);
	}
	
	public static void setLevel(int i){
		Level = new Stat<Integer>(i);
	}
	
	public static void setOriginalState(int s){
		originalState = new Stat<Integer>(s);
	}
	
	public static Stat<Integer> getOriginalState(){
		return originalState;
	}
	public static Stat<Integer> getNumMatches(){
		return NumMatches;
	}
	public static Stat<Integer> getLevel(){
		return Level;
	}
}
