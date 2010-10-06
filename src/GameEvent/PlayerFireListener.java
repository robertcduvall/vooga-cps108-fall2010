package GameEvent;

import engine.event.IEventListener;

public interface PlayerFireListener extends IEventListener {
	public void fireAction(PlayerFireEvent e);
}
