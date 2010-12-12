package arcade.store.gui.pages;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import javax.swing.JLabel;

public class NoDropSelectedView {

	private JPanel NoDropPanel = null;  //  @jve:decl-index=0:visual-constraint="230,33"
	private JButton NoDropButton = null;
	private JLabel NoDropoLabel = null;

	private JFrame frame;
	
	public NoDropSelectedView()
	{
		frame = getFrame();
		frame.setVisible(true);
	}
	
	private JFrame getFrame() {
		
		JFrame frame = new JFrame();
		frame.setContentPane(getNoDropPanel());
		frame.setSize(300, 200);
		return frame;
	}

	/**
	 * This method initializes NoDropPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getNoDropPanel() {
		if (NoDropPanel == null) {
			NoDropoLabel = new JLabel();
			NoDropoLabel.setBounds(new Rectangle(73, 38, 161, 30));
			NoDropoLabel.setText("No Item Has Been Selected");
			NoDropPanel = new JPanel();
			NoDropPanel.setLayout(null);
			NoDropPanel.setSize(new Dimension(302, 166));
			NoDropPanel.add(getNoDropButton(), null);
			NoDropPanel.add(NoDropoLabel, null);
		}
		return NoDropPanel;
	}

	/**
	 * This method initializes NoDropButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNoDropButton() {
		if (NoDropButton == null) {
			NoDropButton = new JButton();
			NoDropButton.setBounds(new Rectangle(110, 102, 81, 31));
			NoDropButton.setText("OK");
			NoDropButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					frame.setVisible(false);
					frame.dispose();
				}
			});
			
		}
		return NoDropButton;
	}




}
