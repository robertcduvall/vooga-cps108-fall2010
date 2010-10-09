package vooga.games.zombieland;

import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.player.control.PlayerSprite;

import java.util.*;

public class Zombie extends PlayerSprite {

	private Shooter myTarget;
	private double myTargetX;
	private double myTargetY;
	private String myDirectionToMove;
	private double mySpeed;
	private int myDamage;
	private static int myAtttackDelay = 30;
	private int myAttackDelayStep;
	private String currentAttackAnimation = "";
	private Zombieland game;
	private Random random;
	private final static int ITEM_CHANCE = 20;
	
	public Zombie(String name, String stateName, AnimatedSprite down,
			AnimatedSprite up, AnimatedSprite left, AnimatedSprite right,
			Shooter hero, Zombieland zombieland) {
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
		
		game = zombieland;
		
		random = new Random();
	}

	/**
	 * Sets the target for the current zombie. Can be used if at some point we
	 * have more than one human target
	 * 
	 * @param hero
	 *            Target shooter
	 */
	private void setHumanTarget(Shooter hero) {
		myTarget = hero;
	}

	/**
	 * Set the damage of the zombie
	 * 
	 * @param hit
	 *            damage of the zombie
	 */
	private void setDamage(int hit) {
		myDamage = hit;
	}

	/**
	 * Tests to see if this zombie is closer to its target in the X direction.
	 * Used to control movement of this zombie
	 * 
	 * @return true if the this zombie is closer to its target in the x
	 *         direction
	 */
	private boolean isCloserInXDirection() {
		return Math.abs(getX() - myTargetX) >= Math.abs(getY() - myTargetY);
	}

	/**
	 * Tests whether the zombie has a health of 0. Used to keep track of and
	 * remove zombies when they have been killed
	 * 
	 * @return
	 */
	public boolean isHealthZero() {
		return (getHealth() <= 0);
	}

	/**
	 * Tests to see if the myAttackDelayStep has reached the threshold defined
	 * by myAtttackDelay. If so, the zombie is able to attack and vice versa.
	 * Used to restrict the number of attacks for a single zombie
	 * 
	 * @return true if the zombie is ready for the next attack
	 */
	public boolean isAbleToAttack() {
		return myAttackDelayStep == myAtttackDelay;
	}

	/**
	 * Get the damage of the zombie
	 * 
	 * @return damage of the zombie
	 */
	public int getDamage() {
		return myDamage;
	}

	/**
	 * Returns the attack direction of the zombie. Here the returned values are
	 * predefined to be 0, 1, 2, or 3, representing Right, Up, Left, Bottom
	 * respectively.
	 * 
	 * @return
	 */
	public int getAttackDirection() {
		if (isCloserInXDirection()) {
			return ((myTargetX - getX()) > 0) ? 0 : 2;
		} else
			return ((myTargetY - getY()) > 0) ? 3 : 1;
	}

	public double getDirection() {
		myTargetX = myTarget.getX();
		myTargetY = myTarget.getY();

		if (isCloserInXDirection()) {
			myDirectionToMove = "X";
			return (myTargetX - getX()) / Math.abs(myTargetX - getX());
		}

		// Else is assumed here. Implicitly calls on iAmCloserInYDirection()
		myDirectionToMove = "Y";
		return (myTargetY - getY()) / Math.abs(myTargetY - getY());

	}

	public void calculateDamage(double damage) {
		updateHealth((int) damage);
	}

	public void updateAttactStep() {
		myAttackDelayStep++;
	}

	public void resetAttackDelayStep() {
		myAttackDelayStep = 0;
	}

	public void attackFrom(String fromSide) {
		currentAttackAnimation = fromSide;
	}

	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (isHealthZero()) {
			setToCurrentSprite("ZombieDeath");
			AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
			int item = random.nextInt(100);
			if (sprite.getFrame() == sprite.getFinishAnimationFrame()) {
				setActive(false);
				myTarget.updateScore(1);
				
				if (item < ITEM_CHANCE){
					game.addRandomItem(getX(), getY());
				}
			}
			return;
		}

		if (!currentAttackAnimation.isEmpty()) {
			setToCurrentSprite(currentAttackAnimation);
			AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
			if (sprite.getFrame() == sprite.getFinishAnimationFrame()) {
				currentAttackAnimation = "";
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
		}

		if (myDirectionToMove.equals("Y")) {
			if (direction < 0) {
				moveY(mySpeed);
				setToCurrentSprite("Up");
			} else {
				moveY(Math.abs(mySpeed));
				setToCurrentSprite("Down");
			}
		}
	}

}
