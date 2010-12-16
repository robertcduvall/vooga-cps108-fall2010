package arcade.wall.views.walltab;

import java.awt.Component;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import arcade.lobby.model.ProfileSet;
import arcade.wall.models.data.comment.Comment;

/**
 * Represents the JPanel used to display game comments and ratings.
 * @author John, Cam, Bhawana
 *
 */
@SuppressWarnings("serial")
public class DisplayPanel extends JPanel {
	private JLabel 
	myGameHeaderLabel, 
	myTopRatedGamesLabel;
	private JTextArea myCommentsArea;
	
	public DisplayPanel() {
		myGameHeaderLabel = new JLabel();
		myGameHeaderLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		myTopRatedGamesLabel = new JLabel();
		myTopRatedGamesLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		updateTopRatedGamesLabel();
//		setGameHeaderLabel(WallTabPanel.myGameChoices[FeedbackPanel.myGameComboBox.getSelectedIndex()]);
		this.add(myGameHeaderLabel);
		myCommentsArea = new JTextArea();
		myCommentsArea.setEditable(false);
		myCommentsArea.setBorder(WallTabPanel.constructWallBorder(WallTabPanel.myResources.getString("commentsAreaBorder")));
		this.add(myCommentsArea);
		this.add(myTopRatedGamesLabel);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(WallTabPanel.constructWallBorder(WallTabPanel.myResources.getString("displayPanelBorder")));
		
	}

	public void setGameHeaderLabel(String selectedGame) {
		this.myGameHeaderLabel.setText("<html>" + 
				"<font color=blue>" + selectedGame + "</font> || Average Rating: " +
				+ WallTabPanel.myController.getRating(selectedGame) + "</html>");
		myGameHeaderLabel.setHorizontalAlignment(SwingConstants.LEFT);
	}
	
	/**
	 * Refreshes the CommentsArea to display the comments for the selected game.
	 */
	public void refreshCommentsArea(List<Comment> gameComments){
		String displayString = "";
		for(Comment comment: gameComments){  
			displayString += " >> ''" + comment.getString() + "'' ---" + ProfileSet.getProfile(Integer.parseInt(comment.getUserId())).getFirstName() + "\n";           
		}
		myCommentsArea.setText(displayString);
	}
	
	/**
	 * Updates the Top-Rated Games label to display the Top 3 games in terms of average rating.
	 */
	public void updateTopRatedGamesLabel() {
		List<String> gameRankList = WallTabPanel.myController.getGameRankList();
		myTopRatedGamesLabel.setText("<html>" + 
				"<font color=red> Top Rated Game: " + gameRankList.get(0) + "</font> <br/>" +
				"2nd Place Game: " + gameRankList.get(1) + "<br/>" +
				"3rd Place Game: " + gameRankList.get(2)
				+ "</html>");
		myTopRatedGamesLabel.setHorizontalAlignment(SwingConstants.LEFT);
	}
}
