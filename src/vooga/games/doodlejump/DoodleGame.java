package vooga.games.doodlejump;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import com.golden.gamedev.*;
import com.golden.gamedev.object.*;


public class DoodleGame extends Game {
	private Sprite piece;
	
	public void initResources() {
        BufferedImage image = getImage("piece1.png");
        double x = 100;
        double y = 200;
        piece = new Sprite(image, x, y);
        //BufferedImage[] images = getImages("image2.png", 3, 1);


        // File I/O Engine
        //URL url = bsIO.getURL("level.txt");


        // Sound Music Engine
        //playMusic("music1.mid");


        // Timer Engine
        setFPS(100);
    }


    public void update(long elapsedTime) {
        // Input Engine
        //if (keyPressed(KeyEvent.VK_SPACE)) {

            // Sound Engine
          //  playSound("sound1.wav");
        //}
    }


    public void render(Graphics2D g) {
        // Graphics Engine
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        piece.render(g);
    }

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new TetrisGame(), new Dimension(640,480), false);
        game.start();
    }

}
