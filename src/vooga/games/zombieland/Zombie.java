package vooga.games.zombieland;

import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.player.control.GameEntitySprite;
import vooga.engine.player.control.PlayerSprite;

import java.util.*;
/**
 * Zombie Class. Contains all behavior and controls for zombies in the game
 * @author Jimmy Mu, Aaron Choi, Yang Su
 *
 */
public class Zombie extends GameEntitySprite {

	private final static int ITEM_CHANCE = 20;
	private static int attackDelay = 30;
	private static int zombieAppeared =0;
	private static double zombieDamage = 0;
	private static double zombieHealth = 0;
	
	private Shooter target;
	private String directionToMove;
	private double speed;

	private int attackDelayStep;
	private String currentAttackAnimation = "";
	private Zombieland game;
	private Random random;
	

	public Zombie(String name, String stateName, AnimatedSprite down,
			AnimatedSprite up, AnimatedSprite left, AnimatedSprite right,
			Shooter hero, Zombieland zombieland, int health, int damage) {
		super(name, stateName, down);
		mapNameToSprite("Up", up);
		mapNameToSprite("Left", left);
		mapNameToSprite("Right", right);
		mapNameToSprite("Down", down);

		setHumanTarget(hero);
		directionToMove = "X";
		speed = -0.25;
		
		resetZombieCount();
		setHealth(health);
		setDamage(damage);
		resetAttackDelayStep();

		game = zombieland;
		random = new Random();
	}
	
	public static void updateZombieCount()
	{
		zombieAppeared++;
	}
	
	public static void resetZombieCount()
	{
		zombieAppeared =0;
	}
	
	public static int getZombieCount()
	{
		return zombieAppeared;
	}
	
	public static void setHealth(int health)
	{
		zombieHealth = health;
	}
	
	public static void updateHealth(int update)
	{
		zombieHealth+=update;
	}
	
	public static void updateStats(int level)
	{
		zombieHealth*=level /1.5;
		zombieDamage = zombieHealth;
	}
	
	public static double getHealth()
	{
		return zombieHealth;
	}
	

	/**
	 * Sets the target for the current zombie. Can be used if at some point we
	 * have more than one human target
	 * 
	 * @param hero
	 *            Target shooter
	 */
	private void setHumanTarget(Shooter hero) {
		target = hero;
	}

	/**
	 * Set the damage of the zombie
	 * 
	 * @param hit
	 *            damage of the zombie
	 */
	private void setDamage(int hit) {
		zombieDamage = hit;
	}

	/**
	 * Tests to see if this zombie is closer to its target in the X direction.
	 * Used to control movement of this zombie
	 * 
	 * @return true if the this zombie is closer to its target in the x
	 *         direction
	 */
	private boolean isCloserInXDirection() {
		return Math.abs(getX() - target.getX()) >= Math.abs(getY()
				- target.getY());
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
	 * Tests to see if the AttackDelayStep has reached the threshold defined by
	 * AtttackDelay. If so, the zombie is able to attack and vice versa. Used to
	 * restrict the number of attacks for a single zombie
	 * 
	 * @return true if the zombie is ready for the next attack
	 */
	public boolean isAbleToAttack() {
		return attackDelayStep == attackDelay;
	}

	/**
	 * Get the damage of the zombie
	 * 
	 * @return damage of the zombie
	 */
	public double getDamage() {
		return zombieDamage;
	}

	/**
	 * Returns the attack direction of the zombie. Here the returned values are
	 * predefined to be 0, 1, 2, or 3, representing Right, Up, Left, Bottom
	 * respectively. Used to initiate attack animations
	 * 
	 * @return attack direction
	 */
	public int getAttackDirection() {
		if (isCloserInXDirection()) {
			return ((target.getX() - getX()) > 0) ? 0 : 2;
		} else
			return ((target.getY() - getY()) > 0) ? 3 : 1;
	}

	/**
	 * Get movement directions
	 * 
	 * @return direction
	 */
	public double getDirection() {
		if (isCloserInXDirection()) {
			directionToMove = "X";
			return (target.getX() - getX()) / Math.abs(target.getX() - getX());
		}

		// Else is assumed here. Implicitly calls on iAmCloserInYDirection()
		directionToMove = "Y";
		return (target.getY() - getY()) / Math.abs(target.getY() - getY());

	}

	/**
	 * Update the zombie's health according to the damage taken
	 * 
	 * @param damage
	 *            damage taken by the zombie
	 */
	public void calculateDamage(double damage) {
		updateHealth((int) damage);
	}

	/**
	 * Update attack step. Used to force intervals between attacks
	 */
	public void updateAttactStep() {
		attackDelayStep++;
	}

	/**
	 * reset attack step
	 */
	public void resetAttackDelayStep() {
		attackDelayStep = 0;
	}

	/**
	 * Set the label for the current attack animation so it corresponds to the
	 * attack direction of the zombie
	 * 
	 * @param side
	 *            attack direction
	 */
	public void setAttackAnimation(String side) {
		currentAttackAnimation = side;
	}

	/**
	 * Keep track of heath and controls animation, movement, and item drop
	 */
	public void update(long elapsedTime) {
		super.update(elapsedTime);

		if (isHealthZero()) {
			setToCurrentSprite("ZombieDeath");
			AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
			int item = random.nextInt(100);
			// When the death animation is finished
			if (sprite.getFrame() == sprite.getFinishAnimationFrame()) {
				// Kill zombie
				setActive(false);
				// Update score
				target.updateScore(1);

				if (item < ITEM_CHANCE) {
					//Drop item
					game.addRandomItem(getX(), getY());
				}
			}
			return;
		}
		
		//Attack animation
		if (!currentAttackAnimation.isEmpty()) {
			setToCurrentSprite(currentAttackAnimation);
			AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
			if (sprite.getFrame() == sprite.getFinishAnimationFrame()) {
				currentAttackAnimation = "";
			}
			return;
		}

		// Movement control
		double direction = getDirection();
		if (directionToMove.equals("X")) {
			if (direction < 0) {
				moveX(speed);
				setToCurrentSprite("Left");
			} else {
				moveX(Math.abs(speed));
				setToCurrentSprite("Right");
			}
		}

		if (directionToMove.equals("Y")) {
			if (direction < 0) {
				moveY(speed);
				setToCurrentSprite("Up");
			} else {
				moveY(Math.abs(speed));
				setToCurrentSprite("Down");
			}
		}
	}

}
