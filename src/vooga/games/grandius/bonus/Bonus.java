package vooga.games.grandius.bonus;

public class Bonus {

	private int enemiesKilled;
	private int boomersKilled;
	private int zipstersKilled;
	
	private int bonusValue;
	
	public Bonus()
	{
		
	}
	
	public void killedZipster()
	{
		zipstersKilled++;
		enemiesKilled++;
	}
	
	public void killedBoomer()
	{
		boomersKilled++;
		enemiesKilled++;
	}
	
	public void displayBonuses()
	{
		//TODO add graphical display
		if(enemiesKilled > 30);
			bonusValue += 50;
		if(zipstersKilled > 20);
			bonusValue += 100;
		if(boomersKilled > 20);
		 	bonusValue += 150;
	}
	
	public int getBonusValue()
	{
		return bonusValue;
	}
}
