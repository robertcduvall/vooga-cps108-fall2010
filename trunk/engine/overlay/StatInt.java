/**
 * Int specific Stat tracker
 * 
 *
 * 
 * getStat() will be called when it is time to display the stat
 * in order to change the stat call setStat(int i)
 * 
 * @author Justin Goldsimth
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

}
