package CyberionSprite;

import java.awt.image.BufferedImage;

import GameEvent.PlayerFireEvent;
import GameEvent.PlayerFireListener;

import com.golden.gamedev.object.AdvanceSpriteGroup;
import com.golden.gamedev.object.Sprite;

import engine.event.IEvent;
//player shot class handles adding new sprites to screen upon fire command.
public class PlayerShot extends AdvanceSpriteGroup implements
		PlayerFireListener {

	BufferedImage image;

	public PlayerShot(String groupName, int topOffset, int leftOffset,
			int bottomOffset, int rightOffset, BufferedImage image) {
		super(groupName, topOffset, leftOffset, bottomOffset, rightOffset);
		this.image = image;

	}
	//when the fire event is performed, calls fireAction()
	@Override
	public void actionPerformed(IEvent e) {
		if (e.getName().matches("PlayerFireEvent")) {
			fireAction((PlayerFireEvent) e);
		}
	}
	//adds a new sprite with the appropriate velocity and quantity to the sprite group so it can
	//be updated and rendered
	@Override
	public void fireAction(PlayerFireEvent e) {
		if (e.getWeaponPower() == 1) {
			Sprite shot = new Sprite(image, e.getX() + 20, e.getY() - 5);
			shot.setMovement(0.5, 0);
			add(shot);
		}
		if (e.getWeaponPower() == 2) {
			Sprite shot = new Sprite(image, e.getX() + 21 - 5, e.getY() - 5);
			shot.setMovement(0.5, 0);
			add(shot);
			shot = new Sprite(image, e.getX() + 21 + 5, e.getY() - 5);
			shot.setMovement(0.5, 0);
			add(shot);
		}
		if (e.getWeaponPower() == 3) {
			Sprite shot = new Sprite(image, e.getX() + 21 - 15, e.getY() - 5);
			shot.setMovement(0.5, 0);
			add(shot);

			shot = new Sprite(image, e.getX() + 21, e.getY() - 5);
			shot.setMovement(0.5, 0);
			add(shot);

			shot = new Sprite(image, e.getX() + 21 + 15, e.getY() - 5);
			shot.setMovement(0.5, 0);
			add(shot);
		}
	}
}
