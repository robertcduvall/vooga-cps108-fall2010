package vooga.games.tronlegacy;

import java.awt.Color;
import java.awt.Graphics2D;

import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.state.GameState;

public class PauseState extends GameState {

	public PauseState() {

		OverlayTracker tracker = OverlayCreator
				.createOverlays("src/vooga/games/tronlegacy/resources/overlays.xml");
		SpriteGroup pauseOverlayGroup = tracker.getOverlayGroups().get(1);

		this.addGroup(pauseOverlayGroup);
	}

	public PauseState(GameState previousState) {
		this();
		addRenderState(previousState);
	}

}
