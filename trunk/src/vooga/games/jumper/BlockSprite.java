package vooga.games.jumper;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.ItemSprite;

public class BlockSprite extends ItemSprite{

	private int myXSpeed;
	private int myYSpeed;
	
	

	public BlockSprite(Sprite s){
		this("", "default", s, 5);
	}
	
	public BlockSprite(Sprite s, int numUses) {
		this("", "default", s, numUses);
	}

	
	public BlockSprite(String name, String stateName, Sprite s, int numUses) {
		super(name, stateName, s, numUses);
		myXSpeed = 0;
		myYSpeed = 0;
	}

	public int getMyXSpeed() {
		return myXSpeed;
	}

	public void setMyXSpeed(int myXSpeed) {
		this.myXSpeed = myXSpeed;
	}

	public int getMyYSpeed() {
		return myYSpeed;
	}

	public void setMyYSpeed(int myYSpeed) {
		this.myYSpeed = myYSpeed;
	}
	
}
