package vooga.games.grandius.states;

import vooga.engine.core.Game;
import vooga.engine.state.MenuGameState;
import vooga.games.grandius.buttons.PlayButton;
import vooga.games.grandius.buttons.QuitButton;
//TODO This GameState should be implemented using a MenuGameState
//private void buildMenuState() {
//	menuState = new GameState();
//	menuState.addGroup(backgroundGroup);
//	for(int i=1;i<9;i++){
//		OverlayString menu = new OverlayString(Resources.getString("menu"+i), font);
//		menu.setLocation(Resources.getInt("menuX"), Resources.getInt("menuY")*i);
//		menuGroup.add(menu);
//	}               
//	menuState.addRenderGroup(menuGroup);
//}
public class GrandiusMenuState extends MenuGameState {

	private PlayButton myPlayButton;
	private QuitButton myQuitButton;
	
	public GrandiusMenuState(Game game) {
		this.myPlayButton = new PlayButton(game);
		this.myQuitButton = new QuitButton(game);
	}

	@Override
	public void initialize() {
		addButton(myPlayButton);
		addButton(myQuitButton);
	}
	
}
