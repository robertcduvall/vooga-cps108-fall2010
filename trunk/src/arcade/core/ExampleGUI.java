package arcade.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ExampleGUI extends JFrame{

	private JLabel codeL, displayL;
	private JTextField codeTF, displayTF;
	private JButton redeemB, exitB;

	private RedeemButtonHandler cbHandler;
	private ExitButtonHandler ebHandler;

	public ExampleGUI()
	{
		/*

		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makeTextPanel("Panel #1");
		tabbedPane.addTab("Tab 1", null, panel1,
		"Does nothing");

		JComponent panel2 = makeTextPanel("Panel #2");
		tabbedPane.addTab("Tab 2", null, panel2,
		"Does twice as much nothing");

		JComponent panel3 = makeTextPanel("Panel #3");
		tabbedPane.addTab("Tab 3", null, panel3,
		"Still does nothing");

		JComponent panel4 = makeTextPanel("Panel #4");
		tabbedPane.addTab("Tab 4", null, panel4,
		"Does nothing at all");
		
		JComponent panel5 = makeTextPanel("Panel #5");
		tabbedPane.addTab("Tab 5", null, panel5,
		"Does nothing at all");

		JComponent panel6 = makeTextPanel("Panel #6");
		tabbedPane.addTab("Tab 6", null, panel6,
		"Does nothing at all");
		
        add(tabbedPane);
        
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		redeemB = new JButton("Redeem");
		cbHandler = new RedeemButtonHandler();
		redeemB.addActionListener(cbHandler);
		exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);

		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  

		setSize(xSize,ySize);  
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		*/
		makeCustomPanel();
	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	
	protected void makeProPanel() {
		JPanel basic = new JPanel();
	    basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
	    add(basic);

	    JPanel topPanel = new JPanel(new BorderLayout(0, 0));
	    topPanel.setMaximumSize(new Dimension(450, 20));
	    JLabel hint = new JLabel("Rating Systems");
	    hint.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
	    topPanel.add(hint);

	    ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
	    Image scaled = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	    icon = new ImageIcon(scaled);
	    JLabel label = new JLabel(icon);
	    label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	    topPanel.add(label, BorderLayout.EAST);
	    
	    //hint.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	    JSeparator separator = new JSeparator();
	    separator.setForeground(Color.gray);

	    topPanel.add(separator, BorderLayout.SOUTH);

	    basic.add(topPanel);

	    JPanel textPanel = new JPanel(new BorderLayout());
	    textPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
	    JTextPane pane = new JTextPane();

	    pane.setContentType("text/html");
	    String text = "hello world";
	    pane.setText(text);
	    pane.setEditable(false);
	    textPanel.add(pane);

	    basic.add(textPanel);

	    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	    JButton ntip = new JButton("Next Tip");
	    ntip.setMnemonic(KeyEvent.VK_N);
	    JButton close = new JButton("Close");
	    close.setMnemonic(KeyEvent.VK_C);

	    bottom.add(ntip);
	    bottom.add(close);
	    basic.add(bottom);

	    bottom.setMaximumSize(new Dimension(450, 0));

	    setSize(new Dimension(450, 350));
	    setResizable(false);
	    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
    }
	
	protected void makeCustomPanel() {
		
		JPanel topPanel = new JPanel(new FlowLayout());
	    
	    //hint.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    topPanel.add(makeLeftPanel());
	    topPanel.add(makeCenterPanel());
	    topPanel.add(makeRightPanel());
	    add(topPanel);
	    
	    setSize(new Dimension(450, 350));
	    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
    }
	private JComponent makeCenterPanel()
	{
		JPanel center = new JPanel();
		
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
	    JLabel label = new JLabel(icon);
	    label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	    center.add(label);
	    
	    return center;
	}
	private JComponent makeLeftPanel()
	{
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		
		JLabel rateThis = new JLabel("Rate This Game");
		left.add(rateThis);
		
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
	    Image scaled = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	    icon = new ImageIcon(scaled);
	    JLabel label = new JLabel(icon);
	    label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	    left.add(label);
	    
	    JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
	    separator.setForeground(Color.BLUE);
	    left.add(separator);
	    
	    JLabel rateOthers = new JLabel("Rate These Other Games");
		left.add(rateOthers);
		
		JLabel moreLabels = new JLabel(icon);
	    left.add(moreLabels);
	    
	    return left;
	}
	private JComponent makeRightPanel()
	{
		JPanel right = new JPanel();
	    right.setLayout(new GridLayout(3, 0));
	    
	    JPanel playerAvatar = new JPanel();
	    playerAvatar.setLayout(new BoxLayout(playerAvatar, BoxLayout.Y_AXIS));
	    playerAvatar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    JLabel playerName = new JLabel("Player Avatar");
	    playerAvatar.add(playerName);
	    
	    JPanel lobby = new JPanel();
	    lobby.setLayout(new BoxLayout(lobby, BoxLayout.Y_AXIS));
	    lobby.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    JLabel lobbyFriends = new JLabel("You have 0 friends");
	    lobby.add(lobbyFriends);
	    
	    JPanel ads = new JPanel();
	    ads.setLayout(new BoxLayout(ads, BoxLayout.Y_AXIS));;
	    ads.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    JLabel randomAd = new JLabel("Buy Coke.");
	    ads.add(randomAd);
	    
	    right.add(playerAvatar);
	    right.add(lobby);
	    right.add(ads);
	    
	    return right;
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
