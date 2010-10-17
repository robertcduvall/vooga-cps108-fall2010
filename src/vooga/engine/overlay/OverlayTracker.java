package vooga.engine.overlay;


import java.util.ArrayList;

import com.golden.gamedev.object.SpriteGroup;

public class OverlayTracker {
	
	private ArrayList<SpriteGroup> overlayGroups;
	private ArrayList<OverlayBar> barOverlays;
	private ArrayList<OverlayString> stringOverlays;
	private ArrayList<OverlayStat> statOverlays;
	private ArrayList<OverlayIcon> iconOverlays;
	private ArrayList<OverlayStatImage> imageOverlays;
	private ArrayList<Stat> stats;
	

	public OverlayTracker(){
		overlayGroups = new ArrayList<SpriteGroup>();
		barOverlays = new ArrayList<OverlayBar>();
		stringOverlays = new ArrayList<OverlayString>();
		statOverlays = new ArrayList<OverlayStat>();
		iconOverlays = new ArrayList<OverlayIcon>();
		imageOverlays = new ArrayList<OverlayStatImage>();
		stats = new ArrayList<Stat>();
		
	}


	public ArrayList<SpriteGroup> getOverlayGroups() {
		return overlayGroups;
	}



	public ArrayList<OverlayBar> getBarOverlays() {
		return barOverlays;
	}


	public ArrayList<OverlayString> getStringOverlays() {
		return stringOverlays;
	}


	public ArrayList<OverlayStat> getStatOverlays() {
		return statOverlays;
	}


	public ArrayList<OverlayIcon> getIconOverlays() {
		return iconOverlays;
	}



	public ArrayList<OverlayStatImage> getImageOverlays() {
		return imageOverlays;
	}


	public ArrayList<Stat> getStats() {
		return stats;
	}
}
