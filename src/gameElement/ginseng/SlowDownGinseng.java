package gameElement.ginseng;

import gameElement.Getter;
import tools.ResourceManager;
import util.Location2D;
import util.Vector;

public class SlowDownGinseng extends Ginseng{
	public SlowDownGinseng(Location2D center) {
		super(ResourceManager.SLOWDOWN_GINSENG_IMAGE, center, Ginseng.DEFAULT_GINSENG_SIZE, new Vector(Ginseng.DEFAULT_FALL_SPEED, Vector.DOWN));
	}

	@Override
	public void affectGetter(Getter getter) {
		getter.slowDown();
		getter.gainHealthPoint();
	}
}
