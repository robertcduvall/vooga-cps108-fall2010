package vooga.games.galaxyinvaders;

import java.awt.Graphics2D;

import com.golden.gamedev.object.PlayField;

import vooga.engine.state.GameState;

public class GalaxyGameState extends GameState{

	PlayField playfield;
	
	public GalaxyGameState(PlayField pf){
		playfield = pf;
	}
	
	public PlayField getPlayField(){
		return playfield;
	}
	
	public void render(Graphics2D g){
		playfield.render(g);
	}
	
	public void update(long time){
		playfield.update(time);
	}
}
