package vooga.games.cyberion.GameEvent;

import vooga.engine.event.IEventListener;

public interface PlayerMoveListener extends IEventListener{
	public void moveAction(PlayerMoveEvent e);
}
