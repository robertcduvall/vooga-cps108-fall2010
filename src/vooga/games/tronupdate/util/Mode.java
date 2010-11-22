package vooga.games.tronupdate.util;

public class Mode {
	private static boolean singlePlayer,multiplePlayer;
	private Mode(){
	}
	
	public static void setSinglePlayer(){
		singlePlayer = true; multiplePlayer = false;
	}
	public static void setMultiplePlayer(){
		singlePlayer = false; multiplePlayer = true;
	}
	public static boolean isSingle(){
		return singlePlayer;
	}
	public static boolean isMultiple(){
		return multiplePlayer;
	}
	
}
