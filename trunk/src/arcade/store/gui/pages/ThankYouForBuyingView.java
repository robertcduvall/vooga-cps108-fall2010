package arcade.store.gui.pages;

import javax.swing.JFrame;

import arcade.core.mvc.IController;
import arcade.store.control.CheckoutController;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import javax.swing.JLabel;

public class ThankYouForBuyingView {

	private JFrame frame;
	
	private JPanel ThankYouPanel = null;  //  @jve:decl-index=0:visual-constraint="249,39"
	private JButton ThankYouButton = null;
	private JLabel ThankYouLabel = null;
	
	public ThankYouForBuyingView()
	{
		frame = getFrame();
		frame.setVisible(true);
	}

	private JFrame getFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("Thank You!");
		frame.setSize(385, 200);
		frame.setContentPane(getThankYouPanel());
		return frame;
	}

	/**
	 * This method initializes ThankYouPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getThankYouPanel() {
		if (ThankYouPanel == null) {
			ThankYouLabel = new JLabel();
			ThankYouLabel.setBounds(new Rectangle(96, 46, 201, 39));
			ThankYouLabel.setText("Thank You For Buying At the Store!");
			ThankYouPanel = new JPanel();
			ThankYouPanel.setLayout(null);
			ThankYouPanel.setSize(new Dimension(375, 179));
			ThankYouPanel.add(getThankYouButton(), null);
			ThankYouPanel.add(ThankYouLabel, null);
		}
		return ThankYouPanel;
	}

	/**
	 * This method initializes ThankYouButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getThankYouButton() {
		if (ThankYouButton == null) {
			ThankYouButton = new JButton();
			ThankYouButton.setBounds(new Rectangle(148, 111, 76, 35));
			ThankYouButton.setText("OK");
			ThankYouButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					frame.setVisible(false);
					frame.dispose();
				}
			});
		}
		return ThankYouButton;
	}
	
	
	
}
