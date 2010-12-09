package arcade.core.examples;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import arcade.core.ExampleGUI;
import arcade.core.Window;

public class HighScore extends Window {
	private String gameName;
	public HighScore(String gn) {
		super(300, 120);
		gameName=gn;
	}

	@Override
	protected void createContents() {

		
		JLabel l1=new JLabel("Congrats! You got a new High Score in "
				+ gameName);
		JLabel l2=new JLabel("Would you like to submit your high score?");
		JPanel panel = new JPanel();
		panel.add(l1);
		panel.add(l2);
		panel.add(makeButtons());
		add(panel);
	}

	private JPanel makeButtons() {
		JPanel panel=new JPanel();
		JButton yes = new JButton("Yes");
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExampleGUI.addHighScore();
				setVisible(false);
				dispose();
			}

		});
		JButton no = new JButton("No");
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		panel.add(yes);
		panel.add(no);
		return panel;
	}
}
