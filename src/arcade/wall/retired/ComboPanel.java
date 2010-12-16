package arcade.wall.retired;

import javax.swing.JRadioButton;


/**
 * 
 * @author Cameron McCallie
 *
 */
@SuppressWarnings("serial")
public class ComboPanel extends AbstractButtonPanel{
	
	private String[] myRatings;
	
	public ComboPanel(int scale) {
		super(scale);
		myRatings = new String[scale];
		for (int i = 1; i < scale+1; i ++){
			myRatings[i-1] = ""+i;
		}
		
	}

	/**
	 * Adds a comment after the rating.
	 * Ex: 4 - Awesome!
	 * 
	 * Note that the user would have to specify the "-".
	 */
	public void addComment(int index, String comment){
		String newLabel = myRatings[index - 1] + " " + comment;
		myRatings[index - 1] = newLabel;
	}
}
