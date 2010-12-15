package arcade.mod.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConsoleView extends JFrame {
	JPanel centralPanel;
	JTextField textInput;
	String myVariable;
	String myInput;

	public ConsoleView() {
		initialize();
	}

	public void initialize() {

		textInput = new JTextField();
		textInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				String temp = textInput.getText();
				temp = temp.replace(" ", "");
				String[] input = temp.split("=");
				myVariable = input[0];
				if (input.length > 1) {
					myInput = input[1];
				}
				runConsoleCommand();
			}

		});
		add(textInput);
		this.setTitle("Console");

		setSize(600, 50);
		setVisible(true);
	}

	private void runConsoleCommand() {

	}

}