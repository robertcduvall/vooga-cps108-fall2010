package engine.player.control;

public class MovementKeys {

    private boolean manualReset;
    private double myDefaultDeltaLeft;
    private double myDefaultDeltaRight;
    private double myDefaultDeltaUp;
    private double myDefaultDeltaDown;

    private double myDeltaLeft;
    private double myDeltaRight;
    private double myDeltaUp;
    private double myDeltaDown;

    public MovementKeys() {
        setDefaultMovement();
        manualReset = false;
    }
    
    public MovementKeys(double left, double right, double up, double down) {
        resetMovement(left, right, up, down);
        setDefaultMovement();
    }

    public void resetMovement(double left, double right, double up, double down) {
        myDeltaLeft = left;
        myDeltaRight = right;
        myDeltaUp = up;
        myDeltaDown = down;
        manualReset = true;
    }
  

    private void setDefaultMovement() {
        myDefaultDeltaLeft = myDefaultDeltaDown = 5;
        myDefaultDeltaRight = myDefaultDeltaUp = -5;
    }

    public double getDeltaLeft() {
        if (manualReset)
            return myDeltaLeft;
        return myDefaultDeltaLeft;
    }

    public double getDeltaRight() {
        if (manualReset)
            return myDeltaRight;
        return myDefaultDeltaRight;
    }

    public double getDeltaUp() {
        if (manualReset)
            return myDeltaUp;
        return myDefaultDeltaUp;
    }

    public double getDeltaDown() {
        if (manualReset)
            return myDeltaDown;
        return myDefaultDeltaDown;
    }

}
