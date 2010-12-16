package arcade.wall.controllers;

import javax.swing.JFrame;

/**
 * JFrame holder that contains a WallWidget. This JFrame appears when a user starts a game.
 * @author Cameron McCallie
 * @author John Kline  
 * @author Bhawana Singh
 */
@SuppressWarnings("serial")
public class WallWidget extends JFrame{
	
	public WallWidget(String gameName){
		super();
		WallWidgetController controller = new WallWidgetController();
		controller.setTitle(gameName);
		this.setTitle("WallWidget");
		this.add(controller.getView().getPanel());
		this.pack();
		this.setVisible(true);
	}
}
