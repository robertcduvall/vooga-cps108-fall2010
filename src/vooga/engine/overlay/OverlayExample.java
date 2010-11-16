package vooga.engine.overlay;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.PlayField;

import vooga.engine.core.Game;

/**
 * This example shows how to create two different groups of Overlays from an XML file and display them on screen.
 * Notice that the code itself does almost nothing. All of the positioning and styling of the Overlays is handled
 * in the OverlayExample.xml file.
 * 
 * The first group contains an OverlayStat with the label "SCORE: ".
 * The second group contains two Overlays: an OverlayString with the text "HEALTH: " and an OverlayBar.
 * 
 * <xmp>
 * public class OverlayExample extends Game{
	
	PlayField myPlayfield;
	
	public static void main(String[] args){
		GameLoader game = new GameLoader();
        game.setup(new OverlayExample(), new Dimension(640,480), false);
        game.start();
	}
	
	public void initResources() {
		
		OverlayCreator oc = new OverlayCreator();
		
		//If you use any of the three provided GameFont's then you must set
		//the game. Eventually the GameFont's should be in Resources so you
		//should not have to set the game.
		//Also this does not need to be done if you are using normal fonts.
		oc.setGame(this);
		
		OverlayTracker track = oc.createOverlays("src/vooga/engine/overlay/OverlayExample.xml");
		
		// Playfield can also be replaced with States from the GameState API
		myPlayfield = new PlayField();
        
        myPlayfield.addGroup(track.getOverlayGroup("ScoreGroup"));
        myPlayfield.addGroup(track.getOverlayGroup("HealthGroup"));
    }
	
	public void update(long elapsedTime) {
		myPlayfield.update(elapsedTime);
	}
	
	public void render(Graphics2D g) {
		myPlayfield.render(g);
	}
	
}
 * </xmp>
 * 
 * @author Andrew Brown
 */

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
		
		//If you use any of the three provided GameFont's then you must set
		//the game. Eventually the GameFont's should be in Resources so you
		//should not have to set the game.
		//Also this does not need to be done if you are using normal fonts.
		oc.setGame(this);
		
		OverlayTracker track = oc.createOverlays("src/vooga/engine/overlay/OverlayExample.xml");
		
		// Playfield can also be replaced with States from the GameState API
		myPlayfield = new PlayField();
        
        myPlayfield.addGroup(track.getOverlayGroup("ScoreGroup"));
        myPlayfield.addGroup(track.getOverlayGroup("HealthGroup"));
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
