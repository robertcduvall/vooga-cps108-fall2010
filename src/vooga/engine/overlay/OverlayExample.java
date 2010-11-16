package vooga.engine.overlay;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.PlayField;

import vooga.engine.core.Game;

public class OverlayExample extends Game{
	
	PlayField myPlayfield;
	
	public static void main(String[] args){
		GameLoader game = new GameLoader();
        game.setup(new OverlayExample(), new Dimension(640,480), false);
        game.start();
	}
	
	@Override
	public void initResources() {
		
		OverlayCreator oc = new OverlayCreator();
		oc.setGame(this);
		
		OverlayTracker track = oc.createOverlays("src/vooga/engine/overlay/OverlayExample.xml");
		
		myPlayfield = new PlayField();
        
        myPlayfield.addGroup(track.getOverlayGroup("first"));
        myPlayfield.addGroup(track.getOverlayGroup("second"));
        myPlayfield.addGroup(track.getOverlayGroup("third"));
    }
	
	@Override
	public void update(long elapsedTime) {
		myPlayfield.update(elapsedTime);
	}
	
	@Override
	public void render(Graphics2D g) {
		myPlayfield.render(g);
	}
	
}
