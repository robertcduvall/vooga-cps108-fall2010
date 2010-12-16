package arcade.mod.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import javax.swing.JTextField;

/**
 * 
 * Console view
 * gets user input and stores it
 * @author vitorolivier
 *
 */
public class ConsoleView extends JFrame {

	public static final String NO_INPUT = "";
	public JTextField textInput;
	public String myInput = "NO_INPUT";

	public ConsoleView() {
		initialize();

	}

	public void initialize() {

		textInput = new JTextField();
		textInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {

				myInput = textInput.getText();

			}

		});
		add(textInput);
		this.setTitle("Console");

		setSize(600, 50);
		setVisible(false);
	}

}