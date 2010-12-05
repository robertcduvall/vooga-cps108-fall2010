package arcade.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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

	}
	
	public void initialize()
	{
		gameParser = new GameParser();
		gameParser.parseGame(gameName, gameProperties);
		add(setText());
		add(startButton());
	}
	
	public JTextArea setText()
	{
		JTextArea text = new JTextArea();
		text.append(gameProperties.get("genre")[0] + "\n");
		
		for (String instruction : gameProperties.get("instructions"))
		{
			text.append(instruction + "\n");
		}
		return text;		
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
				Class<?> newGame = Class.forName(gameName);
				Constructor<?> gameConstructor = newGame.getConstructor();
				Game.launch((Game)gameConstructor.newInstance());
			} catch (Throwable e1){
				e1.printStackTrace();
			}
		}
	}

}
