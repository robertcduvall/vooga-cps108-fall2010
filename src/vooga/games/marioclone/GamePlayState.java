package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Randomizer;
import vooga.engine.resource.RandomizerException;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;

public class GamePlayState extends GameState {

	private ColorBackground marioBackground;
	private MarioPlayField playfield;
	private SpriteGroup tileGroup;
	private SpriteGroup marioGroup;
	private TileMap map;
	private MarioSprite mario;
	private SpriteGroup enemyGroup;
	private int width;
	private int height;
	private KeyboardControl myControl;
	private Stat<Integer> myHealth;
	private Stat<Integer> myEnemiesRemaining;
	private OverlayStat myEnemyOverlay;
	private Timer timer;
	
	private static final int FREQ_ENEMIES = 5000;
	private static final int INITIAL_HP = 100;
	private static final int NUM_ENEMIES = 1;
	
	public GamePlayState(MarioSprite mario, int width, int height) {
		this.mario = mario;
		this.width = width;
		this.height = height;
		init();
	}
	
	@Override
	public void update(long t) {
		super.update(t);
		

    	playfield.update(t);
    	mario.stop();
		enemyGroup.removeInactiveSprites();
		myEnemiesRemaining.setStat(enemyGroup.getSize()); 
		
    	if(timer.action(t)) {
			for(int j = 0; j < NUM_ENEMIES; j++) {
				Enemy enemy = new Enemy("enemy1","regular",Resources.getImage("EnemyR"),Resources.getImage("EnemyL"));
				try {
					enemy.setLocation(Randomizer.nextDouble(0,width), Randomizer.nextDouble(0,height));
				} catch (RandomizerException e) {
					e.printStackTrace();
				}
				enemyGroup.add(enemy);
			}
    	}
	}
	
//	@Override
	public void init() {
		marioBackground = new ColorBackground(Color.white);
		marioBackground.setClip(0, 0, width, height);
		playfield = new MarioPlayField();
		tileGroup = new SpriteGroup("Tile Group");
		
		marioGroup = new SpriteGroup("Mario Group");
		mario.setLocation(150, 290);
		marioGroup.add(mario);
		playfield.addGroup(marioGroup);
		
		
		enemyGroup = new SpriteGroup("Enemy Group");
		for(int j = 0; j < NUM_ENEMIES; j++) {
			Enemy enemy = new Enemy("enemy1","regular",Resources.getImage("EnemyR"),Resources.getImage("EnemyL"));
			try {
				enemy.setLocation(Randomizer.nextDouble(0,width), Randomizer.nextDouble(0,height));
			} catch (RandomizerException e) {
				e.printStackTrace();
			}
			enemyGroup.add(enemy);
		}	
		playfield.addGroup(enemyGroup);
		
		try {
			map = new TileMap(new File("src/vooga/games/marioclone/testmap.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		playfield.addTileMap(map);
		
		myHealth = new Stat<Integer>(new Integer(INITIAL_HP));
		myEnemiesRemaining = new Stat<Integer>(new Integer(enemyGroup.getSize()));

		myEnemyOverlay = new OverlayStat("Enemies Remaining: ", myEnemiesRemaining);
		myEnemyOverlay.setLocation(width - 1000, 5);

		playfield.add(myEnemyOverlay);
		
		playfield.addCollisionGroup(marioGroup, playfield.getTileMap().getTileGroup(), new MarioToTileCollision());
		playfield.addCollisionGroup(enemyGroup, playfield.getTileMap().getTileGroup(), new EnemyToTileCollision());
		playfield.addCollisionGroup(marioGroup, enemyGroup, new MarioToEnemyCollision());
		
		timer = new Timer(FREQ_ENEMIES);
		
		
	}
	
	public int getEnemiesRemaining() {
		return enemyGroup.getSize();
	}

	// Initialization is not very useful if it is called before the constructor assigns the fields.
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
        playfield.setBackground(marioBackground);
		playfield.render(g);
	}

}
