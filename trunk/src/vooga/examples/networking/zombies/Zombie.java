package vooga.examples.networking.zombies;

import java.util.Random;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.engine.util.AnimationUtil;
import vooga.examples.networking.zombies.events.AddRandomItemEvent;
import vooga.examples.networking.zombies.gamestates.PlayState;

import com.golden.gamedev.object.AnimatedSprite;

/**
 * Zombie Class. Contains all behavior and controls for zombies in the game
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
@SuppressWarnings("serial")
public class Zombie extends BetterSprite implements Constants {

	private static final int ZOMBIES_PER_LEVEL = 25;
	private static int itemChance;
	private static int attackDelay;
	private static double zombieStatMultiplier;

	private double zombieDamage;
	private int zombieCurrentHealth;

	private Shooter[] targets;
	private AddRandomItemEvent randomItemEvent;
	private double speed;

	private int attackDelayStep;
	private String currentAttackAnimation;

	public Zombie(String name, int level, Shooter[] players, PlayState state, long seed) {

		super(name, AnimationUtil.getInitializedAnimatedSprite(Resources
				.getAnimation(ZOMBIE_DOWN)));

		addSprite(ZOMBIE_DOWN, initializeSprites(ZOMBIE_DOWN));
		addSprite(ZOMBIE_UP, initializeSprites(ZOMBIE_UP));
		addSprite(ZOMBIE_LEFT, initializeSprites(ZOMBIE_LEFT));
		addSprite(ZOMBIE_RIGHT, initializeSprites(ZOMBIE_RIGHT));
		addSprite(ATTACKDOWN, initializeSprites(ATTACKDOWN));
		addSprite(ATTACKUP, initializeSprites(ATTACKUP));
		addSprite(ATTACKLEFT, initializeSprites(ATTACKLEFT));
		addSprite(ATTACKRIGHT, initializeSprites(ATTACKRIGHT));
		addSprite(DEATH, initializeSprites(DEATH));

		setHumanTarget(players);

		currentAttackAnimation = "";
		attackDelay = Resources.getInt("zombieAttackDelay");
		itemChance = Resources.getInt("itemChance");
		zombieStatMultiplier = Resources.getDouble("zombieStatMultiplier");

		speed = Resources.getDouble("zombieSpeed");
		zombieCurrentHealth = Resources.getInt("startZombieHealth");
		zombieDamage = Resources.getInt("startZombieDamage");

		updateStats(level);

		Random random = new Random(seed);
		chooseRandomLocation(random);

		this.setActive(true);
	}

	private AnimatedSprite initializeSprites(String sprite) {
		return AnimationUtil.getInitializedAnimatedSprite(Resources
				.getAnimation(sprite));
	}

	private void chooseRandomLocation(Random random) {
		setX(random.nextInt(GAME_WIDTH));
		setY(random.nextInt(GAME_HEIGHT));
	}

	public void updateStats(int level) {
		zombieCurrentHealth = (int) (zombieCurrentHealth * level / zombieStatMultiplier);
		zombieDamage = (int) (zombieDamage + level / zombieStatMultiplier);
	}

	public double getHealth() {
		return zombieCurrentHealth;
	}

	public static int zombiesPerLevel() {
		return ZOMBIES_PER_LEVEL;
	}

	/**
	 * Sets the target for the current zombie. Can be used if at some point we
	 * have more than one human target
	 * 
	 * @param hero
	 *            Target shooter
	 */
	private void setHumanTarget(Shooter[] heros) {
		targets = heros;
	}

	/**
	 * Tests to see if this zombie is closer to its target in the X direction.
	 * Used to control movement of this zombie
	 * 
	 * @return true if the this zombie is closer to its target in the x
	 *         direction
	 */
	private boolean isCloserInXDirection(Shooter target) {
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
	 * Returns the attack direction of the zombie. The up and down is reversed
	 * because the coordinate system has its positive y axis in the down
	 * direction, so everything's flipped when it comes to y.
	 * 
	 * @return attack direction
	 */
	public String getDirection() {
		Shooter target = null;
		double distance = 100000;
		for(Shooter possibleTarget : targets){
			double possibleDistance = Math.sqrt(Math.pow(Math.abs(getX() - possibleTarget.getX()), 2) + Math.pow(Math.abs(getY() - possibleTarget.getY()), 2));
			if(possibleDistance < distance){
				distance = possibleDistance;
				target = possibleTarget;
			}
		}
		if (isCloserInXDirection(target)) {
			return ((target.getX() - getX()) > 0) ? ZOMBIE_RIGHT : ZOMBIE_LEFT;
		} else
			return ((target.getY() - getY()) > 0) ? ZOMBIE_DOWN : ZOMBIE_UP;
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

		// check death and death animation
		if (isDead()) {
			performDeathAnimation();
			return;
		}

		// Attack animation
		if (!currentAttackAnimation.isEmpty()) {
			setZombieAnimation();
			return;
		}

		// update movement
		updateMovement();
	}

	private void updateMovement() {
		/*
		 * Movement control Speed is inherently negative. This is due to the
		 * coordinate system: the positive x axis is to the right and the
		 * positive y axis is down. So to move left or up, the speed remains
		 * negative, to move right or down, the speed needs to be positive.
		 */
		String direction = getDirection();
		if (direction.equals(ZOMBIE_RIGHT)) {
			moveX(Math.abs(speed));
			setAsRenderedSprite(ZOMBIE_RIGHT);
			return;
		}
		if (direction.equals(ZOMBIE_UP)) {
			moveY(speed);
			setAsRenderedSprite(ZOMBIE_UP);
			return;
		}
		if (direction.equals(ZOMBIE_LEFT)) {
			moveX(speed);
			setAsRenderedSprite(ZOMBIE_LEFT);
			return;
		}
		if (direction.equals(ZOMBIE_DOWN)) {
			moveY(Math.abs(speed));
			setAsRenderedSprite(ZOMBIE_DOWN);
			return;
		}
	}

	private void setZombieAnimation() {
		setAsRenderedSprite(currentAttackAnimation);
		AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
		if (sprite.getFrame() == sprite.getFinishAnimationFrame()) {
			currentAttackAnimation = "";
		}
	}

	/**
	 * This method sets up the drop item event
	 * 
	 * @param listener
	 */
	public void setDropItemListener(IEventHandler listener) {
		randomItemEvent = (AddRandomItemEvent) listener;
	}

	private void performDeathAnimation() {
		setAsRenderedSprite("ZombieDeath");
		AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
		// When the death animation is finished
		if (sprite.getFrame() == sprite.getFinishAnimationFrame()) {
			// Kill zombie
			setActive(false);
			// Update score
			for(Shooter target : targets){
				target.updateScore(1);
			}

			int item = (int) (Math.random() * 100);
			if (item < itemChance) {
				randomItemEvent.addRandomItem(getX(), getY());
			}
		}
		return;
	}

}
