package arcade.store.gui.pages;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import arcade.core.Arcade;
import arcade.core.Window;
import arcade.store.control.ProfileController;

public class CredditPurchaseView extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CREDDITS_ADDED_STRING = "Creddits Added to Your Account!";
	private ProfileController controller;
	private JTextField credditsTextField;
	
	public CredditPurchaseView(ProfileController control) {
		super(500, 100);
		setName("Purchase Creddits");
		controller = control;
	}

	@Override
	protected void createContents() {
		
		JPanel panel = new JPanel();
		JLabel messageLabel=new JLabel("Enter Number of Creddits:");
		credditsTextField = new JTextField("       ");
		JLabel credditsAddedLabel = new JLabel("");
		
		JButton addCredditsButton = new JButton("Add Creddits");
		addCredditsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
		        controller.processCredditPurchase(credditsTextField.getText());
		        closeWindow();
			}
		});
		
		JButton backToStoreButton=new JButton("View Store");
		backToStoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
		        dispose();
			}
		});
		
		panel.setLayout(new FlowLayout());
		panel.add(messageLabel);
		panel.add(credditsTextField);
		panel.add(addCredditsButton);
		panel.add(credditsAddedLabel);
		panel.add(backToStoreButton);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				
		add(panel);
	}
	
	private void closeWindow() {
		this.setVisible(false);
	}

}
