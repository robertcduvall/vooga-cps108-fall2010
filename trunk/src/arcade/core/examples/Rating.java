package arcade.core.examples;

import arcade.core.GameView;
import arcade.core.Panel;
import arcade.wall.controllers.WallTabController;
import arcade.wall.views.walltab.FeedbackPanel;
import arcade.wall.views.walltab.WallTabPanel;

public class Rating extends Panel {
	private WallTabController myController;
	private FeedbackPanel ratingPanel;
	
	public Rating() {
		/*
		JLabel rateThis = new JLabel("Rate This Game");
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		Image scaled = icon.getImage().getScaledInstance(25, 25,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaled);
		JLabel label = new JLabel(icon);
		add(rateThis);
		add(label);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		*/
		initialize();
	}
	
	private void initialize()
	{
		myController = new WallTabController();
		ratingPanel = myController.getView().getFeedbackPanel();
		int indexOfGame = getIndex();
		
		if(indexOfGame != -1)
		{
			ratingPanel.setSelectedIndex(indexOfGame);
		}
		
		add(ratingPanel);
	}
	
	private int getIndex()
	{
		String[] gameArray = WallTabPanel.myGameChoices;
		String currentGame = GameView.getGame();
		
		for(int i = 0; i < gameArray.length; i++)
		{
			if(gameArray[i].equals(currentGame))
			{
				return i;
			}
		}
		return -1;
	}

}
