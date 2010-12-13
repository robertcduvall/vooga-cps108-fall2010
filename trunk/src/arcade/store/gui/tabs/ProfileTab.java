package arcade.store.gui.tabs;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;
import arcade.store.account.StoreUser;
import arcade.store.control.ProfileController;
import arcade.store.items.DbItemAndUserFactory;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class ProfileTab extends Tab implements IViewer{
	

	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="133,-45"
	private JLabel introLabel1 = null;
	private JLabel userImageLabel = null;
	private JTextField usernameTextField = null;
	private JLabel AvailableCredditsLabel = null;
	private JTextField availableCredditsTextField = null;
	private JButton purchaseCredditsButton = null;
	private JButton editMyProfileButton = null;
	private JTable purchasedGamesTable = null;
	private JLabel MyPurchasedGamesLabel = null;
	private JButton RefreshButton = null;
	
	private static final String NAME = "Shop Profile";
	
	private ProfileController controller;
	
	public ProfileTab()
	{
		controller = new ProfileController();
		controller.addViewer(this);
		setName(NAME);
	}
	
	@Override
	public String getName()
	{
		return NAME;
	}
	

	@Override
	public void setController(IController control) 
	{
		controller = (ProfileController) control;
	}
	
	@Override
	public IController getController()
	{
		return controller;
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
			userImageLabel = new JLabel();
			userImageLabel.setBounds(new Rectangle(102, 54, 142, 132));
			try {
				userImageLabel.setIcon(new ImageIcon(""));
			}
			catch (Exception e){
				System.out.println(e);
			}
			introLabel1 = new JLabel();
			introLabel1.setText("Manage My Shop Account");
			introLabel1.setBounds(new Rectangle(13, 14, 163, 16));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setSize(new Dimension(726, 620));
			jPanel.add(introLabel1, null);
			jPanel.add(userImageLabel, null);
			jPanel.add(getUsernameTextField(), null);
			jPanel.add(AvailableCredditsLabel, null);
			jPanel.add(getAvailableCredditsTextField(), null);
			jPanel.add(getPurchaseCredditsButton(), null);
			jPanel.add(getEditMyProfileButton(), null);
			jPanel.add(getPurchasedGamesTable(), null);
			jPanel.add(MyPurchasedGamesLabel, null);
			jPanel.add(getRefreshButton(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes usernameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			usernameTextField.setHorizontalAlignment(JTextField.CENTER); // Borrowed from http://www.exampledepot.com/egs/javax.swing.text/tf_Align.html
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
	public JTable getPurchasedGamesTable() {
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

	
}
