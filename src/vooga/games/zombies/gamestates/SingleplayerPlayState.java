package vooga.games.zombies.gamestates;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import vooga.engine.overlay.*;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.util.SoundPlayer;
import vooga.games.zombies.*;
import vooga.games.zombies.events.*;

public class SingleplayerPlayState extends PlayState implements Constants {
	private static final String levelXmlPath = "src/vooga/games/zombies/resources/levels/singleplayerLevel.xml";
	
	public SingleplayerPlayState(Blah game) {
		super(game, levelXmlPath);
	}

	@Override
	protected void setupPlayer() {
		player = (Shooter) playField.getGroup("Players").getSprites()[0];
		player.setOverlayName("player1");
	}

	/**
	 * This method initializes the zombies, bullets, players, overlays
	 * SpriteGroup, set them up with their respective collision managers, and
	 * associate these managers with playField.
	 */
	@Override
	protected void initEnvironment() {

		eventPool = new EventPool();
		SpriteGroup items = playField.getGroup("Items");
		AddRandomItemEvent additems = new AddRandomItemEvent(playField, player,
				items);

		addZombies = new AddZombieEvent(playField.getGroup("Zombies"));

		LevelEndEvent endLevel = new LevelEndEvent(new Shooter[] {player}, this, addZombies,
				additems, new Random().nextLong());

		SpriteGroup bullets = playField.getGroup("Bullets");
		AddBulletsEvent addbullets = new AddBulletsEvent(bullets);
		player.setBulletListener(addbullets);

		eventPool.addEvent(additems);
		eventPool.addEvent(addbullets);
		eventPool.addEvent(addZombies);
		eventPool.addEvent(endLevel);

		int delay = Resources.getInt("timer");
		timer = new Timer(delay);

		SoundPlayer.playMusic(playField.getMusic(0));
	}

	/**
	 * render the graphics component in the game
	 */
	public void render(Graphics2D g) {
		super.render(g);
		if (player.hasDied()) {
			endGame();
		}
	}
	/**
	 * Stop the game altogether
	 */
	private void endGame() {
		currentGame.updateHighScore((double)player.getScore().getStat());
		currentGame.end();
	}
}
