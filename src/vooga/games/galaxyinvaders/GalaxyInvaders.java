package vooga.games.galaxyinvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import vooga.engine.event.*;
import vooga.engine.overlay.*;
import vooga.engine.player.control.*;
import vooga.engine.resource.*;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ColorBackground;


/**
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class GalaxyInvaders extends Game {
	
	private static final int LIVES = 5;
	
	private static final int PLAY = 1;
	private static final int LOST = 2;

	private Background bg;
	private PlayerSprite ship;
    private Levels levels;
	private SpriteGroup torpedos;
	private SpriteGroup enemyTorpedos;
	private SpriteGroup enemies;
	private SpriteGroup players;
	private SpriteGroup items;
	private SpriteGroup blockades;
	private EventManager event;
	private CollisionManager torpedoCollider;
	private CollisionManager torpedoPlayerCollider;
	private CollisionManager itemPlayerCollider;
	private CollisionManager torpedoBlockCollider;
	private StatInt scoreStat;
	private int state;
//	private StatInt livesStat;
//	private OverlayManager overlayManager;
	private OverlayStatColors scoreOverlay;
	//	private OverlayStat livesOverlay;
	
    private GameFontManager gameFontManager;
    private GameFont myFont;

	public void initResources() {
		gameFontManager = new GameFontManager();
		Font f = new Font("sansserif", Font.BOLD, 20);
		myFont = gameFontManager.getFont(f);
		state = PLAY;
		bg = new ColorBackground(Color.BLACK, 700, 800);
		ship = new PlayerSprite("p1", "default", new Sprite(getImage("img/ship.gif"), getWidth()/2, getHeight()-100));
		ship.setLives(LIVES);
		items = new SpriteGroup("items");
		torpedos = new SpriteGroup("shots");
		enemies = new SpriteGroup("enemies");
		players = new SpriteGroup("players");
		blockades = new SpriteGroup("blockades");
		//		overlayManager = new OverlayManager(this, 20, 20);
		scoreStat = new StatInt(0);
		//		livesStat = new StatInt(5);
		scoreOverlay = new OverlayStatColors("Score", scoreStat,  new Font("mine", Font.PLAIN, 22), Color.RED);
		//		livesOverlay = new OverlayStat("Lives", livesStat);
		//		overlayManager.addOverlay(scoreOverlay, 20, 20);
		//		overlayManager.addOverlay(livesOverlay, 80, 20);
		levels = new Levels();
		try {
			levels.createLevels();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		initEnemies();
		initBlocks();
		event = new EventManager();
		players.add(ship);
		enemyTorpedos = new SpriteGroup("enemyTorpedos");
		torpedoCollider = new TorpedoEnemyCollider(this);
		torpedoCollider.setCollisionGroup(torpedos, enemies);
		torpedoPlayerCollider = new TorpedoPlayerCollider(this);
		torpedoPlayerCollider.setCollisionGroup(enemyTorpedos, players);
		itemPlayerCollider = new ItemPlayerCollider();
		itemPlayerCollider.setCollisionGroup(items, players);
		torpedoBlockCollider = new TorpedoBlockCollider();
		torpedoBlockCollider.setCollisionGroup(enemyTorpedos, blockades);
	}
	
	public void initEnemies() {	
		for (int i=0; i<12; i++){
            EnemySprite e = new EnemySprite("", "default", new Sprite(getImage("img/enemy1.gif"), (i%4)*50, ((int)(i/4))*50), levels.getLevelPath());
            Sprite damaged = new Sprite(getImage("img/enemy1damage.png"));
			e.mapNameToSprite("damaged", damaged);
            enemies.add(e);
        }    
	}

	public void initBlocks() {
		for(int i = 100; i<getWidth(); i+=200) {
			BlockadeSprite b = new BlockadeSprite("", "default", new Sprite(getImage("img/barrier.png"), i, 600));
			blockades.add(b);
		}
	}
	public void render(Graphics2D g) {
		if(state == PLAY) {
			bg.render(g);
			ship.render(g);
			enemies.render(g);
			items.render(g);
			torpedos.render(g);
			enemyTorpedos.render(g);
			scoreOverlay.render(g);
			blockades.render(g);
			
	        myFont.drawString(g, " Lives: " + ship.getLives(),  getWidth() - 200, 8);
//			livesOverlay.render(g);
//			overlayManager.render(g);
		}

		if(state == LOST) {
			bg.render(g);
			ship.render(g);
			enemies.render(g);
			scoreOverlay.render(g);
			myFont.drawString(g, " Lives: " + ship.getLives(),  getWidth() - 200, 8);
			myFont.drawString(g, "GAME OVER!      CLICK TO PLAY AGAIN",  getWidth()/4-50, getHeight()/2);
		}
	}

	public void update(long time) {
		if(state == PLAY) {
			int bombSeed;
			try {
				bombSeed = Randomizer.nextInt(1000);
			} catch (RandomizerException e) {
				e.printStackTrace();
				bombSeed = 0;
			}

			if(bombSeed>994) {
				spawnEnemyBomb();
			}
			
			int itemSeed;
			try {
				itemSeed = Randomizer.nextInt(1000);
			} catch (RandomizerException e) {
				e.printStackTrace();
				itemSeed = 0;
			}

			if(itemSeed>997) {
				spawnHealth();
			}

			bg.update(time);
			ship.update(time);
			enemies.update(time);
			torpedos.update(time);
			items.update(time);
			blockades.update(time);
			enemyTorpedos.update(time);
			scoreOverlay.update(time);
			//		livesOverlay.update(time);

			//		overlayManager.update(time);

			torpedoCollider.checkCollision();
			torpedoPlayerCollider.checkCollision();
			itemPlayerCollider.checkCollision();
			torpedoBlockCollider.checkCollision();

			if(keyDown(KeyEvent.VK_LEFT)) {
				if(ship.getX()>0-15)  moveLeft();
			}
			if(keyDown(KeyEvent.VK_RIGHT)) {
				if(ship.getX()<getWidth()-45)   moveRight();
			}
			if(keyPressed(KeyEvent.VK_SPACE)) {
				fire();
			}


			if(enemies.getSize()==0) {
				if(levels.getLevel()<3) {
					levels.nextLevel();
					initEnemies();
				}
				else {
					state = LOST;
				}
			}

			if(ship.getLives()<=0) {
				state = LOST;
			}
		}

		if(state == LOST) {
			bg.update(time);
			ship.update(time);
			scoreOverlay.update(time);
			if(click()) {
				state = PLAY;
			}
		}
	}
	
	private void spawnHealth() {
		Sprite temp = new Sprite(getImage("img/health.png"), getWidth()/2, 0);
		temp.setVerticalSpeed(.1);
		items.add(temp);
	}
	
	private void spawnEnemyBomb() {
		int enemySeed;
		Sprite[] enemySprites = enemies.getSprites();
		int count = 0;
		for(Sprite s : enemySprites) {
			if(s!=null) {
				if(s.isActive())
					count++;
			}
		}
		if(count>0) {
			try {
				enemySeed = Randomizer.nextInt(count);
			} catch (RandomizerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				enemySeed = 0;
			}
			EnemySprite enemy = (EnemySprite) enemySprites[enemySeed];
			Sprite temp = new Sprite(getImage("img/torpedo.png"), enemy.getX()+25, enemy.getY()+30);
			temp.setSpeed(0, .5);
			enemyTorpedos.add(temp);
		}
	}

	public void moveLeft() {
		ship.move(-5, 0);
	}
	
	public void moveRight() {
		ship.move(5, 0);
	}
	
	public void fire() {
		Sprite temp = new Sprite(getImage("img/torpedo.png"), ship.getX()+25, ship.getY()-35);
		temp.setSpeed(0, -.8);
		torpedos.add(temp);
	}
	
	public void increasePlayerScore() {
		scoreStat.addTo(1);
	}
	
	public void reverseVelocity(Sprite[] enemySprites) {
		for(Sprite s : enemySprites) {
			if(s!=null) {
				s.setHorizontalSpeed(s.getHorizontalSpeed()*-1);
			}
		}
	}
	

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new GalaxyInvaders(), new Dimension(700,800), false);
		game.start();
	}


}
