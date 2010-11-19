package vooga.games.zombieland.gamestates;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.zombieland.Blah;
import vooga.games.zombieland.Constants;

public class ZombielandMenuState extends GameState implements Constants{

	private static Blah currentGame;
	private static SpriteGroup backgroundGroup;
	
	
	@Override
	public void activate()
	{
		super.activate();
		
	}

	@Override
	public void initialize() {
		backgroundGroup = new SpriteGroup("Background");
		
		BufferedImage menu = Resources.getImage("menu");
		Sprite menuSprite = new Sprite(menu, GAME_WIDTH, GAME_HEIGHT);
		
		
		backgroundGroup.add(menuSprite);	
//		this.addGroup(backgroundGroup);
		//zombielandPlayField.addGroup(backgroundGroup);
	}
	
	
	
	
	
	
}
