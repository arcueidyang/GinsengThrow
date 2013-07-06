package tools;

import util.GameImage;
import util.Sound;

public class ResourceManager {

	public static GameImage BACKGROUND = null;
	
	public static GameImage CHAR_IMAGE = null;
	
	public static GameImage NORMAL_GINSENG_IMAGE = null;
	
	public static GameImage POISON_GINSENG_IMAGE = null;
	
	public static GameImage SPEEDUP_GINSENG_IMAGE = null;
	
	public static GameImage SLOWDOWN_GINSENG_IMAGE = null;
	
	public static GameImage FULLRECOVERY_GINSENG = null;
	
	public static Sound BACKGROUND_MUSIC = null;
	
	public static void initAllImages() {
		BACKGROUND = new GameImage("background.jpg");
		CHAR_IMAGE = new GameImage("feeder.png");
		NORMAL_GINSENG_IMAGE = new GameImage("normalGinseng.png");
		POISON_GINSENG_IMAGE = new GameImage("poisonGinseng.png");
		SPEEDUP_GINSENG_IMAGE = new GameImage("speedUpGinseng.png");
		SLOWDOWN_GINSENG_IMAGE = new GameImage("slowDownGinseng.png");
		FULLRECOVERY_GINSENG = new GameImage("fullRecoveryGinseng.png");
		//BACKGROUND_MUSIC = new Sound();
	}
}
