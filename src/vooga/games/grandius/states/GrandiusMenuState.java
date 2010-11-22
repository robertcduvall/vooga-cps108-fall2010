package vooga.games.grandius.states;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.MenuGameState;
import vooga.games.grandius.sprites.buttons.AboutButton;
import vooga.games.grandius.sprites.buttons.PlayButton;

public class GrandiusMenuState extends MenuGameState {

		private AboutButton myAboutButton;
        private PlayButton myPlayButton;
        private Game myGame;
        private EventPool eventPool;
        
        public GrandiusMenuState(Game game) {
                myGame = game;
        }

        @Override
        public void initialize() {
                this.myPlayButton = new PlayButton(myGame);
                addButton(myPlayButton);
                this.myAboutButton = new AboutButton(myGame);
                addButton(myAboutButton);
                eventPool = new EventPool();
                eventPool.addEvent(myPlayButton);
                eventPool.addEvent(myAboutButton);
        }
        
        @Override
        public void update(long elapsedTime) {
                super.update(elapsedTime);
                eventPool.checkEvents();
        }
}
