package arcade.core.examples;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import arcade.core.Panel;
import arcade.lobby.model.ProfileSet;
import arcade.store.StoreModel;
import arcade.wall.models.WallModel;
import arcade.wall.views.walltab.WallTabPanel;


public class OtherRatings extends Panel {
	private WallModel model;
	private String[] gameList;
	
	public OtherRatings(){
		/*
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		JLabel rateOthers = new JLabel("Rate These Other Games");
		JLabel moreLabels = new JLabel(icon);
		
		add(rateOthers);
		add(moreLabels);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		*/
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
