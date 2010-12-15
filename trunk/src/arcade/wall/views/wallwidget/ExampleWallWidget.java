package arcade.wall.views.wallwidget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import arcade.wall.controllers.WallWidget;

/**
 * An example to show how to pull up WallWidget.
 * @author Bhawana, John, Cam
 *
 */
public class ExampleWallWidget{
	
	public ExampleWallWidget() {
		JFrame frame = new JFrame();
		frame.setSize(200,200);
		frame.setVisible(true);
		
		JButton button = new JButton("Review");
		button.addActionListener(new ReviewButtonListener());
		frame.add(button);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class ReviewButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			WallWidget wallWidget = new WallWidget();
		}
	}

	public static void main(String[] args){
		ExampleWallWidget exampleWidget = new ExampleWallWidget();
	}
}
