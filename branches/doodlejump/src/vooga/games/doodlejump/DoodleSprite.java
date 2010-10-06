package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.PlayerSprite;

public class DoodleSprite extends PlayerSprite {

	public DoodleSprite(String name, String stateName, Sprite s) {
		super(name, stateName, s);
	}
	
	public void moveLeft(){
		//if (isOnScreen(0, 0, 695, 822)) {
			super.setX(this.getX()-1);
		//}
	}
	
	public void moveRight(){
		//if (isOnScreen(0, 0, 695, 822)) {
		super.setX(this.getX()+1);
		//}
	}
}
