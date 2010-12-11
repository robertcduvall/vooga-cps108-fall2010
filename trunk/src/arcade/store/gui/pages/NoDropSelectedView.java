package arcade.store.gui.pages;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;

public class NoDropSelectedView {

	JFrame frame;
	private JPanel NoSelectedPanel = null;  //  @jve:decl-index=0:visual-constraint="205,-2"
	private JButton NoSelectedButton = null;
	private JLabel NoSelectedLabel = null;
	
	public void NoDropSelectedView()
	{
		frame = getFrame();
		frame.setVisible(true);
	}

	private JFrame getFrame() {
		JFrame frame = new JFrame();
		frame.setSize(450, 230);
		frame.setContentPane(getNoSelectedPanel());
		return frame;
	}

	/**
	 * This method initializes NoSelectedPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getNoSelectedPanel() {
		if (NoSelectedPanel == null) {
			NoSelectedLabel = new JLabel();
			NoSelectedLabel.setBounds(new Rectangle(107, 32, 228, 63));
			NoSelectedLabel.setText("You Have Not Selected An Item To Drop");
			NoSelectedPanel = new JPanel();
			NoSelectedPanel.setLayout(null);
			NoSelectedPanel.setSize(new Dimension(448, 183));
			NoSelectedPanel.add(getNoSelectedButton(), null);
			NoSelectedPanel.add(NoSelectedLabel, null);
		}
		return NoSelectedPanel;
	}

	/**
	 * This method initializes NoSelectedButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNoSelectedButton() {
		if (NoSelectedButton == null) {
			NoSelectedButton = new JButton();
			NoSelectedButton.setBounds(new Rectangle(187, 127, 74, 32));
			NoSelectedButton.setText("OK");
			NoSelectedButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					frame.setVisible(false);
					frame.dispose();
				}
			});
		}
		return NoSelectedButton;
	}
	
	
	
	
	
}
