package arcade.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import vooga.engine.core.Game;

@SuppressWarnings("serial")
public class GameView extends JPanel implements ActionListener{
	
	private GameParser gameParser;
	private String gameName;
	private Map<String, String[]> gameProperties;
	
	public GameView(String game)
	{
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		gameName = game;
		initialize();
	}
	
	public void initialize()
	{
		gameParser = new GameParser();
		gameProperties = new HashMap<String, String[]>();
		gameParser.parseGame(gameName, gameProperties);
		add(setText());
		add(startButton());
	}
	
	public JTextPane setText()
	{
	    JTextPane textPane = new JTextPane();
	    textPane.setContentType("text/html");
        String description = "<p><b>" + gameProperties.get("name")[0] +"</b></p>" +
           "<p>Genre: " +  gameProperties.get("genre")[0] + "</p>" +
            "<p>Description: " + gameProperties.get("description")[0] + "</p>" +
            "<p><b>Instructions</b></p><p>";
        textPane.setEditable(false);
		
		for (String instruction : gameProperties.get("instructions"))
		{
			description += instruction + "<br>";
		}	
		description += "</p>";
		textPane.setText(description);
		return textPane;
	}
	
	public JButton startButton()
	{
		JButton start = new JButton("Start");
		start.setActionCommand("start");
		start.addActionListener(this);
		return start;
	}

	public void actionPerformed(ActionEvent e)
	{
		if ("start".equals(e.getActionCommand()))
		{
			try {
				Class<?> newGame = Class.forName("vooga.games." + gameName + ".Blah");
				Constructor<?> gameConstructor = newGame.getConstructor();
				Game.launch((Game)gameConstructor.newInstance());
			} catch (Throwable e1){
				e1.printStackTrace();
			}
		}
	}

    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Game Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        GameView newContentPane = new GameView("zombieland");
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }

	
}
