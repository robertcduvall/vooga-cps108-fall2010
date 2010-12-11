package arcade.wall.controllers;

import javax.swing.JComponent;

import arcade.core.Tab;

/**
 * The VOOGA Wall. 
 * @author Cam, John, Bhawana
 */
@SuppressWarnings("serial")
public class Wall extends Tab {

	private WallTabController myController;
	//TODO create model and pass into controller
	
	public Wall(){
		super();
		setToolTipText("Click here to see your Wall.");
		setName("Wall");
		myController = new WallTabController();
		this.add(myController.getView().getPanel());
	}

	@Override
	public JComponent getContent() {
		return myController.getView().getPanel();
	}
}
