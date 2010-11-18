package vooga.games.cyberion.sprites;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.SpriteGroup;
//extends SpriteGroups and adds different stars in order to be rendered in the background.
public class StarGroup extends SpriteGroup {

	private BufferedImage image;

	public StarGroup(String name, BufferedImage image) {
		super(name);
		this.image = image;
		StarSprite star = new StarSprite(image, 200, 0);
		star.setVerticalSpeed(0.5);
		add(star);
		star = new StarSprite(image, 10, 13);
		star.setVerticalSpeed(0.9);
		add(star);
		star = new StarSprite(image, 100, 87);
		star.setVerticalSpeed(0.7);
		add(star);
		star = new StarSprite(image, 300, 400);
		star.setVerticalSpeed(0.8);
		add(star);
		star = new StarSprite(image, 500, 320);
		star.setVerticalSpeed(0.4);
		add(star);
		star = new StarSprite(image, 234, 234);
		star.setVerticalSpeed(0.3);
		add(star);
		star = new StarSprite(image, 604, 162);
		star.setVerticalSpeed(0.6);
		add(star);
		star = new StarSprite(image, 27, 320);
		star.setVerticalSpeed(0.2);
		add(star);
		star = new StarSprite(image, 600, 320);
		star.setVerticalSpeed(0.3);
		add(star);
	}

}
