package vooga.games.towerdefense.events;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.DropThis;
import vooga.games.towerdefense.actors.enemies.Enemy;
import vooga.games.towerdefense.actors.towers.ShootingTower;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class FindTargetEvent implements IEventHandler{

	private DropThis game;
	
	public void setGame(DropThis game){
		this.game = game;
	}
	
	
	private Sprite findTarget(ShootingTower tower){		
		SpriteGroup enemies = game.getEnemyGroup();
		for(Sprite sprite: enemies.getSprites()){
			if(tower.isValidTarget(sprite)){
				return sprite;
			}
		}
		return null;		
	}

	@Override
	public boolean isTriggered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed() {
		ShootingTower[] towersToCheck = (ShootingTower[]) game.getCurrentLevel().getGroup("tower").getSprites();
		for(int i = 0; i < towersToCheck.length; i++)
		{
			if(towersToCheck[i].getTarget()==null)
			{
				towersToCheck[i].setTarget((Enemy) findTarget(towersToCheck[i]));
			}
		}
	}
}
