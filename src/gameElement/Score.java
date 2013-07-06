package gameElement;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

public class Score {

	public static final int SCORE_PER_GENSENG = 500;
	public static final int DEFAULT_INITIAL_SCORE = 0;
	public static final int DEFAULT_INCREASE_SPEED =10;
	public static final int DEFAULT_INCREASE_VALUE = 1;
	
	public static final int DEFAULT_X_POSITION = 50;
	public static final int DEFAULT_Y_POSITION = 50;
	
	public static final String SCORE = "Score : ";	
	private int myScore;
	private int increaseValue;
	private Timer myTimer;
	
	
	public Score() {
	  	this(DEFAULT_INCREASE_SPEED);
	}
	
	public Score(int speed) {
		this (DEFAULT_INITIAL_SCORE, speed);
	}
	
	public Score(int initialScore, int increaseValue) {
		myScore = initialScore;
		this.increaseValue = increaseValue;
		autoSelfIncrease();
	}
	
	public void increase(int amount) {
		myScore += amount;
	}

	public int getMyScore() {
		return myScore;
	}
	
	public void selfIncrease() {
		myScore += increaseValue;
	}
	
	public void setIncreaseValue(int value) {
		increaseValue = value;
	}
	
	public void autoSelfIncrease() {
		myTimer = new Timer();
		
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				selfIncrease();
			}			
		};
		
		myTimer.scheduleAtFixedRate(task, DEFAULT_INCREASE_SPEED, DEFAULT_INCREASE_SPEED);
	}
	
	public void stopSelfIncrease() {
		myTimer.cancel();
	}
	
	public void reset() {
		myScore = DEFAULT_INITIAL_SCORE;
		autoSelfIncrease();
	}
	
	public void paint(Graphics2D g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.drawString(SCORE + Integer.toString(myScore), DEFAULT_X_POSITION, DEFAULT_Y_POSITION);
	}
}
