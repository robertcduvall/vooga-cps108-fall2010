package arcade.wall.controllers;

import javax.swing.JComponent;
import javax.swing.JPanel;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.lobby.model.Profile;

/**
 * The VOOGA WallTab. This class is reference in arcade.core.componentList.properties 
 * @author Cameron McCallie
 * @author John Kline  
 * @author Bhawana Singh
 */


@SuppressWarnings("serial")
public class WallTab extends JPanel implements Tab {

	private WallTabController myController;
	//TODO create model and pass into controller
	Profile myProfile;
	
	public WallTab(){
		super();
		setToolTipText("Click here to see your Wall.");
		setName("Wall");
		myController = new WallTabController();
		add(myController.getView().getPanel());
		refresh();
	}

	@Override
	public JComponent getContent() {
		return this;
	}

	@Override
	public IController getController() {
		return null;
	}

	@Override
	public void refresh() {
		myController.refreshMainPanelText();
	}
}
