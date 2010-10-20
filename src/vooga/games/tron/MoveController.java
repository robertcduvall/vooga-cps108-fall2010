package vooga.games.tron;
/**
 * This class translates the keyboard input into the user controlled information
 * @author Meng Li,Brent Sodman,JiaQi Yan
 * 
 */
import vooga.games.tron.eventsystem.UserInputEventAdaptor;
import vooga.games.tron.players.TronPlayer;

import com.golden.gamedev.Game;

public class MoveController extends UserInputEventAdaptor{   
	Game game;
	TronPlayer myPlayer;
	public MoveController(Game game, TronPlayer player){
		super(game);
		this.game=game;  
		this.myPlayer = player;	
	}
	/**
	 * handles down turning
	 */
	@Override
	public void down() {
		if(!myPlayer.getDirection().equals("down")&&!myPlayer.getDirection().equals("up")){
			myPlayer.setDirection("down");
			myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}  		
	}
	/**
	 * handles left turning
	 */
	@Override
	public void left() {
		if(!myPlayer.getDirection().equals("left")&&!myPlayer.getDirection().equals("right")){
			myPlayer.setDirection("left");
			myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}
	/**
	 * handles right turning
	 */
	@Override
	public void right() {
		if(!myPlayer.getDirection().equals("right")&&!myPlayer.getDirection().equals("left")){
			myPlayer.setDirection("right");
			myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}
	/**
	 * handles up turning
	 */
	@Override
	public void up() {
		if(!myPlayer.getDirection().equals("up")&&!myPlayer.getDirection().equals("down")){
			myPlayer.setDirection("up");
			myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}	

}
