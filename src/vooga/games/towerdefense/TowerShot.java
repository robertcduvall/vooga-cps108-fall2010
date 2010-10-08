package vooga.games.towerdefense;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import vooga.engine.collision.OverlapChecker;

import com.golden.gamedev.object.Sprite;

public class TowerShot extends Sprite{
	
	private Point2D.Double destination;
	private double speed;
	
	public TowerShot(BufferedImage image, double x, double y, double destinationX, double destinationY, double speed){
		super(image, x, y);
		destination = new Point2D.Double(destinationX, destinationY);
		this.speed = speed;
	}
	
	public void update(long elapsedTime){
		setActive(!moveTo(elapsedTime, destination.x, destination.y, speed));
	}

}
