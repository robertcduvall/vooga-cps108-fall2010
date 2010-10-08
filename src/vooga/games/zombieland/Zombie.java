package vooga.games.zombieland;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;

import vooga.engine.player.control.PlayerSprite;

public class Zombie extends PlayerSprite {

	private Shooter myTarget;
	private double myTargetX;
	private double myTargetY;
	private String myDirectionToMove;
	private double mySpeed;
	private Timer myTimer;
	private int myDamage;
	private static int myAtttackDelay = 20;
	private int myAttackDelayStep;

	public Zombie(String name, String stateName, AnimatedSprite down, AnimatedSprite up,
			AnimatedSprite left, AnimatedSprite right, Shooter hero) {
		super(name, stateName, down);
		mapNameToSprite("Up", up);
		mapNameToSprite("Left", left);
		mapNameToSprite("Right", right);
		mapNameToSprite("Down", down);

		setHumanTarget(hero);
		myDirectionToMove = "X";
		mySpeed = -0.25;

		setHealth(25);
		setDamage(5);
		resetAttackDelayStep();
		myTimer = new Timer(1000);
	}

	/**
	 * If at some point we have more than one human target
	 * @param hero
	 */
	private void setHumanTarget(Shooter hero) {
		myTarget = hero;
	}


	public void resetAttackDelayStep() {
		myAttackDelayStep= 0;
	}

	public void updateAttactStep(){
		myAttackDelayStep++;
	}

	private void setDamage(int hit){
		myDamage = hit;
	}

	public int getDamage(){
		return myDamage;
	}

	private double getDirection() {
		myTargetX = myTarget.getX();
		myTargetY = myTarget.getY();

		if (iAmCloserInXDirection()) {
			myDirectionToMove = "X";
			return myTargetX - getX();
		}

		//Else is assumed here. Implicitly calls on iAmCloserInYDirection()
		myDirectionToMove = "Y";
		return myTargetY - getY();

	}

	private boolean iAmCloserInXDirection() {
		return Math.abs(getX() - myTargetX) >= Math.abs(getY() - myTargetY);
	}


	public void update(long elapsedTime) {		
		if (healthIsZero()){
			if (myTimer.action(elapsedTime)){
				setActive(false);
				myTarget.updateScore(1);
			}
			return;
		}

		double direction = getDirection();

		if (myDirectionToMove.equals("X")) {
			if (direction < 0) {
				moveX(mySpeed);
				setToCurrentSprite("Left");
			} else {
				moveX(Math.abs(mySpeed));
				setToCurrentSprite("Right");
			}

			return;
		} 

		if(myDirectionToMove.equals("Y"))
		{
			if (direction < 0) {
				moveY(mySpeed);
				setToCurrentSprite("Up");
				return; 
			} else {
				moveY(Math.abs(mySpeed));
				setToCurrentSprite("Down");
			}
			return;
		}		
	}




public void attackFrom(String fromSide)
{
	setToCurrentSprite(fromSide);

	//how do you add a timer here?

}


public void calculateDamage(double damage)
{
	updateHealth((int) damage);


}

public boolean healthIsZero()
{
	return (getHealth() <= 0);
}


public boolean isAbleToAttack()
{
	return myAttackDelayStep == myAtttackDelay;
}



}
