package vooga.games.grandius.events;

import vooga.engine.event.IEventHandler;
import vooga.games.grandius.DropThis;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class ZipsterBrokeThroughEvent implements IEventHandler {

	protected DropThis grandius; 
	private SpriteGroup enemyGroup;

	public ZipsterBrokeThroughEvent(DropThis grandius, SpriteGroup enemyGroup){
		this.grandius = grandius;
		this.enemyGroup = enemyGroup;
	}

	@Override
	public boolean isTriggered() {
		return hasZipsterBrokenThrough();
	}

	@Override
	public void actionPerformed() {
		grandius.getGameStateManager().switchTo(grandius.getGameStateManager().getGameState(1));
	}

	private boolean hasZipsterBrokenThrough() {
		for (Sprite s: enemyGroup.getSprites()) {
			if (s!=null && (s.getX() < 0)) {
				return true;
			}
		}
		return false;
	}
}
