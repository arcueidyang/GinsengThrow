package gui;

import javax.swing.JOptionPane;

import gui.Canvas;


public class GameThread implements Runnable {

	private Canvas myCanvas;
	private static boolean continued = true;
	private Thread th;
	
	public GameThread() {
		myCanvas = new Canvas();
		
	    th = new Thread(this);
		th.start();
	}
	
	@Override
	public void run() {
		while(true) {
			if(continued) {
				myCanvas.update();
				myCanvas.repaint();
				continued = myCanvas.isGetterAlive(); 	
			}else {
				myCanvas.stopLoseHealth();
				int response = JOptionPane.showOptionDialog(myCanvas, "Game Over. Do you want to start a new game?",  "Game Over", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(response == 1 || response == -1) {
					System.exit(0);
				}else if(response == 0) {
					continued = true;
					myCanvas.reset();
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}
	
	public void pause() {
		myCanvas.stopLoseHealth();
		th.suspend();
	}
	
	public void resume() {
		th.resume();
		myCanvas.resumeLoseHealth();
	}
	
	public Canvas getCanvas() {
		return myCanvas;
	}
	
	public void resetGame() {
		myCanvas.reset();
	}
}
