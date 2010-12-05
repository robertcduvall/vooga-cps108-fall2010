package arcade.core;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ExampleGUI extends JFrame{

	private JLabel codeL, displayL;
	private JTextField codeTF, displayTF;
	private JButton redeemB, exitB;

	private RedeemButtonHandler cbHandler;
	private ExitButtonHandler ebHandler;

	public ExampleGUI()
	{
		codeL = new JLabel("Enter the 9 digit code: ", SwingConstants.RIGHT);
		displayL = new JLabel("Your total Howl Points: ", SwingConstants.RIGHT);

		codeTF = new JTextField(10);
		displayTF = new JTextField(10);

		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makeTextPanel("Panel #1");
		tabbedPane.addTab("Tab 1", null, panel1,
		"Does nothing");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeTextPanel("Panel #2");
		tabbedPane.addTab("Tab 2", null, panel2,
		"Does twice as much nothing");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = makeTextPanel("Panel #3");
		tabbedPane.addTab("Tab 3", null, panel3,
		"Still does nothing");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		JComponent panel4 = makeTextPanel(
		"Panel #4 (has a preferred size of 410 x 50).");
		panel4.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("Tab 4", null, panel4,
		"Does nothing at all");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

		redeemB = new JButton("Redeem");
		cbHandler = new RedeemButtonHandler();
		redeemB.addActionListener(cbHandler);
		exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);

		setTitle("Howl");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(3, 2));


		pane.add(displayL);
		pane.add(displayTF);
		pane.add(codeL);
		pane.add(codeTF);
		pane.add(redeemB);
		pane.add(exitB);

		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  

		setSize(xSize,ySize);  
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

	private class RedeemButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String code;

			code = codeTF.getText(); 
			if (code.length() !=9)displayTF.setText("invalid code");
			else{
				System.out.println("hah");
				codeTF.setText("You've already redeemed this code. Try another.");
			}
			displayTF.setText(" points.");
		}
	}

	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	public static void main(String[] args)
	{
		ExampleGUI fr = new ExampleGUI();
	}
}
