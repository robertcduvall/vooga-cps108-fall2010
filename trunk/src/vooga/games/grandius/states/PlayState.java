package vooga.games.grandius.states;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.grandius.Player;
import vooga.games.grandius.collisions.BasicCollision;
import vooga.games.grandius.enemy.boss.reacher.Reacher;
import vooga.games.grandius.enemy.boss.reacher.ReacherEye;
import vooga.games.grandius.enemy.common.Zipster;
import vooga.games.grandius.weapons.BlackHole;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class PlayState extends GameState {
	
	//private PlayField myPlayField;
	private Map<SpriteGroup, Double> spriteGroupSpeedMap;
	private SpriteGroup playerGroup;
	private SpriteGroup projectileGroup;
	private SpriteGroup enemyProjectileGroup;
	private SpriteGroup enemyGroup;
	private SpriteGroup bossPartGroup;
	private SpriteGroup bossGroup;  
	private SpriteGroup missileGroup;
	private SpriteGroup blackHoleGroup;
	private SpriteGroup backgroundGroup; 
	private Player player;
	private boolean reacherShieldsDepleted;
	private boolean isInvincible = false;
	private boolean skipLevel = false;

	@Override
	public void initialize() {
		//myPlayField = new PlayField();
		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>();
		addSpriteGroups(); //Important that background group is always created first.
		createComets();
		backgroundGroup.add(new BetterSprite(Resources.getImage("BG")));	
		addPlayer();	
	}
	
	private void addPlayer() {
		int playerInitialX = Resources.getInt("PlayerInitialX");
		int playerInitialY = Resources.getInt("PlayerInitialY");
		BetterSprite shipSprite = new BetterSprite(Resources.getImage("PlayerShipSingle"),playerInitialX,playerInitialY);
		player = new Player("alive", shipSprite);
		playerGroup.add(player);		
	}

	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
//		updateScreenSprites(); //TODO is this method needed?
		//updateEntities();
		//checkBossParts();
		//checkLevelComplete();
	}

	//TODO create PlayField for levels using Game.initLevel()
	/**
	 * Creates the different SpriteGroups and registers them to the PlayState's PlayField. 
	 * Also adds the necessary SpriteGroup entries to the spriteGroupSpeedMap.
	 */
	private void addSpriteGroups() {
		PlayField newField = new PlayField();
		backgroundGroup =       		newField.addGroup(new SpriteGroup("Background"));
		playerGroup =            		newField.addGroup(new SpriteGroup("Player"));
		projectileGroup =               newField.addGroup(new SpriteGroup("Projectile"));
		enemyProjectileGroup =  		newField.addGroup(new SpriteGroup("EnemyProjectile"));
		enemyGroup =                    newField.addGroup(new SpriteGroup("Enemy"));
		bossPartGroup =                 newField.addGroup(new SpriteGroup("BossPart"));
		bossGroup =                     newField.addGroup(new SpriteGroup("Boss"));
		missileGroup =                  newField.addGroup(new SpriteGroup("Missile"));
		blackHoleGroup =                newField.addGroup(new SpriteGroup("BlackHole"));
		this.addUpdatePlayField(newField);
		this.addRenderPlayField(newField);
		
		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>(); //TODO get rid of the negative signs?
		spriteGroupSpeedMap.put(projectileGroup,        Resources.getDouble("projectileSpeed"));
		spriteGroupSpeedMap.put(enemyProjectileGroup,   -Resources.getDouble("projectileSpeed"));
		spriteGroupSpeedMap.put(enemyGroup,             Resources.getDouble("enemySpeed"));
		spriteGroupSpeedMap.put(bossPartGroup,          -Resources.getDouble("bossPartSpeed"));
		spriteGroupSpeedMap.put(bossGroup,              -Resources.getDouble("bossSpeed"));
		spriteGroupSpeedMap.put(missileGroup,           Resources.getDouble("projectileSpeed"));
	}
	
	/**
	 * Adds the necessary CollisionGroups to the PlayState.
	 * @param collisions
	 */
	public void addCollisions(List<BasicCollision> collisions) { //TODO find a way to make this method private?
		PlayField newField = new PlayField();
		newField.addCollisionGroup(playerGroup, enemyGroup, collisions.get(0));
		newField.addCollisionGroup(playerGroup, bossPartGroup, collisions.get(1));
		newField.addCollisionGroup(playerGroup, bossGroup, collisions.get(2));
		newField.addCollisionGroup(projectileGroup, enemyGroup, collisions.get(3));
		newField.addCollisionGroup(projectileGroup, bossPartGroup, collisions.get(4));
		newField.addCollisionGroup(playerGroup, enemyProjectileGroup, collisions.get(5));
		newField.addCollisionGroup(projectileGroup, bossGroup, collisions.get(6));
		newField.addCollisionGroup(missileGroup, enemyGroup, collisions.get(7));
		newField.addCollisionGroup(missileGroup, bossPartGroup, collisions.get(8));
		newField.addCollisionGroup(missileGroup, bossGroup, collisions.get(9));
		newField.addCollisionGroup(blackHoleGroup, enemyGroup, collisions.get(10));
		this.addUpdatePlayField(newField); //TODO right way to do this?
		this.addRenderPlayField(newField);
	}
	
	/**
	 * Creates background "comet sprites" to provide illusion of movement through space.
	 */
	private void createComets() {
		PlayField newField = new PlayField();
		for (int j = 0; j < Resources.getInt("NumComets"); j++) {
			Random valX = new Random();
			Random valY = new Random();
			double x = valX.nextDouble();
			double y = valY.nextDouble();
			BetterSprite backgroundSprite = new BetterSprite(Resources.getImage("Comet"),
					(x * Resources.getInt("cometX")), 
					(y * Resources.getInt("cometY")));
			backgroundSprite.setHorizontalSpeed(Resources.getDouble("cometVX"));
			newField.add(backgroundSprite);
		}
		this.addUpdatePlayField(newField);
		this.addRenderPlayField(newField);
	}
	
//	/**
//	 * Updates the various enemies that are on screen.
//	 */
//	private void updateScreenSprites() {
//		ArrayList<ArrayList<BetterSprite>> currentSprites = levelManager
//				.currentLevel();
//		updateSpriteGroup(enemyGroup, currentSprites, 0);
//		updateSpriteGroup(bossPartGroup, currentSprites, 1);
//		updateSpriteGroup(bossGroup, currentSprites, 2);
//	}

//	/**
//	 * Utility method used in updateScreenSprites().
//	 */
//	private void updateSpriteGroup(SpriteGroup spriteGroup,
//			ArrayList<ArrayList<BetterSprite>> currentSprites, int index) {
//		spriteGroup.clear();
//		Collection<BetterSprite> screenSprites = currentSprites.get(index);
//		for (BetterSprite s : screenSprites) {
//			if (s == null)
//				break;
//			spriteGroup.add(s);
//		}
//	}
	
	private void updateEntities() {
//		updateScreenSprites();
		player.setVerticalSpeed(0);
		for (Sprite as : enemyGroup.getSprites()) {
			if (as == null)
				break;
			if (as instanceof Zipster) {
				if (((Zipster) (as)).willFire(player)) {
					enemyProjectileGroup.add(((Zipster) (as)).fireLaser());
//					playSound(Resources.getSound("ZipsterLaserSound"));
				}
				as.setHorizontalSpeed(-((Zipster) (as)).getSpeed());
				((Zipster) as).setAsRenderedSprite("SpinningZipsterSprite");
//				((Zipster) as).setImages(new BufferedImage[] { Resources
//						.getAnimation("SpinningZipster")[((Zipster) as)
//						.getSpin()] });
				if (!((Zipster) as).isProximateToBlackHole())
					((Zipster) as).setSpin(0);
				((Zipster) as).setProximateToBlackHole(false);
			}
		}
	
		for (Sprite bp : bossPartGroup.getSprites()) {
			if (bp == null)
				break;
			if (bp instanceof ReacherEye) {
				if (((ReacherEye) (bp)).willFire(player)) {
					enemyProjectileGroup.add(((ReacherEye) (bp)).fireBeam());
//					playSound(Resources.getSound("ReacherEyeBeamSound"));
				}
				bp.setHorizontalSpeed(-((ReacherEye) (bp)).getSpeed());
			}
		}
		for (Sprite b : bossGroup.getSprites()) {
			if (b == null)
				break;
			if (b instanceof Reacher) {
				if (((Reacher) (b)).topBeamWillFire(player)) {
					enemyProjectileGroup.add(((Reacher) (b)).fireTopBeam());
//					playSound(Resources.getSound("ReacherBeamSound"));
				}
				if (((Reacher) (b)).bottomBeamWillFire(player)) {
					enemyProjectileGroup.add(((Reacher) (b)).fireBottomBeam());
//					playSound(Resources.getSound("ReacherBeamSound"));
				}
				if (((Reacher) (b)).redRayWillFire(player)) {
					enemyProjectileGroup.add(((Reacher) (b)).fireRedRay());
//					playSound(Resources.getSound("ReacherRedRaySound"));
				}
				b.setHorizontalSpeed(-((Reacher) (b)).getSpeed());
			}
		}

		double bulletSpeed = Resources.getDouble("bulletSpeed");
		resetSpriteSpeed(projectileGroup, bulletSpeed);
		resetSpriteSpeed(enemyProjectileGroup, -1 * bulletSpeed);
		resetSpriteSpeed(missileGroup, bulletSpeed);

		for (Sprite h : blackHoleGroup.getSprites()) {
			if (h == null)
				break;
			if (h.isActive()) {
				((BlackHole) h).suckEnemies(enemyGroup);
				((BlackHole) h).setPlayerCompensationSpeed(0);
			}
		}

		//TODO this code needs to be moved
//		if (keyDown(KeyEvent.VK_LEFT)) {
//			for (SpriteGroup sg : spriteGroupSpeedMap.keySet()) {
//				moveSpriteGroup(sg, "right", spriteGroupSpeedMap.get(sg));
//			}
//			for (BetterSprite h : blackHoleGroup.getSprites()) {
//				if (h == null)
//					break;
//				((BlackHole) h).setPlayerCompensationSpeed(1 * PLAYER_SPEED);
//			}
//		}
//		if (keyDown(KeyEvent.VK_RIGHT)) {
//			for (SpriteGroup sg : spriteGroupSpeedMap.keySet()) {
//				moveSpriteGroup(sg, "left", spriteGroupSpeedMap.get(sg));
//			}
//			for (BetterSprite h : blackHoleGroup.getSprites()) {
//				if (h == null)
//					break;
//				((BlackHole) h).setPlayerCompensationSpeed(-1 * PLAYER_SPEED);
//			}
//		}
//		if (keyDown(KeyEvent.VK_DOWN)) {
//			playerSprite.setVerticalSpeed(PLAYER_SPEED);
//		}
//		if (keyDown(KeyEvent.VK_UP)) {
//			playerSprite.setVerticalSpeed(-1 * PLAYER_SPEED);
//		}
	}
	
	private void resetSpriteSpeed(SpriteGroup spriteGroup, double newSpeed) {
		for (Sprite s : spriteGroup.getSprites()) {
			if (s == null)
				break;
			s.setHorizontalSpeed(newSpeed);
		}
	}

	/**
	 * Utility method used in updateEntities().
	 */
	private void moveSpriteGroup(SpriteGroup spriteGroup, String direction,
			double offset) {
		if (direction.equals("right")) {
			for (Sprite s : spriteGroup.getSprites()) {
				if (s == null)
					break;
				s.setHorizontalSpeed(1 * Resources.getDouble("playerSpeed") + offset);
			}
		} else if (direction.equals("left")) {
			for (Sprite s : spriteGroup.getSprites()) {
				if (s == null)
					break;
				s.setHorizontalSpeed(-1 * Resources.getDouble("playerSpeed") + offset);
			}
		}
	}
	
	private void checkBossParts() {
		if (bossGroup.isActive()) {
			// Change this method to accomodate more than one level
			Sprite reacherSprite = bossGroup.getSprites()[0];
			int reacherEyesDestroyed = 0;
			for (Sprite bp : bossPartGroup.getSprites()) {
				if (bp == null)
					break;
				if (bp instanceof ReacherEye && !bp.isActive())
					reacherEyesDestroyed++;
			}
			if (reacherEyesDestroyed == 1) {
				((Reacher) reacherSprite).setImage(Resources.getImage("ReacherShieldsMedium"));
//				((Reacher) (reacherSprite))
//						.setImage(new BufferedImage[] { Resources
//								.getAnimation("Reacher")[1] });
			} else if (reacherEyesDestroyed == 3) {
				((Reacher) reacherSprite).setImage(Resources.getImage("ReacherShieldsLow"));
//				((Reacher) (reacherSprite))
//						.setImage(new BufferedImage[] { Resources
//								.getAnimation("Reacher")[2] });
			} else if (reacherEyesDestroyed == 5 && !reacherShieldsDepleted) {
				// Deplete shields, but do not reset to the "depleted shields"
				// image more than once (the next
				// images should be of the Reacher's status becoming yellow,
				// then red)
				((Reacher) reacherSprite).setImage(Resources.getImage("ReacherShieldsDepleted"));
//				((Reacher) (reacherSprite))
//						.setImage(new BufferedImage[] { Resources
//								.getAnimation("Reacher")[3] });
				reacherShieldsDepleted = true;
				((Reacher) (reacherSprite)).setVulnerable(true);
			}
		}
	}
	
	/**
	 * Checks to see if a level has been completed (all the enemies have been
	 * cleared, or the user has used the skip level cheat code.
	 */
	private boolean checkLevelComplete() {
		if (skipLevel) {
			skipLevel = false;
			return true;
		}
		for (Sprite s : enemyGroup.getSprites()) {
			if (s.isActive()) {
				return false;
			}
		}
		for (Sprite s : bossPartGroup.getSprites()) {
			if (s.isActive()) {
				return false;
			}
		}
		for (Sprite s : bossGroup.getSprites()) {
			if (s.isActive()) {
				return false;
			}
		}
		return true;
	}
	
	
}