package vooga.games.grandius.bonus;

public class Bonus {

	private int enemiesKilled;
	private int boomersKilled;
	private int zipstersKilled;
	
	private int bonusCash;
	
	public Bonus()
	{
		
	}
	
	/**
	 * Increase count for Zipsters killed.
	 */
	public void killedZipster()
	{
		zipstersKilled++;
		enemiesKilled++;
	}
	
	/**
	 * Increase count for Boomers killed.
	 */
	public void killedBoomer()
	{
		boomersKilled++;
		enemiesKilled++;
	}
	
	/**
	 * Called at the end of a level to display the
	 * bonuses earned in the level. It also updates
	 * the current bonus total.
	 */
	public void displayBonuses()
	{
		//TODO add graphical display
		if(enemiesKilled > 30);
			bonusCash += 50;
		if(zipstersKilled > 20);
			bonusCash += 100;
		if(boomersKilled > 20);
		 	bonusCash += 150;
	}
	
	/**
	 * Returns the current bonus total. Extra
	 * cash is only added at the end of the 
	 * level.
	 * @return
	 */
	public int getBonusValue()
	{
		return bonusCash;
	}
}
