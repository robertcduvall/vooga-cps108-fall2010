package arcade.store.gui.pages;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import javax.swing.JLabel;

public class ItemHasBeenDroppedView {

	private JFrame frame;
	private JPanel ItemDroppedPanel = null;  //  @jve:decl-index=0:visual-constraint="283,101"
	private JButton ItemDroppedButton = null;
	private JLabel ItemDroppedLabel = null;
	
	public ItemHasBeenDroppedView()
	{
		frame = getFrame();
		frame.setVisible(true);
	}
	
	public JFrame getFrame()
	{
		JFrame frame = new JFrame();
		frame.setSize(330, 220);
		frame.setContentPane(getItemDroppedPanel());
		return frame;
	}

	/**
	 * This method initializes ItemDroppedPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getItemDroppedPanel() {
		if (ItemDroppedPanel == null) {
			ItemDroppedLabel = new JLabel();
			ItemDroppedLabel.setBounds(new Rectangle(86, 30, 153, 43));
			ItemDroppedLabel.setText("Item Has Been Dropped");
			ItemDroppedPanel = new JPanel();
			ItemDroppedPanel.setLayout(null);
			ItemDroppedPanel.setSize(new Dimension(319, 192));
			ItemDroppedPanel.add(getItemDroppedButton(), null);
			ItemDroppedPanel.add(ItemDroppedLabel, null);
		}
		return ItemDroppedPanel;
	}

	/**
	 * This method initializes ItemDroppedButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getItemDroppedButton() {
		if (ItemDroppedButton == null) {
			ItemDroppedButton = new JButton();
			ItemDroppedButton.setBounds(new Rectangle(121, 103, 76, 39));
			ItemDroppedButton.setText("OK");
			ItemDroppedButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					frame.setVisible(false);
					frame.dispose();
				}
			});
			
		}
		return ItemDroppedButton;
	}
	
}
