package vooga.games.grandius.states;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.grandius.collisions.BasicCollision;

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
	
	@Override
	public void initialize() {
		//myPlayField = new PlayField();
		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>();
		this.createComets();
	}
	
	@Override
	public void update(long elapsedTime){
		
		super.update(elapsedTime);
		
	}

	/**
	 * Creates the different SpriteGroups and registers them to the playfield. Also adds the necessary SpriteGroups to the
	 * spriteGroupSpeedMap.
	 */
	private void addSpriteGroups() {
		PlayField newField = new PlayField();
		playerGroup =            		newField.addGroup(new SpriteGroup("Player"));
		projectileGroup =               newField.addGroup(new SpriteGroup("Projectile"));
		enemyProjectileGroup =  		newField.addGroup(new SpriteGroup("EnemyProjectile"));
		enemyGroup =                    newField.addGroup(new SpriteGroup("Enemy"));
		bossPartGroup =                 newField.addGroup(new SpriteGroup("BossPart"));
		bossGroup =                     newField.addGroup(new SpriteGroup("Boss"));
		missileGroup =                  newField.addGroup(new SpriteGroup("Missile"));
		blackHoleGroup =                newField.addGroup(new SpriteGroup("BlackHole"));
		backgroundGroup =       		newField.addGroup(new SpriteGroup("Background"));

		this.addUpdatePlayField(newField);
		this.addRenderPlayField(newField);
		
		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>(); //TODO get rid of the negative signs?
		spriteGroupSpeedMap.put(projectileGroup,        Resources.getDouble("ProjectileSpeed"));
		spriteGroupSpeedMap.put(enemyProjectileGroup,   -Resources.getDouble("ProjectileSpeed"));
		spriteGroupSpeedMap.put(enemyGroup,             Resources.getDouble("EnemySpeed"));
		spriteGroupSpeedMap.put(bossPartGroup,          -Resources.getDouble("BossPartSpeed"));
		spriteGroupSpeedMap.put(bossGroup,              -Resources.getDouble("BossSpeed"));
		spriteGroupSpeedMap.put(missileGroup,           Resources.getDouble("ProjectileSpeed"));
	}
	
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
	
	private void createComets() {
		PlayField newField = new PlayField();
		for (int j = 0; j < Resources.getInt("NumComets"); j++) { // create 500 background sprites
			Random valX = new Random();
			Random valY = new Random();
			double x = valX.nextDouble();
			double y = valY.nextDouble();
			Sprite backgroundSprite = new Sprite(Resources.getImage("Comet"),
					(x * Resources.getInt("cometX")), 
					(y * Resources.getInt("cometY")));
			backgroundSprite.setHorizontalSpeed(Resources.getDouble("cometVX"));
			newField.add(backgroundSprite);
		}
		this.addUpdatePlayField(newField);
		this.addRenderPlayField(newField);
	}
}