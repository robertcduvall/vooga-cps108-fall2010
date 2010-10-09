package vooga.games.zombieland;

import com.golden.gamedev.object.Sprite;

public class WeaponItem extends Item{
	private int bonusAmmo;
	private int weaponID;
	public WeaponItem(Shooter shooter, Sprite s, int weapon, double x, double y){
		super(shooter,s,x,y);
		weaponID=weapon;

		switch (weapon){
		case 1: bonusAmmo=100; break;
		case 2: bonusAmmo=60; break;
		}
	}
	public void act() {
		getPlayer().addAmmo(weaponID, bonusAmmo);
	}

}

