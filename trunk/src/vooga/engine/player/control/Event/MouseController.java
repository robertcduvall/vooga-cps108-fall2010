package vooga.engine.player.control.Event;


import com.golden.gamedev.engine.BaseInput;

/**
 * MouseController implements the interface IPlayerController and allows for the
 * input to the game to be a mouse.
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 *
 */
public class MouseController implements IPlayerController {

    private BaseInput myMouseController;

    /**
     * Constructs a MouseController
     * @param bi baseInput class that is part of the main game class.
     */
    public MouseController(BaseInput bi) {
        myMouseController = bi;
    }

    @Override // returns amount to change the X position by.
    public double deltaX() {
        return myMouseController.getMouseDX();
    }

    @Override // returns amount to change the Y position by.
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
