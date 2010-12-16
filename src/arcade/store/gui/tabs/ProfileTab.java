package arcade.store.gui.tabs;

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

	private static final long serialVersionUID = 1L;
	private static final String NAME = "Shop Profile";
	private static final String FILE_PATH = "arcade.store.resources.ProfileController";
	private static final String PROFILE_TAB_FILE_PATH = "arcade.store.gui.resources.storeTabProperties";

	private ProfileController controller;
	private JButton purchaseCredditsButton;
	private JTextField usernameTextField;
	private JTextField availableCredditsTextField;
	private JTable purchasedItemsTable;

	public ProfileTab() {
		setResourceBundleFilePath(PROFILE_TAB_FILE_PATH);

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
		purchaseCredditsButton
				.setBounds(new Rectangle(
						Integer.parseInt(getString("purchaseCredditsButtonXString")),
						Integer.parseInt(getString("purchaseCredditsButtonYString")),
						Integer.parseInt(getString("purchaseCredditsButtonWidthString")),
						Integer.parseInt(getString("purchaseCredditsButtonHeightString"))));

		purchaseCredditsButton
				.setText(getString("purchaseCredditsButtonString"));
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
		usernameTextField.setBounds(new Rectangle(Integer
				.parseInt(getString("usernameTextFieldXString")), Integer
				.parseInt(getString("usernameTextFieldYString")), Integer
				.parseInt(getString("usernameTextFieldWidthString")), Integer
				.parseInt(getString("usernameTextFieldHeightString"))));

		availableCredditsTextField = new JTextField();
		availableCredditsTextField
				.setBounds(new Rectangle(
						Integer.parseInt(getString("availableCredditsTextFieldXProfileString")),
						Integer.parseInt(getString("availableCredditsTextFieldYProfileString")),
						Integer.parseInt(getString("availableCredditsTextFieldWidthProfileString")),
						Integer.parseInt(getString("availableCredditsTextFieldHeightProfileString"))));

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

	public void setUserImage(ImageIcon image) {
		getUserImageLabel().setIcon(image);
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
		editMyProfileButton.setBounds(new Rectangle(Integer
				.parseInt(getString("editMyProfileButtonXString")), Integer
				.parseInt(getString("editMyProfileButtonYString")), Integer
				.parseInt(getString("editMyProfileButtonWidthString")), Integer
				.parseInt(getString("editMyProfileButtonHeightString"))));

		editMyProfileButton.setText(getString("editMyProfileButtonString"));
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
		itemTableScrollPane.setBounds(new Rectangle(Integer
				.parseInt(getString("itemTableScrollPaneXString")), Integer
				.parseInt(getString("itemTableScrollPaneYString")), Integer
				.parseInt(getString("itemTableScrollPaneWidthString")), Integer
				.parseInt(getString("itemTableScrollPaneHeightString"))));

		return itemTableScrollPane;
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

				controller.processEvent("refresh");
			}
		});
		return refreshButton;
	}

	private JLabel getUserImageLabel() {
		JLabel userImageLabel = new JLabel();
		userImageLabel.setBounds(new Rectangle(Integer
				.parseInt(getString("userImageLabelXString")), Integer
				.parseInt(getString("userImageLabelYString")), Integer
				.parseInt(getString("userImageLabelWidthString")), Integer
				.parseInt(getString("userImageLabelHeightString"))));

		try {
			userImageLabel.setIcon(new ImageIcon(
					getString("userImageLabelString")));
		} catch (Exception e) {
			throw TabExceptions.IMAGE_NOT_FOUND;
		}
		return userImageLabel;
	}

	private JLabel getProfileTabMessageLabel() {
		JLabel profileTabMessageLabel = new JLabel();
		profileTabMessageLabel
				.setText(getString("profileTabMessageLabelString"));
		profileTabMessageLabel
				.setBounds(new Rectangle(
						Integer.parseInt(getString("profileTabMessageLabelXString")),
						Integer.parseInt(getString("profileTabMessageLabelYString")),
						Integer.parseInt(getString("profileTabMessageLabelWidthString")),
						Integer.parseInt(getString("profileTabMessageLabelHeightString"))));

		return profileTabMessageLabel;
	}

	private JLabel getAvailableCredditsLabel() {
		JLabel availableCredditsLabel = new JLabel();
		availableCredditsLabel
				.setBounds(new Rectangle(
						Integer.parseInt(getString("availableCredditsLabelXProfileString")),
						Integer.parseInt(getString("availableCredditsLabelYProfileString")),
						Integer.parseInt(getString("availableCredditsLabelWidthProfileString")),
						Integer.parseInt(getString("availableCredditsLabelHeightProfileString"))));

		availableCredditsLabel
				.setText(getString("availableCredditsLabelProfileString"));
		return availableCredditsLabel;
	}

	private JLabel getPurchasedItemsLabel() {
		JLabel purchasedItemsLabel = new JLabel();
		purchasedItemsLabel.setBounds(new Rectangle(Integer
				.parseInt(getString("purchasedItemsLabelXString")), Integer
				.parseInt(getString("purchasedItemsLabelYString")), Integer
				.parseInt(getString("purchasedItemsLabelWidthString")), Integer
				.parseInt(getString("purchasedItemsLabelHeightString"))));

		purchasedItemsLabel.setText(getString("purchasedItemsLabelString"));
		return purchasedItemsLabel;
	}

	@Override
	public void refresh() {
		controller.initialize();
	}

}
