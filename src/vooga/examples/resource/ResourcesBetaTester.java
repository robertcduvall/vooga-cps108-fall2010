package vooga.examples.resource;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.IOException;

import vooga.engine.resource.ResourcesBeta;
import vooga.games.towerdefense.TowerDefense;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;


public class ResourcesBetaTester extends Game{

	@Override
	public void initResources() {
		ResourcesBeta.initialize(this);
		ResourcesBeta.loadImage("red", "red.png");
		ResourcesBeta.loadSound("red", "red.wav");
		try {
			ResourcesBeta.loadImageFile("red.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new ResourcesBetaTester(), new Dimension(100, 100), false);
		game.start();
	}

}
