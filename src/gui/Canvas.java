package gui;

import gameElement.Feeder;
import gameElement.Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JComponent;

import tools.ResourceManager;
import util.Location2D;

public class Canvas extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int UPDATE_TIME = 20;
	public static final Dimension DEFAULT_FRAME_DIMENSION = new Dimension(640, 600);
	public static final String GAME_FRAME_NAME = "Ginseng Throw";
	
	public static int LEFT_MOVE = KeyEvent.VK_A;
	public static int RIGHT_MOVE = KeyEvent.VK_D;
	public static int THROW_GENSING = KeyEvent.VK_SPACE;
	
	private Game myGame;

	
	public Canvas() {
		super();
		myGame = new Game();
		makeSettings();
		initKeyListener();
		initMouseListener();
	}
	
	private void makeSettings() {
		this.setName(GAME_FRAME_NAME);
		this.setPreferredSize(DEFAULT_FRAME_DIMENSION);
		setSize(DEFAULT_FRAME_DIMENSION);
		this.setFocusable(true);
		setVisible(true);
	}
	
	private void initKeyListener() {
		KeyListener keyListener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == LEFT_MOVE) {
					myGame.getFeeder().leftMove(Feeder.DEFAULT_SPEED);
				}else if (arg0.getKeyCode() == RIGHT_MOVE) {
					myGame.getFeeder().rightMove(Feeder.DEFAULT_SPEED);
				}else if (arg0.getKeyChar() == THROW_GENSING) {
					myGame.throwGinseng();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == LEFT_MOVE || arg0.getKeyCode() == RIGHT_MOVE) {
				    myGame.getFeeder().stop();
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {	
			}
		};
		this.addKeyListener(keyListener);
	}
	
	public void initMouseListener() {
		MouseListener mouseListener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				myGame.throwGinseng();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				//do nothing
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			    //do nothing
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				myGame.getFeeder().moveToDesitination(new Location2D(arg0.getX(), arg0.getY()));
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				myGame.getFeeder().stop();
			}
		};
		this.addMouseListener(mouseListener);
	}
	
	public void update() {
		myGame.update(Canvas.DEFAULT_FRAME_DIMENSION);	
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		BufferedImage image = new BufferedImage(ResourceManager.BACKGROUND.getImage().getWidth(), 
			  ResourceManager.BACKGROUND.getImage().getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = image.createGraphics();
		this.paintComponents(g2);
		g2.drawImage(ResourceManager.BACKGROUND.getImage(), 0, 0, this);
		myGame.paint((Graphics2D)g2);
		g.drawImage(image, 0, 48, this);
		System.out.println("Painted");
	}
	
	public void reset() {
		myGame.reset();
	}
	
	public boolean isGetterAlive() {
		return (myGame.getGetter().getHealth() > 0);
	}
	
	public void stopLoseHealth() {
		myGame.getGetter().stopLoseHealth();
	}
	
	public void resumeLoseHealth() {
		myGame.getGetter().autoLoseHealth();
	}
	
	public void saveGame(File saveFile) { 
		myGame.saveGame(saveFile);
	};
		
	public void loadGame(File loadFile) {
		//TODO
	}
	
	
}
