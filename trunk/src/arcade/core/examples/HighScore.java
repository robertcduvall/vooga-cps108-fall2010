package arcade.core.examples;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import arcade.core.Window;

public class HighScore extends Window {
	public HighScore() {
		setSize(300, 120);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocation(300,300);
		createContents();
	}

	@Override
	protected void createContents() {

		
		JLabel l1=new JLabel("Congrats! You got a new High Score");
		JLabel l2=new JLabel("Would you like to submit your score?");
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
				HighScorePanel.addHighScore();
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
