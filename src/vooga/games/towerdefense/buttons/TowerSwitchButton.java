package vooga.games.towerdefense.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.widget.Button;

public class TowerSwitchButton extends Button {
	private static final long serialVersionUID = 1L;
	private String tower;
	
	public TowerSwitchButton(Game game){
		super(game);
	}

	public TowerSwitchButton(Game game, BufferedImage image, double x, double y) {
		super(game, image, x, y);
	}

	@Override
	public void actionPerformed() {
		System.out.println("Pressed");
	}
	
	public void setTower(String tower){
		this.tower = tower;
	}
}
