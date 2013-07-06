package strategies;

import java.awt.Dimension;

import util.Sprite;
import util.Vector;

public interface IStrategy {
    
	public void move(Sprite ob , Dimension dmn);
	public void setSpeed(Vector speed);
}
