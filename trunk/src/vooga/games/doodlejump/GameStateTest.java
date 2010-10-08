package vooga.games.doodlejump;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * The GameStateTest class is a class to test the functionality of the
 * GameStateManager class and the GameState class. Once the basics of these
 * classes are understood and implemented they will be integrated with
 * DoodleGame to switch through several game states
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class GameStateTest extends Game {

	private GameStateManager game_state_manager;
	private GameState start_menu, game_play, pause_menu;
	private PlayField playfield;
	private Background start_background, game_background, pause_background;

	@Override
	public void initResources() {
		game_state_manager = new GameStateManager();

		start_menu = new DoodleState();
		game_play = new DoodleState();
		pause_menu = new DoodleState();

		game_state_manager.addGameState(start_menu);
		game_state_manager.addGameState(game_play);
		game_state_manager.addGameState(pause_menu);

		game_state_manager.switchTo(start_menu);

		start_background = new ImageBackground(
				getImage("images/default-play.png"));
		game_background = new ImageBackground(getImage("images/background.png"));
		pause_background = new ImageBackground(
				getImage("images/pause-cover-resume.png"));

		playfield = new PlayField(start_background);
	}

	@Override
	public void update(long elapsedTime) {

		if (keyPressed(KeyEvent.VK_P) && !start_menu.isActive()
				&& game_play.isActive()) {

			System.out.println("switch to pause menu");
			System.out.println("start_menu: " + start_menu.isActive());
			System.out.println("game_play: " + game_play.isActive());
			System.out.println("pause_menu: " + pause_menu.isActive());
			System.out.println();

			game_state_manager.switchTo(pause_menu);
			game_play.deactivate();
			playfield.setBackground(pause_background);
		}

		else if (keyPressed(KeyEvent.VK_P) && !start_menu.isActive()
				&& pause_menu.isActive()) {

			System.out.println("switch to game play");
			System.out.println("start_menu: " + start_menu.isActive());
			System.out.println("game_play: " + game_play.isActive());
			System.out.println("pause_menu: " + pause_menu.isActive());
			System.out.println();

			game_state_manager.switchTo(game_play);
			pause_menu.deactivate();
			playfield.setBackground(game_background);
		}

		if (keyPressed(KeyEvent.VK_P) && start_menu.isActive()) {

			System.out.println("start_menu: " + start_menu.isActive());
			System.out.println("game_play: " + game_play.isActive());
			System.out.println("pause_menu: " + pause_menu.isActive());
			System.out.println();

			game_state_manager.switchTo(game_play);

			playfield.setBackground(game_background);
		}

		playfield.update(elapsedTime);
	}

	@Override
	public void render(Graphics2D g) {
		game_state_manager.render(g);
		playfield.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new GameStateTest(), new Dimension(532, 850), false);
		game.start();
	}
}
