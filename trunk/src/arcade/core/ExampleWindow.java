package arcade.core;

import javax.swing.JLabel;

public class ExampleWindow extends Window {

	public ExampleWindow() {
		super(400, 400);
		setName("ExampleWindow");
	}

	@Override
	protected void createContents() {
		JLabel a=new JLabel("ads");
		a.setHorizontalAlignment(WIDTH/2);
		getContentPane().add(a);
	}

}
