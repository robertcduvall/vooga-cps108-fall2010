package GameEvent;

import engine.event.IEventListener;

public interface EnemyFireListener extends IEventListener {
	public void fireAction(EnemyFireEvent e);
}
