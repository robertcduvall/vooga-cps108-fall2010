package arcade.store.test;


import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import arcade.core.api.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;
import arcade.store.test.CheckoutController;
import arcade.store.control.MainController;

public class CheckoutGUI implements Tab {

	// private JPanel myCheckOutTab = null; //
	// @jve:decl-index=0:visual-constraint="199,58"
	private JLabel checkoutLabel = null;
	private JPanel wishlistpanel = null;
	private JLabel AvailableCreditjLabel = null;
	private JLabel totalCostLabel = null;
	private JLabel credditAfterPurchaseLabel = null;
	private JTextField availableCredditsTextField = null;
	private JTextField jTextField = null;
	private JTextField RemainingCredditsTextField1 = null;
	private JButton DropItemButton = null;
	private JButton SaveCartButton = null;
	private JList itemsList = null;
	private JButton BuyCartButton = null;
	private JButton DropCartButton = null;

	private static final String NAME = "Checkout Page";

	private CheckoutController controller; // @jve:decl-index=0:
	private JButton RefreshButton = null;
	
	public static void main(String args[])
	{
		new CheckoutGUI();
	}
	
	
	/**
	 * This method initializes myCheckOutTab
	 * 
	 * @return javax.swing.JPanel
	 */
	public CheckoutGUI() {
		controller = new CheckoutController("arcade.store.test.checkoutMethods");
		controller.addViewer(this);

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

		JFrame frame = new JFrame();
		
		
		frame.add(checkoutLabel, null);
		frame.add(getWishlistpanel(), null);
		frame.add(AvailableCreditjLabel, null);
		frame.add(totalCostLabel, null);
		frame.add(credditAfterPurchaseLabel, null);
		frame.add(getAvailableCredditsTextField(), null);
		frame.add(getTotalCostTextField(), null);
		frame.add(getRemainingCredditsTextField(), null);
		frame.add(getDropItemsButton(), null);
		frame.add(getJButton(), null);
		frame.add(getBuyCartButton(), null);
		frame.add(getDropCartButton(), null);
		frame.add(getRefreshButton(), null);
		frame.pack();
		frame.setVisible(true);
	}



	@Override
	public void refresh() {
		controller.initialize();
	}


	@Override
	public IController getController() {
		return controller;
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
	public JTextField getAvailableCredditsTextField() {
		if (availableCredditsTextField == null) {
			availableCredditsTextField = new JTextField();
			availableCredditsTextField
					.setBounds(new Rectangle(349, 87, 98, 25));
		}
		return availableCredditsTextField;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField getTotalCostTextField() {
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
	public JTextField getRemainingCredditsTextField() {
		if (RemainingCredditsTextField1 == null) {
			RemainingCredditsTextField1 = new JTextField();
			RemainingCredditsTextField1.setBounds(new Rectangle(349, 157, 98,
					25));
		}
		return RemainingCredditsTextField1;
	}

	/**
	 * This method initializes buyItemsButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDropItemsButton() {
		if (DropItemButton == null) {
			DropItemButton = new JButton();
			DropItemButton.setBounds(new Rectangle(218, 226, 100, 26));
			DropItemButton.setText("Drop Item");
			DropItemButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					controller.processEvent("confirmDropItem");
				}
			});
		}
		return DropItemButton;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (SaveCartButton == null) {
			SaveCartButton = new JButton();
			SaveCartButton.setBounds(new Rectangle(340, 226, 108, 26));
			SaveCartButton.setText("Save Cart");
			SaveCartButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					controller.processEvent("saveCart");
				}
			});
		}
		return SaveCartButton;
	}

	/**
	 * This method initializes itemsList
	 * 
	 * @return javax.swing.JList
	 */
	public JList getItemsList() {
		if (itemsList == null) {
			itemsList = new JList();

			itemsList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					controller.processEvent("registerSelected");
				}
			});

		}
		return itemsList;
	}

	/**
	 * This method initializes BuyCartButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBuyCartButton() {
		if (BuyCartButton == null) {
			BuyCartButton = new JButton();
			// 218, 226, 100, 26
			BuyCartButton.setBounds(new Rectangle(218, 267, 100, 26));
			BuyCartButton.setText("Buy Cart");
			BuyCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					controller.processEvent("confirmBuyCart");
				}
			});
		}
		return BuyCartButton;
	}

	/**
	 * This method initializes DropCartButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDropCartButton() {
		if (DropCartButton == null) {
			DropCartButton = new JButton();
			DropCartButton.setBounds(new Rectangle(340, 267, 108, 26));
			DropCartButton.setText("Drop Cart");
			DropCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					controller.processEvent("confirmDropCart");
				}
			});
		}
		return DropCartButton;
	}

	/**
	 * This method initializes RefreshButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getRefreshButton() {
		if (RefreshButton == null) {
			RefreshButton = new JButton();
			RefreshButton.setBounds(new Rectangle(291, 15, 87, 26));
			RefreshButton.setText("Refresh");
			RefreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {

					controller.initialize();
				}
			});
		}
		return RefreshButton;
	}


	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}


}
