package gameElement;

import gameElement.ginseng.GinsengEntity;
import gui.Canvas;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import util.GameImage;
import util.Location2D;
import util.Sprite;
import util.Vector;

public class Feeder extends Sprite {

	public static final double DEFAULT_SPEED = 10;
	public static final Location2D INITIAL_POSITION = new Location2D(30,100);
	public static final Dimension DEFAULT_FEEDER_SIZE = new Dimension(60, 60);
	public static final long DEFAULT_SKILL_COOL_DOWN = 3000;
	public static final double TOLERANCE = 10;
	
	
	
	private boolean available;
	
	public Feeder(GameImage image, Location2D center, Dimension size) {
		super(image, center, size, new Vector(0 , Vector.RIGHT));
	    available = true;
	}

	
    public void leftMove(double speed) {
    	super.setVelocity(speed, Vector.LEFT);	
		updatePosition();   
    }
    
    public void leftMove() {
        leftMove(DEFAULT_SPEED);
    }
    
    public void rightMove(double speed) {
        if(this.getX() <= Canvas.DEFAULT_FRAME_DIMENSION.getWidth() - 30) {
        	super.setVelocity(speed, Vector.RIGHT);	
        }
        updatePosition();
    }
    
    public void rightMove() {
        rightMove(DEFAULT_SPEED);
    }
    
    public void moveToDesitination(Location2D loc) {
    	double x = loc.getMyX();
    	if(getX() < x) {
    		rightMove();
    	}else if (getX() > x) {
    	    leftMove();
    	}
    }
    
    public void throwGensing(GinsengEntity ginsengs) {
    	if(available) {
    	    ginsengs.ranGinseng(new Location2D(this.getX(), this.getY()));    	
    	    available = false;
    	    skillCoolDown();
    	}else {
    		System.out.println("Skill cooling down");
    	}
    }

    public void skillCoolDown() {
    	Timer timer = new Timer();
        TimerTask task = new TimerTask() {
			@Override
			public void run() {
				available = true;
			}
        };
        timer.schedule(task,DEFAULT_SKILL_COOL_DOWN);
    }
    
    public void stop() {
    	super.setVelocity(0, getVelocity().getMyAngle());
    }
    
    public void updatePosition() {
        move(getVelocity());	
        
    	
    }
    
    @Override
    public void update(Dimension bounds) {
    	if(this.getX() <= 0) {
    		this.setCenter(30, this.getY());
    	} else if (this.getX() >= 640) {
    		this.setCenter(610, this.getY());
    	}
    	super.update(bounds);
    } 
}
