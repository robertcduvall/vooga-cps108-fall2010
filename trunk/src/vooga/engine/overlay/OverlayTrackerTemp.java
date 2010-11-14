package vooga.engine.overlay;




import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.golden.gamedev.object.SpriteGroup;
/**
 * Class used to track overlays and Stats.
 * 
 * 
 * @author Justin Goldsmith
 *
 */
public class OverlayTrackerTemp {
	
	private Map<String, SpriteGroup> myOverlayGroups;
	private Map<String, Overlay> myOverlays;
	private Map<String, Stat<?>> myStats;
	

	public OverlayTrackerTemp(){
		myOverlayGroups = new TreeMap<String, SpriteGroup>();
		myOverlays = new TreeMap<String, Overlay>();
		myStats = new TreeMap<String, Stat<?>>();
		
	}


	public SpriteGroup getOverlayGroup(String name) {
		return null;
	}

	public Stat<?> getStat(String name){
		return myStats.get(name);
	}
	
	public<T> Stat<T> getStat(String name, T t){
		return (Stat<T>)myStats.get(name);	
	}
	
	public Overlay getOverlay(String name){
		return myOverlays.get(name);
	}
	
	public<T extends Overlay> T getOverlay(String name, T t){
		return (T)myOverlays.get(name);
	}
	
	protected void putOverlayGroup(String key, SpriteGroup value){
		myOverlayGroups.put(key, value);
	}
	
	protected void putOverlay(String key, Overlay value){
		myOverlays.put(key, value);
	}
	
	protected void putStat(String key, Stat<?> value){
		myStats.put(key, value);
	}

}
