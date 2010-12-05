package arcade.store.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JInternalFrame;

public class StoreAccountManageView {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="59,10"
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	public StoreAccountManageView() {
		JFrame frame = getJFrame();
	}
	
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(590, 317));
			jFrame.setContentPane(getJPanel());
		}
		return jFrame;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setText("Account Management Page");
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.add(jLabel, BorderLayout.NORTH);
			jPanel.add(getJTextField(), BorderLayout.WEST);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setText("user");
		}
		return jTextField;
	}

}
