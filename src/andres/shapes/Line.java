package andres.shapes;
import processing.core.PApplet;

/** This class represents a double precision Line. It can perform common calculations
 *   as well as drawing a representation of the Line to a Processing PApplet.
 *  
 *  @author Andres Carranza
 *  @version 9/27/2019
 */
public class Line extends Shape{
	private double x2, y2;
	
	/** Constant that means that two line segments don't intersect
	 */
	public static final double DOES_NOT_INTERSECT = Double.MAX_VALUE;
	/** Constant that means that two line segments are colinear
	 */
	public static final double COLINEAR = Double.MIN_VALUE;
	
	/** Constructs a new line segment, with endpoints at (x1, y1) and (x2, y2)
	 * 
	 * @param x1 The first x coordinate
	 * @param y1 The first y coordinate
	 * @param x2 The second x coordinate
	 * @param y2 The second y coordinate
	 */
	public Line(float x1, float y1, float x2, float y2) {
		super(x1, y1);
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Creates a line object with initial point at (x,y), and endpoint at a specified length away at theta degrees
	 * @param x the x coordinate for initial point
	 * @param y the y coordinate for initial point
	 * @param theta the angle of the line with the horizontal in radians
	 * @param length the length of the line
	 * @return
	 */
	public static Line constructLineWithAngle(float x, float y, float theta, float length) {
		return new Line(x,y,(float)( x + Math.cos(theta) * length), (float)(y + Math.sin(theta) * length));
	}
	
	@Override	
	/** Draws the line with (x,y) as the top left corner of the bounding rectangle
	 * with the predefined processing settings 
	 * 
	 * @param marker the PApplet to be used to draw the line
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.line((float)(getX()),(float) (getY()), (float)(x2), (float)(y2));
		marker.pop();
	}

	/** Checks if two lines intersect
	 * 
	 * @param l2 The line to be checked
	 * @return if this line segments intersects l2
	 */
	public boolean intersects(Line l2) {
		if(isAPoint() )
			return l2.isPointInside(getX(), getY());
		else if(l2.isAPoint())
			return isPointInside(l2.getX(), l2.getY());
		else if(colinear(l2))
			return true;
		else if(isHorizontal()) 
			return isPointInside(l2.inverseF(getY()), getY()) && l2.isPointInside(l2.inverseF(getY()), getY());
		else if(l2.isHorizontal())
			return isPointInside(inverseF(l2.getY()),l2.getY()) && l2.isPointInside(inverseF(l2.getY()),l2.getY());
		else if(isParallel(l2))
			return false;


		double x = intersectionX(getX(),x2,getY(),y2,l2.getX(),l2.x2,l2.getY(),l2.y2);
		double y = intersectionY(getX(),x2,getY(),y2,l2.getX(),l2.x2,l2.getY(),l2.y2);


		return isPointInside(x, y) && l2.isPointInside(x, y);
	}

	
	@Override
	/** Checks if this line segment contains the point (x,y)
	 * 
	 * @param x the x coordinate to be checked
	 * @param y the y coordinate to be checked
	 * @return true if it contains it, false otherwise
	 */
	public boolean isPointInside(double x, double y) {
		return x >= Math.min(getX(), x2) && x<= Math.max(getX(), x2) && y >= Math.min(getY(), y2) && y<= Math.max(getY(), y2);
	}

	@Override
	/**
	 * Calculates the x coordinate of the mid point
	 * 
	 * @return the x center of the line
	 */
	public double getCenterX() {
		return (x2 - getX()) / 2;
	}

	@Override
	/**
	 * Calculates the y coordinate of the mid point
	 * 
	 * @return the y center of the line
	 */	public double getCenterY() {
		return (y2 -getY()) / 2;
	}

	@Override
	/**
	 * Calculates the rectangle for which the line is its diagonal
	 * 
	 * @return the bounding rectangle of this line
	 */
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), x2 - getX(), y2 - getY());
	}
	
	/** Calculates the x coordinate for intersection
	 * 
	 * @param l2 The line to be checked
	 * @return The intersection x point for the 2 lines. If they don't intersect, or are colinear, returns a constant
	 */
	public double intersectionX(Line l2) {
		if(isAPoint() ) {
			if(l2.isPointInside(getX(), getY()))
				return (double) getX();
			else
				return DOES_NOT_INTERSECT;
		}
		else if(l2.isAPoint() ) {
			if(isPointInside(l2.getX(), l2.getY()))
				return (double) l2.getX();
			else
				return DOES_NOT_INTERSECT;
		}
		else if(colinear(l2))
			return COLINEAR;
		else if(isHorizontal()) {
			if( isPointInside(l2.inverseF(getY()), getY()) && l2.isPointInside(l2.inverseF(getY()), getY()))
				return (double) l2.inverseF(getY());
			else
				return DOES_NOT_INTERSECT;
		}
		else if(l2.isHorizontal()) {
			if( isPointInside(inverseF(l2.getY()), l2.getY()) && l2.isPointInside(l2.inverseF(l2.getY()), l2.getY()))
				return (double) inverseF(l2.getY());
			else
				return DOES_NOT_INTERSECT;
		}
		else if(isParallel(l2))
			return DOES_NOT_INTERSECT;
		return (double) intersectionX(getX(),x2,getY(),y2,l2.getX(),l2.x2,l2.getY(),l2.y2);

	}

	/** Calculates the y coordinate for intersection
	 * 
	 * @param l2 The line to be checked
	 * @return The intersection y point for the 2 lines. If they don't intersect, or are colinear, returns a constant
	 */
	public double intersectionY(Line l2) {
		if(isAPoint() ) {
			if(l2.isPointInside(getX(), getY()))
				return (double) getY();
			else
				return DOES_NOT_INTERSECT;
		}
		else if(l2.isAPoint() ) {
			if(isPointInside(l2.getX(), l2.getY()))
				return (double) l2.getY();
			else
				return DOES_NOT_INTERSECT;
		}
		else if(colinear(l2))
			return COLINEAR;
		else if(isHorizontal()) {
			if( isPointInside(l2.inverseF(getY()), getY()) && l2.isPointInside(l2.inverseF(getY()), getY()))
				return (double) getY();
			else
				return DOES_NOT_INTERSECT;
		}
		else if(l2.isHorizontal()) {
			if( isPointInside(inverseF(l2.getY()), l2.getY()) && l2.isPointInside(l2.inverseF(l2.getY()), l2.getY()))
				return (double) l2.getY();
			else
				return DOES_NOT_INTERSECT;
		}
		else if(isParallel(l2))
			return DOES_NOT_INTERSECT;
		return (double) intersectionY(getX(),x2,getY(),y2,l2.getX(),l2.x2,l2.getY(),l2.y2);
	}
	
	/** Calculates the length of this line segment
	 * 
	 * @return the length of the line segment with double precision
	 */
	public double length() {
		return Math.sqrt(Math.pow(getX() - x2 , 2) + Math.pow(getY()-y2, 2) );
	}
	

	/** Checks if this line is horizontal
	 * 
	 * @return true if horizontal, false otherwise
	 */
	public boolean isHorizontal() {
		return getY() == y2;
	}


	/** Returns the value of f(x) for the line containing this line segment
	 * 
	 * @param x the value to be inputed into f(x)
	 * @return the value of f(x) with the given x
	 */
	public double f(double x) {
		if(Double.isInfinite(slope()) )
			return getY();

		return slope() * (x - getX()) + getY();
	}

	/** Returns the value of the inverse of f(x) (f(y)) for the line containing this line segment
	 * 
	 * @param y the value to be inputed into f(y)
	 * @return the value of f(y) with the given y
	 */
	public double inverseF(double y){
		if(Double.isInfinite(inverseSlope()) )
			return getX();

		return inverseSlope() * (y-getY()) + getX();
	}

	/** Calculates the inverse of the slope of the line segment
	 * 
	 * @return the inverse of the slope of the line segment
	 */
	public double inverseSlope() {
		return 1/slope();
	}

	/** Calculates the slope of the line segment
	 * 
	 * @return the slope of the line segment
	 */
	public double slope() {
		return (y2- getY()) / (x2-getX());
	}

	/** Checks if this line segment is a point
	 * 
	 * @return true if its a point, false otherwise
	 */
	public boolean isAPoint() {
		if(getX() == x2 && getY() == y2)
			return true;
		return false;
	}

	/** Checks if this line is colinear with l2
	 * 
	 * @param l2 The line to be checked
	 * @return true if colinear, false otherwise
	 */
	public boolean colinear(Line l2) {
		if(Double.isInfinite(l2.slope()) && Double.isInfinite(slope()))
			if(l2.getX()== getX() )
				return true;
			else
				return false;
			
		return Math.abs(f(0)- l2.f(0)) <= 0.000001 && Math.abs(f(1)- l2.f(1)) <= 0.000001;
	}

	/** Checks if this line is parallel with l2
	 * 
	 * @param l2 The line to be checked
	 * @return true if parallel, false otherwise
	 */
	public boolean isParallel(Line l2) {
		return (Math.abs(slope() - l2.slope()) <= 0.00000001) || (slope() == l2.slope());
	}
	

	
	/** Returns the second x coordinate of the line segment
	 * 
	 * @return x2
	 */
	public double getX2() {
		return (double) x2;
	}
	

	/** Returns the second y coordinate of the line segment
	 * 
	 * @return y2
	 */
	public double getY2() {
		return (double) y2;
	}
	
	private double intersectionX(double x1, double x2, double y1, double y2, double x3, double x4, double y3, double y4) {
		return ((x1*y2 - y1 * x2) *(x3 - x4) - (x1-x2) *(x3 * y4 - y3 * x4)) / ((x1 - x2)*(y3- y4) - (y1 - y2)*(x3- x4)); 
	}
	private double intersectionY(double x1, double x2, double y1, double y2, double x3, double x4, double y3, double y4) {

		return	((x1*y2 - y1 * x2) *(y3 - y4) - (y1-y2) *(x3 * y4 - y3 * x4)) / ((x1 - x2)*(y3- y4) - (y1 - y2)*(x3- x4)); 
	}

	@Override
	public double getWidth() {
		return x2 - getX();
	}

	@Override
	public double getHeight() {
		return y2 - getY();
	}

}
