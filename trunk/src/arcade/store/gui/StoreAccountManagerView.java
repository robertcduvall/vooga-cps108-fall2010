package arcade.store.gui;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import arcade.core.Tab;
import arcade.store.StoreModel;
import arcade.store.control.Control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class StoreAccountManagerView extends Tab{

	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="98,34"
	private JLabel jLabel = null;
	private JTextPane jTextPane = null;
	private JTextField jTextField = null;
	private JTextPane jTextPane1 = null;
	private JButton jButton = null;
	private JTextField jTextField1 = null;
	private JLabel jLabel1 = null;
	private JTextPane jTextPane2 = null;

	public StoreAccountManagerView() {
		setName("Manage Account");
	}
	
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JComponent getContent() {
		if (jPanel == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(554, 90, 249, 182));
			//jLabel1.setText("JLabel1");
			jLabel1.setIcon(new ImageIcon("src/arcade/store/gui/resources/TrollFace.png"));
			jLabel = new JLabel();
			//jLabel.setText("JLabel");
			jLabel.setIcon(new ImageIcon("src/arcade/store/gui/resources/happy-face.png"));
			jLabel.setBounds(new Rectangle(29, 30, 198, 162));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setSize(new Dimension(863, 412));
			jPanel.add(jLabel, null);
			jPanel.add(getJTextPane(), null);
			jPanel.add(getJTextPane1(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJTextPane2(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setText("USERNAME");
			jTextPane.setBounds(new Rectangle(30, 210, 197, 37));
		}
		return jTextPane;
	}

	/**
	 * This method initializes jTextPane1	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane1() {
		if (jTextPane1 == null) {
			jTextPane1 = new JTextPane();
			jTextPane1.setText("CURRENT BALANCE");
			jTextPane1.setBounds(new Rectangle(554, 27, 248, 35));
		}
		return jTextPane1;
	}
	
	/**
	 * This method initializes jTextPane2	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane2() {
		if (jTextPane2 == null) {
			jTextPane2 = new JTextPane();
			jTextPane2.setText("You know you want more Creddits.");
			jTextPane2.setBounds(new Rectangle(555, 284, 250, 47));
		}
		return jTextPane2;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Add Creddits");
			jButton.setBounds(new Rectangle(554, 348, 123, 43));
		}
		return jButton;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("5");
			jTextField1.setBounds(new Rectangle(691, 350, 117, 38));
		}
		return jTextField1;
	}






}
