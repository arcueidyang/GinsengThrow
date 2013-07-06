package gameElement;

import gameElement.ginseng.Ginseng;
import gameElement.ginseng.GinsengEntity;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import strategies.HorizonStrategy;
import strategies.IStrategy;
import util.GameImage;
import util.Location2D;
import util.Sprite;
import util.Vector;

public class Getter extends Sprite {

	public static final int SLOW_SPEED = 1;
	public static final int INITIAL_SPEED = 3;
	public static final int FAST_SPEED = 5;
	public static final Location2D INITIAL_POSITION = new Location2D(30,520);
	public static final Dimension DEFAULT_GETTER_SIZE = new Dimension(60, 60);
	public static final int DEFAULT_HEALTH_POINT = 10;
    public static final long DEFAULT_LOSE_HEALTH_TIME = 5000;
	
	private IStrategy myStrategy;
	private int myHealth;
	private Timer myTimer;
	
	public Getter(GameImage image, Location2D center, Dimension size,
			Vector velocity) {
		super(image, center, size, velocity);
		myHealth = DEFAULT_HEALTH_POINT;
		
	}
	
	public Getter(GameImage image, Location2D center, Dimension size) {
		this(image, center, size, new Vector(INITIAL_SPEED, Vector.RIGHT));
	}
	
	public void update(Dimension bounds, GinsengEntity ginsengs) {
		detectGinseng(ginsengs);
		myStrategy.move(this, bounds);	
	}
	
	public void setVelocity(double speed) {
		double direction = getVelocity().getMyAngle();
		setVelocity(speed, direction);
	}
	
	public void inverseDirection() {
		getVelocity().inverse();
	}
	
	public void gainHealthPoint() {
		myHealth ++;
		System.out.println("Gain health");
	}
	
	public void HealthFullRecovery() {
		myHealth = DEFAULT_HEALTH_POINT;
	}
	
	public void loseHealthPoint() {
		myHealth --;
		System.out.println("Lose health");
	}
	
	public int getHealth() {
		return myHealth;
	}
	
	@Override
	public void reset() {
		super.reset();
		myStrategy = new HorizonStrategy(this.getVelocity().getMyMagnitude());
		myHealth = DEFAULT_HEALTH_POINT;
		autoLoseHealth();
	}
	
	public void speedUp() {
		if(getVelocity().getMyMagnitude() == SLOW_SPEED) {
			setVelocity(INITIAL_SPEED);
		}else {
			setVelocity(FAST_SPEED);
		}
		this.updateNewVelocity();
		System.out.println("Speed up");
	}
	
	public void slowDown() {
		if(getVelocity().getMyMagnitude() == FAST_SPEED) {
			setVelocity(INITIAL_SPEED);
		}else {
			setVelocity(SLOW_SPEED);
		}
		this.updateNewVelocity();
		System.out.println("Slow down");
	}
	
	public void updateNewVelocity() {
		myStrategy.setSpeed(getVelocity());
	}
	
	public void autoLoseHealth() {
		myTimer = new Timer(); 
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				loseHealthPoint();
				System.out.println("HP auto lose");
			}
		};
		myTimer.scheduleAtFixedRate(task, DEFAULT_LOSE_HEALTH_TIME, DEFAULT_LOSE_HEALTH_TIME);
	}
	
	public void stopLoseHealth() 
	{
		myTimer.cancel();
	}
	
	public void gainGinseng(Ginseng gin) {
		gin.affectGetter(this);
	}
	
	public synchronized void detectGinseng(GinsengEntity ginsengs) {
		List<Ginseng> removed = new ArrayList<Ginseng>();
	
		for(Ginseng g : ginsengs.getCurrentGinsengs()) {
			boolean isCollisioned = Sprite.detectCollision(g, this);
			if(isCollisioned) {
				gainGinseng(g);
				removed.add(g);
				
			}
		}
		ginsengs.removeGinseng(removed);
	}
}
