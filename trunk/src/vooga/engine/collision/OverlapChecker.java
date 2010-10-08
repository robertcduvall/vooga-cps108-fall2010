package vooga.engine.collision;

import java.awt.geom.Rectangle2D;

import com.golden.gamedev.object.Sprite;

public class OverlapChecker {

	public static boolean checkOverlap(Sprite sprite1, Sprite sprite2){
		Rectangle2D boundingBox1 = new Rectangle2D.Double(sprite1.getX(), sprite1.getY(), sprite1.getWidth(), sprite1.getHeight());
		return boundingBox1.intersects(sprite2.getX(), sprite2.getY(), sprite2.getWidth(), sprite2.getHeight());
	}
}
