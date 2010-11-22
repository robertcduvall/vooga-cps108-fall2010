package vooga.games.jumper.states;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.MenuGameState;
import vooga.games.jumper.buttons.GoButton;

public class StartingMenuGameState extends MenuGameState {

        private GoButton myGoButton;
        private Game myGame;
        private EventPool eventPool;
        
        public StartingMenuGameState(Game game) {
                myGame = game;
        }

        @Override
        public void initialize() {
                this.myGoButton = new GoButton(myGame);
                addButton(myGoButton);
                eventPool = new EventPool();
                eventPool.addEvent(myGoButton);
        }
        
        @Override
        public void update(long elapsedTime) {
                super.update(elapsedTime);
                eventPool.checkEvents();
        }
}
