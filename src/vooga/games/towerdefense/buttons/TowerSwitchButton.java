package vooga.games.towerdefense.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.games.towerdefense.actors.Player;
import vooga.games.towerdefense.actors.towers.Tower;
import vooga.widget.Button;

public class TowerSwitchButton extends Button {
	private static final long serialVersionUID = 1L;
	private Tower tower;
	private Player player;
	
	public TowerSwitchButton(Player player){
		super();
		this.player = player;
	}

	public TowerSwitchButton(BufferedImage image, double x, double y, Player  player) {
		super(image, x, y);
		this.player = player;
	}

	@Override
	public void actionPerformed() {
		player.changeTowerType(tower);
	}
	
	public void setTower(Tower tower){
		this.tower = tower;
	}
}
