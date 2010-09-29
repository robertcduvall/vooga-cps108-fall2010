package engine.player.control;

import java.awt.event.KeyEvent;
import com.golden.gamedev.Game;
import com.golden.gamedev.engine.BaseInput;

import engine.player.control.IPlayerController;

/**
 * This class provides mappings to the UP, LEFT, RIGHT, and DOWN keys for moving
 * a player in the BreakerPong game. This controller is used for one of the
 * players.
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 * 
 */

public class KeyboardController implements IPlayerController {

    private BaseInput myKeyboardController;
    private MovementKeys myMovementKeys;

    /**
     * Constructs a KeyboardController with default movement values
     * @param bi baseInput class that is part of the main game class.
     */
    public KeyboardController(BaseInput bi) {
        myKeyboardController = bi;
        myMovementKeys = new MovementKeys();
    }

    /**
     * Constructs KeyboardController with movement values that are user-chosen.
     * @param bi baseInput from main game class.
     * @param left number of pixels to move left when left is activated.
     * @param right number of pixels to move right when right is activated.
     * @param up number of pixels to move up.
     * @param down number of pixels to move down.
     */
    public KeyboardController(BaseInput bi, double left, double right,
            double up, double down) {
        this(bi);
        setMovementSize(left, right, up, down);
    }

    /**
     * Allows you to change the amount moved up, down, left, or right.
     * @param left pixels to move left.
     * @param right pixels to move right.
     * @param up pixels to move up.
     * @param down pixels to move down.
     */
    public void setMovementSize(double left, double right, double up,
            double down) {
        myMovementKeys = new MovementKeys(left, right, up, down);
    }

    // returns amount to change the X position by.
    public double deltaX() {
        return 0;
    }

    // returns amount to change the Y position by.
    public double deltaY() {
        if (upPressActivated())
            return myMovementKeys.getDeltaUp();
        if (downPressActivated())
            return myMovementKeys.getDeltaDown();
        return 0;
    }

    // uses myGame instance variable to check if certain keys are pressed
    private boolean downPressActivated() {

        return myKeyboardController.isKeyDown(KeyEvent.VK_DOWN);
    }

    private boolean upPressActivated() {

        return myKeyboardController.isKeyDown(KeyEvent.VK_UP);
    }

    private boolean rightPressActivated() {

        return myKeyboardController.isKeyDown(KeyEvent.VK_RIGHT);
    }

    private boolean leftPressActivated() {

        return myKeyboardController.isKeyDown(KeyEvent.VK_LEFT);
    }

    public double deltaVelocityX() {
        return 0;
    }

    public double deltaVelocityY() {
        return 0;
    }

}
