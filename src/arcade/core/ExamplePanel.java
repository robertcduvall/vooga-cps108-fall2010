package arcade.core;

import javax.swing.JButton;

public class ExamplePanel extends Panel {

	public ExamplePanel() {
		JButton a = new JButton("Hello");

		add(a);
	}

	@Override
	public String getComponentName() {
		return "ExampleTab";
	}

	@Override
	public String getComponentDescription() {
		return "This is an example";
	}

}
