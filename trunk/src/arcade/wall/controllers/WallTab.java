package arcade.wall.controllers;

import javax.swing.JFrame;

/**
 * The VOOGA WallTab. 
 * @author Cam, John, Bhawana
 */
@SuppressWarnings("serial")
public class WallTab extends JFrame {

	private WallTabController myController;
	//TODO create model and pass into controller
	
	public WallTab(){
		super();
//		setToolTipText("Click here to see your Wall.");
//		setName("Wall");
		myController = new WallTabController();
		this.add(myController.getView().getPanel());
		this.pack();
		this.setVisible(true);
	}

//	@Override
//	public JComponent getContent() {
//		return myController.getView().getPanel();
//	}
}
