package vooga.engine.level;
	









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

	
	/**
	 * constructor...
	 */
	public Objective () {
		
	}
	
	public boolean isMet () {
		return isMet;
	}
	
	
	

}
