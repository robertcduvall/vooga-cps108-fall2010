package vooga.engine.overlay;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 * The OverlayBar class displays a bar that grows or shrinks based on an int and
 * a maximum indicated by the user
 * 
 * This example creates an OverlayBar that displays the score of a game (as represented by myStat). The
 * OverlayBar is placed at point (10,10);
 * </br>
 * <code>
 * </br>
 * Stat<Integer> myStat;
 * </br>
 * myStat = new Stat(Integer 0);
 * </br>
 * OverlayBar scorebar = new OverlayBar(myStat,100);
 * </br>
 * addObject(scorebar,10,10);
 * </code> 
 * 
 * @author Andrew Brown
 * 
 */
public class OverlayBar extends Overlay {

	private static final int DEFAULT_MAXLENGTH = 300;
	private static final int DEFAULT_HEIGHT = 20;
	private static final Color DEFAULT_FILLCOLOR = Color.BLACK;
	private static final Color DEFAULT_BACKGROUNDCOLOR = Color.RED;

	private Stat<Integer> myStat;
	private int myMaxLength = DEFAULT_MAXLENGTH;
	private int myMaxScore;
	private Color myColor = DEFAULT_FILLCOLOR;
	private Color myBackgroundColor = DEFAULT_BACKGROUNDCOLOR;
	private int myHeight = DEFAULT_HEIGHT;
	
	private static final long serialVersionUID = 1L;


	/**
	 * Constructs an OverlayBar with the given Stat<Integer> and maxScore.
	 * 
	 * @param stat
	 *            The statistic that the OverlayBar should represent.
	 * @param maxScore
	 *            The maximum value the statistic can reach.
	 */
	public OverlayBar(Stat<Integer> stat, int maxScore) {
		myStat = stat;
		myMaxScore = maxScore;
	}

	/**
	 * Prints the bar on the screen by calling drawBar().
	 */
	@Override
	public void update(long t) {
		drawBar();
	}

	/**
	 * This method draws the OverlayBar on the screen according to the
	 * specifications set by the user.
	 */
	private void drawBar(){
		BufferedImage bufferedImage = new BufferedImage(myMaxLength, myHeight,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setColor(myBackgroundColor);
		g2d.fillRect(0, 0, myMaxLength, myHeight);
		
		float ratio = (float) myStat.getStat() / (float) myMaxScore;

		// only draw the bar fill if the score is greater than 0
		if (ratio > 0) {
			int width = Math.round(ratio * myMaxLength);
			g2d.setColor(myColor);
			g2d.fillRect(0, 0, width, myHeight);
		}
		
		g2d.dispose();
		setImage(bufferedImage);
	}

	/**
	 * This method returns the color of the OverlayBar's fill. This is the color
	 * of the rectangle that grows and shrinks based on the score.
	 * 
	 * @return The color of the rectangle.
	 */
	public Color getColor() {
		return myColor;
	}

	/**
	 * This method sets the color of the OverlayBar's fill. This sets the color
	 * of the rectangle that grows and shrinks based on the score.
	 * 
	 * @param color
	 *            The new color for the rectangle
	 */
	public void setColor(Color color) {
		if(color != null){
			myColor = color;
		}
	}

	/**
	 * Returns the maximum length of the OverlayBar.
	 * 
	 * @return The maximum length of the OverlayBar.
	 */
	public int getMaxLength() {
		return myMaxLength;
	}

	/**
	 * Sets the maximum length of the OverlayBar. It must be greater than 0.
	 * 
	 * @param maxLength
	 *            The length the maximum should be set to.
	 */
	public void setMaxLength(int maxLength) {
		if(maxLength > 0){
			myMaxLength = maxLength;
		}
	}

	/**
	 * Returns the Stat that the OverlayBar represents.
	 * 
	 * @return The Stat the OverlayBar represents.
	 */
	public Stat<Integer> getStat() {
		return myStat;
	}

	/**
	 * Sets the Stat the OverlayBar represents.
	 * 
	 * @param stat
	 *            The stat the OverlayBar should represent.
	 */
	public void setStat(Stat<Integer> stat) {
		if(stat != null){
			myStat = stat;
		}
	}

	/**
	 * Returns the maximum score for the OverlayBar, which determines the length
	 * of the OverlayBar relative to the current score.
	 * 
	 * @return The maximum score for the OverlayBar.
	 */
	public int getMaxScore() {
		return myMaxScore;
	}

	/**
	 * Sets the maximum score for the OverlayBar, which determines the length of
	 * the OverlayBar relative to the current score. Must be greater than 0.
	 * 
	 * @param maxScore 
	 * 			The score the maximum should be set to.
	 */
	public void setMaxScore(int maxScore) {
		if(maxScore > 0){
			myMaxScore = maxScore;
		}
	}

	/**
	 * Returns the background color of the OverlayBar.
	 * 
	 * @return The background color of the OverlayBar.
	 */
	public Color getBackgroundColor() {
		return myBackgroundColor;
	}

	/**
	 * Sets the backgroundColor of the OverlayBar.
	 * 
	 * @param backgroundColor
	 *            The color the background color should be set to.
	 */
	public void setBackgroundColor(Color backgroundColor) {
		if(backgroundColor != null){
			myBackgroundColor = backgroundColor;
		}
	}

	/**
	 * Returns the height of the OverlayBar.
	 * 
	 * @return The height of the OverlayBar.
	 */
	@Override
	public int getHeight() {
		return myHeight;
	}

	/**
	 * Sets the height of the OverlayBar. Must be greater than 0.
	 * 
	 * @param height
	 *            The height the OverlayBar should be set to.
	 */
	public void setHeight(int height) {
		if(height > 0){
			myHeight = height;
		}
	}

}
