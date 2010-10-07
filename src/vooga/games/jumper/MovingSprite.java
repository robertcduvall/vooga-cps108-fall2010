package vooga.games.jumper;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class MovingSprite extends Sprite {

    private double myXSpeed;
    private double myYSpeed;

    public MovingSprite(BufferedImage image, Point location, Point velocity) {
        super(image, location.x, location.y);
        setMyXSpeed(velocity.x);
        setMyYSpeed(velocity.y);
    }

    public void setMyXSpeed(int myXSpeed) {
        this.myXSpeed = myXSpeed;
    }

    public double getMyXSpeed() {
        return myXSpeed;
    }

    public void setMyYSpeed(int myYSpeed) {
        this.myYSpeed = myYSpeed;
    }

    public double getMyYSpeed() {
        return myYSpeed;
    }
    
    public void update(long elapsedTime){
    	move(myXSpeed, myYSpeed);
    	//setSpeed(myXSpeed, myYSpeed);
    }
}