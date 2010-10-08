package vooga.games.grandius.bonus;

public class Cheats {

	private final static String INVINCIBILITY = "superman";
	private final static String EXTRA_POINTS = "highscore";
	private final static String EXTRA_CASH = "showmethemoney";
	
	
	public static boolean getCheat(String userInput)
	{
		if(userInput.equalsIgnoreCase(INVINCIBILITY))
		{
			
		} else if (userInput.equalsIgnoreCase(EXTRA_POINTS))
		{
			
		} else if (userInput.equalsIgnoreCase(EXTRA_CASH))
		{
			
		}
		
		return false;
	}
	
}
