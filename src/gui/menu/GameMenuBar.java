package gui.menu;

import gui.GameThread;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GameMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenu fileMenu;
	private JMenu gameMenu;
	private JMenu helpMenu;
	private GameThread myGameThread;
	
	public GameMenuBar(GameThread thread) {
		super();
		myGameThread = thread;
		createFileMenu();
		createGameMenu();
		createHelpMenu();
	}
	
	private void createFileMenu() {
	
		fileMenu = new FileMenu("File", myGameThread);
		fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);
	}
	
	private void createGameMenu() {
		gameMenu = new GameMenu("Game", myGameThread);
		gameMenu.setMnemonic(KeyEvent.VK_G);
		add(gameMenu);
		
	}
	
	private void createHelpMenu() {
		helpMenu = new HelpMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		add(helpMenu);
	}
	
	
}
