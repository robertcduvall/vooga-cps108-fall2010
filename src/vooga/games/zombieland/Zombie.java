package vooga.games.zombieland;

import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.util.AnimationUtil;
import vooga.games.zombieland.gamestates.ZombielandPlayState;

/**
 * Zombie Class. Contains all behavior and controls for zombies in the game
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class Zombie extends BetterSprite implements Constants {

	private static final int ZOMBIES_PER_LEVEL = 25;
//	private static int itemChance;
//	private static int attackDelay;
//	private static double zombieStatMultiplier;
//
//	private double zombieDamage;
//	private int zombieCurrentHealth;

	private Shooter target;
//	private double speed;
//
//	private int attackDelayStep;
//	private String currentAttackAnimation;

	private Blah game;

	
	
	public Zombie() {
		super();

		AnimatedSprite down = AnimationUtil
				.getInitializedAnimatedSprite(Resources
						.getAnimation(ZOMBIE_DOWN));
		AnimatedSprite up = AnimationUtil
				.getInitializedAnimatedSprite(Resources
						.getAnimation(ZOMBIE_UP));
		AnimatedSprite left = AnimationUtil
				.getInitializedAnimatedSprite(Resources
						.getAnimation(ZOMBIE_LEFT));
		AnimatedSprite right = AnimationUtil
				.getInitializedAnimatedSprite(Resources
						.getAnimation(ZOMBIE_RIGHT));
		AnimatedSprite attackDown = AnimationUtil
				.getInitializedAnimatedSprite(Resources
						.getAnimation(ATTACKDOWN));
		AnimatedSprite attackUp = AnimationUtil
				.getInitializedAnimatedSprite(Resources
						.getAnimation(ATTACKUP));
		AnimatedSprite attackLeft = AnimationUtil
				.getInitializedAnimatedSprite(Resources
						.getAnimation(ATTACKLEFT));
		AnimatedSprite attackRight = AnimationUtil
				.getInitializedAnimatedSprite(Resources
						.getAnimation(ATTACKRIGHT));
		AnimatedSprite death = AnimationUtil
				.getInitializedAnimatedSprite(Resources
						.getAnimation(DEATH));

		addSprite(ZOMBIE_DOWN, down);
		addSprite(ZOMBIE_UP, up);
		addSprite(ZOMBIE_LEFT, left);
		addSprite(ZOMBIE_RIGHT, right);
		addSprite(ATTACKDOWN, attackDown);
		addSprite(ATTACKUP, attackUp);
		addSprite(ATTACKLEFT, attackLeft);
		addSprite(ATTACKRIGHT, attackRight);
		addSprite(DEATH, death);

//		setHumanTarget(player);

//		currentAttackAnimation = "";
//		attackDelay = Resources.getInt("zombieAttackDelay");
//		itemChance = Resources.getInt("itemChance");
//		zombieStatMultiplier = Resources
//				.getDouble("zombieStatMultiplier");
//
//		speed = Resources.getDouble("zombieSpeed");
//		zombieCurrentHealth = Resources.getInt("startZombieHealth");
//		zombieDamage = Resources.getInt("startZombieDamage");

		updateStats(1);

		chooseRandomLocation();

//		game = currentGame;

		this.setActive(true);
	}

	public void setGame(Blah currentGame)
	{
		game = currentGame;
	}
	
	private void chooseRandomLocation() {
		setX(Math.random() * GAME_WIDTH);
		setY(Math.random() * GAME_HEIGHT);
	}

	public void updateStats(int level) {
		setStat("zombieCurrentHealth", new Stat<Integer>((int) ((Integer)(readStat("zombieCurrentHealth")) * level / (Integer) readStat("zombieStatMultiplier"))));
		setStat("zombieDamage", new Stat<Integer>( (int) ((Integer) readStat("zombieDamage") + level / (Integer) readStat("zombieStatMultiplier"))));
	}

	public double getHealth() {
		return (Double) readStat("zombieCurrentHealth");
	}

	public static int zombiesPerLevel() {
		return ZOMBIES_PER_LEVEL;
	}

	public Object readStat(String stat)
	{
		return getStat(stat).getStat();
	}
	
	/**
	 * Sets the target for the current zombie. Can be used if at some point we
	 * have more than one human target
	 * 
	 * @param hero
	 *            Target shooter
	 */
	public void setHumanTarget(Shooter hero) {
		target = hero;
	}

	public Shooter getTarget() {
		return target;
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
		return readStat("attackDelayStep") == readStat("attackDelay");
	}

	/**
	 * Get the damage of the zombie
	 * 
	 * @return damage of the zombie
	 */
	public double getDamage() {
		return (Double) readStat("zombieDamage");
	}

	/**
	 * Returns the attack direction of the zombie. The up and down is reversed
	 * because the coordinate system has its positive y axis in the down
	 * direction, so everything's flipped when it comes to y.
	 * 
	 * @return attack direction
	 */
	public String getDirection() {
		if (isCloserInXDirection()) {
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
		setStat("zombieCurrentHealth", new Stat<Double>((Double) readStat("zombieCurrentHealth") - damage));
	}

	/**
	 * Update attack step. Used to force intervals between attacks
	 */
	public void updateAttackStep() {
		setStat("attackDelayStep", new Stat<Integer>((Integer) readStat("attackDelayStep")+1));
	}

	/**
	 * reset attack step
	 */
	public void resetAttackDelayStep() {
		setStat("attackDelayStep", new Stat<Integer>(0));
	}

	/**
	 * Set the label for the current attack animation so it corresponds to the
	 * attack direction of the zombie
	 * 
	 * @param side
	 *            attack direction
	 */
	public void setAttackAnimation(String side) {
		setStat("currentAttackAnimation", new Stat<String> (side));
	}

	/**
	 * Keep track of heath and controls animation, movement, and item drop
	 */
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (isDead()) {
			setAsRenderedSprite("ZombieDeath");
			AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
			// When the death animation is finished
			if (sprite.getFrame() == sprite.getFinishAnimationFrame()) {
				// Kill zombie
				setActive(false);
				// Update score
				target.updateScore(1);

				int item = (int) (Math.random() * 100);
				if (item < (Integer) readStat("itemChance")) {

					((ZombielandPlayState) game.getPlayGameState())
							.addRandomItem(getX(), getY());
				}
			}
			return;
		}

		// Attack animation
		if (!((String) readStat("currentAttackAnimation")).isEmpty()) {
			setAsRenderedSprite((String) readStat("currentAttackAnimation"));
			AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
			if (sprite.getFrame() == sprite.getFinishAnimationFrame()) {
				setStat("currentAttackAnimation", new Stat<String>(""));
			}
			return;
		}

		/*
		 * Movement control Speed is inherently negative. This is due to the
		 * coordinate system: the positive x axis is to the right and the
		 * positive y axis is down. So to move left or up, the speed remains
		 * negative, to move right or down, the speed needs to be positive.
		 */
		String direction = getDirection();
		if (direction.equals(ZOMBIE_RIGHT)) {
			moveX(Math.abs((Integer)readStat("speed")));
			setAsRenderedSprite(ZOMBIE_RIGHT);
			return;
		}
		if (direction.equals(ZOMBIE_UP)) {
			moveY((Integer)readStat("speed"));
			setAsRenderedSprite(ZOMBIE_UP);
			return;
		}
		if (direction.equals(ZOMBIE_LEFT)) {
			moveX((Integer)readStat("speed"));
			setAsRenderedSprite(ZOMBIE_LEFT);
			return;
		}
		if (direction.equals(ZOMBIE_DOWN)) {
			moveY(Math.abs((Integer)readStat("speed")));
			setAsRenderedSprite(ZOMBIE_DOWN);
			return;
		}
	}

}