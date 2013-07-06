package tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import gameElement.Game;

public class GameSaver {

	public static final String DEFAULT_SAVE_PATH = "./save/";
	
	private Game myGame;
	
	private FileWriter myWriter;
	
	public GameSaver(Game game) {
		myGame = game;
	}
	
	public void saveFile(File saveFile) {
		
		try {
			myWriter = new FileWriter(saveFile);
			myWriter.write("Score " + myGame.getCurrentScore() +"\n");
		    myWriter.write("Health " +  myGame.getGetter().getHealth() + "\n");
		    myWriter.write("Getter " + myGame.getGetter().getX() + " " + myGame.getGetter().getY() + " " + 
		                      myGame.getGetter().getVelocity().getMyMagnitude() + " " + 
		    		          myGame.getGetter().getVelocity().getMyAngle()+"\n");

		    myWriter.write("Feeder " + myGame.getFeeder().getX() + " " + myGame.getFeeder().getY());
			myWriter.close();			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void saveFile(String fileName) {
		File saveFile = new File(DEFAULT_SAVE_PATH + fileName);
		saveFile(saveFile);
	}
	
}
