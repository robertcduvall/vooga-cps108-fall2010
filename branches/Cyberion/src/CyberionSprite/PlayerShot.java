package CyberionSprite;

import java.awt.image.BufferedImage;

import GameEvent.PlayerFireEvent;
import GameEvent.PlayerFireListener;

import com.golden.gamedev.object.AdvanceSpriteGroup;
import com.golden.gamedev.object.Sprite;

import engine.event.IEvent;

public class PlayerShot extends AdvanceSpriteGroup implements
		PlayerFireListener {

	BufferedImage image;

	public PlayerShot(String groupName, int topOffset, int leftOffset,
			int bottomOffset, int rightOffset, BufferedImage image) {
		super(groupName, topOffset, leftOffset, bottomOffset, rightOffset);
		this.image = image;

	}

	@Override
	public void actionPerformed(IEvent e) {
		if (e.getName().matches("PlayerFireEvent")) {
			fireAction((PlayerFireEvent) e);
		}
	}

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
