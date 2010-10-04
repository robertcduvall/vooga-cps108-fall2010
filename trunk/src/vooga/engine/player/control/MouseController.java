package vooga.engine.player.control;


import com.golden.gamedev.engine.BaseInput;

/**
 * MouseController implements the interface IPlayerController and allows for the
 * input to the game to be a mouse.
 * 
 * This an example of how one might implement a MouseController for a player.
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 *
 */
public class MouseController implements IPlayerController {

    private BaseInput myMouseController;

    /**
     * Constructs a MouseController
     * @param bi baseInput class that is public state of the main game class.
     */
    public MouseController(BaseInput bi) {
        myMouseController = bi;
    }

    // returns amount to change the X position by.
    public double deltaX() {
        return myMouseController.getMouseDX();
    }

    // returns amount to change the Y position by.
    public double deltaY() {
        return myMouseController.getMouseDY();
    }

    public double deltaVelocityX() {
        return 0;
    }

    public double deltaVelocityY() {
        return 0;
    }

}
