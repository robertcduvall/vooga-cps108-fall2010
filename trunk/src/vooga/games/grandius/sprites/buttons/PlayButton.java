package vooga.games.grandius.sprites.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.widget.Button;

@SuppressWarnings("serial")
public class PlayButton extends Button {
        private static final int START_X = 220;
        private static final int START_Y = 0;
        private static final BufferedImage myImage = Resources.getImage("playButtonImage");
		private DropThis myGame;
        
        public PlayButton(Game game) {
                super(game, myImage, START_X, START_Y);
                myGame = (DropThis) game;
        }
        
        @Override
        public void actionPerformed() {
                myGame.startPlayState();
                myGame.playMusic(Resources.getSound("walkinOnTheSun"));
                myGame.playSound(Resources.getSound("watchThisSound"));
        }
}
