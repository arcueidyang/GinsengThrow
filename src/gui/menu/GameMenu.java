package gui.menu;

import gameElement.Game;
import gui.GameThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tools.GameSaver;

public class GameMenu extends JMenu {
	
	private JMenuItem difficultyItem;
	private JMenuItem controlItem;
	private JMenuItem saveItem;
	private JMenuItem loadItem;

	private GameThread myGameThread;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GameMenu(GameThread thread) {
		this("", thread);
	}
	
	public GameMenu(String title, GameThread thread) {
		super(title);
		myGameThread = thread;
	    createDifficultyItem();
	    createControlItem();
	    createSaveItem();
	    createLoadItem();
	}

	private void createDifficultyItem() {
		difficultyItem = new JMenuItem("Difficulty");
		difficultyItem.setMnemonic(KeyEvent.VK_D);
		
		ActionListener difficultyListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane optionPane = new JOptionPane();
				JSlider slider = GameMenu.getSlider(optionPane);
				optionPane.setMessage(new Object[] {"select a difficulty", slider});
				optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
				optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
				JDialog dialog = optionPane.createDialog(null, "Select difficulty");
				dialog.setVisible(true);
				int response =  Integer.parseInt(optionPane.getInputValue().toString());
				System.out.println("Difficulty set to : " + response);
				//TODO
			}
		};
		difficultyItem.addActionListener(difficultyListener);
		add(difficultyItem);
	}
	
	private void createControlItem() {
		controlItem = new JMenuItem("Control");
		controlItem.setMnemonic(KeyEvent.VK_O);
		
		ActionListener controlListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(controlItem, "This function will be developed later", 
						                   null, JOptionPane.OK_OPTION);
			}
		};
		
		controlItem.addActionListener(controlListener);
		add(controlItem);
	}
	
	private void createSaveItem() {
		saveItem = new JMenuItem("Save");
		saveItem.setMnemonic(KeyEvent.VK_S);
		
		ActionListener saveListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				myGameThread.pause();
				JFileChooser saveFileChooser = getFileChooser(GameSaver.DEFAULT_SAVE_PATH, true);
				saveFileChooser.showDialog(saveItem, "Save");
			}
		};
		
		saveItem.addActionListener(saveListener);
		add(saveItem);
	}
	
	private void createLoadItem() {
		loadItem = new JMenuItem("Load");
		loadItem.setMnemonic(KeyEvent.VK_L);
		
		ActionListener loadListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myGameThread.pause();
				JFileChooser loadFileChooser = getFileChooser(GameSaver.DEFAULT_SAVE_PATH, false);
				loadFileChooser.showDialog(loadItem, "Load");
			}
		};
		
	    loadItem.addActionListener(loadListener);
	    add(loadItem);
	}
	
	public static JSlider getSlider(final JOptionPane optionPane) {
		JSlider slider = new JSlider();
		slider.setMaximum(Game.MAX_DIFFICULTY);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		ChangeListener changeListener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider)arg0.getSource();
				if(!source.getValueIsAdjusting()) {
					optionPane.setInputValue(new Integer(source.getValue()));
				}
			}
		};
		slider.addChangeListener(changeListener);
		return slider;
	}
	
	public JFileChooser getFileChooser(String relativePath, final boolean isSave) {
		JFileChooser fileChooser = new JFileChooser(relativePath);
		fileChooser.setControlButtonsAreShown(true);
		
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser theFileChooser = (JFileChooser)arg0.getSource();
				String command = arg0.getActionCommand();
				if(command.equals(JFileChooser.APPROVE_SELECTION)) {
					File selectedFile = theFileChooser.getSelectedFile();
					if(isSave) {
						myGameThread.getCanvas().saveGame(selectedFile);	
					}else{
						myGameThread.getCanvas().loadGame(selectedFile);
					}
					myGameThread.resume();
				}
			}
		};
		
		fileChooser.addActionListener(actionListener);
		
		return fileChooser;
	}
	
	
	
}
