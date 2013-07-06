package util;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
     
	private static final String RESOURCE_LOCATION = "/sounds/";
	
	private AudioClip myClip;
	
	public Sound (String filename) {
        setSound(filename);
    }

    public void setSound (String filename) {
        myClip = Applet.newAudioClip(getClass().getResource(RESOURCE_LOCATION + filename));
    }

    public void play () {
        myClip.play();
    }

    public void stop () {
        myClip.stop();
    }
}
