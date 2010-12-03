package arcade.store.addendum;

public class ShopCredit {

	private double timeCredit;
	private double gameCredit;
	
	
	public ShopCredit()
	{
		timeCredit = 0;
		gameCredit = 0;
	}
	
	public double getTimeCredit()
	{
		return timeCredit;
	}
	
	public void addTimeCredit(double time)
	{
		timeCredit += time;
	}
	
	public double getGameCredit()
	{
		return gameCredit;
	}
	
	public void addGameCredit(double credit)
	{
		gameCredit += credit;
	}
	
	
	
	
	
}
