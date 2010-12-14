package arcade.wall.widget;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class WallWidget extends JFrame{

	public static void main(String[] args){
		WallWidget wallWidget = new WallWidget();
	}
	
	public WallWidget(){
		super();
		WallWidgetController controller = new WallWidgetController();
		this.add(controller.getView().getPanel());
		this.pack();
		this.setVisible(true);
	}
}
