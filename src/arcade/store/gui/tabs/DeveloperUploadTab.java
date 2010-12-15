package arcade.store.gui.tabs;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JButton;

public class DeveloperUploadTab {

	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="21,25"
	private JLabel jLabel = null;
	private JTable gamesTable = null;
	private JLabel tableLabel = null;
	private JButton editGameButton = null;
	private JButton addNewGameButon = null;

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			tableLabel = new JLabel();
			tableLabel.setBounds(new Rectangle(15, 45, 270, 16));
			tableLabel.setText("My Uploaded Games");
			jLabel = new JLabel();
			jLabel.setText("Developer Management Center");
			jLabel.setBounds(new Rectangle(15, 15, 270, 16));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setSize(new Dimension(737, 300));
			jPanel.add(jLabel, null);
			jPanel.add(getGamesTable(), null);
			jPanel.add(tableLabel, null);
			jPanel.add(getEditGameButton(), null);
			jPanel.add(getAddNewGameButon(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes gamesTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getGamesTable() {
		if (gamesTable == null) {
			gamesTable = new JTable();
			gamesTable.setBounds(new Rectangle(14, 65, 511, 204));
		}
		return gamesTable;
	}

	/**
	 * This method initializes editGameButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditGameButton() {
		if (editGameButton == null) {
			editGameButton = new JButton();
			editGameButton.setBounds(new Rectangle(550, 66, 158, 27));
			editGameButton.setText("Edit Game Attributes");
		}
		return editGameButton;
	}

	/**
	 * This method initializes addNewGameButon	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddNewGameButon() {
		if (addNewGameButon == null) {
			addNewGameButon = new JButton();
			addNewGameButon.setBounds(new Rectangle(550, 105, 158, 26));
			addNewGameButon.setText("Add New Game");
		}
		return addNewGameButon;
	}

}
