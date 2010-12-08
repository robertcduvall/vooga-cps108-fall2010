package arcade.store.gui.guitabs;

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

public class MyCheckoutGUI extends Tab{

	private JPanel myCheckOutTab = null;  //  @jve:decl-index=0:visual-constraint="199,58"
	private JLabel checkoutLabel = null;
	private JPanel wishlistpanel = null;
	private JLabel AvailableCreditjLabel = null;
	private JLabel totalCostLabel = null;
	private JLabel credditAfterPurchaseLabel = null;
	private JTextField availableCredditsTextField = null;
	private JTextField jTextField = null;
	private JTextField RemainingCredditsTextField1 = null;
	private JButton buyItemsButton = null;
	private JButton jButton = null;
	private JList itemsList = null;
	/**
	 * This method initializes myCheckOutTab	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	
	public MyCheckoutGUI()
	{
		setName("My Checkouts");
	}
	
	public JComponent getContent() {
		if (myCheckOutTab == null) {
			credditAfterPurchaseLabel = new JLabel();
			credditAfterPurchaseLabel.setBounds(new Rectangle(227, 163, 119, 28));
			credditAfterPurchaseLabel.setText("Remaining Creddits");
			totalCostLabel = new JLabel();
			totalCostLabel.setBounds(new Rectangle(225, 122, 119, 24));
			totalCostLabel.setText("Total Cost");
			AvailableCreditjLabel = new JLabel();
			AvailableCreditjLabel.setBounds(new Rectangle(222, 87, 121, 25));
			AvailableCreditjLabel.setText("Available Creddits");
			checkoutLabel = new JLabel();
			checkoutLabel.setText("My Check Out: ");
			checkoutLabel.setBounds(new Rectangle(16, 15, 86, 32));
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
			availableCredditsTextField.setBounds(new Rectangle(349, 88, 98, 25));
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
			jTextField.setBounds(new Rectangle(351, 124, 97, 27));
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
			RemainingCredditsTextField1.setBounds(new Rectangle(353, 164, 97, 25));
		}
		return RemainingCredditsTextField1;
	}

	/**
	 * This method initializes buyItemsButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuyItemsButton() {
		if (buyItemsButton == null) {
			buyItemsButton = new JButton();
			buyItemsButton.setBounds(new Rectangle(218, 226, 100, 26));
			buyItemsButton.setText("Buy Item(s)");
		}
		return buyItemsButton;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(340, 226, 104, 27));
			jButton.setText("Drop Item(s)");
		}
		return jButton;
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



}
