package arcade.store.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import arcade.store.control.Control;
import arcade.store.items.IItemInfo;

import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class GamePurchaseView {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="80,17"
	private JPanel panel = null;
	private JLabel coverArtHolder = null;
	private JButton purchaseButton = null;
	private JPanel centerPanel = null;
	private JTextField titleField = null;
	private JTextField priceField = null;
	private JTextArea descriptionField = null;
	private JLabel titleLabel = null;
	private JLabel priceLabel = null;
	private JLabel descriptionLabel = null;
	private Control controller;
	
	public GamePurchaseView(Control control) {
		JFrame frame = getJFrame();
		//frame.pack();
		frame.setVisible(true);
		controller = control;
	}
	
	
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(542, 321));
			jFrame.setTitle("Game Purchase View");
			jFrame.setContentPane(getPanel());
		}
		return jFrame;
	}

	/**
	 * This method initializes panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel() {
		if (panel == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(10);
			borderLayout.setVgap(10);
			coverArtHolder = new JLabel();
			panel = new JPanel();
			panel.setLayout(borderLayout);
			panel.add(coverArtHolder, BorderLayout.WEST);
			panel.add(getPurchaseButton(), BorderLayout.EAST);
			panel.add(getCenterPanel(), BorderLayout.CENTER);
		}
		return panel;
	}

	/**
	 * This method initializes purchaseButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPurchaseButton() {
		if (purchaseButton == null) {
		purchaseButton = new JButton();
			purchaseButton.setText("Add Game To Cart");
			purchaseButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					controller.processPurchaseButton(getTitleField().getText());
				}
			});
		}
		return purchaseButton;
	}

	
	public JLabel getCoverArt() {
		if(coverArtHolder == null) {
			coverArtHolder = new JLabel();
			
		}
		return coverArtHolder;
	}
	
	/**
	 * This method initializes centerPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 2;
			descriptionLabel = new JLabel();
			descriptionLabel.setText("Description:");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridy = 1;
			priceLabel = new JLabel();
			priceLabel.setText("Price: ");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridy = 0;
			titleLabel = new JLabel();
			titleLabel.setText("Title: ");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 3;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.anchor = GridBagConstraints.CENTER;
			gridBagConstraints2.gridwidth = 3;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints.gridx = 1;
			centerPanel = new JPanel();
			centerPanel.setLayout(new GridBagLayout());
			centerPanel.add(getTitleField(), gridBagConstraints);
			centerPanel.add(getPriceField(), gridBagConstraints1);
			centerPanel.add(getDescriptionField(), gridBagConstraints2);
			centerPanel.add(titleLabel, gridBagConstraints3);
			centerPanel.add(priceLabel, gridBagConstraints4);
			centerPanel.add(descriptionLabel, gridBagConstraints11);
		}
		return centerPanel;
	}

	/**
	 * This method initializes titleField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTitleField() {
		if (titleField == null) {
			titleField = new JTextField();
			titleField.setEditable(false);
		}
		return titleField;
	}

	/**
	 * This method initializes priceField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getPriceField() {
		if (priceField == null) {
			priceField = new JTextField();
			priceField.setEditable(false);
		}
		return priceField;
	}

	/**
	 * This method initializes descriptionField	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getDescriptionField() {
		if (descriptionField == null) {
			descriptionField = new JTextArea();
			descriptionField.setEditable(false);
			descriptionField.setLineWrap(true);
		}
		return descriptionField;
	}

	

}
