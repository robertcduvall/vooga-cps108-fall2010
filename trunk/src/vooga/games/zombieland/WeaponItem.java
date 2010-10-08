package vooga.games.zombieland;

import com.golden.gamedev.object.Sprite;

public class WeaponItem extends Item{
	private int bonusAmmo;
	private int weaponID;
	public WeaponItem(Shooter s, int weapon, double x, double y){
		super(s,x,y);
	}
	public void act() {
		getPlayer().addAmmo(weaponID, bonusAmmo);
	}

}

	