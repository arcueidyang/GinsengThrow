package util;

public class Vector {

	public static final double RIGHT = 0;
	
	public static final double UP = 90;
	
	public static final double LEFT = 180;
	
	public static final double DOWN = 270;
	
	private double myMagnitude;
	private double myAngle;
	
	public Vector(double magnitude, double angle) {
		myMagnitude = magnitude;
		myAngle = angle;
	}
	
	public Vector(Vector vector) {
		this(vector.getMyMagnitude(),vector.getMyAngle());
	}
	
	public Vector() {
		this(0,0);
	}
	
	public void scale(double scale) {
		myMagnitude *= scale;
	}
	
	public void turn(double change) {
		myAngle = (myAngle + change)%360;
	}
	
	public void inverse() {
		turn(180);
	}
	
	public static double angleBetween(double y, double x) {
		return Math.atan2(y, x);
	}
	
	public static double sumMagnitude(double x, double y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public void sum(Vector vector) {
		double x = getXChange() + vector.getXChange();
		double y = getYChange() + vector.getYChange();
		double angle = Math.toDegrees(Vector.angleBetween(y, x));
		double magnitude = Vector.sumMagnitude(x, y);
		myMagnitude = magnitude;
		myAngle = angle;
	}
	
	public double getXChange() {
		return myMagnitude * Math.cos(Math.toRadians(myAngle));
	}
	
	public double getYChange() {
		return myMagnitude * Math.sin(Math.toRadians(myAngle));
	}
	
	public double getMyMagnitude() {
		return myMagnitude;
	}
	
	public double getMyAngle() {
		return myAngle;
	}

}
