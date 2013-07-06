import tools.ResourceManager;
import gui.GameFrame;



public class Main {

	public static void main(String[] args) {
		
		ResourceManager.initAllImages();
	    new GameFrame();
    }
}
