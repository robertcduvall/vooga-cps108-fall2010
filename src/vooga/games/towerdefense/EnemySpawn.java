package vooga.games.towerdefense;

import java.util.ArrayList;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.Utility;

import vooga.engine.overlay.Stat;
import vooga.engine.overlay.StatInt;
import vooga.engine.resource.Resources;

public class EnemySpawn extends Enemy {
	
	SpriteGroup myEnemyGroup;

	public EnemySpawn(ArrayList<PathPoint> path, int speed,
			Stat<Integer> selfEstem, Stat<Integer> score, Stat<Integer> money, SpriteGroup enemyGroup) {
		super(path, speed, 10, selfEstem, score, money);
		myEnemyGroup = enemyGroup;
		
	}
	
	@Override
	protected void setImage() {
		setImage(Resources.getImage("duvallFaceYellow"));
	}
	
	@Override
	protected void gotHit() {
		setActive(false);
		for(int i = 0; i< 5; i++){
			Enemy temp = new Enemy(myPath, Utility.getRandom(20, 80), Utility
					.getRandom(1, 3), mySelfEstem, myScore, myMoney);
			temp.myLoc = myLoc-(i*5);
			myEnemyGroup.add(temp);
		}
		super.gotHit();
	}
	
	
	

}
