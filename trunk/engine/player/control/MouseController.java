package engine.player.control;

import engine.core.Game;

public class MouseController implements IPlayerController {

	private Game myGame;
	
	public MouseController(Game game)
	{
		myGame = game;
	}
	@Override
	public double deltaX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double deltaY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double deltaVelocityX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double deltaVelocityY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
