package vooga.games.zombieland;

public abstract class Weapon {
	
	private Shooter player;
	private int ammoCount;
	
	public Weapon(Shooter s, int ammo){
		player=s;
		ammoCount=ammo;
	}
	
	public int getAmmo(){
		return ammoCount;
	}
	
	public void fire(){
		if(ammoCount>0)
			fireBullets();
		ammoCount--;
	}
	
	public double getOrientation(){
		return player.getOrientation();
	}
	protected abstract void fireBullets();
	
	/**
	 * Creates a bullet whose orientation is specified by angle
	 * @param angle orientation of the bullet to be created
	 */
	
	protected void makeBullet(double angle) {
		player.addBulletToGame(new Bullet(player.getX(),player.getY(),angle),angle);
	}
}
