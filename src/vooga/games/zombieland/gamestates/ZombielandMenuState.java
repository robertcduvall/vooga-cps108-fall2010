package vooga.games.zombieland.gamestates;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


import vooga.engine.state.GameState;
import vooga.games.zombieland.Blah;
import vooga.games.zombieland.Constants;
import vooga.games.zombieland.ZombielandResources;

public class ZombielandMenuState extends GameState implements Constants{

	private static Blah currentGame;
	private static SpriteGroup backgroundGroup;
	
	public ZombielandMenuState()
	{
		//super();
		//currentGame = game;
		backgroundGroup = new SpriteGroup("Background");
		
		BufferedImage menu = ZombielandResources.getImage("menu");
		Sprite menuSprite = new Sprite(menu, GAME_WIDTH, GAME_HEIGHT);
		
		
		backgroundGroup.add(menuSprite);	
		this.addGroup(backgroundGroup);
		//zombielandPlayField.addGroup(backgroundGroup);
		
	}
	
//	@Override
//	public void render(Graphics2D g)
//	{
//		super.render(g);
//	}
	
	@Override
	public void activate()
	{
		super.activate();
		
	}
	
	
	
	
	
	
}
