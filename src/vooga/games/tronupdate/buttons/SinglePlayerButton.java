package vooga.games.tronupdate.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.games.tronupdate.util.Mode;
import vooga.widget.Button;


public class SinglePlayerButton extends Button{
	public SinglePlayerButton(Game game, double x, double y) {
		super(game, x, y);
		initialize();
	}

	private void initialize(){
		OverlayTracker tracker = OverlayCreator.createOverlays(Resources.getString("overlayFileURL"));
		//tracker.getOverlay(singlePlayerButton);
	}
	@Override
	public void actionPerformed() {
		Mode.setSinglePlayer();
	}
}
