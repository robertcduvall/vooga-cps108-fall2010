package arcade.store.gui.guitabs;

import javax.swing.JComponent;

import arcade.core.Tab;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class MyProfileTabGUI extends Tab{
	
	private static final String USER_AVATAR = "src/arcade/store/gui/resources/robert-duvall-avatar.jpg";
	private static final String USER_NAME = "Robert Duvall";

	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="133,-45"
	private JLabel introLabel1 = null;
	private JLabel userImageLabel = null;
	private JTextField usernameTextField = null;
	private JLabel totalGamePlayLabel = null;
	private JLabel AvailableCredditsLabel = null;
	private JTextField totalGamePlayTextField = null;
	private JTextField availableCredditsTextField = null;
	private JButton purchaseCredditsButton = null;
	private JButton editMyProfileButton = null;
	private JButton editMyPurchaseHistoryButton = null;
	private JTable purchasedGamesTable = null;
	private JLabel MyPurchasedGamesLabel = null;
	
	public MyProfileTabGUI()
	{
		setName("My Shop Profile");
	}


	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JComponent getContent() {
		if (jPanel == null) {
			MyPurchasedGamesLabel = new JLabel();
			MyPurchasedGamesLabel.setBounds(new Rectangle(102, 289, 139, 35));
			MyPurchasedGamesLabel.setText("My Purchased Games");
			AvailableCredditsLabel = new JLabel();
			AvailableCredditsLabel.setBounds(new Rectangle(361, 94, 111, 29));
			AvailableCredditsLabel.setText("Availabe Creddits");
			totalGamePlayLabel = new JLabel();
			totalGamePlayLabel.setBounds(new Rectangle(361, 53, 111, 29));
			totalGamePlayLabel.setText("Total Gameplay: ");
			userImageLabel = new JLabel();
			userImageLabel.setBounds(new Rectangle(102, 54, 142, 132));
			userImageLabel.setIcon(new ImageIcon(USER_AVATAR));
			introLabel1 = new JLabel();
			introLabel1.setText("Manage My Shop Account");
			introLabel1.setBounds(new Rectangle(13, 14, 163, 16));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setSize(new Dimension(726, 620));
			jPanel.add(introLabel1, null);
			jPanel.add(userImageLabel, null);
			jPanel.add(getUsernameTextField(), null);
			jPanel.add(totalGamePlayLabel, null);
			jPanel.add(AvailableCredditsLabel, null);
			jPanel.add(getTotalGamePlayTextField(), null);
			jPanel.add(getAvailableCredditsTextField(), null);
			jPanel.add(getPurchaseCredditsButton(), null);
			jPanel.add(getEditMyProfileButton(), null);
			jPanel.add(getEditMyPurchaseHistoryButton(), null);
			jPanel.add(getPurchasedGamesTable(), null);
			jPanel.add(MyPurchasedGamesLabel, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes usernameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			usernameTextField.setText(USER_NAME);
			usernameTextField.setHorizontalAlignment(JTextField.CENTER); // Borrowed from http://www.exampledepot.com/egs/javax.swing.text/tf_Align.html
			usernameTextField.setEditable(false);
			usernameTextField.setBounds(new Rectangle(102, 198, 142, 26));
		}
		return usernameTextField;
	}

	/**
	 * This method initializes totalGamePlayTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTotalGamePlayTextField() {
		if (totalGamePlayTextField == null) {
			totalGamePlayTextField = new JTextField();
			totalGamePlayTextField.setBounds(new Rectangle(506, 53, 111, 29));
		}
		return totalGamePlayTextField;
	}

	/**
	 * This method initializes availableCredditsTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAvailableCredditsTextField() {
		if (availableCredditsTextField == null) {
			availableCredditsTextField = new JTextField();
			//506, 53, 111, 29
			availableCredditsTextField.setBounds(new Rectangle(506, 94, 111, 29));
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
		}
		return editMyProfileButton;
	}

	/**
	 * This method initializes editMyPurchaseHistoryButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditMyPurchaseHistoryButton() {
		if (editMyPurchaseHistoryButton == null) {
			editMyPurchaseHistoryButton = new JButton();
			editMyPurchaseHistoryButton.setBounds(new Rectangle(486, 264, 149, 32));
			editMyPurchaseHistoryButton.setText("Edit My History");
		}
		return editMyPurchaseHistoryButton;
	}

	/**
	 * This method initializes purchasedGamesTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getPurchasedGamesTable() {
		if (purchasedGamesTable == null) {
			purchasedGamesTable = new JTable();
			
			   purchasedGamesTable.setModel(new javax.swing.table.DefaultTableModel(
			            new Object [][] {
			                {null, null, null, null},
			                {null, null, null, null},
			                {null, null, null, null},
			                {null, null, null, null}
			            },
			            new String [] {
			                "Title 1", "Title 2", "Title 3", "Title 4"
			            }
			        ));
//			
			purchasedGamesTable.setBounds(new Rectangle(102, 347, 542, 256));
		}
		return purchasedGamesTable;
	}
	
	
}
