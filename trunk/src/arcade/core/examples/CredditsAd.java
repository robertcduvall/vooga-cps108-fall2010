package arcade.core.examples;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import arcade.core.Arcade;
import arcade.core.Window;

public class CredditsAd extends Window {
	
	private static final int STORE_TAB = 3;

	public CredditsAd() {
		super(200, 200);
		setName("ExampleWindow");
	}

	@Override
	protected void createContents() {
		JPanel panel = new JPanel();
		
		JLabel message=new JLabel("Moar Creddits?");
		
		JLabel image =new JLabel(new ImageIcon("src/arcade/store/gui/resources/TrollFace.png"));
		
		JButton linkToStore = new JButton("MOAR");
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
		
//		message.setAlignmentX(CENTER_ALIGNMENT);
//		linkToStore.setAlignmentX(LEFT_ALIGNMENT);
//		close.setAlignmentX(linkToStore.getAlignmentX());
		
		
		panel.add(message);
		panel.add(image);
		panel.add(linkToStore);
		panel.add(close);
		pack();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		add(panel);
	}

}
