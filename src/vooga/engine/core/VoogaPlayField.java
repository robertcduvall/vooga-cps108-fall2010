package vooga.engine.core;

import java.util.ArrayList;

import vooga.engine.overlay.OverlayTracker;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

/**
 * Extension of the Golden T Playfield to allow addition of OverlayTracker to the Playfield.
 * Example:
 * <pre>
 * <code>
 * VoogaPlayfield playfield = new VoogaPlayfield();
 * OverlayTracker track = OverlayCreator.createOverlays(String xmlFileLocation);
 * playfield.addOverlayTracker(track);
 * </pre>
 * </code>
 * @author Bhawana , Cameron , Derek
 *
 */
public class VoogaPlayField extends PlayField {
	
	public VoogaPlayField(Background background){
		super(background);
	}
	
	public VoogaPlayField() {
		super();
	}
	
	/**
	 * Adds OverlayTracker to the playfield.
	 * @param overlayTracker
	 */
	public void addOverlayTracker(OverlayTracker overlayTracker){
		addOverlayGroups(overlayTracker.getOverlayGroups());
	}


	/**
	 * Adds every SpriteGroup from the OverlayTracker to the playfield.
	 * @param overlayGroups
	 */
	private void addOverlayGroups(ArrayList<SpriteGroup> overlayGroups) {
		for(SpriteGroup overlay : overlayGroups){
			this.addGroup(overlay);
		}
	}

}
