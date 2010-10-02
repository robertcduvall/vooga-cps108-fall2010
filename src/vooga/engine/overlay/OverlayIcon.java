package vooga.engine.overlay;

import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

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

	private GreenfootImage myIcon;
	private int myNumOfIcons;
	private int myPreviousNumOfIcons;
	private StatInt myStatKeeper;
	private Stat<Integer> myStatKeeperGen;
	private ArrayList<OverlayStatImage> myList;
	private OverlayString myText;
	
	
	/**
	 * Creats Overlay Icon that is Int specific
	 * @param stat Specific class for Ints only
	 * @param newIcon Icon to display
	 * @param label String to label the icons
	 */
	public OverlayIcon (StatInt stat, GreenfootImage newIcon, String label)  //Dimensions of the image
	{
		myText = new OverlayString(label);
		myStatKeeper = stat;
		myIcon = newIcon;
		myNumOfIcons = 0;
		myPreviousNumOfIcons = 0;
		myList = new ArrayList<OverlayStatImage>();
		makeLabel();
	}
	
	
	/**
	 * 
	 * Creats Overlay Icon class that is Integer specific
	 * @param stat Stat object that contains an Integer to keep track of
	 * @param newIcon Icon to display
	 * @param label String to label the icons
	 * @param width Width of the icon
	 * @param height Height of the icon
	 */
	public OverlayIcon (Stat<Integer> stat, GreenfootImage newIcon, String label, int width, int height) //Dimensions given
	{
		this(stat, newIcon, label);
		myIcon.scale(width, height);
	}
	
	/**
	 * 
	 * Creats Overlay Icon class that is Integer specific
	 * @param stat Stat object that contains an Integer to keep track of
	 * @param newIcon Icon to display
	 * @param label String to label the icons
	 */
	public OverlayIcon (Stat<Integer> stat, GreenfootImage newIcon, String label)  //Dimensions of the image
	{
		myText = new OverlayString(label);
		myStatKeeperGen = stat;
		myIcon = newIcon;
		myNumOfIcons = 0;
		myPreviousNumOfIcons = 0;
		myList = new ArrayList<OverlayStatImage>();
		makeLabel();
	}
	
	
	/**
	 * 
	 * Creats Overlay Icon that is Int specific
	 * @param stat Specific class for Ints only
	 * @param newIcon Icon to display
	 * @param label String to label the icons
	 * @param width Width of the icon
	 * @param height Height of the icon
	 */
	public OverlayIcon (StatInt stat, GreenfootImage newIcon, String label, int width, int height) //Dimensions given
	{
		this(stat, newIcon, label);
		myIcon.scale(width, height);
	}
	
	
	
	
	private void makeLabel(){  //make the label, which is text appearing before the icons
		myText.print(myText.getString());
		this.setImage(myText.getImage());
	}
	
	
	
	
	/**
	 * Updates the number of images showing
	 */
	@Override
	public void act()
	{
		if (myStatKeeper != null){
			myNumOfIcons = myStatKeeper.getStat();
		}else if(myStatKeeperGen != null){
			myNumOfIcons = myStatKeeperGen.getStat();
		}else{
			myNumOfIcons = 0;
		}
		update();
	}
	
	private void update()
	{
		//Loops until the previous number of icons equals the new number of icons
		while(myNumOfIcons != myPreviousNumOfIcons) 
		{
			if(myNumOfIcons > myPreviousNumOfIcons){
				OverlayStatImage image = new OverlayStatImage(myIcon);
				myList.add(image);
				double endOfString =  getX() + ((double)getImage().getWidth())/2;
				int widthOfIcons = (image.getImage().getWidth() + 5) * (myList.size() -1);
				getWorld().addObject(image, (int)(endOfString + widthOfIcons + 8) , getY());
				myPreviousNumOfIcons++;
			}
				
			else if(myNumOfIcons < myPreviousNumOfIcons)
			{
				OverlayStatImage image = myList.remove(myList.size() - 1);
				image.getWorld().removeObject(image);
				myPreviousNumOfIcons--;
			}
			else{
				break;
			}
		}
		double endOfString =  getX() + ((double)getImage().getWidth())/2;
		int paddingBetweenIcons = 5;
		int paddingBetweenStringAndIcons = 8;
		int widthOfIcons = (myList.get(myList.size()-1).getImage().getWidth() + paddingBetweenIcons);
		for(int i=0; i<myList.size(); i++){
			myList.get(i).setLocation((int)(endOfString + (widthOfIcons* (i)) + paddingBetweenStringAndIcons) , getY());
		}
		
	}
	
	
	/**
	 * @return width of the Overlay Icon including String and all icons
	 */
	@Override
	public int getWidth(){
		int mid = myText.getX();
		int begLocOfString = mid - myText.getImage().getWidth()/2;
		int locOfLastIcon = myList.get(myList.size() - 1).getX();
		int endLocOfLastIcon = locOfLastIcon + myList.get(myList.size() - 1).getImage().getWidth()/2;
		return endLocOfLastIcon - begLocOfString;
		
	}
	
	
	public void setFont(Font font){
		myText.setFont(font);
		makeLabel();
	}
	
	public void setColor(Color color){
		myText.setColor(color);
		makeLabel();
	}

}

