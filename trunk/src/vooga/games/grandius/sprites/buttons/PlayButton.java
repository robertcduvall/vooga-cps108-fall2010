package vooga.games.grandius.sprites.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.grandius.Blah;
import vooga.widget.Button;

@SuppressWarnings("serial")
public class PlayButton extends Button {
        private static final int START_X = 350;
        private static final int START_Y = 400;
        private static final BufferedImage myImage = Resources.getImage("playButtonImage");
		private Blah myGame;
        
        public PlayButton(Game game) {
                super(myImage, START_X, START_Y);
                myGame = (Blah) game;
        }
        
        @Override
        public void actionPerformed() {
                myGame.getGameStateManager().switchTo(myGame.getGameStateManager().getGameState(5));
                myGame.playMusic(Resources.getSound("walkinOnTheSun"));
                myGame.playSound(Resources.getSound("watchThisSound"));
        }
}
