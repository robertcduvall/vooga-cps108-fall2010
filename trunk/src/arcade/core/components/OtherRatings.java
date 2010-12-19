package arcade.core.components;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import arcade.core.api.Panel;
import arcade.lobby.model.ProfileSet;
import arcade.store.model.StoreModel;
import arcade.wall.models.WallModel;
import arcade.wall.views.walltab.WallTabPanel;

/**
 * A Panel to be added to the main view of the Arcade. Displays the average ratings 
 * of the games that are available for you to play
 * 
 * @author Aaron Choi, Derek Zhou, Yang Su
 *
 */

public class OtherRatings extends Panel {
	private WallModel model;
	private String[] gameList;
	
	public OtherRatings(){
		initialize();
	}
	
	private void initialize()
	{
		gameList = StoreModel.getUserOwnedGamesAsStrings((ProfileSet.getCurrentProfile().getUserId()));
		model = new WallModel();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		for(String game: gameList)
		{
			add(getGameBlockInformation(game));
		}
	}
	
	public JLabel getGameBlockInformation(String selectedGame) {
		JLabel gameInformation = new JLabel();
		gameInformation.setText("<html>" + 
				"<font color=blue>" + selectedGame + "</font> || Average Rating: " +
				+ WallTabPanel.myController.getRating(selectedGame) + "</html>");
		return gameInformation;
	}
}
