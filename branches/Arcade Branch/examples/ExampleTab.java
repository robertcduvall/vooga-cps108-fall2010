package arcade.core.examples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import arcade.core.Arcade;
import arcade.core.Tab;

public class ExampleTab extends Tab {
	public ExampleTab() {
		setToolTipText("This is an example");
		setName("ExampleTab");
	}

	@Override
	public JComponent getContent() {
		JPanel panel = new JPanel();
		JButton a = new JButton("back");
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arcade.switchToTab(0);
			}
		});
		panel.add(a);
		return panel;
	}

}
