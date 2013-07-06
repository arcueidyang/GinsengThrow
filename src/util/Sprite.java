package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;


public abstract class Sprite {
    
    private Location2D myCenter;
    private Vector myVelocity;
    private Dimension mySize;
    private GameImage myImage;
    
    private Location2D myOriginalCenter;
    private Vector myOriginalVelocity;
    private Dimension myOriginalSize;
    private GameImage myOriginalImage;
    
    private Rectangle myBounds;
    

    public Sprite (GameImage image, Location2D center, Dimension size) {
        this(image, center, size, new Vector());
    }

    public Sprite (GameImage image, Location2D center, Dimension size, Vector velocity) {
        myOriginalCenter = new Location2D(center);
        myOriginalSize = new Dimension(size);
        myOriginalVelocity = new Vector(velocity);
        myOriginalImage = image;
        reset();
        resetBounds();
    }

   
    public void update (Dimension bounds) {
    	
        Vector v = new Vector(myVelocity);
        move(v);
    }

    
    public void move (Vector v) {
        myCenter.move(v);
        resetBounds();
    }

    public void setCenter (double x, double y) {
        myCenter.setLocation(x, y);
        resetBounds();
    }

    
    public double getX () {
        return myCenter.getMyX();
    }

    public double getY () {
        return myCenter.getMyY();
    }

    public double getLeft () {
        return myCenter.getMyX() - mySize.width / 2;
    }

    public double getTop () {
        return myCenter.getMyY() - mySize.height / 2;
    }

    public double getRight () {
        return myCenter.getMyX() + mySize.width / 2;
    }

    public double getBottom () {
        return myCenter.getMyY() + mySize.height / 2;
    }

    public double getWidth () {
        return mySize.getWidth();
    }

    public double getHeight () {
        return mySize.getHeight();
    }

    public void scale (double widthFactor, double heightFactor) {
        mySize.setSize(mySize.width * widthFactor, mySize.height * heightFactor);
        resetBounds();
    }

    
    public void setSize (int width, int height) {
        mySize.setSize(width, height);
        resetBounds();
    }

    public Vector getVelocity () {
        return myVelocity;
    }

    public void setVelocity (double magnitude, double angle) {
        myVelocity = new Vector(magnitude, angle);
    }

    public void setView (GameImage image) {
        if (image != null) {
            myImage = image;
        }
    }

    public Rectangle getBounds () {
        return myBounds;
    }

    public boolean intersects (Sprite other) {
        return getBounds().intersects(other.getBounds());
    }

    public boolean intersects (Point2D point) {
        return getBounds().contains(point);
    }

    public void reset () {
    	System.out.println(this.getClass());
        myCenter = new Location2D(myOriginalCenter);
        mySize = new Dimension(myOriginalSize);
        myVelocity = new Vector(myOriginalVelocity);
        myImage = new GameImage(myOriginalImage);
    }

    public void paint (Graphics2D pen)
    {
        myImage.paint(pen, myCenter, mySize);
    }

    protected void resetBounds () {
        myBounds = new Rectangle((int)getLeft(), (int)getTop(), mySize.width, mySize.height);
    }
    
    public static boolean detectCollision(Sprite ginseng, Sprite getter) {
    	if(ginseng.getX() >= (getter.getX() - ginseng.getWidth()/2 - getter.getWidth()/2) && ginseng.getX() <= (getter.getX() + getter.getWidth()/2 + ginseng.getWidth()/2)
    			&&ginseng.getY() >= (getter.getY() - getter.getHeight()/2 - ginseng.getHeight()/2) && ginseng.getY() <= getter.getY()) {
    		return true;
    	}
    	return false;
    }
}
