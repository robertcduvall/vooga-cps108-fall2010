package vooga.games.towerdefense.states;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import vooga.engine.control.Control;
import vooga.engine.control.MouseControl;
import vooga.engine.core.PlayField;
import vooga.engine.core.Sprite;
import vooga.engine.event.EventPool;
import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.towerdefense.actors.Player;
import vooga.games.towerdefense.events.BuildTowerEvent;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.ImageUtil;





public class PlayState extends GameState{	

	@Override
	public void initialize() {
		addPlayField(initPlayField());
	}
	
	private PlayField initPlayField(){
		PlayField playField = new PlayField();
		playField.setBackground(initBackground());
		
		EventPool eventPool = new EventPool();
		playField.addEventPool(eventPool);
		
		BuildTowerEvent buildTower = new BuildTowerEvent();
		eventPool.addEvent(buildTower);
		Sprite player = initPlayer(buildTower);
		playField.add(player);
		
		playField.addControl(initControl(player));
		
		
		return playField;
	}
	
	private Background initBackground(){
		BufferedImage backgroundImage = Resources.getImage("background");
		backgroundImage = ImageUtil.resize(backgroundImage, Resources.getInt("gameWidth"), Resources.getInt("gameHeight"));
		return new ImageBackground(backgroundImage);
	}
	
	private Sprite initPlayer(BuildTowerEvent buildTowerEvent){
		Sprite player = new Player(Resources.getImage("towerPreview"), 0 , 0, buildTowerEvent);		
		return player;
	}
	
	public Control initControl(Sprite player){
		MouseControl playerControl = new MouseControl(player, Resources.getGame());	
		playerControl.addInput(MouseEvent.BUTTON1, "onClick", "vooga.games.towerdefense.Player");
		playerControl.addInput(MouseEvent.MOUSE_MOVED, "move", "vooga.games.towerdefense.Player");
		
		return playerControl;
	}

}
