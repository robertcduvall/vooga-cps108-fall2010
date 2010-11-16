package vooga.engine.level;

import java.util.*;

public class LevelSwitchingConditions {
	private int levelSwitchIndex;
	private PriorityQueue<LevelSwitch> conditions = new PriorityQueue<LevelSwitch>(100,new Comp());
	
	public void addCondition(LevelSwitch condition){
		conditions.add(condition);
	}
	public void removeCondition(LevelSwitch condition){
		conditions.remove(condition);
	}
	public PriorityQueue<LevelSwitch> getConditions(){
		return conditions;
	}
	public void setConditions(PriorityQueue<LevelSwitch> c){
		conditions = c;
	}
	public boolean levelSwitch(){
		for(LevelSwitch ls:conditions){
			if (ls.switchLevel()){
				levelSwitchIndex = ls.switchToLevel();
				return true;
			}
		}
		return false;
	}
	public int getSwitchToLevel(){
		return levelSwitchIndex;
	}
	
	class Comp implements Comparator<LevelSwitch>{
		@Override
		public int compare(LevelSwitch ls0, LevelSwitch ls1) {
			return (ls0.getPriority()-ls1.getPriority());
		}
		
	}
}
