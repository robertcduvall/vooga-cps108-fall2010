package vooga.games.towerdefense.states;

import java.awt.event.MouseEvent;
import com.golden.gamedev.object.background.*;


import vooga.engine.control.*;
import vooga.engine.core.PlayField;
import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.towerdefense.*;





public class PlayState extends GameState{
	
	

	@Override
	public void initialize() {
		addPlayField(initPlayField());
	}
	
	public PlayField initPlayField(){
		PlayField playField = new PlayField();
		playField.setBackground(new ImageBackground(Resources.getImage("background"), Resources.getInt("gameWidth"), Resources.getInt("gameHeight")));
		Sprite player = initPlayer();
		playField.add(player);
		playField.addControl(initControl(player));
		
		return playField;
	}
	
	public Sprite initPlayer(){
		Sprite player = new Player(Resources.getImage("towerPreview"), 0 , 0);		
		return player;
	}
	
	public Control initControl(Sprite player){
		MouseControl playerControl = new MouseControl(player, Resources.getGame());	
		playerControl.addInput(MouseEvent.BUTTON1, "onClick", "vooga.games.towerdefense.Player");
		playerControl.addInput(MouseEvent.MOUSE_MOVED, "move", "vooga.games.towerdefense.Player");
		
		return playerControl;
	}

}
