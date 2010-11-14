package vooga.engine.overlay;

/**
 * Int specific Stat tracker
 * getStat() will be called when it is time to display the stat
 * in order to change the stat call setStat(int i) 
 * 
 * <pre>
 * <p>StatInt stat = new StatInt(5);</p>
 * <p>stat.setStat(7);</p>
 * </pre>
 * 
 *  @author Justin Goldsimth
 */
public class StatInt{

	private int myStat;
	
	
	/**
	 * Creates generic stat tracker object
	 * 
	 * @param i int that is being tracked
	 */
	public StatInt(int i){
		myStat = i;
	}
	
	public int getStat() {
		
		return myStat;
	}
	
	public void setStat(int i){
		myStat = i;
	}

	
	public void addTo(int i){
		myStat += i;
	}
	
	public void subtractFrom(int i){
		myStat -= i;
	}
}
