package vooga.games.tronupdate.util;

public class Mode {
	private static boolean singlePlayer,multiplePlayer,AI;
	private Mode(){
	}
	
	public static void setSinglePlayer(){
		singlePlayer = true; multiplePlayer = false; AI = false;
	}
	public static void setMultiplePlayer(){
		singlePlayer = false; multiplePlayer = true; AI = false;
	}
	public static void setAI(){
		AI=true; singlePlayer = false; multiplePlayer = false;
	}
	public static boolean isSingle(){
		return singlePlayer;
	}
	public static boolean isMultiple(){
		return multiplePlayer;
	}
	public static boolean isAI(){
		return AI;
	}
}
