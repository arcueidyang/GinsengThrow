package gameElement.ginseng;

import gameElement.Getter;

import java.awt.Dimension;
import strategies.IStrategy;
import strategies.VerticalStrategy;
import util.GameImage;
import util.Location2D;
import util.Sprite;
import util.Vector;

public abstract class Ginseng extends Sprite{

	public static final double DEFAULT_FALL_SPEED = 3;
	public static final Dimension DEFAULT_GINSENG_SIZE = new Dimension(40, 40);
	
	private IStrategy myStrategy;
	
	public Ginseng(GameImage image, Location2D center, Dimension size,
			Vector velocity) {
		super(image, center, size, velocity);
		myStrategy = new VerticalStrategy(velocity.getMyMagnitude());
	}
	
	@Override
	public void update(Dimension bounds) {
		myStrategy.move(this, bounds);
	}
	
	public void affectGetter(Getter getter) {
	    
	}
}
