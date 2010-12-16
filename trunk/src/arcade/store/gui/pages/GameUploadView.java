package arcade.store.gui.pages;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import arcade.core.Window;

public class GameUploadView extends Window{

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="101,18"
	private JPanel jContentPane = null;
	private JLabel titleLabel = null;
	private JLabel genreLabel = null;
	private JLabel Description = null;
	private JLabel tagsLabel = null;
	private JLabel priceLabel = null;
	private JLabel classLabel = null;
	private JLabel imagepathsLabel = null;
	private JLabel instructionsLabel = null;
	private JTextField gameTitleBox = null;
	private JTextArea descriptionArea = null;

	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(462, 209));
			jFrame.setTitle("Add Game To Store");
			jFrame.setContentPane(getJContentPane());
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			instructionsLabel = new JLabel();
			instructionsLabel.setBounds(new Rectangle(15, 299, 101, 16));
			instructionsLabel.setText("Instructions:");
			imagepathsLabel = new JLabel();
			imagepathsLabel.setBounds(new Rectangle(15, 261, 105, 16));
			imagepathsLabel.setText("Image Paths:");
			classLabel = new JLabel();
			classLabel.setBounds(new Rectangle(16, 225, 140, 16));
			classLabel.setText("Main Game Classname:");
			priceLabel = new JLabel();
			priceLabel.setBounds(new Rectangle(14, 190, 38, 16));
			priceLabel.setText("Price:");
			tagsLabel = new JLabel();
			tagsLabel.setBounds(new Rectangle(15, 157, 130, 16));
			tagsLabel.setText("Tags:");
			Description = new JLabel();
			Description.setBounds(new Rectangle(14, 84, 130, 16));
			Description.setText("Description:");
			genreLabel = new JLabel();
			genreLabel.setBounds(new Rectangle(15, 55, 130, 16));
			genreLabel.setText("Genre:");
			titleLabel = new JLabel();
			titleLabel.setBounds(new Rectangle(15, 16, 130, 16));
			titleLabel.setText("Game Title:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(titleLabel, null);
			jContentPane.add(genreLabel, null);
			jContentPane.add(Description, null);
			jContentPane.add(tagsLabel, null);
			jContentPane.add(priceLabel, null);
			jContentPane.add(classLabel, null);
			jContentPane.add(imagepathsLabel, null);
			jContentPane.add(instructionsLabel, null);
			jContentPane.add(getGameTitleBox(), null);
			jContentPane.add(getDescriptionArea(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes gameTitleBox	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getGameTitleBox() {
		if (gameTitleBox == null) {
			gameTitleBox = new JTextField();
			gameTitleBox.setBounds(new Rectangle(170, 13, 327, 20));
		}
		return gameTitleBox;
	}

	/**
	 * This method initializes descriptionArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getDescriptionArea() {
		if (descriptionArea == null) {
			descriptionArea = new JTextArea();
		}
		return descriptionArea;
	}

	@Override
	protected void createContents() {
		// TODO Auto-generated method stub
		
	}

}
