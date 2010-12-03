package arcade.wall;

import javax.swing.JFrame;

/**
 * Playing around with an initial Wall GUI
 * @author Cam
 */
public class Wall extends JFrame{


	public Wall(String gamerTag){
		setTitle(gamerTag + "'s Wall");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
