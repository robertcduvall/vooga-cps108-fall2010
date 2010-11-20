package vooga.examples.resource.profile;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author David G. Herzka
 * 
 */
public class Main {

	private ColorProfile profile;
	private JList list;
	private JButton setButton;
	private JButton addButton;
	private JTextField colorText;
	private JFrame frame;

	private boolean setButtonPress;
	private boolean listAction;
	protected boolean addButtonPress;
	private JTextField newUserText;
	private JTextField newColorText;

	public static void main(String[] args) throws InterruptedException {

		Main m = new Main();
		m.init();

	}

	private void init() throws InterruptedException {

		profile = new ColorProfile();

		frame = new JFrame("Profile Example");
		frame.setSize(400, 400);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] users = profile.getUsers();
		list = new JList(users);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listAction = true;
			}
		});
		JScrollPane scrollPane = new JScrollPane(list);
		frame.add(scrollPane);

		colorText = new JTextField(10);
		frame.add(colorText);

		setButton = new JButton("Set");
		setButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setButtonPress = true;
			}
		});
		frame.add(setButton);

		JPanel newUserPanel = new JPanel();
		frame.add(newUserPanel);

		newUserText = new JTextField(10);
		newUserPanel.add(newUserText);

		newColorText = new JTextField(10);
		newUserPanel.add(newColorText);

		addButton = new JButton("Add User");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonPress = true;
			}
		});
		newUserPanel.add(addButton);

		frame.setVisible(true);

		while (true) {
			if (setButtonPress) {
				profile.setColor((String) list.getSelectedValue(), Integer
						.parseInt(colorText.getText()));
				drawBG();
				setButtonPress = false;
			}
			if (listAction) {
				drawBG();
				listAction = false;
			}
			if (addButtonPress) {
				profile.setColor(newUserText.getText(), Integer
						.parseInt(newColorText.getText()));
				list.setListData(profile.getUsers());
				list.setSelectedIndex(0);
				addButtonPress = false;
			}
			Thread.sleep(10);
		}

	}

	private void drawBG() {
		int color = profile.getColor((String) list.getSelectedValue());
		frame.getContentPane().setBackground(new Color(color));
		colorText.setText(Integer.toString(color));
	}

}
