package util;

public class Location2D {

	private double myX;
	private double myY;
	
	public Location2D(double x, double y) {
	    myX = x;
	    myY = y;
	}
	
	public Location2D(Location2D location) {
		this(location.getMyX(),location.getMyY());
	}
	
	
	public double distance(double x, double y) {
	    double xDifference = myX - x;
	    double yDifference = myY - y;
	    
	    return Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
	}
	
	public double distance(Location2D location) {
		double result = distance(location.getMyX(),location.getMyY());
	    return result;
	}

	public void move(double magnitude , double angle) {
		myX += magnitude * Math.cos(Math.toRadians(angle));
		myY -= magnitude * Math.sin(Math.toRadians(angle));
	}
	
	public void move(Vector vector) {
		move(vector.getMyMagnitude(),vector.getMyAngle());
	}
	
	public Vector difference(Location2D location) {
		double magnitude = distance(location);
		double angle = Math.asin((location.getMyY() - getMyY())/magnitude);
		return new Vector(magnitude,angle);
	}
	
	public Vector difference(double x, double y) {
		Vector result = difference(new Location2D(x,y));
	    return result;
	}
	
	public void setLocation(Location2D location) {
		myX = location.getMyX();
		myY = location.getMyY();
	}
	
	public void setLocation(double x , double y) {
		setLocation(new Location2D(x,y));
	}
	
	public double getMyX() {
		return myX;
	}

	public double getMyY() {
		return myY;
	}
	
	
	
}
