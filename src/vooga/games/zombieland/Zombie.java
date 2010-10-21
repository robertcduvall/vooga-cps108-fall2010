package vooga.games.zombieland;

import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.player.control.GameEntitySprite;

import java.util.*;

/**
 * Zombie Class. Contains all behavior and controls for zombies in the game
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class Zombie extends GameEntitySprite {

	private static int itemChance;
	private static int attackDelay;
	private static double zombieStatMultiplier;
	
	private double zombieDamage;
	private int zombieCurrentHealth;

	private Shooter target;
	private String directionToMove;
	private double speed;

	private int attackDelayStep;
	private String currentAttackAnimation;
	
	private DropThis game;

	private static String LEFT;
	private static String RIGHT;
	private static String UP;
	private static String DOWN;
	private static String ATTACKLEFT;
	private static String ATTACKRight;
	private static String ATTACKUP;
	private static String ATTACKDown;
	private static String DEATH;
	

	public Zombie(String name, String stateName, int level, Shooter player,
			DropThis currentGame) {
		super(name, stateName, ZombielandResources.getInitializedAnimatedSprite(ZombielandResources
				.getAnimation("ZombieDown")));

		AnimatedSprite down = ZombielandResources.getInitializedAnimatedSprite(ZombielandResources
				.getAnimation("ZombieDown"));
		AnimatedSprite up = ZombielandResources.getInitializedAnimatedSprite(ZombielandResources
				.getAnimation("ZombieUp"));
		AnimatedSprite left = ZombielandResources.getInitializedAnimatedSprite(ZombielandResources
				.getAnimation("ZombieLeft"));
		AnimatedSprite right = ZombielandResources.getInitializedAnimatedSprite(ZombielandResources
				.getAnimation("ZombieRight"));
		AnimatedSprite attackDown = ZombielandResources.getInitializedAnimatedSprite(ZombielandResources
				.getAnimation("ZombieAttackDown"));
		AnimatedSprite attackUp = ZombielandResources.getInitializedAnimatedSprite(ZombielandResources
				.getAnimation("ZombieAttackUp"));
		AnimatedSprite attackLeft = ZombielandResources.getInitializedAnimatedSprite(ZombielandResources
				.getAnimation("ZombieAttackLeft"));
		AnimatedSprite attackRight = ZombielandResources.getInitializedAnimatedSprite(ZombielandResources
				.getAnimation("ZombieAttackRight"));
		AnimatedSprite death = ZombielandResources.getInitializedAnimatedSprite(ZombielandResources
				.getAnimation("ZombieDeath"));

		mapNameToSprite("Down", down);
		mapNameToSprite("Up", up);
		mapNameToSprite("Left", left);
		mapNameToSprite("Right", right);
		mapNameToSprite("AttackDown", attackDown);
		mapNameToSprite("AttackUp", attackUp);
		mapNameToSprite("AttackLeft", attackLeft);
		mapNameToSprite("AttackRight", attackRight);
		mapNameToSprite("ZombieDeath", death);

		setHumanTarget(player);
		
		currentAttackAnimation="";
		attackDelay = ZombielandResources.getInt("zombieAttackDelay");
		itemChance = ZombielandResources.getInt("ITEM_CHANCE");
		zombieStatMultiplier = ZombielandResources.getDouble("zombieStatMultiplier");
		
		directionToMove = "X";
		speed = ZombielandResources.getDouble("zombieSpeed");
		zombieCurrentHealth = ZombielandResources.getInt("startZombieHealth");
		zombieDamage = ZombielandResources.getInt("startZombieDamage");
				
		updateStats(level);
		
		chooseRandomLocation();
	
		game = currentGame;
		
		this.setActive(true);
	}

	private void chooseRandomLocation() {
		setX(Math.random() * ZombielandResources.getInt("GAME_WIDTH"));
		setY(Math.random() * ZombielandResources.getInt("GAME_HEIGHT"));
	}

	public void updateStats(int level) {
		zombieCurrentHealth = (int) (zombieCurrentHealth * level / zombieStatMultiplier);
		zombieDamage = (int) (zombieDamage + level / zombieStatMultiplier);
	}

	public double getHealth() {
		return zombieCurrentHealth;
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
	public boolean isDead() {
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
	public int getDirection() {
		if (isCloserInXDirection()) {
			return ((target.getX() - getX()) > 0) ? 0 : 2;
		} else
			return ((target.getY() - getY()) > 0) ? 3 : 1;
	}

	/**
	 * Update the zombie's health according to the damage taken
	 * 
	 * @param damage
	 *            damage taken by the zombie
	 */
	public void calculateDamage(double damage) {
		zombieCurrentHealth -= damage;
	}

	/**
	 * Update attack step. Used to force intervals between attacks
	 */
	public void updateAttackStep() {
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
		if (isDead()) {
			setToCurrentSprite("ZombieDeath");
			AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
			// When the death animation is finished
			if (sprite.getFrame() == sprite.getFinishAnimationFrame()) {
				// Kill zombie
				setActive(false);
				// Update score
				target.updateScore(1);

				int item = (int) (Math.random()*100);
				if (item < itemChance) {

					((ZombielandPlayState) game.getCurrentState())
					.addRandomItem(getX(), getY());
				}
			}
			return;
		}

		// Attack animation
		if (!currentAttackAnimation.isEmpty()) {
			setToCurrentSprite(currentAttackAnimation);
			AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
			if (sprite.getFrame() == sprite.getFinishAnimationFrame()) {
				currentAttackAnimation = "";
			}
			return;
		}

		// Movement control
		int direction = getDirection();
		switch (direction) {
		case 0:
			moveX(Math.abs(speed));
			setToCurrentSprite("Right");
			break;
		case 1:
			moveY(speed);
			setToCurrentSprite("Up");
			break;
		case 2:
			moveX(speed);
			setToCurrentSprite("Left");
			break;
		case 3:
			moveY(Math.abs(speed));
			setToCurrentSprite("Down");
			break;
		}
	}

}
