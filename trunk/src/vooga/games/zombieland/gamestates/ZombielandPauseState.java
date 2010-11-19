package vooga.games.zombieland.gamestates;

import java.awt.Graphics2D;
import java.util.Random;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.zombieland.Blah;
import vooga.games.zombieland.Constants;

public class ZombielandPauseState extends GameState implements Constants{

	private OverlayTracker tracker;
	private OverlayString overlayPauseString;
	SpriteGroup overlays;
	public ZombielandPauseState(Blah game)
	{
		super();
		OverlayCreator.setGame(game);
		tracker = OverlayCreator.createOverlays(XML_PATH);
		initialize();
	}
	
	public void initialize()
	{
		int pauseStringXMLlocation = Resources.getInt("pauseStringXMLlocation");

		overlayPauseString = tracker.getOverlay("pause", overlayPauseString);
		overlayPauseString.setActive(true);
//		this.addGroup(tracker.getOverlayGroup("pause"));
	}
	public void render(Graphics2D g) {
		overlayPauseString.render(g);
	}
	public void update(long elapsedTime){
		overlayPauseString.setActive(this.isActive());
	}
}
