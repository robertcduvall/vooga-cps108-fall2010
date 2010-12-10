package arcade.store.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import arcade.core.Arcade;
import arcade.core.Window;

public class CredditPurchaseView extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CREDDITS_ADDED_STRING = "Creddits Added to Your Account!";

	public CredditPurchaseView() {
		super(500, 250);
		setName("Purchase Creddits");
	}

	@Override
	protected void createContents() {
		
		JPanel panel = new JPanel();
		JLabel messageLabel=new JLabel("Enter Number of Creddits:");
		JTextField credditsTextField = new JTextField("");
		JLabel credditsAddedLabel = new JLabel("");
		
		JButton addCredditsButton = new JButton("Add Creddits");
		addCredditsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        dispose();
			}
		});
		
		JButton close=new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
		        dispose();
			}
		});
		
		messageLabel.setAlignmentX(CENTER_ALIGNMENT);
		close.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.add(messageLabel);
		panel.add(credditsTextField);
		panel.add(addCredditsButton);
		panel.add(credditsAddedLabel);
		panel.add(close);
		pack();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		add(panel);
	}

}
