package arcade.core.examples;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import arcade.core.Window;

public class ExampleWindow extends Window {

	public ExampleWindow() {
		super(200, 200);
		setName("ExampleWindow");
	}

	@Override
	protected void createContents() {
		JPanel panel = new JPanel();
		JLabel a=new JLabel("Moar Creddits?");
		JLabel image =new JLabel(new ImageIcon("src/arcade/store/gui/resources/TrollFace.png"));
		JButton b=new JButton("close");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
		        dispose();
			}
		});
		a.setAlignmentX(CENTER_ALIGNMENT);
		b.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(a);
		panel.add(image);
		panel.add(b);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		add(panel);
	}

}
