package gameElement;

import gameElement.ginseng.GinsengEntity;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;

import tools.GameLoader;
import tools.GameSaver;
import tools.ResourceManager;

public class Game {

	public static final int MAX_DIFFICULTY = 5;
	public static final double CURRENT_VERSION = 0.1;
	
	private Feeder myFeeder;
    private Getter myGetter;
    private GinsengEntity myGinsengs;
    private Heart myHealth;
    private Score myScore;
    private GameSaver mySaver;
    private GameLoader myLoader;
	
	
	public Game() {
		myScore = new Score();
		myFeeder = new Feeder(ResourceManager.CHAR_IMAGE, Feeder.INITIAL_POSITION, Feeder.DEFAULT_FEEDER_SIZE);
		myGetter = new Getter(ResourceManager.CHAR_IMAGE, Getter.INITIAL_POSITION, Getter.DEFAULT_GETTER_SIZE);
	    myGinsengs = GinsengEntity.getInstance();
	    myHealth = new Heart(myGetter);
	    mySaver = new GameSaver(this);
	    myLoader = new GameLoader(this);
	    
	}
	
	public void paint(Graphics2D pen) {
		myFeeder.paint(pen);
		myGetter.paint(pen);
		myGinsengs.paint(pen);
		myHealth.paint(pen);
		myScore.paint(pen);
	}
	
	public Getter getGetter() {
		return myGetter;
	}
	
	public Feeder getFeeder() {
		return myFeeder;
	}
	
	public void update(Dimension bounds) {
		myFeeder.update(bounds);
		myGetter.update(bounds, myGinsengs);
		myGinsengs.update(bounds);
	}
	
	public void reset() {
		System.out.println("Game reseted");
		myFeeder.reset();
		myGetter.reset();
		myGinsengs.reset();
		myScore.reset();
	}
	
	public void throwGinseng() {
	    myFeeder.throwGensing(myGinsengs);	
	}
	
	public int getCurrentScore() {
		return myScore.getMyScore();
	}
	
	public void saveGame(File saveFile) {
		mySaver.saveFile(saveFile);
	}
	
	public void saveGame(String fileName) {
		mySaver.saveFile(fileName);
	}
	
	public void loadGame(File saveFile) {
		myLoader.getClass();
	}
	
	public void loadGame(String filePath) {
		
	}
}
