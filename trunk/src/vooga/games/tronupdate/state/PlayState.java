package vooga.games.tronupdate.state;


import java.awt.event.KeyEvent;


import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.core.BetterSprite;
import vooga.engine.state.GameState;
import vooga.engine.factory.LevelManager;


public class PlayState extends GameState{
	
	private Game game;
	PlayField myField;
	
	public PlayState(Game game, PlayField field){
		super(field);
		this.game = game;
		myField = field;
	}
	
	public PlayState(Game game, LevelManager levelManager){
		this.game = game;
		for(PlayField field: levelManager.getAllPlayFields()){
			addPlayField(field);
		}
	}

	@Override
	public void initialize() {
		initLevel();
	}
	
	private void initLevel(){
		
		BetterSprite player1 =(BetterSprite)(getGroup("playerSpriteGroup").getSprites()[0]);
		initPlayer1Controls(player1);
		BetterSprite player2 =(BetterSprite)(getGroup("playerSpriteGroup").getSprites()[1]);
		initPlayer2Controls(player2);

	}
	
	private void initPlayer1Controls(BetterSprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "playerXDirectionMove", "vooga.games.tronupdate.items.TronPlayer");
//		playerControl.addInput(KeyEvent.VK_RIGHT, "rotateRight", "vooga.games.asteroids.sprites.Ship");
//		playerControl.addInput(KeyEvent.VK_UP, "thrust", "vooga.games.asteroids.sprites.Ship");
//		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "vooga.games.asteroids.sprites.Ship");
		myField.addControl("player1", playerControl);
	}
	
	private void initPlayer2Controls(BetterSprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "playerXDirectionMove", "vooga.games.tronupdate.items.TronPlayer");
//		playerControl.addInput(KeyEvent.VK_RIGHT, "rotateRight", "vooga.games.asteroids.sprites.Ship");
//		playerControl.addInput(KeyEvent.VK_UP, "thrust", "vooga.games.asteroids.sprites.Ship");
//		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "vooga.games.asteroids.sprites.Ship");
		myField.addControl("player2", playerControl);
	}
}
