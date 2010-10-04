package vooga.engine.overlay;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.ImageUtil;

/**
 * This Overlay class displays a finite number of icons, with the image provided by the user
 * @author Se-Gil Feldsott and Justin Goldsmith
 * 
 * StatInt stat = new StatInt(5);
 * GreenfootImage image = new GreenfootImage("...");
 * OverlayIcon overlay = new OverlayIcon(stat, image, "Lives: ");
 *
 */
public class OverlayIcon extends Overlay {

	private static final long serialVersionUID = 1L;
	private OverlayStatImage myImage;
	private int myNumOfIcons;
	private int myPreviousNumOfIcons;
	private StatInt myStatKeeper;
	private Stat<Integer> myStatKeeperGen;
	private OverlayString myText;
	private SpriteGroup myIconGroup;
	
	
	/**
	 * Creats Overlay Icon that is Int specific
	 * @param stat Specific class for Ints only
	 * @param newIcon Icon to display
	 * @param label String to label the icons
	 */
	public OverlayIcon (StatInt stat, OverlayStatImage newIcon, String label)  //Dimensions of the image
	{
		myText = new OverlayString(label);
		myStatKeeper = stat;
		myImage = newIcon;
		myNumOfIcons = 0;
		myPreviousNumOfIcons = 0;
		myIconGroup = new SpriteGroup("Icons");
		//makeLabel();
	}
	
		
	/**
	 * 
	 * Creats Overlay Icon class that is Integer specific
	 * @param stat Stat object that contains an Integer to keep track of
	 * @param newIcon Icon to display
	 * @param label String to label the icons
	 */
	public OverlayIcon (Stat<Integer> stat, OverlayStatImage newIcon, String label)  //Dimensions of the image
	{
		myText = new OverlayString(label);
		myStatKeeperGen = stat;
		myImage = newIcon;
		myNumOfIcons = 0;
		myPreviousNumOfIcons = 0;
		myIconGroup = new SpriteGroup("Icons");
	}
	
		
	
	public void render(Graphics2D g){  //make the label, which is text appearing before the icons
		myText.setLocation(getX(), getY());
		myText.print(myText.getString(), g);
		myIconGroup.render(g);
		System.out.println(myIconGroup.getSize());
	}
	
	
	
	
	/**
	 * Updates the number of images showing
	 */
	public void update(long t)
	{
		if (myStatKeeper != null){
			myNumOfIcons = myStatKeeper.getStat();
		}else if(myStatKeeperGen != null){
			myNumOfIcons = myStatKeeperGen.getStat();
		}else{
			myNumOfIcons = 0;
		}
		updateIcon();
		myIconGroup.update(t);
	}
	
	private void updateIcon()
	{
		//Loops until the previous number of icons equals the new number of icons
		while(myNumOfIcons != myPreviousNumOfIcons) 
		{
			if(myNumOfIcons > myPreviousNumOfIcons){
				myIconGroup.add(myImage.clone());
				//double endOfString =  getX() + ((double)myText.getMyWidth());
				//int widthOfIcons = (myText.getMyWidth() + 5) * (myIconGroup.getSize() -1);
				//myImage.setLocation((int)(endOfString + widthOfIcons + 8), getY());
				myPreviousNumOfIcons++;
			}
				
			else if(myNumOfIcons < myPreviousNumOfIcons)
			{
				myIconGroup.remove(myIconGroup.getSize() - 1);
				myPreviousNumOfIcons--;
			}
			else{
				break;
			}
		}
		double endOfString =  getX() + ((double)myText.getMyWidth());
		int paddingBetweenIcons = 5;
		int paddingBetweenStringAndIcons = 8;
		if(myIconGroup.getActiveSprite()!= null){
			int widthOfIcons = (myIconGroup.getActiveSprite().getImage().getWidth() + paddingBetweenIcons);
			int i = 0;
			for(Sprite sprite: myIconGroup.getSprites()){
				if(sprite != null){
					sprite.setLocation((int)(endOfString + (widthOfIcons * (i)) + paddingBetweenStringAndIcons) , getY() + myText.getMyHeight()/2);
					i++;
				}
			}
		}
		
	}
	
	
	/**
	 * @return width of the Overlay Icon including String and all icons
	 */
	@Override
	public int getWidth(){
		int begLocOfString = (int)myText.getX();
		int locOfLastIcon = (int)myIconGroup.getSprites()[(myIconGroup.getSize() - 1)].getX();
		int endLocOfLastIcon = locOfLastIcon + myIconGroup.getActiveSprite().getImage().getWidth();
		return endLocOfLastIcon - begLocOfString;
		
	}
	
	
	public void setFont(Font font){
		myText.setFont(font);
	}
	
	public void setColor(Color color){
		myText.setColor(color);
	}

}

