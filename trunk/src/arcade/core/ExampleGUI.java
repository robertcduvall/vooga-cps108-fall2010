package arcade.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ExampleGUI extends JFrame{

	private JLabel codeL, displayL;
	private JTextField codeTF, displayTF;
	private JButton startB, exitB;

	private StartButtonHandler sbHandler;
	private ExitButtonHandler ebHandler;

	public ExampleGUI()
	{
		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makeCustomPanel();
		tabbedPane.addTab("Arcade", null, panel1,
		"This is the Arcade tab");

		JComponent panel2 = makeTextPanel("Lobby");
		tabbedPane.addTab("Lobby", null, panel2,
		"This is the Lobby tab");

		JComponent panel3 = makeTextPanel("Store");
		tabbedPane.addTab("Store", null, panel3,
		"This is the Store tab");
		
        add(tabbedPane);
        
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

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
	
	private JComponent makeCustomPanel() {
		
		JPanel topPanel = new JPanel(new FlowLayout());
	    
	    //hint.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    topPanel.add(makeLeftPanel());
	    topPanel.add(makeCenterPanel());
	    topPanel.add(makeRightPanel());
	    
	    return topPanel;
    }
	private JComponent makeCenterPanel()
	{
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
	    JLabel label = new JLabel(icon);
	    
	    JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
	    separator.setForeground(Color.BLUE);
	    JSeparator moreSeparation = new JSeparator(SwingConstants.HORIZONTAL);
        moreSeparation.setForeground(Color.BLUE);
	    
	    
	    JTextPane descriptionPane = new JTextPane();
	    descriptionPane.setContentType("text/html");
        String description = "<p><b>Description:</b></p>" +
            "<p>This is where instructions for the game would go. <br>" +
            "For example:<br> Left Arrow = move left. <br>" +
            "Right Arrow = move right <br>" +
            "Spacebar = fire.</p>";
        descriptionPane.setText(description);
        descriptionPane.setEditable(false);
        
        startB = new JButton("Start");
		sbHandler = new StartButtonHandler();
		startB.addActionListener(sbHandler);
        
		label.setAlignmentX(CENTER_ALIGNMENT);
		separator.setAlignmentX(CENTER_ALIGNMENT);
		descriptionPane.setAlignmentX(CENTER_ALIGNMENT);
		moreSeparation.setAlignmentX(CENTER_ALIGNMENT);
		startB.setAlignmentX(CENTER_ALIGNMENT);
		
        center.add(label);
        center.add(separator);
        center.add(descriptionPane);
        center.add(moreSeparation);
        center.add(startB);
        
		/*exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);*/
        
        center.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
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
	    
	    left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    return left;
	}
	private JComponent makeRightPanel()
	{
		JPanel right = new JPanel();
	    right.setLayout(new GridLayout(3, 0));
	    
	    ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
	    Image scaled = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	    icon = new ImageIcon(scaled);
	    JLabel label = new JLabel(icon);
	    label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	    
	    JPanel playerAvatar = new JPanel();
	    playerAvatar.setLayout(new BoxLayout(playerAvatar, BoxLayout.Y_AXIS));
	    playerAvatar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    JLabel playerName = new JLabel("Player Avatar");
	    playerAvatar.add(playerName);
	    playerAvatar.add(label);
	    
	    
	    JPanel lobby = new JPanel();
	    lobby.setLayout(new BoxLayout(lobby, BoxLayout.Y_AXIS));
	    lobby.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    JLabel lobbyFriends = new JLabel("You have 0 friends");
	    lobby.add(lobbyFriends);
	    
	    JLabel moreLabels = new JLabel(icon);
	    lobby.add(moreLabels);
	    
	    JPanel ads = new JPanel();
	    ads.setLayout(new BoxLayout(ads, BoxLayout.Y_AXIS));;
	    ads.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    JLabel randomAd = new JLabel("Buy Coke.");
	    ads.add(randomAd);
	    
	    JLabel evenMoreLabels = new JLabel(icon);
	    ads.add(evenMoreLabels);
	    
	    right.add(playerAvatar);
	    right.add(lobby);
	    right.add(ads);
	    
	    right.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    return right;
	}
	
	private class StartButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//TODO: start game
			System.out.println("Started Game!");
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
