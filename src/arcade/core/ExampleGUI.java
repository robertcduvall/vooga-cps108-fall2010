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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

/**
 * This is the example GUI for arcade. It contains scrollable and adjustable
 * panels for the game splash screen, instructions, avatar display,
 * friend lobby display, advertisements, game rating, and display of other games.
 * Most of the components are currently blank, but will be added once each component
 * is done by other groups. The start button will run the game currently on view.
 * 
 * 
 * @author Derek Zhou, Yang Su, Aaron Choi
 * 
 */
public class ExampleGUI extends Tab{

	private JButton startB, exitB;

	private StartButtonHandler sbHandler;
	private ExitButtonHandler ebHandler;
	
	
	private Toolkit tk;

	private static String gameName="cyberion";
	private static JPanel content;
	
	private static JSplitPane columnar;
	public ExampleGUI()
	{
		tk = Toolkit.getDefaultToolkit();
		setName("Arcade");
		setToolTipText("Arcade main view");
	}
	
	public JComponent getContent() {
		
	    columnar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	    		makeLeftPanel(), content);
	    columnar.setOneTouchExpandable(true);
	    columnar.setDividerLocation(.25);
	    
	    JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	    		columnar, makeRightPanel());
	    mainPanel.setOneTouchExpandable(true);
	    mainPanel.setDividerLocation(.75);
	    setContent();
	    return mainPanel;
    }
	
	private static void setContent(){
		content=new GameView(gameName);
		columnar.setRightComponent(content);
	}
	
	public static void setGame(String name){
		gameName=name;
		setContent();
		System.out.println(gameName);
	}
	
	//makes the center panel
//	private JComponent makeCenterPanel()
//	{	
//		JPanel center = new JPanel();
//		center.add(new GameView("zombieland"));
		
//		JPanel top = new JPanel();
//		int xSizeOfColumn =(int) ( tk.getScreenSize().getWidth()/2);  
//		int ySizeOfColumn = ((int) tk.getScreenSize().getHeight()/2);
//		
//		top.setPreferredSize(new Dimension(xSizeOfColumn, ySizeOfColumn));
//		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
//		
//		JPanel bottom = new JPanel();
//		
//		bottom.setPreferredSize(new Dimension(xSizeOfColumn, ySizeOfColumn));
//		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
//		
//		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
//	    JLabel label = new JLabel(icon);
//	    
//	    
//	    JTextPane descriptionPane = new JTextPane();
//	    descriptionPane.setContentType("text/html");
//        String description = "<p><b>Description:</b></p>" +
//            "<p>This is where instructions for the game would go. <br>" +
//            "For example:<br> Left Arrow = move left. <br>" +
//            "Right Arrow = move right <br>" +
//            "Spacebar = fire.</p>";
//        descriptionPane.setText(description);
//        descriptionPane.setEditable(false);
//        
//        startB = new JButton("Start");
//        startB.setPreferredSize(new Dimension(400, 50));
//		sbHandler = new StartButtonHandler();
//		startB.addActionListener(sbHandler);
//        
//		label.setAlignmentX(CENTER_ALIGNMENT);
//		descriptionPane.setAlignmentX(CENTER_ALIGNMENT);
//		startB.setAlignmentX(CENTER_ALIGNMENT);
//		
//		top.add(label);
//		bottom.add(new Box.Filler(new Dimension(0, 20),
//				new Dimension(0, 20),
//				new Dimension(0, 20)));
//		bottom.add(startB);
//		bottom.add(new Box.Filler(new Dimension(0, 20),
//				new Dimension(0, 20),
//				new Dimension(0, 20)));
        
		/*exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);*/
        
        
		//divides the center pane into the splash and the instructions
//        JSplitPane gameInstruction = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
//	    		top, bottom);
//	    gameInstruction.setOneTouchExpandable(true);
//	    gameInstruction.setDividerLocation(.75);
//	    gameInstruction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
//	    return center;
//	}
	
	//makes the left hand side panel
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
	
	//makes the right hand side panel
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
	    
	    //adds panels to the grid layout
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
	
	//starts the game on the splash screen
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
}
