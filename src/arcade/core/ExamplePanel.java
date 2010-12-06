package arcade.core;

import javax.swing.JButton;

public class ExamplePanel extends Panel {

	public ExamplePanel() {
		JButton a = new JButton("Hello");

		add(a);
		setToolTipText("This is an example");
		setName("ExamplePanel");
	}

}
