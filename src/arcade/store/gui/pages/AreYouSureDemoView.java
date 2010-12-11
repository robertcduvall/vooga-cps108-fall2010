package arcade.store.gui.pages;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arcade.core.mvc.IController;
import arcade.store.control.PurchaseItemController;

public class AreYouSureDemoView {

	PurchaseItemController controller;
	
	private JPanel AreYouSurePanel = null;  //  @jve:decl-index=0:visual-constraint="213,86"
	private JLabel AreYouSureLabel = null;
	private JButton YesButton = null;
	private JButton NoButton = null;
	private JFrame frame;
	
	public AreYouSureDemoView(IController control)
	{   frame = getFrame();
		controller = (PurchaseItemController) control;
		frame.setVisible(true);
	}

	private JFrame getFrame() {
		JFrame frame = new JFrame();
		frame.setSize(400, 140);
		frame.setTitle("Confirmation");
		frame.setContentPane(getAreYouSurePanel());
		
		return frame;
	}

	/**
	 * This method initializes AreYouSurePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAreYouSurePanel() {
		if (AreYouSurePanel == null) {
			AreYouSureLabel = new JLabel();
			AreYouSureLabel.setBounds(new Rectangle(55, 11, 284, 30));
			AreYouSureLabel.setText("Are You Sure You Want to Demo This Game?");
			AreYouSurePanel = new JPanel();
			AreYouSurePanel.setLayout(null);
			AreYouSurePanel.setSize(new Dimension(394, 100));
			AreYouSurePanel.add(AreYouSureLabel, null);
			AreYouSurePanel.add(getYesButton(), null);
			AreYouSurePanel.add(getNoButton(), null);
		}
		return AreYouSurePanel;
	}

	/**
	 * This method initializes YesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getYesButton() {
		if (YesButton == null) {
			YesButton = new JButton();
			YesButton.setBounds(new Rectangle(109, 57, 64, 31));
			YesButton.setText("Yes");
			YesButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					controller.processDemoGame();
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
			NoButton.setBounds(new Rectangle(231, 58, 62, 30));
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
