package strategies;

import java.awt.Dimension;

import util.Sprite;
import util.Vector;

public class HorizonStrategy implements IStrategy {

	private Vector mySpeed;
	
	
	public HorizonStrategy(double speed) {
		mySpeed = new Vector(speed , Vector.RIGHT);
	}
	
	public HorizonStrategy() {
		mySpeed = new Vector(0 , Vector.RIGHT);
	}
	
	public void setSpeed(Vector newSpeed) {
	    mySpeed = newSpeed;
    }
	
	@Override
	public void move(Sprite ob , Dimension dim) {
	    if( (ob.getLeft() <= 0 && mySpeed.getMyAngle() == Vector.LEFT) || 
	    		(ob.getRight() >= dim.width && mySpeed.getMyAngle() == Vector.RIGHT)) {
	    	mySpeed.inverse();
	    } else {
	    	Vector movement = new Vector(mySpeed);
	    	ob.move(movement);
	    }
	    
	}
	
	public Vector getMySpeed() {
		return mySpeed;
	}
}
