package vooga.games.tron.eventsystem;
/**
 * handles input event for user two
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */
import java.awt.event.KeyEvent;
import com.golden.gamedev.Game;


public abstract class User2InputEvent{
	Game game;
	public User2InputEvent(Game game){
		this.game=game;
	}
	/**
	 * check and updates the keyboard input
	 */
	public void checkInput(){

		if(game.keyDown(KeyEvent.VK_D)){  
			this.right();
		}
		else if(game.keyDown(KeyEvent.VK_A)){
			this.left();
		}
		else if(game.keyDown(KeyEvent.VK_S)){
			this.down();
		}
		else if(game.keyDown(KeyEvent.VK_W)){
			this.up();
		}
		else if(game.keyDown(KeyEvent.VK_H)){
			this.spacePressed();
		}
		else if(game.click()){
			this.mouseLeftPressed();
		}
		else if(game.rightClick()){
			this.mouseRightPressed();
		}

	}

	public abstract void down();	
	public abstract void up();	
	public abstract void left();
	public abstract void right();
	public abstract void spacePressed();
	public abstract void mouseLeftPressed();
	public abstract void mouseRightPressed();

}
