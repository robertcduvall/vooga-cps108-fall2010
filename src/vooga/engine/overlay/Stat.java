package vooga.engine.overlay;

/**
 * Generic Stat class, can be of any non primitive type.
 * 
 * toString() on object will be called when it is displayed
 * getStat() will be called when it is time to display the stat
 * in order to change the stat call setStat(T i)
 * 
 * <xmp>
 * <p>Stat<String> stat = new Stat<String>("Hello");</p>
 * <p>stat.setStat("World");</p>
 * </xmp>
 * 
 *  @author Justin Goldsimth
 */
public class Stat<T> {

	
	private T myStat;
	private T myInitialStat;
	
	
	/**
	 * Creates generic stat tracker object
	 * 
	 * @param i can be any Object
	 */
	public Stat(T i){
		myStat = i;
		myInitialStat = i;
	}
	
	
	public T getStat() {
		return myStat;
	}
	
	public void setStat(T i){
			myStat = i;
		}
	
	/**
	 * Resets the stat to the initial value.
	 */
	public void reset(){
		myStat = myInitialStat;
	}
	}
	
	
