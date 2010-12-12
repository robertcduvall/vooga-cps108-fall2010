package arcade.ads.adscontent;

import org.w3c.dom.NamedNodeMap;

import vooga.engine.core.BetterSprite;

/**
 * This is simple class will provide general functionality for all ads, such as
 * name, start and end date, maximum vies, onClick, onMouseOver, update, and
 * render methods. We feel like these are the very basic methods and ideas that
 * every single ad is going to need.
 * 
 * @author Hao He (hh89@duke.edu)
 * @author Nick Straub (njs7@duke.edu)
 * @author Scott Winkleman (saw26@duke.edu)
 * @author Kate Yang (kly2@duke.edu)
 * 
 * @version 1.0
 */

public abstract class BasicAds extends BetterSprite
{

	protected String name;
	protected int xMin;
	protected int xMax;
	protected int yMin;
	protected int yMax;
	protected long startTime;
	protected long endTime;
	protected String targetURL;

	public BasicAds()
	{
		super();
	}
	
	/**
	 * sets ads name
	 * 
	 * @param name
	 */
	public BasicAds(String name)
	{
		this.name = name;
	}

	/**
	 * sets rendering area
	 * 
	 * @param name
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 */
	public BasicAds(String name, int xMin, int xMax, int yMin, int yMax, String targetURL)
	{
		this.name = name;
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.targetURL = targetURL;
	}

	/**
	 * action when user clicks on the ads
	 */
	public abstract void onClick();

	/**
	 * action when user moves the mouse on the ads
	 */
	public abstract void onMouseOver();

	// /**
	// * update ads
	// */
	// public abstract void update();

	// /**
	// * render ads
	// */
	// public abstract void render();

	/**
	 * check if ad is active
	 */
	public abstract boolean isActive();

	/**
	 * get ads's name
	 * 
	 * @return ads's name
	 */
	public String getName()
	{
		return this.name;
	};

	/**
	 * set ads's name
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	};

	/**
	 * get ads's start time
	 * 
	 * @return ads's start time
	 */
	public long getStartTime()
	{
		return this.startTime;
	};

	/**
	 * set ads's start time
	 * 
	 * @param startTime
	 */
	public void setStartTime(long startTime)
	{
		this.startTime = startTime;
	};

	/**
	 * get ads's end time
	 * 
	 * @return ads's end time
	 */
	public long getEndTime()
	{
		return endTime;
	};

	/**
	 * set ads's end time
	 * 
	 * @param endTime
	 */
	public void setEndTime(long endTime)
	{
		this.endTime = endTime;
	}

	/**
	 * get ads's duration
	 * 
	 * @return
	 */
	public long getDuration()
	{
		return getEndTime() - getStartTime();
	};
	
	public void setParameters(NamedNodeMap attributes)
	{
		
	}
	
	/**
	 * 	opens the targetURL in the default browser
	 * 	http://www.mkyong.com/java/open-browser-in-java-windows-or-linux/
	 */
	public void openBrowser(){
		String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
		try{ 
		    if (os.indexOf( "win" ) >= 0) {
	 
		        // this doesn't support showing urls in the form of "page.html#nameLink" 
		        rt.exec( "rundll32 url.dll,FileProtocolHandler " + targetURL);
	 
		    } else if (os.indexOf( "mac" ) >= 0) {
	 
		        rt.exec( "open " + targetURL);
	 
	            } else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {
	 
		        // Do a best guess on unix until we get a platform independent way
		        // Build a list of browsers to try, in this order.
		        String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
		       			             "netscape","opera","links","lynx"};
	 
		        // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
		        StringBuffer cmd = new StringBuffer();
		        for (int i=0; i<browsers.length; i++)
		            cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + targetURL + "\" ");
	 
		        rt.exec(new String[] { "sh", "-c", cmd.toString() });
	 
	           } else {
	                return;
	           }
	       }catch (Exception e){
		    return;
	       }
	}
}
