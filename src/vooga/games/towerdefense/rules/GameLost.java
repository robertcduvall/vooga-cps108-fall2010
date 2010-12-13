package vooga.games.towerdefense.rules;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.towerdefense.actors.Player;

import com.golden.gamedev.object.SpriteGroup;

public class GameLost implements Rule{

	@Override
	public void enforce(SpriteGroup... groups) {
		GameStateManager gameStateManager = Resources.getGame().getGameStateManager();
		Resources.getGame().updateHighScore(((Player) groups[0].getActiveSprite()).getScore());
		GameState gameOverState = gameStateManager.getGameState(0);
		System.out.println("GameOver");
		gameStateManager.switchTo(gameOverState);
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		for(SpriteGroup group: groups){
			Player player = (Player)group.getActiveSprite();
			if(player.getSelfEsteem()<=0){
				player.removeSelfEsteem(-100);
				return true;
			}
		}
		return false;
	}

}
