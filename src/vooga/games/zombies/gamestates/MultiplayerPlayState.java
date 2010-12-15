package vooga.games.zombies.gamestates;

import java.awt.Graphics2D;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import vooga.engine.networking.client.Username;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.util.SoundPlayer;
import vooga.games.zombies.*;
import vooga.games.zombies.events.*;

public class MultiplayerPlayState extends PlayState implements Constants {

	private SpriteGroup otherPlayers;
	public static long seed;
	public Timer reviveTimer;
	private static final String levelXmlPath = "src/vooga/games/zombies/resources/levels/multiplayerLevel.xml";

	public MultiplayerPlayState(Blah game) {
		super(game, levelXmlPath);
	}

	@Override
	protected void setupPlayer(){
		player = (Shooter) playField.getGroup("Players").getSprites()[0];
		player.setConnection(connection);
		player.setOverlayName("player1");
		otherPlayers = playField.getGroup("OtherPlayers");
		Shooter otherPlayer1 = (Shooter) otherPlayers.getSprites()[0];
		otherPlayer1.setOverlayName("player2");
	}

	@Override
	protected void initEnvironment(){
		eventPool = new EventPool();
		SpriteGroup items = playField.getGroup("Items");
		addItems = new AddRandomItemEvent(playField, player, items);

		addZombies = new AddZombieEvent(playField.getGroup("Zombies"));

		SpriteGroup bullets = playField.getGroup("Bullets");
		AddBulletsEvent addbullets = new AddBulletsEvent(bullets);
		player.setBulletListener(addbullets);
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter otherPlayer = (Shooter) otherPlayers.getSprites()[i];
			if(otherPlayer != null)
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
	 * Set the message to the String we received.
	 * 
	 * @param data
	 *            data received from the socket
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void interpretMessage(String data) {
		if(data.startsWith("yourName")){
			player.setName(data.substring(9));
		}
		else if(data.startsWith(Username.getIdentifier())){
			String userName = ((Username) (Username.deserialize(data))).getUsername();
			for (int i = 0; i < otherPlayers.getSprites().length; i++) {
				Shooter shooter = (Shooter) otherPlayers.getSprites()[i];
				if(shooter != null && shooter.getName() == null){
					shooter.setName(userName);
					return;
				}
			}
		}
		else if(data.startsWith(Health.getIdentifier())){
			int health = ((Health) (Health.deserialize(data))).getHealth();
			((Shooter)(otherPlayers.getSprites()[0])).setHealth(health);
		}
		else if(data.startsWith(ZombieSeed.getIdentifier())){
			seed = ((ZombieSeed) (ZombieSeed.deserialize(data))).getSeed();
			Shooter[] shooters = new Shooter[otherPlayers.getSprites().length + 1];
			shooters[0] = player;
			for(int i = 0; i < otherPlayers.getSprites().length; i++){
				shooters[i + 1] = (Shooter)(otherPlayers.getSprites()[i]);
			}
			LevelEndEvent endLevel = new LevelEndEvent(shooters, this, addZombies, addItems, seed);
			eventPool.addEvent(endLevel);
		}
		else{
			System.out.println(data);
			setMessage(data);
		}
	}

	public boolean goUp() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if(player != null){
				player.goUp();
			}
		}
		return false;
	}

	public boolean goDown() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if(player != null){
				player.goDown();
			}
		}
		return false;
	}

	public boolean goLeft() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if(player != null){
				player.goLeft();
			}
		}
		return false;
	}

	public boolean goRight() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if(player != null){
				player.goRight();
			}
		}
		return false;
	}

	public boolean shoot() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if(player != null){
				player.shoot();
			}
		}
		return false;
	}

	public boolean killOtherPlayer() {
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter player = (Shooter) (otherPlayers.getSprites()[i]);
			if(player != null){
				player.setHealth(0);
			}
		}
		return false;
	}

	public void render(Graphics2D g){
		super.render(g);
		int playerHealth = getPlayerHealth(player);

		if (player.getTimesRevived() == 1 && playerHealth <= 0) {
			currentGame.end();
		}

		if (playerHealth <= 0 && playField.getControl("Shooter") != null) {
			playField.removeControl("Shooter");
		}

		if (playerHealth > 0 && playField.getControl("Shooter") == null) {
			playField.addControl("Shooter", control);
		}
		for (int i = 0; i < otherPlayers.getSprites().length; i++) {
			Shooter otherPlayer = (Shooter) otherPlayers.getSprites()[i];
			if (otherPlayer != null && playerHealth <= 0 && getPlayerHealth(otherPlayer) <= 0) {
				currentGame.updateHighScore((double)player.getScore().getStat());
				currentGame.end();
			}
		}
	}
}
