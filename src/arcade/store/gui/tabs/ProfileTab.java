package arcade.store.gui.tabs;

import javax.swing.JComponent;
import arcade.core.mvc.IController;
import arcade.store.control.ProfileController;
import arcade.store.gui.StoreTab;

import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProfileTab extends StoreTab {

	// private JPanel jPanel = null; //
	// @jve:decl-index=0:visual-constraint="133,-45"

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NAME = "Shop Profile";
	private static final String FILE_PATH = "arcade.store.resources.ProfileController";

	private ProfileController controller;
	private JButton purchaseCredditsButton;
	private JTextField usernameTextField;
	private JTextField availableCredditsTextField;
	private JTable purchasedItemsTable;

	public ProfileTab() {
		initializeProfileTab();

		add(getProfileTabMessageLabel(), null);
		add(getUserImageLabel(), null);
		add(getUsernameTextField(), null);
		add(getAvailableCredditsLabel(), null);
		add(getAvailableCredditsTextField(), null);
		add(getPurchaseCredditsButton(), null);
		add(getEditMyProfileButton(), null);
		add(getItemTableScrollPane(), null);
		add(getPurchasedItemsLabel(), null);
		add(getRefreshButton(), null);
	}

	private void initializeProfileTab() {
		controller = new ProfileController(FILE_PATH);
		controller.addViewer(this);

		purchaseCredditsButton = new JButton();
		purchaseCredditsButton.setBounds(new Rectangle(486, 158, 149, 32));
		purchaseCredditsButton.setText("Purchase Creddits");
		purchaseCredditsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.processEvent("purchasePopUp");
			}
		});

		usernameTextField = new JTextField();
		usernameTextField.setHorizontalAlignment(JTextField.CENTER); // Borrowed
																		// from
																		// http://www.exampledepot.com/egs/javax.swing.text/tf_Align.html
		usernameTextField.setEditable(false);
		usernameTextField.setBounds(new Rectangle(102, 198, 142, 26));

		availableCredditsTextField = new JTextField();
		availableCredditsTextField.setBounds(new Rectangle(506, 94, 111, 29));

		purchasedItemsTable = new JTable();

		setName(NAME);
		setLayout(null);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setController(IController control) {
		controller = (ProfileController) control;
	}

	@Override
	public IController getController() {
		return controller;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	@Override
	public JComponent getContent() {
		return this;
	}

	/**
	 * This method initializes usernameTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public void setUsernameTextField(String name) {
		usernameTextField.setText(name);
	}

	/**
	 * This method initializes availableCredditsTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getAvailableCredditsTextField() {
		return availableCredditsTextField;
	}

	public void setAvailableCredditsTextField(String creddits) {
		availableCredditsTextField.setText(creddits);
	}

	/**
	 * This method initializes purchaseCredditsButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getPurchaseCredditsButton() {
		return purchaseCredditsButton;
	}

	public void checkPurchaseCredditsButtonPriviliges(boolean privilege) {
		purchaseCredditsButton.setEnabled(privilege);
	}

	/**
	 * This method initializes editMyProfileButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getEditMyProfileButton() {
		JButton editMyProfileButton = new JButton();
		editMyProfileButton.setBounds(new Rectangle(486, 211, 149, 32));
		editMyProfileButton.setText("Edit My Profile");
		editMyProfileButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});
		return editMyProfileButton;
	}

	/**
	 * This method initializes purchasedGamesTable
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getPurchasedItemsTable() {
		return purchasedItemsTable;
	}

	public void setPurchasedItemsTableModel(DefaultTableModel model) {
		purchasedItemsTable.setModel(model);
	}

	public JScrollPane getItemTableScrollPane() {
		JScrollPane itemTableScrollPane = new JScrollPane();
		itemTableScrollPane.setViewportView(getPurchasedItemsTable());
		itemTableScrollPane.setBounds(new Rectangle(102, 347, 542, 256));
		return itemTableScrollPane;
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

				controller.processEvent("refresh");
			}
		});
		return refreshButton;
	}

	private JLabel getUserImageLabel() {
		JLabel userImageLabel = new JLabel();
		userImageLabel.setBounds(new Rectangle(102, 54, 142, 132));
		try {
			userImageLabel.setIcon(new ImageIcon(""));
		} catch (Exception e) {
			throw TabExceptions.IMAGE_NOT_FOUND;
		}
		return userImageLabel;
	}

	private JLabel getProfileTabMessageLabel() {
		JLabel profileTabMessageLabel = new JLabel();
		profileTabMessageLabel.setText("Manage My Shop Account");
		profileTabMessageLabel.setBounds(new Rectangle(13, 14, 163, 16));
		return profileTabMessageLabel;
	}

	private JLabel getAvailableCredditsLabel() {
		JLabel availableCredditsLabel = new JLabel();
		availableCredditsLabel.setBounds(new Rectangle(361, 94, 111, 29));
		availableCredditsLabel.setText("Availabe Creddits");
		return availableCredditsLabel;
	}

	private JLabel getPurchasedItemsLabel() {
		JLabel purchasedItemsLabel = new JLabel();
		purchasedItemsLabel.setBounds(new Rectangle(102, 289, 139, 35));
		purchasedItemsLabel.setText("Purchased Items");
		return purchasedItemsLabel;
	}

	@Override
	public void refresh() {
		controller.initialize();
	}

}
