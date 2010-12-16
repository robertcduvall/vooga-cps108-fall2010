package arcade.store.gui.tabs;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import arcade.core.mvc.IController;
import arcade.store.control.CheckoutController;
import arcade.store.gui.StoreTab;

public class CheckoutTab extends StoreTab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private JPanel myCheckOutTab = null; //
	// @jve:decl-index=0:visual-constraint="199,58"
	private static final String NAME = /* TODO: "Checkout Page" */"Checkout Page";
	private static final String FILE_PATH = /*
											 * TODO:
											 * "arcade.store.resources.CheckoutController"
											 */"arcade.store.resources.CheckoutController"; // @jve:decl-index=0:

	private CheckoutController controller; // @jve:decl-index=0:
	private JTextField availableCredditsTextField;
	private JList itemsList;
	private JTextField totalCostTextField;
	private JTextField remainingCredditsTextField;

	/**
	 * This method initializes myCheckOutTab
	 * 
	 * @return javax.swing.JPanel
	 */
	public CheckoutTab() {
		setName(NAME);

		initializeComponents();

		setLayout(null);

		add(getCheckoutLabel(), null);
		add(getWishlistpanel(), null);
		add(getAvailableCredditsLabel(), null);
		add(getTotalCostLabel(), null);
		add(getCredditAfterPurchaseLabel(), null);
		add(getAvailableCredditsTextField(), null);
		add(getTotalCostTextField(), null);
		add(getRemainingCredditsTextField(), null);
		add(getDropItemsButton(), null);
		add(getJButton(), null);
		add(getBuyCartButton(), null);
		add(getDropCartButton(), null);
		add(getRefreshButton(), null);
	}

	private void initializeComponents() {
		controller = new CheckoutController(FILE_PATH);
		controller.addViewer(this);

		availableCredditsTextField = new JTextField();
		availableCredditsTextField.setBounds(new Rectangle(349, 87, 98, 25)); //TODO:

		itemsList = new JList();
		itemsList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				controller.processEvent("registerSelected");
			}
		});

		totalCostTextField = new JTextField();
		totalCostTextField.setBounds(new Rectangle(349, 122, 98, 25)); //TODO:

		remainingCredditsTextField = new JTextField();
		remainingCredditsTextField.setBounds(new Rectangle(349, 157, 98, 25)); //TODO:
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void refresh() {
		controller.initialize();
	}

	@Override
	public void setController(IController control) {
		controller = (CheckoutController) control;
	}

	@Override
	public IController getController() {
		return controller;
	}

	private JLabel getCheckoutLabel() {
		JLabel checkoutLabel = new JLabel();
		//TODO:
		checkoutLabel.setText("My Check Out: ");
		checkoutLabel.setBounds(new Rectangle(16, 15, 101, 32));

		return checkoutLabel;
	}

	private JLabel getAvailableCredditsLabel() {
		JLabel availableCredditsLabel = new JLabel();
		//TODO:
		availableCredditsLabel.setBounds(new Rectangle(222, 87, 124, 25));
		availableCredditsLabel.setText("Available Creddits");

		return availableCredditsLabel;
	}

	private JLabel getTotalCostLabel() {
		JLabel totalCostLabel = new JLabel();
		totalCostLabel.setBounds(new Rectangle(222, 122, 124, 25));
		totalCostLabel.setText("Total Cost");

		return totalCostLabel;
	}

	private JLabel getCredditAfterPurchaseLabel() {
		JLabel credditAfterPurchaseLabel = new JLabel();
		credditAfterPurchaseLabel.setBounds(new Rectangle(222, 157, 124, 25));
		credditAfterPurchaseLabel.setText("Remaining Creddits");

		return credditAfterPurchaseLabel;
	}

	/**
	 * This method initializes wishlistpanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getWishlistpanel() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
		JPanel wishlistpanel = new JPanel();
		wishlistpanel.setLayout(new GridBagLayout());
		wishlistpanel.setBounds(new Rectangle(17, 75, 184, 243));
		wishlistpanel.add(getItemsList(), gridBagConstraints);
		return wishlistpanel;
	}

	/**
	 * This method initializes availableCredditsTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getAvailableCredditsTextField() {
		return availableCredditsTextField;
	}

	// TODO: Comment
	/**
	 * Set the available creddits TextField to the parameter text.
	 */
	public void setAvailableCredditsTextField(String text) {
		availableCredditsTextField.setText(text);
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTotalCostTextField() {
		return totalCostTextField;
	}

	// TODO:
	public void setTotalCostTextField(String text) {
		totalCostTextField.setText(text);
	}

	/**
	 * This method initializes RemainingCredditsTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getRemainingCredditsTextField() {
		return remainingCredditsTextField;
	}

	// TODO:
	public void setRemainigCredditsTextField(String text) {
		remainingCredditsTextField.setText(text);
	}

	/**
	 * This method initializes buyItemsButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDropItemsButton() {
		JButton dropItemButton = new JButton();
		dropItemButton.setBounds(new Rectangle(218, 226, 100, 26));
		dropItemButton.setText("Drop Item");
		dropItemButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				controller.processEvent("dropItem");
			}
		});
		return dropItemButton;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		JButton saveCartButton = new JButton();
		saveCartButton.setBounds(new Rectangle(340, 226, 108, 26));
		saveCartButton.setText("Save Cart");
		saveCartButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.processEvent("saveCart");
			}
		});
		return saveCartButton;
	}

	/**
	 * This method initializes itemsList
	 * 
	 * @return javax.swing.JList
	 */
	private JList getItemsList() {
		return itemsList;
	}

	// TODO:
	public void setItemsList(String[] items) {
		itemsList.setListData(items);
	}

	// TODO:
	public Object getSelectedItem() {
		return itemsList.getSelectedValue();
	}

	/**
	 * This method initializes BuyCartButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBuyCartButton() {
		JButton buyCartButton = new JButton();
		buyCartButton.setBounds(new Rectangle(218, 267, 100, 26));
		buyCartButton.setText("Buy Cart");
		buyCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				controller.processEvent("buyCart");
			}
		});
		return buyCartButton;
	}

	/**
	 * This method initializes DropCartButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDropCartButton() {
		JButton dropCartButton = new JButton();
		dropCartButton.setBounds(new Rectangle(340, 267, 108, 26));
		dropCartButton.setText("Drop Cart");
		dropCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				controller.processEvent("dropCart");
			}
		});
		return dropCartButton;
	}

	/**
	 * This method initializes RefreshButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getRefreshButton() {
		JButton refreshButton = new JButton();
		refreshButton.setBounds(new Rectangle(291, 15, 87, 26));
		refreshButton.setText("Refresh");
		refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

				controller.processEvent("reset");
			}
		});
		return refreshButton;
	}

}