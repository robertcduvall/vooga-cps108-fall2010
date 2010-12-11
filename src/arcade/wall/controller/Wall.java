package arcade.wall.controller;

import javax.swing.JComponent;

import arcade.core.Tab;

/**
 * The VOOGA Wall. 
 * @author Cam, John, Bhawana
 */
@SuppressWarnings("serial")
public class Wall extends Tab {

	private WallController myController;
	
	public Wall(){
		super();
		setToolTipText("Click here to see your Wall.");
		setName("Wall");
		myController = new WallController();
		this.add(myController.getWallPanel().getPanel());
	}

	@Override
	public JComponent getContent() {
		return myController.getWallPanel().getPanel();
	}
}
