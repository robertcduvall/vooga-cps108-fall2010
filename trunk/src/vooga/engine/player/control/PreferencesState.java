package vooga.engine.player.control;

import java.awt.event.MouseEvent;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameState;

public class PreferencesState extends GameState{
	
	private OverlayTracker tracker;
	private MouseControl control;
	private SpriteGroup overlays;
	
	public PreferencesState(){
		super();
	}
	
	public PreferencesState(String location) {
		tracker = OverlayCreator.createOverlays(location);
		overlays = tracker.getOverlayGroups().get(0);
		addGroup(overlays);
		control = new MouseControl();
	}

	public void setCategories(String name, String type, Stat<?> stat) {
		
		control.setParams(new Class[]{String.class, String.class, Stat.class});
		control.addInput(MouseEvent.BUTTON1, "changeStat", "vooga.engine.player.control.PreferencesState", name, type, stat);
		
	}
	
	public void changeStat(String name, String type, Stat<?> stat)
	{
		
	}
	
}
