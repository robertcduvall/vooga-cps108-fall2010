package vooga.games.tronupdate.util;

public class Mode {
	private static boolean singlePlayer,multiplePlayer,AI;
	//private static boolean maze,classic,blocks;
	private static int environment;
	private static int numMatches;
	private Mode(){
	}
	
	public static void setEnvironment(int e){
		environment = e;
	}
	public static int getEnvironment(){
		return environment;
	}
	
//	public static void setMaze(){
//		maze = true; classic = false; blocks = false;
//	}
//	public static void setClassic(){
//		maze = false; classic = true; blocks = false;
//	}
//	public static void setBlocks(){
//		maze = false; classic = false; block = true;
//	}
//	public static boolean isMaze(){
//		return maze;
//	}
//	public static boolean isClassic(){
//		return classic;
//	}
//	public static boolean isBlocks(){
//		return blocks;
//	}
	
	public static void setNumMatches(){
		numMatches++;
	}
	public static int getNumMatches(){
		return numMatches;
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
