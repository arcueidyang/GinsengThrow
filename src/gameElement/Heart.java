package gameElement;

import java.awt.Graphics2D;

import util.GameImage;

public class Heart {
	
	public static final int RIGHT_MOST_POSITION = 580;
	public static final int DEFAULT_Y_POSITION = 20;
	public static final int SPACE_BETWEEN_HEART = 40;
	

	private Getter myGetter;
	private GameImage heartImage;
	
	public Heart(Getter getter) {
		myGetter = getter;
		heartImage = new GameImage("heart.png");
	}
	
	public void paint(Graphics2D g) {
        for(int i =0, n=myGetter.getHealth() ; i<n ; i++) {
        	g.drawImage(heartImage.getImage(), RIGHT_MOST_POSITION - i*SPACE_BETWEEN_HEART, DEFAULT_Y_POSITION, null);
        }
	}
	
	
}
