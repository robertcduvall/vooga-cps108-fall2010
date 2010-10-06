package GameEvent;

import engine.event.IEventListener;

public interface PlayerMoveListener extends IEventListener{
	public void moveAction(PlayerMoveEvent e);
}
