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

public class SorryYouCantDoThisView {

	private JFrame frame;
	
	private JPanel SorryPanel = null;  //  @jve:decl-index=0:visual-constraint="224,35"
	private JButton SorryButton = null;
	private JLabel SorryMessayLabel = null;
	
	
	public SorryYouCantDoThisView() {
		
		frame = getJFrame();
		frame.setVisible(true);
		
	}

	private JFrame getJFrame() {
		
		JFrame frame = new JFrame();
		frame.setTitle("Invalid Purchase Cart");
		frame.setContentPane(getSorryPanel());
		frame.setSize(370, 200);
		return frame;
	}

	/**
	 * This method initializes SorryPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSorryPanel() {
		if (SorryPanel == null) {
			SorryMessayLabel = new JLabel();
			SorryMessayLabel.setBounds(new Rectangle(48, 43, 275, 31));
			SorryMessayLabel.setText("Sorry, We Cannot Proceed This Cart Purchase");
			SorryPanel = new JPanel();
			SorryPanel.setLayout(null);
			SorryPanel.setSize(new Dimension(344, 160));
			SorryPanel.add(getSorryButton(), null);
			SorryPanel.add(SorryMessayLabel, null);
		}
		return SorryPanel;
	}

	/**
	 * This method initializes SorryButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSorryButton() {
		if (SorryButton == null) {
			SorryButton = new JButton();
			SorryButton.setBounds(new Rectangle(139, 107, 82, 32));
			SorryButton.setText("OK");
			SorryButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					frame.setVisible(false);
					frame.dispose();
				}
			});
		}
		return SorryButton;
	}

}
