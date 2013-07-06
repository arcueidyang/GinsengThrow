package gui;

import gui.menu.GameMenuBar;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class GameFrame {

	private GameThread myGameThread;
	private JMenuBar myMenuBar;
	private JFrame myFrame;
	
	public GameFrame() {
		myGameThread = new GameThread();
		myMenuBar = new JMenuBar();
		myFrame = new JFrame("Ginseng Throw");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.add(myGameThread.getCanvas(),BorderLayout.SOUTH);
		myMenuBar = new GameMenuBar(myGameThread);
		myFrame.setJMenuBar(myMenuBar);
		myFrame.setSize(640, 600);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
		myFrame.setResizable(false);
	}
}
