package vooga.games.tronupdate.items;

/**
 * This class holds the speed-up bonus for the player
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.games.tronupdate.util.*;


public abstract class Bonus extends BetterSprite{
	private static final long serialVersionUID = 1L;
	
	private double col;
	private double row;
	private double BounsImageWidth;
	/**
	 * constructor
	 * @param image
	 * @param initialColPosition
	 * @param initialRowPosition
	 * @param BounsImageWidth
	 */
	
	public Bonus(BufferedImage image,double initialColPosition,double initialRowPosition,int BounsImageWidth){
		super(image,initialColPosition*BounsImageWidth,initialRowPosition*BounsImageWidth);
		this.col=initialColPosition;
		this.row=initialRowPosition;
		this.BounsImageWidth=BounsImageWidth;
	}
	/**
	 * get the width of the bonus image
	 * @return
	 */
	public double getBounsImageWidth(){
		return BounsImageWidth;
	}
	/**
	 * get row
	 * @return
	 */
	public double getRow(){
		return row;
	}
	/**
	 * get column
	 * @return
	 */
	public double getCol(){
		return col;
	}
	
	public abstract void act();
	public abstract boolean isConsumed();
	
	
}
