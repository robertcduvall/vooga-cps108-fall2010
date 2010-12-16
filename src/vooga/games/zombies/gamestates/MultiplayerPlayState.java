package vooga.games.zombies.gamestates;

import java.awt.Graphics2D;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.util.SoundPlayer;
import vooga.games.zombies.*;
import vooga.games.zombies.events.*;
import vooga.games.zombies.serializeables.Health;
import vooga.games.zombies.serializeables.Name;
import vooga.games.zombies.serializeables.Username;
import vooga.games.zombies.serializeables.ZombieSeed;

public class MultiplayerPlayState extends PlayState implements Constants {

	private SpriteGroup otherPlayers;

	public MultiplayerPlayState(Blah game) {
		super(game,
				"src/vooga/games/zombies/resources/levels/multiplayerLevel.xml");
	}

	/**
	 * Sets up the players in the game. Right now it is setup to work with 2
	 * players
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 */

	@Override
	protected void setupPlayer() {
		player = (Shooter) playField.getGroup("Players").getSprites()[0];
		player.setConnection(connection);
		player.setOverlayName("player1");
		otherPlayers = playField.getGroup("OtherPlayers");
		Shooter otherPlayer1 = (Shooter) otherPlayers.getSprites()[0];
		otherPlayer1.setOverlayName("player2");
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
		addItems = new AddRandomItemEvent(playField, player, items);

		addZombies = new AddZombieEvent(playField.getGroup("Zombies"));

		SpriteGroup bullets = playField.getGroup("Bullets");
		AddBulletsEvent addbullets = new AddBulletsEvent(bullets);
		player.setBulletListener(addbullets);
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter otherPlayer = (Shooter) otherPlayers.getSprites()[i];
			if (otherPlayer != null)
				otherPlayer.setBulletListener(addbullets);
		}
		eventPool.addEvent(addItems);
		eventPool.addEvent(addbullets);
		eventPool.addEvent(addZombies);

		int delay = Resources.getInt("timer");
		timer = new Timer(delay);

		SoundPlayer.playMusic(playField.getMusic(0));
	}

	/**
	 * Deserialize the player's name and set the overlay
	 * 
	 * @param data
	 * @return false because the game shouldn't stop
	 */

	public boolean name(String data) {
		player.setName(((Name) (Name.deserialize(data))).getName());
		return false;
	}

	/**
	 * Deserialize the other players' names and set their overlays
	 * 
	 * @param data
	 * @return false because the game shouldn't stop
	 */

	public boolean userName(String data) {
		String userName = ((Username) (Username.deserialize(data)))
				.getUsername();
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter shooter = (Shooter) otherPlayers.getSprites()[i];
			if (shooter != null && shooter.getName() == null) {
				shooter.setName(userName);
			}
		}
		return false;
	}

	/**
	 * Deserialize the health of the other player and set the overlays
	 * appropriately
	 * 
	 * @param data
	 * @return false because the game shouldn't stop
	 */

	public boolean health(String data) {
		int health = ((Health) (Health.deserialize(data))).getHealth();
		((Shooter) (otherPlayers.getSprites()[0])).setHealth(health);
		return false;
	}

	/**
	 * Initializes the random zombie creator with the seed from the server so
	 * each player produces the same random zombies
	 * 
	 * @param data
	 * @return false because the game shouldn't end
	 */

	public boolean seed(String data) {
		long seed = ((ZombieSeed) (ZombieSeed.deserialize(data))).getSeed();
		Shooter[] shooters = new Shooter[otherPlayers.getSprites().length + 1];
		shooters[0] = player;
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			shooters[i + 1] = (Shooter) (otherPlayers.getSprites()[i]);
		}
		LevelEndEvent endLevel = new LevelEndEvent(shooters, this, addZombies,
				addItems, seed);
		eventPool.addEvent(endLevel);
		return false;
	}

	/**
	 * Makes the other players in the game move up.
	 * 
	 * @return false because the game shouldn't end
	 */

	public boolean goUp() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if (player != null) {
				if (player != null)
					player.goUp();
			}
		}
		return false;
	}

	/**
	 * Makes the other players in the game move down.
	 * 
	 * @return false because the game shouldn't end
	 */

	public boolean goDown() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if (player != null) {
				if (player != null)
					player.goDown();
			}
		}
		return false;
	}

	/**
	 * Makes the other players in the game move left.
	 * 
	 * @return false because the game shouldn't end
	 */
	public boolean goLeft() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if (player != null) {
				if (player != null)
					player.goLeft();
			}

		}
		return false;
	}

	/**
	 * Make the other players in the game move right depending on the data sent
	 * 
	 * @return false because the game shouldn't end
	 */

	public boolean goRight() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if (player != null) {
				if (player != null)
					player.goRight();
			}

		}
		return false;
	}

	/**
	 * Make the other players in the game shoot depending on the data sent
	 * 
	 * @return false because the game shouldn't end
	 */
	public boolean shoot() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if (player != null) {
				if (player != null)
					player.shoot();
			}

		}
		return false;
	}

	/**
	 * Kill all the other players in the game
	 * 
	 * @return false because the game shouldn't end
	 */
	public boolean killOtherPlayer() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if (player != null) {
				if (player != null)
					player.setHealth(0);
			}
		}
		return false;
	}

	/**
	 * End the game if everyone is dead
	 */

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		if (player.hasDied() && othersDead())
			endGame();
	}

	/**
	 * Check to see if other players are dead
	 * 
	 * @return true if other players are all dead
	 */

	private boolean othersDead() {
		boolean othersDead = true;
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if (player != null) {
				if (!player.hasDied())
					othersDead = false;
			}
		}
		return othersDead;
	}
}
