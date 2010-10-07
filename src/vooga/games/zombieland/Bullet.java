package vooga.games.zombieland;

import java.awt.image.BufferedImage;

import vooga.engine.player.control.GameEntitySprite;

import com.golden.gamedev.object.Sprite;

public class Bullet extends GameEntitySprite {

	private Shooter player;

	private double damage;
	private double velocity;
	private int angle;

	public Bullet(Shooter s, double theta) {
		super("Bullet","Moving",new Sprite());
		player = s;
		angle=(int) theta;

		if(angle>-45&&angle<45)
			setOffset(30,25);
		else if(angle>45&&angle<135)
			setOffset(-15,50);
		else if(angle>135&&angle<225)
			setOffset(-25,10);
		else
			setOffset(5,0);

		damage = velocity=5;
	}
	public void setOffset(double x, double y) {
		setX(player.getX()+x);
		setY(player.getY()+y);
	}
	public void update(long elapsedTime) {
		this.moveX(Math.cos(angle/360.0*Math.PI*2) * velocity);
		this.moveY(Math.sin(angle/360.0*Math.PI*2) * velocity);
		//		super.update(elapsedTime);
	}
}
