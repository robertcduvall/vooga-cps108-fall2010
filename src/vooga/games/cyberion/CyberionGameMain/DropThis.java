package vooga.games.cyberion.CyberionGameMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import vooga.engine.event.EventManager;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.resource.ResourcesBeta;
import vooga.engine.state.GameStateManager;

import vooga.games.cyberion.Collision.EnemyCollidesWithShot;
import vooga.games.cyberion.Collision.PlayerCollidesEnemy;
import vooga.games.cyberion.Collision.PlayerCollidesWall;
import vooga.games.cyberion.Collision.PlayerCollidesWithBonus;
import vooga.games.cyberion.Collision.PlayerCollidesWithShot;
import vooga.games.cyberion.CyberionSprite.Bonus;
import vooga.games.cyberion.CyberionSprite.EnemyShip;
import vooga.games.cyberion.CyberionSprite.EnemyShot;
import vooga.games.cyberion.CyberionSprite.PlayerShip;
import vooga.games.cyberion.CyberionSprite.PlayerShot;
import vooga.games.cyberion.CyberionSprite.StarGroup;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * Cyberion shooting game
 * 
 * @author Hao He, Scott Winkleman, Vitor Olivier
 * @version 1.0
 * @see http://www.istisoft.net/online/cyberion/cyberion.html
 */

public class DropThis extends Game {

	MainGameState playGameState = new MainGameState();
	GameStateManager gameStateManager = new GameStateManager();
	

	
	// calls other methods and initializes all remaining variables
	public void initResources() {

		Resources.setGame(this);
		loadImages();
		playGameState.setGame(this);
		playGameState.initialize();
		playGameState.activate();
		gameStateManager.addGameState(playGameState);


	}

	private void loadImages() {
		try {
			Resources
					.loadFile("src/vooga/games/cyberion/Resources/imageList.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// updates sprite groups and checks for collisions
	public void update(long elapsedTime) {
		gameStateManager.update(elapsedTime);

	}

	// renders active sprites
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		gameStateManager.render(g);
		
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DropThis(), new Dimension(640, 480), false);
		game.start();
	}

}
