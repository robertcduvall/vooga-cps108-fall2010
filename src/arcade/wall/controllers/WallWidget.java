package arcade.wall.controllers;

import javax.swing.JFrame;


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
