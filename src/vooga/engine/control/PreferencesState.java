package vooga.engine.control;

import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameState;

public class PreferencesState extends GameState{
	
	private OverlayTracker tracker;
	private MouseControl control;
	private SpriteGroup overlays;
	private ResourceBundle resources;
	
	public PreferencesState(){
		super();
		resources = ResourceBundle.getBundle("vooga.engine.control.PreferencesResources");
	}
	
	public PreferencesState(String location) {
		tracker = OverlayCreator.createOverlays(location);
		overlays = tracker.getOverlayGroups().get(0);
		addGroup(overlays);
		control = new MouseControl();
	}

	public void addCategories(String name, String type, Stat<?> stat) {
		Class<?> myClass = PreferencesState.class;
		try {
		Method myMethod = myClass.getMethod(resources.getString(type), String.class, Stat.class);
		myMethod.invoke(this, name, stat);
		}
		catch (Throwable e)
		{
			System.out.println(e);
		}
	}
	
	public void addString(String name, Stat<?> stat) {
		
	}
	
	public void setCategories(String name, String type, Stat<?> stat) {
		
		control.setParams(new Class[]{String.class, String.class, Stat.class});
		control.addInput(MouseEvent.BUTTON1, "changeStat", "vooga.engine.player.control.PreferencesState", new Stat<String>(name), new Stat<String>(type), stat);
		
	}
	
	public void changeStat(String name, String type, Stat<?> stat)
	{
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
}
