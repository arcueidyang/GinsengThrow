package gameElement.ginseng;

import gameElement.Getter;
import tools.ResourceManager;
import util.Location2D;
import util.Vector;

public class SpeedUpGinseng extends Ginseng {

	public SpeedUpGinseng(Location2D center) {
		super(ResourceManager.SPEEDUP_GINSENG_IMAGE, center, Ginseng.DEFAULT_GINSENG_SIZE, new Vector(Ginseng.DEFAULT_FALL_SPEED, Vector.DOWN));
	}

	@Override
	public void affectGetter(Getter getter) {
		getter.speedUp();
		getter.gainHealthPoint();
	}
}
