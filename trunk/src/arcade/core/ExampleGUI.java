package arcade.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
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
	
	private Toolkit tk;

	public ExampleGUI()
	{
		tk = Toolkit.getDefaultToolkit();
		
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
	
	private JComponent makeCustomPanel() {
	    JSplitPane columnar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	    		makeLeftPanel(), makeCenterPanel());
	    columnar.setOneTouchExpandable(true);
	    columnar.setDividerLocation(.25);
	    
	    JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	    		columnar, makeRightPanel());
	    mainPanel.setOneTouchExpandable(true);
	    mainPanel.setDividerLocation(.75);
	    
	    return mainPanel;
    }
	private JComponent makeCenterPanel()
	{	
		JPanel top = new JPanel();
		int xSizeOfColumn =(int) ( tk.getScreenSize().getWidth()/2);  
		int ySizeOfColumn = ((int) tk.getScreenSize().getHeight()/2);
		
		top.setPreferredSize(new Dimension(xSizeOfColumn, ySizeOfColumn));
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		
		JPanel bottom = new JPanel();
		
		bottom.setPreferredSize(new Dimension(xSizeOfColumn, ySizeOfColumn));
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
	    JLabel label = new JLabel(icon);
	    
	    
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
        startB.setPreferredSize(new Dimension(400, 50));
		sbHandler = new StartButtonHandler();
		startB.addActionListener(sbHandler);
        
		label.setAlignmentX(CENTER_ALIGNMENT);
		descriptionPane.setAlignmentX(CENTER_ALIGNMENT);
		startB.setAlignmentX(CENTER_ALIGNMENT);
		
		top.add(label);
		bottom.add(descriptionPane);
		bottom.add(new Box.Filler(new Dimension(0, 20),
				new Dimension(0, 20),
				new Dimension(0, 20)));
		bottom.add(startB);
		bottom.add(new Box.Filler(new Dimension(0, 20),
				new Dimension(0, 20),
				new Dimension(0, 20)));
        
		/*exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);*/
        
        
        JSplitPane gameInstruction = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
	    		top, bottom);
	    gameInstruction.setOneTouchExpandable(true);
	    gameInstruction.setDividerLocation(.75);
	    gameInstruction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    return gameInstruction;
	}
	private JComponent makeLeftPanel()
	{
		JPanel left = new JPanel();
		int xSizeOfColumn =(int) ( tk.getScreenSize().getWidth()/5);  
		int ySizeOfColumn = ((int) tk.getScreenSize().getHeight()/2);
		
		left.setMinimumSize(new Dimension(xSizeOfColumn, ySizeOfColumn));
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		
		JLabel rateThis = new JLabel("Rate This Game");
		
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
	    Image scaled = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	    icon = new ImageIcon(scaled);
	    JLabel label = new JLabel(icon);
	    
	    JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
	    separator.setForeground(Color.BLUE);
	    
	    JLabel rateOthers = new JLabel("Rate These Other Games");
		
		JLabel moreLabels = new JLabel(icon);
	    
	    
	    left.add(rateThis);
	    left.add(label);
	    left.add(separator);
	    left.add(rateOthers);
	    left.add(moreLabels);
	    
	    left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    return left;
	}
	private JComponent makeRightPanel()
	{
		JPanel right = new JPanel();
		int xSizeOfColumn =(int) ( tk.getScreenSize().getWidth()/5);  
		int ySizeOfColumn = ((int) tk.getScreenSize().getHeight()/2);
		
		right.setMinimumSize(new Dimension(xSizeOfColumn, ySizeOfColumn));
	    right.setLayout(new GridLayout(3, 0));
	    
	    ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
	    Image scaled = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	    icon = new ImageIcon(scaled);
	    JLabel label = new JLabel(icon);
	    
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
