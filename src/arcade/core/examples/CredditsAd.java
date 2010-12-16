package arcade.core.examples;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import arcade.core.Arcade;
import arcade.core.api.Window;

public class CredditsAd extends Window {
	
	private static final int STORE_TAB = 3;

	public CredditsAd() {
		super(250, 250);
		setName("Creddits Ad");
	}

	@Override
	protected void createContents() {
		JPanel panel = new JPanel();
		
		JLabel message=new JLabel("Moar Store?");
		
		JLabel image =new JLabel(new ImageIcon("src/arcade/store/gui/resources/TrollFace.png"));
		
		JButton linkToStore = new JButton("View Store");
		linkToStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arcade.switchToTab(STORE_TAB);
				setVisible(false);
		        dispose();
			}
		});
		
		JButton close=new JButton("close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
		        dispose();
			}
		});
		
		message.setAlignmentX(CENTER_ALIGNMENT);
		image.setAlignmentX(CENTER_ALIGNMENT);
		linkToStore.setAlignmentX(CENTER_ALIGNMENT);
		close.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.add(message);
		panel.add(image);
		panel.add(linkToStore);
		panel.add(close);
		pack();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		add(panel);
	}

}
