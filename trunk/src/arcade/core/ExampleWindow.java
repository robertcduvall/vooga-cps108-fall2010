package arcade.core;

import javax.swing.JButton;

public class ExampleWindow extends Window {

	public ExampleWindow() {
		super(400, 400);
		setName("ExampleWindow");
	}

	@Override
	protected void createContents() {
		JButton a = new JButton("sdf");
		getContentPane().add(a);
	}

}
