package engine.player.control;

import com.golden.gamedev.engine.BaseInput;

public class MouseController implements IPlayerController {

    private BaseInput myMouseController;

    public MouseController(BaseInput bi) {
        myMouseController = bi;
    }

    @Override
    public double deltaX() {
        return myMouseController.getMouseDX();
    }

    @Override
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
