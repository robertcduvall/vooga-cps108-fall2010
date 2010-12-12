package arcade.store.gui.pages;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arcade.core.mvc.IController;
import arcade.store.control.CheckoutController;

public class AreYouSureDropCart {

	private CheckoutController controller;
	private JFrame frame;
	private JPanel DropItemPanel = null;  //  @jve:decl-index=0:visual-constraint="235,15"
	private JLabel AreYouSureLabel = null;
	private JButton YesButton = null;
	private JButton NoButton = null;
	
	public AreYouSureDropCart(IController control)
	{
		controller = (CheckoutController) control;
		frame = getFrame();
		frame.setVisible(true);
	}

	private JFrame getFrame() {
		JFrame frame = new JFrame();
		frame.setContentPane(getDropItemPanel());
		frame.setSize(330, 200);
		return frame;
	}

	/**
	 * This method initializes DropItemPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDropItemPanel() {
		if (DropItemPanel == null) {
			AreYouSureLabel = new JLabel();
			AreYouSureLabel.setText("Are You Sure You Want to Drop This Cart?");
			AreYouSureLabel.setBounds(new Rectangle(41, 31, 242, 39));
			DropItemPanel = new JPanel();
			DropItemPanel.setLayout(null);
			DropItemPanel.setSize(new Dimension(318, 150));
			DropItemPanel.add(AreYouSureLabel, null);
			DropItemPanel.add(getYesButton(), null);
			DropItemPanel.add(getNoButton(), null);
		}
		return DropItemPanel;
	}

	/**
	 * This method initializes YesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getYesButton() {
		if (YesButton == null) {
			YesButton = new JButton();
			YesButton.setBounds(new Rectangle(51, 89, 86, 34));
			YesButton.setText("Yes");
			YesButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					controller.processDropCart();
					frame.setVisible(false);
					frame.dispose();
				}
			});
		}
		return YesButton;
	}

	/**
	 * This method initializes NoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNoButton() {
		if (NoButton == null) {
			NoButton = new JButton();
			NoButton.setBounds(new Rectangle(176, 90, 90, 32));
			NoButton.setText("No");
			NoButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					frame.setVisible(false);
					frame.dispose();
				}
			});
		}
		return NoButton;
	}
	
	
	




}
