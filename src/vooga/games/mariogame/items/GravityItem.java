package vooga.games.mariogame.items;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class GravityItem extends Sprite {

	private double myGravity;

	public GravityItem(BufferedImage s, double gravity) {
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
