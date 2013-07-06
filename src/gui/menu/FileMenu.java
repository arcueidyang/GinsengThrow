package gui.menu;

import gui.GameThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FileMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuItem newItem;
	private JMenuItem pauseItem;
	private JMenuItem resumeItem;
	private JMenuItem closeItem;
	
	
	private GameThread myGameThread;
	
	public FileMenu(GameThread thread) {
		this("", thread);
	}
	
	public FileMenu(String name, GameThread thread) {
		super(name);
		myGameThread = thread;
		createNewItem();
		createPauseItem();
		createResumeItem();
		createCloseItem();
	}
	
	private void createNewItem() {
		newItem = new JMenuItem("New");
		newItem.setMnemonic(KeyEvent.VK_N);
		
		ActionListener newActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				myGameThread.pause();
				int response = JOptionPane.showOptionDialog(newItem, "Do you really want to create a new game? ?" ,
						"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(response == 0) {
					myGameThread.resetGame();
					myGameThread.resume();
				}else if(response == 1) {
					myGameThread.resume();
				}
			}
		};
		newItem.addActionListener(newActionListener);
		add(newItem);
	}
	
	private void createPauseItem() {
	
		pauseItem = new JMenuItem("Pause");
		pauseItem.setMnemonic(KeyEvent.VK_P);
				
		ActionListener pauseActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    myGameThread.pause();
			}
		};
		
		pauseItem.addActionListener(pauseActionListener);
		add(pauseItem);
	}
	
	private void createResumeItem() {
		resumeItem = new JMenuItem("Resume");
		resumeItem.setMnemonic(KeyEvent.VK_R);
        
		ActionListener resumeActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    myGameThread.resume();
			}
		};
		
		resumeItem.addActionListener(resumeActionListener);
		add(resumeItem);

	}
	
	private void createCloseItem() {
		closeItem = new JMenuItem("Close");
		closeItem.setMnemonic(KeyEvent.VK_C);
		
		ActionListener closeActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myGameThread.pause();
				int response = JOptionPane.showOptionDialog(closeItem, "DO YOU REALLY WANT TO CLOSE THIS GAME?", 
						                      "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				if(response == 0) {
					System.exit(0);
				}else if(response == 1) {
					myGameThread.resume();
				}
			}
		};
		closeItem.addActionListener(closeActionListener);
		add(closeItem);
	}
	
}
