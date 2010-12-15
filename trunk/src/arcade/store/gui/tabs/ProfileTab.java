package arcade.store.gui.tabs;

import javax.swing.JComponent;
import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;
import arcade.store.control.ProfileController;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class ProfileTab extends JPanel implements Tab, IViewer {

	// private JPanel jPanel = null; //
	// @jve:decl-index=0:visual-constraint="133,-45"
	private JLabel introLabel1 = null;
	private JLabel userImageLabel = null;
	private JTextField usernameTextField = null;
	private JLabel AvailableCredditsLabel = null;
	private JTextField availableCredditsTextField = null;
	private JButton purchaseCredditsButton = null;
	private JButton editMyProfileButton = null;
	private JTable purchasedItemsTable = null;
	private JLabel MyPurchasedItemsLabel = null;
	private JButton RefreshButton = null;
	private JScrollPane itemTableScrollPane = null;

	private static final String NAME = "Shop Profile";

	private ProfileController controller;

	public ProfileTab() {
		controller = new ProfileController();
		controller.addViewer(this);
		setName(NAME);

		MyPurchasedItemsLabel = new JLabel();
		MyPurchasedItemsLabel.setBounds(new Rectangle(102, 289, 139, 35));
		MyPurchasedItemsLabel.setText("My Purchased Items");
		AvailableCredditsLabel = new JLabel();
		AvailableCredditsLabel.setBounds(new Rectangle(361, 94, 111, 29));
		AvailableCredditsLabel.setText("Availabe Creddits");
		userImageLabel = new JLabel();
		userImageLabel.setBounds(new Rectangle(102, 54, 142, 132));
		try {
			userImageLabel.setIcon(new ImageIcon(""));
		} catch (Exception e) {
			System.out.println(e);
		}
		introLabel1 = new JLabel();
		introLabel1.setText("Manage My Shop Account");
		introLabel1.setBounds(new Rectangle(13, 14, 163, 16));

		setLayout(null);
		add(introLabel1, null);
		add(userImageLabel, null);
		add(getUsernameTextField(), null);
		add(AvailableCredditsLabel, null);
		add(getAvailableCredditsTextField(), null);
		add(getPurchaseCredditsButton(), null);
		add(getEditMyProfileButton(), null);
		add(getItemTableScrollPane(), null);
		add(MyPurchasedItemsLabel, null);
		add(getRefreshButton(), null);
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
	public JComponent getContent() {
		return this;
	}

	/**
	 * This method initializes usernameTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			usernameTextField.setHorizontalAlignment(JTextField.CENTER); // Borrowed
																			// from
																			// http://www.exampledepot.com/egs/javax.swing.text/tf_Align.html
			usernameTextField.setEditable(false);
			usernameTextField.setBounds(new Rectangle(102, 198, 142, 26));
		}
		return usernameTextField;
	}

	/**
	 * This method initializes availableCredditsTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField getAvailableCredditsTextField() {
		if (availableCredditsTextField == null) {
			availableCredditsTextField = new JTextField();
			// 506, 53, 111, 29
			availableCredditsTextField
					.setBounds(new Rectangle(506, 94, 111, 29));
		}
		return availableCredditsTextField;
	}

	/**
	 * This method initializes purchaseCredditsButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getPurchaseCredditsButton() {
		if (purchaseCredditsButton == null) {
			purchaseCredditsButton = new JButton();
			purchaseCredditsButton.setBounds(new Rectangle(486, 158, 149, 32));
			purchaseCredditsButton.setText("Purchase Creddits");
			purchaseCredditsButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					controller.openCredditPurchaseView();
				}
			});
		}
		return purchaseCredditsButton;
	}

	/**
	 * This method initializes editMyProfileButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getEditMyProfileButton() {
		if (editMyProfileButton == null) {
			editMyProfileButton = new JButton();
			editMyProfileButton.setBounds(new Rectangle(486, 211, 149, 32));
			editMyProfileButton.setText("Edit My Profile");
			editMyProfileButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

				}
			});
		}
		return editMyProfileButton;
	}

	/**
	 * This method initializes purchasedGamesTable
	 * 
	 * @return javax.swing.JTable
	 */
	public JTable getPurchasedItemsTable() {
		if (purchasedItemsTable == null) {
			purchasedItemsTable = new JTable();
		}
		return purchasedItemsTable;
	}

	public JScrollPane getItemTableScrollPane() {
		if (itemTableScrollPane == null) {
			itemTableScrollPane = new JScrollPane();
			itemTableScrollPane.setViewportView(getPurchasedItemsTable());
			itemTableScrollPane.setBounds(new Rectangle(102, 347, 542, 256));
		}
		return itemTableScrollPane;
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
	public void refresh() {
		this.repaint();
	}

}
