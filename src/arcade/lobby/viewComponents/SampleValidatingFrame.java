package arcade.lobby.viewComponents;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import arcade.util.guiComponents.ValidatingComponent;
import arcade.util.guiComponents.ValidatorDock;

public class SampleValidatingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private ValidatorDock myValidatorDock;
	private JButton validatingButton = null;

	/**
	 * This is the default constructor
	 */
	public SampleValidatingFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		myValidatorDock = getValidatorDock();
		createValidatingFields();
		jContentPane.add(myValidatorDock, BorderLayout.CENTER);
		pack();
		
	}

	private void createValidatingFields() { 
		myValidatorDock.addValidatingComponent(new ValidatingComponent<JTextField>(new JTextField(20), "blah1", new ValidatorText()), "blah1");
		myValidatorDock.addValidatingComponent(new ValidatingComponent<JTextField>(new JTextField(20), "blah2", new ValidatorText(), new ValidatorCapital()), "blah2");
		myValidatorDock.addValidatingComponent(new ValidatingComponent<JColorChooser>(new JColorChooser(), "blah3", new ValidatorColor()), "blah3");
		myValidatorDock.addValidatingComponent(new ValidatingComponent<JTextField>(new JTextField(20), "blah4", new ValidatorText()), "blah4");

		
	}

	private ValidatorDock getValidatorDock() {
		return new ValidatorDock();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getValidatingButton(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes ValidatingButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getValidatingButton() {
		if (validatingButton == null) {
			validatingButton = new JButton();
			validatingButton.setText("Submit");
			validatingButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Map<String, Boolean> temp = myValidatorDock.validateComponents();
					for(String name: temp.keySet()){
						System.out.println(name + " : " + temp.get(name));
					}
				}
			});
		}
		return validatingButton;
	}

}
