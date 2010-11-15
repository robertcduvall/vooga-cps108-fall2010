package vooga.engine.level;

import vooga.engine.core.Sprite;

/**
 * this Class add functionality to set objectives for the player and
 * methods to determine if they are met.  I imagine you can have objectives
 * rely on other objectives. For example, and objective may be "Defeat every boss"
 * but defeating each boss is it's own objective.  When the objective is met,
 * something happens, but that's up to the game designer.  A state may be invoked,
 * a rule created or altered, or any number of other changes may be enacted.
 */
public abstract class Objective {
	
	private boolean isMet;
	private int targetNumber = 0;
	private Sprite targetSprite = null;
	private String targetLevel = null;

	
	/**
	 * constructor...
	 */
	public Objective () {
		
	}
	
	public boolean isMet () {
		return isMet;
	}
	
	/**
	 * Setter for numerical objectives like "collect X amount of coins" or "get X experience points"
	 * or "survive for X amount of seconds"
	 * @param num
	 */
	public void setTargetNumber(int num) {
		targetNumber = num;
		
	}
	

	/**
	 * 
	 * @return the target number for the objective
	 */
	public int getTargetNumber () {
		return targetNumber;
	}
	
	
	/**
	 * Setter for sprite objectives like "touch the jetpack sprite" or "shoot the
	 * jedi sprite" or "step on the rare candy sprite"
	 * @param num
	 */
	public void setTargetSprite(Sprite sprite) {
		targetSprite = sprite;
	}
	
	
	/**
	 * 
	 * @return the target sprite for the objective
	 */
	public Sprite getTargetSprite () {
		return targetSprite;
	}

	
	/**
	 * Setter for level objectives like "get to the level with the first boss" or "complete the level with
	 * the green monsters"
	 * @param num
	 */
	public void setTargetLevel(String level) {
		targetLevel = level;
	}
	
	/**
	 * 
	 * @return the target level for the objective
	 */
	public String getTargetLevel () {
		return targetLevel;
	}
	
	
	
	
	
	

}
