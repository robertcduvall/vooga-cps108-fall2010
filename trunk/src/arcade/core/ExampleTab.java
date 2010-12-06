package arcade.core;

import javax.swing.*;

public class ExampleTab extends Tab {
	public ExampleTab() {
		setToolTipText("This is an example");
		setName("ExampleTab");
	}

	@Override
	public JComponent getContent() {
		JPanel panel = new JPanel();
		JButton a = new JButton("sdf");
		panel.add(a);
		return panel;
	}

}
