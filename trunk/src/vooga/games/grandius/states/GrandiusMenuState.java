package vooga.games.grandius.states;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.MenuGameState;
import vooga.games.grandius.buttons.PlayButton;
import vooga.games.grandius.buttons.QuitButton;
//TODO This GameState should be implemented using a MenuGameState
//private void buildMenuState() {
//      menuState = new GameState();
//      menuState.addGroup(backgroundGroup);
//      for(int i=1;i<9;i++){
//              OverlayString menu = new OverlayString(Resources.getString("menu"+i), font);
//              menu.setLocation(Resources.getInt("menuX"), Resources.getInt("menuY")*i);
//              menuGroup.add(menu);
//      }               
//      menuState.addRenderGroup(menuGroup);
//}
public class GrandiusMenuState extends MenuGameState {

        private PlayButton myPlayButton;
        private QuitButton myQuitButton;
        private Game myGame;
        private EventPool eventPool;
        
        public GrandiusMenuState(Game game) {
                myGame = game;
        }

        @Override
        public void initialize() {
                this.myPlayButton = new PlayButton(myGame);
                this.myQuitButton = new QuitButton(myGame);
                addButton(myPlayButton);
                addButton(myQuitButton);
                eventPool = new EventPool();
                eventPool.addEvent(myPlayButton);
                eventPool.addEvent(myQuitButton);
        }
        
        @Override
        public void update(long elapsedTime) {
                super.update(elapsedTime);
                eventPool.checkEvents();
        }
}
