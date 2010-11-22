package vooga.games.cyberion.events;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.engine.overlay.Stat;
import vooga.games.cyberion.DropThis;
import vooga.games.cyberion.sprites.playership.PlayerShip;
import vooga.games.cyberion.states.PlayState;

public class LevelCompleteEvent implements IEventHandler{

	private DropThis myGame;
	private PlayState playState;
	private SpriteGroup spriteGroup;
	
	public LevelCompleteEvent(DropThis game, PlayState state, SpriteGroup group) {
		myGame = game;
		playState = state;
		spriteGroup = group;
	}

	@Override
	public boolean isTriggered() {
		for (Sprite tempSprite : spriteGroup.getSprites()){
			if (tempSprite!=null && tempSprite.isActive())
				return false;
		}
		return true;
	}

	@Override
	public void actionPerformed() {
		//Stat<Integer> tempScore= (Stat<Integer>) playState.getPlayer().getStat("scoreStat");
		//System.out.println(tempScore.getStat());
		PlayState newState = playState.nextLevel();
		//BetterSprite t = (BetterSprite) newState.getPlayField().getGroup("playerGroup").getSprites()[0];//.setStat("scoreStat", tempScore);
		//t.setStat("scoreStat", tempScore);
		//newState.getPlayer().setStat("scoreStat", tempScore);
		//System.out.println((Integer) newState.getPlayer().getStat("scoreStat").getStat());
		myGame.setAsPlayGameState(newState);
		myGame.setPlayState();
	}
}
