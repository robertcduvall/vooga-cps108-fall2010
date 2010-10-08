package vooga.games.zombieland;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * This is the Human Item Collision sytem
 * @author Aaron Choi, Jimmy Mu, Yang Su
 *
 */

public class HICollisionManager extends BasicCollisionGroup{

	public void collided(Sprite human, Sprite item) {

		Shooter currentHuman = (Shooter) human; 
		Item currentItem = (Item) item;
		
		
		
		
	}
	
	
//	public void addRandomItem(int x, int y) {
////		double x=Math.random()*GAME_WIDTH;
////		double y=Math.random()*GAME_HEIGHT;
//		int choice= (int) (Math.random()*3);
//		
//		Item item;
//		switch(choice){
//		case 0: 
//			item= new WeaponItem(myPlayer,new Sprite(myAssaultRifleImage),1,x,y);
//			break;
//		case 1: 
//			item= new WeaponItem(myPlayer,new Sprite(myShotGunImage),2,x,y);
//			break;
//		case 2: 
//			item= new HealthItem(myPlayer,new Sprite(myHealthImage),100,x,y);
//			break;
//			
//		default:
//			item=null;
//		}
//		item.getCurrentSprite().setImage(myHealthImage);
//		item.setActive(true);
//		myItems.add(item);
//	}
//	

}
