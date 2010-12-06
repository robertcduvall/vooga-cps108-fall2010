package arcade.core.examples;

import javax.swing.JButton;

import arcade.core.Panel;

public class ExamplePanel extends Panel {

	public ExamplePanel() {
		JButton a = new JButton("Hello");

		add(a);
		setToolTipText("This is an example");
		setName("ExamplePanel");
	}

}
