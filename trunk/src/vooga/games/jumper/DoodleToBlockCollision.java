package vooga.games.jumper;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class DoodleToBlockCollision extends AdvanceCollisionGroup {

	Jumper game;

	public DoodleToBlockCollision(Jumper game) {
		this.game = game; // set the game owner
		// this is used for getting image and adding explosion to playfield
	}	
	@Override
	public void collided(Sprite doodle, Sprite block) {
		// TODO Auto-generated method stub
		
	}

}
