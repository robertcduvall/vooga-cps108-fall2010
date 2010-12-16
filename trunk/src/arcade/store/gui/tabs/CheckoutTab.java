package arcade.store.gui.tabs;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private static final String NAME = "Checkout Page";
	private static final String FILE_PATH = "arcade.store.resources.CheckoutController"; // @jve:decl-index=0:
	private static final String CHECKOUT_TAB_FILE_PATH = "arcade.store.gui.resources.storeTabProperties";

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
		setResourceBundleFilePath(CHECKOUT_TAB_FILE_PATH);
		setName(NAME);

		initializeCheckoutTab();

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
//		add(getRefreshButton(), null);
	}

	private void initializeCheckoutTab() {
		controller = new CheckoutController(FILE_PATH);
		controller.addViewer(this);

		availableCredditsTextField = new JTextField();
		availableCredditsTextField
				.setBounds(new Rectangle(
						Integer.parseInt(getString("availableCredditsTextFieldXString")),
						Integer.parseInt(getString("availableCredditsTextFieldYString")),
						Integer.parseInt(getString("availableCredditsTextFieldWidthString")),
						Integer.parseInt(getString("availableCredditsTextFieldHeightString"))));
		itemsList = new JList();
		itemsList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				controller.processEvent("registerSelected");
			}
		});

		totalCostTextField = new JTextField();
		totalCostTextField.setBounds(new Rectangle(Integer
				.parseInt(getString("totalCostTextFieldXString")), Integer
				.parseInt(getString("totalCostTextFieldYString")), Integer
				.parseInt(getString("totalCostTextFieldWidthString")), Integer
				.parseInt(getString("totalCostTextFieldHeightString"))));

		remainingCredditsTextField = new JTextField();
		remainingCredditsTextField
				.setBounds(new Rectangle(
						Integer.parseInt(getString("remainingCredditsTextFieldXString")),
						Integer.parseInt(getString("remainingCredditsTextFieldYString")),
						Integer.parseInt(getString("remainingCredditsTextFieldWidthString")),
						Integer.parseInt(getString("remainingCredditsTextFieldHeightString"))));
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
		checkoutLabel.setText(getString("checkoutLabelString"));
		checkoutLabel.setBounds(new Rectangle(Integer
				.parseInt(getString("checkoutLabelXString")), Integer
				.parseInt(getString("checkoutLabelYString")), Integer
				.parseInt(getString("checkoutLabelWidthString")), Integer
				.parseInt(getString("checkoutLabelHeightString"))));
		return checkoutLabel;
	}

	private JLabel getAvailableCredditsLabel() {
		JLabel availableCredditsLabel = new JLabel();
		availableCredditsLabel
				.setBounds(new Rectangle(
						Integer.parseInt(getString("availableCredditsLabelXString")),
						Integer.parseInt(getString("availableCredditsLabelYString")),
						Integer.parseInt(getString("availableCredditsLabelWidthString")),
						Integer.parseInt(getString("availableCredditsLabelHeightString"))));

		availableCredditsLabel
				.setText(getString("availableCredditsLabelString"));

		return availableCredditsLabel;
	}

	private JLabel getTotalCostLabel() {
		JLabel totalCostLabel = new JLabel();
		totalCostLabel.setBounds(new Rectangle(Integer
				.parseInt(getString("totalCostLabelXString")), Integer
				.parseInt(getString("totalCostLabelYString")), Integer
				.parseInt(getString("totalCostLabelWidthString")), Integer
				.parseInt(getString("totalCostLabelHeightString"))));

		totalCostLabel.setText(getString("totalCostLabelString"));

		return totalCostLabel;
	}

	private JLabel getCredditAfterPurchaseLabel() {
		JLabel credditAfterPurchaseLabel = new JLabel();
		credditAfterPurchaseLabel
				.setBounds(new Rectangle(
						Integer.parseInt(getString("credditAfterPurchaseLabelXString")),
						Integer.parseInt(getString("credditAfterPurchaseLabelYString")),
						Integer.parseInt(getString("credditAfterPurchaseLabelWidthString")),
						Integer.parseInt(getString("credditAfterPurchaseLabelHeightString"))));

		credditAfterPurchaseLabel
				.setText(getString("credditAfterPurchaseLabelString"));

		return credditAfterPurchaseLabel;
	}

	/**
	 * This method initializes wishlistpanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getWishlistpanel() {
		JPanel wishListPanel = new JPanel();
		wishListPanel.setLayout(new GridBagLayout());
		wishListPanel.setBounds(new Rectangle(Integer
				.parseInt(getString("wishlistpanelXString")), Integer
				.parseInt(getString("wishlistpanelYString")), Integer
				.parseInt(getString("wishlistpanelWidthString")), Integer
				.parseInt(getString("wishlistpanelHeightString"))));

		wishListPanel.add(getItemsList(), makeGridBagConstraints());
		return wishListPanel;
	}

	/**
	 * Creates a GridBagConstaints object
	 * 
	 * @return gridBagConstraints
	 */
	private GridBagConstraints makeGridBagConstraints() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = Integer
				.parseInt(getString("gridBagConstraintsGridYString"));
		gridBagConstraints.weightx = Double
				.parseDouble(getString("gridBagConstraintsWeightXString"));
		gridBagConstraints.weighty = Double
				.parseDouble(getString("gridBagConstraintsWeightYString"));
		gridBagConstraints.gridx = Integer
				.parseInt(getString("gridBagConstraintsGridXString"));
		;
		return gridBagConstraints;
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
		dropItemButton.setBounds(new Rectangle(Integer
				.parseInt(getString("dropItemButtonXString")), Integer
				.parseInt(getString("dropItemButtonYString")), Integer
				.parseInt(getString("dropItemButtonWidthString")), Integer
				.parseInt(getString("dropItemButtonHeightString"))));

		dropItemButton.setText(getString("dropItemButtonString"));
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
		saveCartButton.setBounds(new Rectangle(Integer
				.parseInt(getString("saveCartButtonXString")), Integer
				.parseInt(getString("saveCartButtonYString")), Integer
				.parseInt(getString("saveCartButtonWidthString")), Integer
				.parseInt(getString("saveCartButtonHeightString"))));

		saveCartButton.setText(getString("saveCartButtonString"));
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
		buyCartButton.setBounds(new Rectangle(Integer
				.parseInt(getString("buyCartButtonXString")), Integer
				.parseInt(getString("buyCartButtonYString")), Integer
				.parseInt(getString("buyCartButtonWidthString")), Integer
				.parseInt(getString("buyCartButtonHeightString"))));

		buyCartButton.setText(getString("buyCartButtonString"));
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
		dropCartButton.setBounds(new Rectangle(Integer
				.parseInt(getString("dropCartButtonXString")), Integer
				.parseInt(getString("dropCartButtonYString")), Integer
				.parseInt(getString("dropCartButtonWidthString")), Integer
				.parseInt(getString("dropCartButtonHeightString"))));

		dropCartButton.setText(getString("dropCartButtonString"));
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
		refreshButton.setBounds(new Rectangle(Integer
				.parseInt(getString("refreshButtonXString")), Integer
				.parseInt(getString("refreshButtonYString")), Integer
				.parseInt(getString("refreshButtonWidthString")), Integer
				.parseInt(getString("refreshButtonHeightString"))));

		refreshButton.setText(getString("refreshButtonString"));
		refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

				controller.processEvent("reset");
			}
		});
		return refreshButton;
	}
}