package arcade.wall.views.walltab;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import arcade.lobby.model.ProfileSet;
import arcade.wall.models.data.comment.Comment;

@SuppressWarnings("serial")
public class DisplayPanel extends JPanel {
	private JLabel 
	myGameHeaderLabel, 
	myTopRatedGamesLabel;
	private JTextArea myCommentsArea;
	
	public DisplayPanel() {
		myGameHeaderLabel = new JLabel();
		myTopRatedGamesLabel = new JLabel();
		updateTopRatedGamesLabel();
		setGameHeaderLabel(WallTabView.myGameChoices[ReviewPanel.gameComboBox.getSelectedIndex()]);
		
		myCommentsArea = new JTextArea();
		myCommentsArea.setEditable(false);
		this.add(myGameHeaderLabel);
		myCommentsArea.setBorder(WallTabView.constructWallBorder(WallTabView.myResources.getString("commentsAreaBorder")));
		this.add(myCommentsArea);
		this.add(myTopRatedGamesLabel);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(WallTabView.constructWallBorder(WallTabView.myResources.getString("displayPanelBorder")));
		
	}
	
	public void setGameHeaderLabel(String selectedGame) {
		this.myGameHeaderLabel.setText("<html>" + 
				"<font color=blue>" + selectedGame + "</font> || Average Rating: " +
				+ WallTabView.myController.getRating(selectedGame) + "</html>");
	}
	
	/**
	 * Refreshes the CommentsArea to display the comments for the selected game.
	 */
	public void refreshCommentsArea(List<Comment> gameComments){
		String displayString = "";
		for(Comment comment: gameComments){  
			String starString = "";
			for (int i = 0; i < Integer.parseInt(comment.getRating()); i++) {
				starString += "*";
			}
			displayString += " >> ''" + comment.getString() + "''  " + starString + 
			" " +  "---" + ProfileSet.getProfile(Integer.parseInt(comment.getUserId())).getFirstName() + "\n";           
		}
		myCommentsArea.setText(displayString);
	}
	
	/**
	 * Updates the Top-Rated Games label to display the Top 3 games in terms of average rating.
	 */
	public void updateTopRatedGamesLabel() {
		List<String> gameRankList = WallTabView.myController.getGameRankList();
		myTopRatedGamesLabel.setText("<html>" + 
				"<font color=red> Top Rated Game: " + gameRankList.get(0) + "</font> <br/>" +
				"2nd Place Game: " + gameRankList.get(1) + "<br/>" +
				"3rd Place Game: " + gameRankList.get(2)
				+ "</html>");
	}
}
