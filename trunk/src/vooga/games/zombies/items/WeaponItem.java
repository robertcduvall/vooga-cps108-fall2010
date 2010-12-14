package vooga.games.zombies.items;

import vooga.games.zombies.Shooter;

import com.golden.gamedev.object.Sprite;

/**
 * Weapon Item class. Subclass of Item. Bonus ammo for a specific weapon
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
@SuppressWarnings("serial")
public class WeaponItem extends Item {
	private int bonusAmmo;
	private int weaponID;

	public WeaponItem(Shooter shooter, Sprite s, int weapon, double x, double y) {
		super(shooter, s, x, y);
		weaponID = weapon;
		switch (weapon) {
		case 1:
			bonusAmmo = 100;
			break;
		case 2:
			bonusAmmo = 60;
			break;
		}
	}
	
	/**
	 * add ammo to a specific weapon
	 */
	public void act() {
		getPlayer().addAmmo(weaponID, bonusAmmo);
		getPlayer().setAmmo();
	}

}
