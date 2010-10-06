package vooga.games.grandius.enemy.boss;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Reacher is the boss of the first Gradius level.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public class Reacher extends GradiusBoss {

	
	public Reacher(BufferedImage[] images, double x, double y, List<BossPart> parts) {
		super(images, x, y, NOT_MOVING, null);
		this.setParts(parts);
	}
	
	public void reach(BossPart arm) {
		//Extend arm horizontally west
	}
}
