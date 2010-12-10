package arcade.store.gui.tabs;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import arcade.core.Tab;

public class CheckoutTab extends Tab{

	private JPanel myCheckOutTab = null;  //  @jve:decl-index=0:visual-constraint="199,58"
	private JLabel checkoutLabel = null;
	private JPanel wishlistpanel = null;
	private JLabel AvailableCreditjLabel = null;
	private JLabel totalCostLabel = null;
	private JLabel credditAfterPurchaseLabel = null;
	private JTextField availableCredditsTextField = null;
	private JTextField jTextField = null;
	private JTextField RemainingCredditsTextField1 = null;
	private JButton buyItemButton = null;
	private JButton dropItemButton = null;
	private JList itemsList = null;
	private JButton buyCartButton = null;
	private JButton dropCartButton = null;
	/**
	 * This method initializes myCheckOutTab	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	
	public CheckoutTab()
	{
		setName("My Checkouts");
	}
	
	public JComponent getContent() {
		if (myCheckOutTab == null) {
			credditAfterPurchaseLabel = new JLabel();
			credditAfterPurchaseLabel.setBounds(new Rectangle(222, 157, 124, 25));
			credditAfterPurchaseLabel.setText("Remaining Creddits");
			totalCostLabel = new JLabel();
			totalCostLabel.setBounds(new Rectangle(222, 122, 124, 25));
			totalCostLabel.setText("Total Cost");
			AvailableCreditjLabel = new JLabel();
			AvailableCreditjLabel.setBounds(new Rectangle(222, 87, 124, 25));
			AvailableCreditjLabel.setText("Available Creddits");
			checkoutLabel = new JLabel();
			checkoutLabel.setText("My Check Out: ");
			checkoutLabel.setBounds(new Rectangle(16, 15, 101, 32));
			myCheckOutTab = new JPanel();
			myCheckOutTab.setLayout(null);
			myCheckOutTab.setSize(new Dimension(460, 381));
			myCheckOutTab.add(checkoutLabel, null);
			myCheckOutTab.add(getWishlistpanel(), null);
			myCheckOutTab.add(AvailableCreditjLabel, null);
			myCheckOutTab.add(totalCostLabel, null);
			myCheckOutTab.add(credditAfterPurchaseLabel, null);
			myCheckOutTab.add(getAvailableCredditsTextField(), null);
			myCheckOutTab.add(getJTextField(), null);
			myCheckOutTab.add(getRemainingCredditsTextField1(), null);
			myCheckOutTab.add(getBuyItemsButton(), null);
			myCheckOutTab.add(getJButton(), null);
			myCheckOutTab.add(getBuyCartButton(), null);
			myCheckOutTab.add(getDropCartButton(), null);
		}
//		setName("My Checkouts");
		return myCheckOutTab;
	}

	/**
	 * This method initializes wishlistpanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getWishlistpanel() {
		if (wishlistpanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			wishlistpanel = new JPanel();
			wishlistpanel.setLayout(new GridBagLayout());
			wishlistpanel.setBounds(new Rectangle(17, 75, 184, 243));
			wishlistpanel.add(getItemsList(), gridBagConstraints);
		}
		return wishlistpanel;
	}

	/**
	 * This method initializes availableCredditsTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAvailableCredditsTextField() {
		if (availableCredditsTextField == null) {
			availableCredditsTextField = new JTextField();
			availableCredditsTextField.setBounds(new Rectangle(349, 87, 98, 25));
		}
		return availableCredditsTextField;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(349, 122, 98, 25));
		}
		return jTextField;
	}

	/**
	 * This method initializes RemainingCredditsTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRemainingCredditsTextField1() {
		if (RemainingCredditsTextField1 == null) {
			RemainingCredditsTextField1 = new JTextField();
			RemainingCredditsTextField1.setBounds(new Rectangle(349, 157, 98, 25));
		}
		return RemainingCredditsTextField1;
	}

	/**
	 * This method initializes buyItemsButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuyItemsButton() {
		if (buyItemButton == null) {
			buyItemButton = new JButton();
			buyItemButton.setBounds(new Rectangle(218, 226, 100, 26));
			buyItemButton.setText("Buy Item");
		}
		return buyItemButton;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (dropItemButton == null) {
			dropItemButton = new JButton();
			dropItemButton.setBounds(new Rectangle(340, 226, 100, 26));
			dropItemButton.setText("Drop Item");
		}
		return dropItemButton;
	}

	/**
	 * This method initializes itemsList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getItemsList() {
		if (itemsList == null) {
			itemsList = new JList();
		}
		return itemsList;
	}

	/**
	 * This method initializes buyCartButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuyCartButton() {
		if (buyCartButton == null) {
			buyCartButton = new JButton();
			//218, 226, 100, 26
			buyCartButton.setBounds(new Rectangle(218, 267, 100, 26));
			buyCartButton.setText("Buy Cart");
		}
		return buyCartButton;
	}

	/**
	 * This method initializes dropCartButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDropCartButton() {
		if (dropCartButton == null) {
			dropCartButton = new JButton();
			dropCartButton.setBounds(new Rectangle(340, 267, 100, 26));
			dropCartButton.setText("Drop Cart");
		}
		return dropCartButton;
	}



}
