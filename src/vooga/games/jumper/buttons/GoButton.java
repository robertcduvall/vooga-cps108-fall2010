package vooga.games.jumper.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.widget.Button;


public class GoButton extends Button{
	
	private static final long serialVersionUID = 1L;
	private String levelName;
	
	public GoButton(){
		super();
	}

	public GoButton(BufferedImage image, double x, double y) {
		super(image, x, y);
	}

	@Override
	public void actionPerformed() {
		System.out.println("Pressed");
	}
	
	public void setLevel(String levelName){
		this.levelName = levelName;
	}

}
