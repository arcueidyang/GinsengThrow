package strategies;

import java.awt.Dimension;

import util.Sprite;
import util.Vector;

public class VerticalStrategy implements IStrategy {
 
	private Vector mySpeed;
	
	
	public VerticalStrategy(double speed) {
		mySpeed = new Vector(speed , Vector.DOWN);
	}
	
	@Override
	public void move(Sprite ob , Dimension dim) {
	    Vector movement = new Vector(mySpeed);
	    ob.move(movement);
	}

    public Vector getMySpeed() {
    	return mySpeed;
    }

    public void setSpeed(Vector newSpeed) {
	    mySpeed = newSpeed;
    }

}
