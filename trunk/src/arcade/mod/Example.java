package arcade.mod;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import javax.swing.JSlider;
import javax.swing.JPasswordField;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;

public class Example extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton brianButton = null;
	private JSlider LevelOfExcellence = null;
	private JPasswordField jPasswordField = null;
	private JMenu AMenu = null;
	private JLabel ExampleLabel = null;
	private JOptionPane jOptionPane = null;
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
		this.setTitle("An example of Swing stuff");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			ExampleLabel = new JLabel();
			ExampleLabel.setText("JLabel");
//			ExampleLabel.set
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
			jContentPane.add(getBrianButton(), null);
			jContentPane.add(getLevelOfExcellence(), null);
			jContentPane.add(ExampleLabel, null);
			jContentPane.add(getJOptionPane(), null);
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
		}
		return LevelOfExcellence;
	}

	/**
	 * This method initializes AMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getAMenu() {
		if (AMenu == null) {
			AMenu = new JMenu();
			AMenu.setBounds(new Rectangle(24, 30, 121, 60));
			AMenu.setText("Example Menu");
		}
		return AMenu;
	}

	/**
	 * This method initializes jOptionPane	
	 * 	
	 * @return javax.swing.JOptionPane	
	 */
	private JOptionPane getJOptionPane() {
		if (jOptionPane == null) {
			jOptionPane = new JOptionPane();
			jOptionPane.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					System.out.println("keyPressed()"); // TODO Auto-generated Event stub keyPressed()
				}
			});
		}
		return jOptionPane;
	}
}
