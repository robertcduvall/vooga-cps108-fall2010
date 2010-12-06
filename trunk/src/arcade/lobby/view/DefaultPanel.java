package arcade.lobby.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DefaultPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel title = null;

	/**
	 * This is the default constructor
	 */
	public DefaultPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		title = new JLabel();
		title.setText("Welcome User!");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("anyThing", Font.PLAIN, 32));
		this.setSize(338, 239);
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.CENTER);
	}

}
