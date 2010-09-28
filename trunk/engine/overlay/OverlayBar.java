package engine.overlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.*;
import greenfoot.*;


/**
 * The OverlayBar class displays a bar that grows or shrinks based on an int and
 * a maximum indicated by the user
 * 
 * @author Andrew Bown
 * 
 */
public class OverlayBar extends Overlay {

	private static int DEFAULT_MAXLENGTH;
	private static int DEFAULT_HEIGHT;
	private static Color DEFAULT_FILLCOLOR;
	private static Color DEFAULT_BACKGROUNDCOLOR;
	private static boolean DEFAULT_SHOWBACKGROUND;

	private Stat<Integer> myStat;
	private int myMaxLength = DEFAULT_MAXLENGTH;
	private int myMaxScore;
	private Color myColor = DEFAULT_FILLCOLOR;
	private Color myBackgroundColor = DEFAULT_BACKGROUNDCOLOR;
	private int myHeight = DEFAULT_HEIGHT;

	/**
	 * Constructs an OverlayBar with the given Stat<Integer> and maxScore.
	 * 
	 * @param stat
	 *            The statistic that the OverlayBar should represent.
	 * @param maxScore
	 *            The maximum value the statistic can reach.
	 */
	public OverlayBar(Stat<Integer> stat, int maxScore) {
		DEFAULT_MAXLENGTH = 300;
		DEFAULT_HEIGHT = 20;
		DEFAULT_FILLCOLOR = Color.BLACK;
		DEFAULT_BACKGROUNDCOLOR = Color.RED;
		DEFAULT_SHOWBACKGROUND = true;

		myStat = stat;
		myMaxScore = maxScore;
	}

	/**
	 * This method is called by the Greenfoot API. It calls drawBar().
	 */
	public void act() {
		drawBar();
	}

	/**
	 * This method draws the OverlayBar on the screen according to the
	 * specifications set by the user.
	 */
	private void drawBar() {
		GreenfootImage image = getImage();
		if (image.getWidth() != myMaxLength || image.getHeight() != myHeight) {
			image = new GreenfootImage(myMaxLength, myHeight);
		}

		// fillRect uses 0,0 because it is drawn in relation to the
		// GreenfootImage boundry
		image.setColor(myBackgroundColor);
		image.fillRect(0, 0, myMaxLength, myHeight);

		float ratio = (float) myStat.getStat() / (float) myMaxScore;

		// only draw the bar fill if the score is greater than 0
		if (ratio > 0) {
			int width = Math.round(ratio * myMaxLength);
			image.setColor(myColor);
			image.fillRect(0, 0, width, myHeight);
		}

		setImage(image);
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
