package vooga.examples.resource.profile;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JList;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		ProfileManager pm = new ProfileManager();
		pm.setColor("user1", 0xFFAAFF);
		pm.setColor("user2", 0xFFFFFF);
		pm.setColor("user3", 0x0000DA);
		pm.setColor("user4", 0x334455);

		JFrame jf = new JFrame("Profile Example");
		jf.setSize(400, 400);
		jf.setLayout(new FlowLayout());

		String[] users = pm.getUsers();
		JList jl = new JList(users);
		jf.add(jl);
		jf.setVisible(true);

		int oldIndex = -1;
		while (true) {
			if (jl.getSelectedIndex() != oldIndex) {
				int color = pm.getColor((String) jl.getSelectedValue());
				jf.setBackground(new Color(color));
				oldIndex = jl.getSelectedIndex();
			}
			Thread.sleep(10);
		}

		// System.out.println(pm.getColor("user1"));
		// System.out.println(pm.getColor("user1"));

	}

}
