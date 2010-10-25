package vooga.games.cyberion;

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
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;

import vooga.games.cyberion.collisions.EnemyCollidesWithShot;
import vooga.games.cyberion.collisions.PlayerCollidesEnemy;
import vooga.games.cyberion.collisions.PlayerCollidesWall;
import vooga.games.cyberion.collisions.PlayerCollidesWithBonus;
import vooga.games.cyberion.collisions.PlayerCollidesWithShot;
import vooga.games.cyberion.sprites.Bonus;
import vooga.games.cyberion.sprites.EnemyShip;
import vooga.games.cyberion.sprites.EnemyShot;
import vooga.games.cyberion.sprites.PlayerShip;
import vooga.games.cyberion.sprites.PlayerShot;
import vooga.games.cyberion.sprites.StarGroup;

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

public class DropThis extends vooga.engine.core.Game {

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
					.loadImageFile("src/vooga/games/cyberion/resources/game.properties");
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
