package vooga.examples.resource.profile;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Main {

	private ProfileManager pm;
	private JList list;
	private JButton button;
	private JTextField text;
	private JFrame frame;

	private boolean buttonPress;
	private boolean listAction;

	public static void main(String[] args) throws InterruptedException {

		Main m = new Main();
		m.init();

	}

	private void init() throws InterruptedException {
		// TODO Auto-generated method stub

		pm = new ProfileManager();
//		pm.setColor("user1", 0xFFAAFF);
//		pm.setColor("user2", 0xFFFFFF);
//		pm.setColor("user3", 0x0000DA);
//		pm.setColor("user4", 0x334455);

		frame = new JFrame("Profile Example");
		frame.setSize(400, 400);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] users = pm.getUsers();
		list = new JList(users);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listAction = true;
			}
		});
		frame.add(list);

		text = new JTextField(10);
		frame.add(text);

		button = new JButton("Set");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonPress = true;
			}
		});
		frame.add(button);

		frame.setVisible(true);

		while (true) {
			if (buttonPress) {
				pm.setColor((String) list.getSelectedValue(), Integer
						.parseInt(text.getText()));
				drawBG();
				buttonPress = false;
			}
			if (listAction) {
				drawBG();
				listAction = false;
			}
			Thread.sleep(10);
		}

	}

	private void drawBG() {
		int color = pm.getColor((String) list.getSelectedValue());
		frame.setBackground(new Color(color));
		text.setText(Integer.toString(color));
	}

}
