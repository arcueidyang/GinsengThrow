package gameElement.ginseng;

import gui.Canvas;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Location2D;

public class GinsengEntity {
	
	private static final String PACKAGE_NAME = "gameElement.ginseng.";

	private List<String> allGinsengs;
	private List<Ginseng> currentGinsengs;
	
	private Random myRandom;
	
	private static  GinsengEntity instance = new GinsengEntity();
	
	private GinsengEntity() {
		allGinsengs = new ArrayList<String>();
	    init();
		currentGinsengs = new ArrayList<Ginseng>();
		myRandom = new Random();
	}
	
	public void init() {
		for(int i =0 ; i < 3 ; i++) {
			allGinsengs.add(PACKAGE_NAME + "NormalGinseng");
		}
		allGinsengs.add(PACKAGE_NAME + "PoisonGinseng");
 		allGinsengs.add(PACKAGE_NAME + "SlowDownGinseng");
		allGinsengs.add(PACKAGE_NAME + "SpeedUpGinseng");
		allGinsengs.add(PACKAGE_NAME + "FullRecoveryGinseng");
	}
	
	public static GinsengEntity getInstance() {
		return instance;
	}
	
	public void ranGinseng(Location2D center) {
		int index = myRandom.nextInt(allGinsengs.size());
		String className = allGinsengs.get(index);
		Class<?> gin = null;
		try {
			gin = Class.forName(className);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		Ginseng ginseng = null;
		
	    Constructor<?>[] cons = gin.getConstructors();
	    try {
			ginseng = (Ginseng)cons[0].newInstance(center);
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	    currentGinsengs.add(ginseng);
	}
	
	public void paint(Graphics2D pen) {
		for(Ginseng g : currentGinsengs) {
			g.paint(pen);
		}
	}
	
	public void checkGinsengOnScreen() {
		for(Ginseng g : currentGinsengs) {
			if(g.getY() > Canvas.DEFAULT_FRAME_DIMENSION.getHeight()) {
				currentGinsengs.remove(g);
			}
		}
	}
	
	public synchronized void update(Dimension bounds) {
		for(Ginseng g : currentGinsengs) {
			g.update(bounds);
		}
	}
	
	public void reset() {
		currentGinsengs.clear();
	}
	
	public List<Ginseng> getCurrentGinsengs() {
		return currentGinsengs;
	}
	
	public synchronized void removeGinseng(Ginseng g) {
		currentGinsengs.remove(g);
	}
	
	public synchronized void removeGinseng(List<Ginseng> ginsengs) {
		for(Ginseng g : ginsengs) {
			currentGinsengs.remove(g);
		}
	}
}
