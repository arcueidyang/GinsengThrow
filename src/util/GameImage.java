package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameImage {

	private static final String RESOURCE_LOCATION = "./images/";
   
    private BufferedImage myImage;
    private String myFileName;

    
    public GameImage (String fileName) {
        try {
			setImage(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public GameImage (GameImage other) {
        this(other.myFileName);
    }

    public void setImage (String fileName) throws IOException {
        myImage = ImageIO.read(new File(RESOURCE_LOCATION + fileName));
        myFileName = fileName;
    }

    public void paint (Graphics2D pen, Location2D center, Dimension size) {
        paint(pen, center, size, 0);
    }

    public void paint (Graphics2D pen, Location2D center, Dimension size, double angle) {
        
        AffineTransform old = new AffineTransform(pen.getTransform());
        
        pen.translate(center.getMyX(), center.getMyY());
        
        pen.rotate(angle);
        
        pen.drawImage(myImage, -size.width / 2, -size.height / 2, size.width, size.height, null);
        
        pen.setTransform(old);
    }
    
    public BufferedImage getImage() {
    	return myImage;
    }
	
}
