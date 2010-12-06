package arcade.core;

import javax.swing.JButton;

public class ExampleWindow extends Window {

	public ExampleWindow() {
		super(400, 400);
	}

	@Override
	public String getComponentName() {
		return "ExampleWindow";
	}

	@Override
	public String getComponentDescription() {
		return "This is an example";
	}

	@Override
	protected void createContents() {
		JButton a = new JButton("sdf");
		getContentPane().add(a);
	}

}
