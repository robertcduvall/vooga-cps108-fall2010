package arcade.core.examples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import arcade.core.Arcade;
import arcade.core.Tab;
import arcade.core.mvc.IController;

public class ExampleTab extends JPanel implements Tab {
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

	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
