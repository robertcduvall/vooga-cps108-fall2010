package vooga.games.towerdefense.events;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.towerdefense.DropThis;
import vooga.games.towerdefense.actors.Player;
import vooga.games.towerdefense.actors.TowerShot;
import vooga.games.towerdefense.actors.enemies.Enemy;
import vooga.games.towerdefense.actors.towers.ShootingTower;

public class ShootEvent implements IEventHandler{
	ShootingTower tower;
	Player player;
	
	public ShootEvent(Player p)
	{
		player = p;
	}
	
	public void setTower(ShootingTower t){
		tower = t;
	}
	
	@Override
	public boolean isTriggered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed() {
		if(tower.canShoot()){
			Enemy target = tower.getTarget();
			target.gotHit();
			tower.resetShot();
			player.addMoney(target.getMoneyPerHit());
			player.addScore(target.getScorePerHit());
			//TowerShot shot = new TowerShot(Resources.getImage("towerShot"),getX(), getY(), target.getX(), target.getY(), shotSpeed);
		}
	}
	
	
}
