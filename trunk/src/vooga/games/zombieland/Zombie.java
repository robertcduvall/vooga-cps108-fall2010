package vooga.games.zombieland;

import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.player.control.GameEntitySprite;

/**
 * Zombie Class. Contains all behavior and controls for zombies in the game
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class Zombie extends GameEntitySprite implements Constants {

	private static final int ZOMBIES_PER_LEVEL = 25;
	private static int itemChance;
	private static int attackDelay;
	private static double zombieStatMultiplier;

	private double zombieDamage;
	private int zombieCurrentHealth;

	private Shooter target;
	private double speed;

	private int attackDelayStep;
	private String currentAttackAnimation;

	private Blah game;


	public Zombie(String name, String stateName, int level, Shooter player,
			Blah currentGame) {
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

		mapNameToSprite(DOWN, down);
		mapNameToSprite(UP, up);
		mapNameToSprite(LEFT, left);
		mapNameToSprite(RIGHT, right);
		mapNameToSprite(ATTACKDOWN, attackDown);
		mapNameToSprite(ATTACKUP, attackUp);
		mapNameToSprite(ATTACKLEFT, attackLeft);
		mapNameToSprite(ATTACKRIGHT, attackRight);
		mapNameToSprite(DEATH, death);
		
		setHumanTarget(player);

		currentAttackAnimation="";
		attackDelay = ZombielandResources.getInt("zombieAttackDelay");
		itemChance = ZombielandResources.getInt("itemChance");
		zombieStatMultiplier = ZombielandResources.getDouble("zombieStatMultiplier");

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

	public static int zombiesPerLevel()
	{
		return ZOMBIES_PER_LEVEL;
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
	public String getDirection() {
		if (isCloserInXDirection()) {
			return ((target.getX() - getX()) > 0) ? RIGHT : LEFT;
		} else
			return ((target.getY() - getY()) > 0) ? DOWN : UP;
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
		String direction = getDirection();
		if(direction.equals(RIGHT)) {
			moveX(Math.abs(speed));
			setToCurrentSprite(RIGHT);
			return;
		}
		if(direction.equals(UP)) {
			moveY(speed);
			setToCurrentSprite(UP);
			return;
		}
		if(direction.equals(LEFT)) {
			moveX(speed);
			setToCurrentSprite(LEFT);
			return;
		}
		if(direction.equals(DOWN)) {
			moveY(Math.abs(speed));
			setToCurrentSprite(DOWN);
			return;
		}
	}

}
