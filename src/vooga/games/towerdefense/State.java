package vooga.games.towerdefense;

import java.util.*;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.player.control.Control;
import vooga.engine.state.GameState;

public class State extends GameState{
	
	private Collection<Control> controls = new ArrayList<Control>();

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	public SpriteGroup addAndReturnGroup(SpriteGroup sg) {
		addGroup(sg);
		return sg;
	}
	
	public Control addControl(Control control){
		controls.add(control);
		return control;
	}
	
	public void update(long t) {
		super.update(t);
		for(Control c: controls){
			c.update();
		}
	}

}
