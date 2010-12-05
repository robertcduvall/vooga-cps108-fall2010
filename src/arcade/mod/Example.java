package arcade.mod;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import javax.swing.JSlider;

public class Example extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton brianButton = null;
	private JSlider LevelOfExcellence = null;

	/**
	 * This is the default constructor
	 */
	public Example() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBrianButton(), null);
			jContentPane.add(getLevelOfExcellence(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes bryanButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBrianButton() {
		if (brianButton == null) {
			brianButton = new JButton();
			brianButton.setText("Brian is sweet");
			brianButton.setBounds(new Rectangle(148, 67, 115, 26));
			brianButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Level of Excellence = " + LevelOfExcellence.getValue()); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return brianButton;
	}

	/**
	 * This method initializes LevelOfExcellence	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getLevelOfExcellence() {
		if (LevelOfExcellence == null) {
			LevelOfExcellence = new JSlider();
			LevelOfExcellence.setBounds(new Rectangle(39, 121, 212, 21));
		}
		return LevelOfExcellence;
	}

}
