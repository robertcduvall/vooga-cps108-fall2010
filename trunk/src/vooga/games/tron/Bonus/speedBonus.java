package vooga.games.tron.bonus;

/**
 * This class holds the speed-up bonus for the player
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class speedBonus extends Sprite {

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
	
	public speedBonus(BufferedImage image,double initialColPosition,double initialRowPosition,int BounsImageWidth){
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
}
