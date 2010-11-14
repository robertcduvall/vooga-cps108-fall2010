package vooga.engine.overlay;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * This Overlay class displays a finite number of icons, with the image provided by the user
 * @author Se-Gil Feldsott and Justin Goldsmith
 * 
 * <pre>
 * <p>StatInt stat = new StatInt(5);</p>
 * <p>GreenfootImage image = new GreenfootImage("...");</p>
 * <p>OverlayIcon overlay = new OverlayIcon(stat, image, "Lives: ");</p>
 * </pre>
 * 
 *  <p>All overlays must be updated and rendered, This is the responsibility of the game creator</p>
 * 
 * 
 *
 */
public class OverlayIcon extends Overlay {

	private OverlayStatImage myIcon;
	private int myNumOfIcons;
	private int myPreviousNumOfIcons;
	private StatInt myStatKeeper;
	private Stat<Integer> myStatKeeperGen;
	private OverlayString myText;
	private SpriteGroup myIconGroup;
		
	/**
	 * 
	 * Creats Overlay Icon class that is Integer specific
	 * @param stat Stat object that contains an Integer to keep track of
	 * @param newIcon Icon to display
	 * @param label String to label the icons
	 */
	public OverlayIcon (Stat<Integer> stat, BufferedImage icon, String label)  //Dimensions of the image
	{
		myText = new OverlayString(label);
		myStatKeeperGen = stat;
		myIcon = new OverlayStatImage(icon);
		myNumOfIcons = stat.getStat().intValue();
		myPreviousNumOfIcons = 0;
		myIconGroup = new SpriteGroup("Icons");
		updateIcon();
	}
	
	public void scaleIcon(int width, int height){
		myIcon.scale(width, height);
	}
	
	
	protected OverlayIcon(Map<String, String> attributes, OverlayTrackerTemp tracker){
		
	}
	
		
	/**
	 * Used to render to the screen.
	 * @param g Graphic to render image to.
	 */
	@Override
	public void render(Graphics2D g){  //make the label, which is text appearing before the icons
		myText.setLocation(getX(), getY());
		myText.print(myText.getString(), g);
		myIconGroup.render(g);
	}
	
	
	
	
	/**
	 * Updates the number of images showing
	 */
	@Override
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
				myIconGroup.add(myIcon.clone());
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
		double endOfString =  getX() + (myText.getWidth());
		int paddingBetweenIcons = 5;
		int paddingBetweenStringAndIcons = 8;
		if(myIconGroup.getActiveSprite()!= null){
			int widthOfIcons = (myIconGroup.getActiveSprite().getImage().getWidth() + paddingBetweenIcons);
			int i = 0;
			for(Sprite sprite: myIconGroup.getSprites()){
				if(sprite != null){
					sprite.setLocation((int)(endOfString + (widthOfIcons * (i)) + paddingBetweenStringAndIcons) , getY() + myText.getHeight()/2);
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
		System.out.println(myIconGroup.getSize());
		int locOfLastIcon = (int)myIconGroup.getSprites()[(myIconGroup.getSize() - 1)].getX();
		int endLocOfLastIcon = locOfLastIcon + myIconGroup.getActiveSprite().getImage().getWidth();
		return endLocOfLastIcon - begLocOfString;
		
	}
	
	
	public void setFont(Font font){
		myText.setFont(font);
	}
	
	public void setFont(GameFont font){
		myText.setFont(font);
	}
	
	public void setColor(Color color){
		myText.setColor(color);
	}
	
	protected OverlayString getOverlayString(){
		return myText;
	}


}

