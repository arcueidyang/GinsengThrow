package gameElement.ginseng;

import gameElement.Getter;
import tools.ResourceManager;
import util.Location2D;
import util.Vector;

public class FullRecoveryGinseng extends Ginseng {
	public FullRecoveryGinseng(Location2D center) {
		super(ResourceManager.FULLRECOVERY_GINSENG, center, Ginseng.DEFAULT_GINSENG_SIZE, new Vector(Ginseng.DEFAULT_FALL_SPEED, Vector.DOWN));
		
	}

	@Override
	public void affectGetter(Getter getter) {
		getter.HealthFullRecovery();
	}
}
