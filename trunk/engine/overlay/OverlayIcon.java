package engine.overlay;

import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

/**
 * This Overlay class displays a finite number of icons, with the image provided by the user
 * @author Se-Gil Feldsott and Justin Goldsmith
 *
 */
public class OverlayIcon extends Overlay {

	private GreenfootImage icon;
	private int numOfIcons;
	private int previousNumOfIcons;
	private StatInt statKeeper;
	private Stat<Integer> statKeeperGen;
	private ArrayList<OverlayStatImage> list;
	private OverlayString text;
	
	
	/**
	 * Creats Overlay Icon that is Int specific
	 * @param stat Specific class for Ints only
	 * @param newIcon Icon to display
	 * @param label String to label the icons
	 */
	public OverlayIcon (StatInt stat, GreenfootImage newIcon, String label)  //Dimensions of the image
	{
		text = new OverlayString(label);
		statKeeper = stat;
		icon = newIcon;
		numOfIcons = 0;
		previousNumOfIcons = 0;
		list = new ArrayList<OverlayStatImage>();
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
		icon.scale(width, height);
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
		text = new OverlayString(label);
		statKeeperGen = stat;
		icon = newIcon;
		numOfIcons = 0;
		previousNumOfIcons = 0;
		list = new ArrayList<OverlayStatImage>();
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
		icon.scale(width, height);
	}
	
	
	
	
	private void makeLabel(){  //make the label, which is text appearing before the icons
		text.print(text.getString());
		this.setImage(text.getImage());
	}
	
	
	
	
	/**
	 * Updates the number of images showing
	 */
	@Override
	public void act()
	{
		if (statKeeper != null){
			numOfIcons = statKeeper.getStat();
		}else{
			numOfIcons = statKeeperGen.getStat();
		}
		update();
	}
	
	private void update()
	{
		//Loops until the previous number of icons equals the new number of icons
		while(numOfIcons != previousNumOfIcons) 
		{
			if(numOfIcons > previousNumOfIcons){
				OverlayStatImage osi = new OverlayStatImage(icon);
				list.add(osi);
				double endOfString =  getX() + ((double)getImage().getWidth())/2;
				int widthOfIcons = (osi.getImage().getWidth() + 5) * (list.size() -1);
				getWorld().addObject(osi, (int)(endOfString + widthOfIcons + 8) , getY());
				previousNumOfIcons++;
			}
				
			else if(numOfIcons < previousNumOfIcons)
			{
				OverlayStatImage osi = list.remove(list.size() - 1);
				osi.getWorld().removeObject(osi);
				previousNumOfIcons--;
			}
			else{
				break;
			}
		}
		double endOfString =  getX() + ((double)getImage().getWidth())/2;
		int widthOfIcons = (list.get(list.size()-1).getImage().getWidth() + 5);
		for(int i=0; i<list.size(); i++){
			list.get(i).setLocation((int)(endOfString + (widthOfIcons* (i)) + 8) , getY());
		}
		
	}
	
	
	/**
	 * @return width of the Overlay Icon including String and all icons
	 */
	public int getWidth(){
		int mid = text.getX();
		int begLocOfString = mid - text.getImage().getWidth()/2;
		int locOfLastIcon = list.get(list.size() - 1).getX();
		int endLocOfLastIcon = locOfLastIcon + list.get(list.size() - 1).getImage().getWidth()/2;
		return endLocOfLastIcon - begLocOfString;
		
	}
	
	
	public void setFont(Font font){
		text.setFont(font);
		makeLabel();
	}
	
	public void setColor(Color color){
		text.setColor(color);
		makeLabel();
	}

}

