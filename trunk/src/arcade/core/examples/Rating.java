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
