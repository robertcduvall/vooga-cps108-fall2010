package vooga.engine.event;


import java.awt.event.KeyEvent;
import com.golden.gamedev.Game;


public abstract class UserInputEvent{
	Game game;
	public UserInputEvent(Game game){
		this.game=game;
	}

	public void checkInput(){
		
		if(game.keyDown(KeyEvent.VK_RIGHT)){  
			this.right();
		}
		if(game.keyDown(KeyEvent.VK_LEFT)){
			this.left();
		}
		if(game.keyDown(KeyEvent.VK_DOWN)){
			this.down();
		}
		if(game.keyDown(KeyEvent.VK_UP)){
			this.up();
		}
		if(game.keyDown(KeyEvent.VK_SPACE)){
			this.spacePressed();
		}
		if(game.click()){
			this.mouseLeftPressed();
		}
		if(game.rightClick()){
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
