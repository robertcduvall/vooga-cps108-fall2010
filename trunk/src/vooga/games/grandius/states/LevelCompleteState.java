package vooga.games.grandius.states;

import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;
//TODO
//This GameState should be implemented using a BasicTextGameState
//private void buildLevelCompleteState() {
//	levelCompleteState = new GameState();
//	int displayX = Resources.getInt("levelCompleteX");
//	int displayY = Resources.getInt("levelCompleteY");
//
//	OverlayString levelComplete1 = new OverlayString("LEVEL " + (levelManager.getCurrentLevel()+1) + " COMPLETE", font);
//	levelComplete1.setLocation(displayX,displayY);
//	OverlayString levelComplete2 = new OverlayString(Resources.getString("levelCompleteMessage"), font);
//	levelComplete2.setLocation(displayX,2*displayY);
//
//	levelCompleteState.addGroup(backgroundGroup);
//	levelCompleteGroup.add(levelComplete1);
//	levelCompleteGroup.add(levelComplete2);
//	levelCompleteState.addRenderGroup(levelCompleteGroup);
//}
public class LevelCompleteState extends BasicTextGameState {

	private static String myLevelCompleteMessage = Resources.getString("levelCompleteMessage");
	public LevelCompleteState() {
		super(myLevelCompleteMessage);
	}
	
}
