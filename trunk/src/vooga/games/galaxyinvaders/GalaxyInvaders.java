package vooga.games.galaxyinvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

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

	private Background bg;
	private PlayerSprite ship;
	private Sprite enemy;
	private SpriteGroup torpedos;
	private SpriteGroup enemyTorpedos;
	private SpriteGroup enemies;
	private SpriteGroup players;
//	private ResourceHandler resources;
//	private KeyboardControl kb;
	private CollisionManager torpedoCollider;
	private CollisionManager torpedoPlayerCollider;
	
	public void initResources() {
//		resources = new ResourceHandler();
		bg = new ColorBackground(Color.BLACK, 700, 800);
		ship = new PlayerSprite("p1", "default", new Sprite(getImage("img/ship.gif"), getWidth()/2, getHeight()-100));
		ship.setLives(LIVES);
		torpedos = new SpriteGroup("shots");
		enemies = new SpriteGroup("enemies");
		players = new SpriteGroup("players");
		initEnemies();
		players.add(ship);
		enemyTorpedos = new SpriteGroup("enemyTorpedos");
		torpedoCollider = new TorpedoEnemyCollider();
		torpedoCollider.setCollisionGroup(torpedos, enemies);
		torpedoPlayerCollider = new TorpedoPlayerCollider(this);
		torpedoPlayerCollider.setCollisionGroup(enemyTorpedos, players);
//		kb = new KeyboardControl(ship, this);
//		kb.addInput(KeyEvent.VK_LEFT, "moveLeft", "vooga.games.galaxyinvaders.GalaxyInvaders", null);
	}
	
	public void initEnemies() {
		
		for(int k=0; k<getHeight()/4; k+=60) {
			for(int j=60; j<getWidth()-60; j+=80) {
				GameEntitySprite e = new EnemySprite("", "default", new Sprite(getImage("img/enemy1.png"), j, k));
				Sprite damaged = new Sprite(getImage("img/enemy1damage.png"));
				e.mapNameToSprite("damaged", damaged);
				e.setSpeed(-.05, .005);
				enemies.add(e);
			}
		}
	}

	public void render(Graphics2D g) {
		bg.render(g);
		ship.render(g);
		enemies.render(g);
		torpedos.render(g);
	}

	public void update(long time) {
		bg.update(time);
		ship.update(time);
		enemies.update(time);
		torpedos.update(time);
		
		torpedoCollider.checkCollision();
		torpedoPlayerCollider.checkCollision();

//		kb.update();
		if(keyDown(KeyEvent.VK_LEFT)) {
			if(ship.getX()>0-15)  moveLeft();
		}
		if(keyDown(KeyEvent.VK_RIGHT)) {
			if(ship.getX()<getWidth()-45)   moveRight();
		}
		if(keyPressed(KeyEvent.VK_SPACE)) {
			fire();
		}
		
		Sprite[] enemySprites = enemies.getSprites();
		for(Sprite s : enemySprites) {
			if(s!=null) {
				if(!s.isOnScreen()) s.setActive(false);
				if(s.getX()<0-15 || s.getX()>getWidth()-39) {
					reverseVelocity(enemySprites);
					break;
				}
			}
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
