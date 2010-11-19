package vooga.games.mariogame.items;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.ItemSprite;

@SuppressWarnings("serial")
public class GravityItem extends ItemSprite {

	private double myGravity;

	public GravityItem(Sprite s, double gravity) {
		super(s);
		myGravity = gravity;
	}

	public double getGravity() {
		return myGravity;
	}

	public void setGravity(double myGravity) {
		this.myGravity = myGravity;
	}
	
}
