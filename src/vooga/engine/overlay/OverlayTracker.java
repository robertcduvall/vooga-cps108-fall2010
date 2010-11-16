package vooga.engine.overlay;




import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.golden.gamedev.object.SpriteGroup;
/**
 * Class used to track overlays and Stats.
 * 
 * For an example see <A HREF="OverlayExample.html">OverlayExample.java</A>.
 *
 * @author Justin Goldsmith
 *
 */
public class OverlayTracker {
	
	private Map<String, SpriteGroup> myOverlayGroups;
	private Map<String, Overlay> myOverlays;
	private Map<String, Stat> myStats;
	

	public OverlayTracker(){
		myOverlayGroups = new TreeMap<String, SpriteGroup>();
		myOverlays = new TreeMap<String, Overlay>();
		myStats = new TreeMap<String, Stat>();
		
	}


	public SpriteGroup getOverlayGroup(String name) {
		return myOverlayGroups.get(name);
	}

	public Stat getStat(String name){
		return myStats.get(name);
	}
	
	
	/**
	 * 
	 * Same as getStat, but returns a stat of whatever type t is
	 */
	public<T> Stat<T> getStat(String name, T t){
		return (Stat<T>)myStats.get(name);	
	}
	
	public Overlay getOverlay(String name){
		return myOverlays.get(name);
	}
	
	
	/**
	 * 
	 * Same as getOverlay, but returns a Specific type of overlay, say 
	 * a OverlayStat depending on what type t is.
	 */
	public<T extends Overlay> T getOverlay(String name, T t){
		return (T)myOverlays.get(name);
	}
	
	protected void putOverlayGroup(String key, SpriteGroup value){
		myOverlayGroups.put(key, value);
	}
	
	protected void putOverlay(String key, Overlay value){
		myOverlays.put(key, value);
	}
	
	protected void putStat(String key, Stat value){
		myStats.put(key, value);
	}
	
	/**
	 * 
	 * @return a set of all the keys in the Stat map
	 */
	public Set<String> statKeySet(){
		return myStats.keySet();
	}
	
	/**
	 * 
	 * @return a set of all the keys in the SpriteGroup map
	 */
	public Set<String> spriteGroupKeySet(){
		return myOverlayGroups.keySet();
	}
	
	/**
	 * 
	 * @return a set of all the keys in the Overlay map
	 */
	public Set<String> overlayKeySet(){
		return myOverlays.keySet();
	}

}
