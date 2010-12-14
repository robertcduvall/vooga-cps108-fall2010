package vooga.games.zombies.events;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.zombies.Shooter;
import vooga.games.zombies.items.HealthItem;
import vooga.games.zombies.items.Item;
import vooga.games.zombies.items.WeaponItem;

public class AddRandomItemEvent implements IEventHandler {

	Random random;
	PlayField playField;
	Shooter player;
	SpriteGroup items;

	public AddRandomItemEvent(PlayField playField, Shooter human,
			SpriteGroup items) {
		random = new Random();
		this.playField = playField;
		player = human;
		this.items = items;
	}

	@Override
	public boolean isTriggered() {
		return false;
	}

	@Override
	public void actionPerformed() {
	}

	/**
	 * Spawn a random item at position (x,y)
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public void addRandomItem(double x, double y) {

		int choice = random.nextInt(3);

		String weaponchoices = Resources.getString("weaponchoices");
		String delim = Resources.getString("delim");

		String[] options = weaponchoices.split(delim);
		String option = options[choice];

		BufferedImage itemimage = Resources.getImage(option);
		SpriteGroup items = playField.getGroup("Items");
		int healthOption = Resources.getInt("healthOption");

		if (choice == healthOption) {
			getHealthItem(x, y, option, itemimage, items);
		} else {
			getWeaponItem(x, y, option, itemimage, items);
		}

	}

	/**
	 * This method returns a gun item and adds ammo for the player.
	 * 
	 * @param x
	 * @param y
	 * @param option
	 * @param itemimage
	 * @param items
	 */
	private void getWeaponItem(double x, double y, String option,
			BufferedImage itemimage, SpriteGroup items) {
		int weaponoption = Resources.getInt(option);
		Item newGun = new WeaponItem(player, new Sprite(itemimage),
				weaponoption, x, y);
		items.add(newGun);
		newGun.setActive(true);
	}

	/**
	 * This method returns a health kit and adds 100 health for the player.
	 * 
	 * @param x
	 * @param y
	 * @param option
	 * @param itemimage
	 * @param items
	 */
	private void getHealthItem(double x, double y, String option,
			BufferedImage itemimage, SpriteGroup items) {
		int health = Resources.getInt(option);
		Item healthkit = new HealthItem(player, new Sprite(itemimage), health,
				x, y);
		items.add(healthkit);
		healthkit.setActive(true);
	}
}
