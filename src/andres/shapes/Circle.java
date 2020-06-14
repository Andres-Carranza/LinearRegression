package andres.shapes;
import processing.core.PApplet;

/**
 *  This class represents a double precision Circle. It can perform common calculations
 *   as well as drawing a representation of the circle to a Processing PApplet.
 *  
 *  @author Andres Carranza
 *  @version 9/27/2019
 */
public class Circle extends Shape2D{
	private double diameter;

	/**
	 * Constructs a new Circle, initialized to location (0,0) and diameter 0
	 */
	public Circle() {
		this(0,0,0);
	}

	/**
	 * Constructs a new Circle, initialized to location (x,y) and diameter 0
	 * 
	 * @param x The X coordinate of the top left corner of the Rectangle bounding the circle
	 * @param y The Y coordinate of the top  left corner of the Rectangle bounding the circle
	 * @param diameter The diameter of the circle
	 */
	public Circle(double x, double y, double diameter) {
		super(x,y);
		this.diameter = diameter;
	}




	@Override
	/**
	 * Calculates the circumfernce of the circle
	 * 
	 * @return the circumference of the circle
	 */
	public double getPerimeter() {
		return Math.abs(Math.PI * diameter);
	}

	@Override
	/**
	 * Returns the area of the circle in double precision
	 * @return The area of the the circle 
	 */	public double getArea() {
		return Math.abs(Math.PI * Math.pow(diameter / 2, 2));
	}

	@Override
	/**
	 * Determines whether the point (x,y) is contained in the circle
	 * 
	 * @param x x - the X coordinate of the point
	 * @param y y - the Y coordinate of the point
	 * @return true if the circle contains the point (x,y), or if the point (x,y) is on the border of the circle. false otherwise
	 */
	public boolean isPointInside(double x, double y) {

		double h = getCenterX(); 
		double k = getCenterY();

		return Math.pow(x-h, 2) + Math.pow(y-k,2) <= Math.pow(diameter/2, 2);
	}

	@Override	
	/** Draws the circle with (x,y) as the top left corner of the bounding rectangle
	 * with the predefined processing settings 
	 * 
	 * @param marker the PApplet to be used to draw the shape
	 */
	public void draw(PApplet marker) {
		super.draw(marker);

		marker.ellipseMode(PApplet.CORNER);
		marker.circle((float)getX(), (float)getY(), (float)diameter);

		marker.pop();
	}



	@Override
	/**
	 * Determines whether the Circle is contained inside the Rectangle r
	 * 
	 * @param r The circle to be checked
	 * @return true if the Circle is inside r or on r. false otherwise
	 */
	public boolean isInside(Rectangle r) {
		double x1 = Math.min(getX(), getX()+ diameter);
		double x2 = Math.max( getX(), getX() + diameter);
		double y1 = Math.min(getY(), getY() + diameter);
		double y2 = Math.max(getY(), getY() + diameter);

		return r.isPointInside(x1 ,y1) && r.isPointInside(x2, y2);

	}

	@Override
	/**
	 * Determines whether the Circle is contained inside the Circle c
	 * 
	 * @param c The circle to be checked
	 * @return true if the Circle is inside c or on c. false otherwise
	 */
	public boolean isInside(Circle c) {
		double x1 = Math.min(getX(), getX() + diameter);
		double x2 = Math.max(getX(), getX() + diameter);
		double y1 = Math.min(getY(), getY() + diameter);
		double y2 = Math.max(getY(), getY() + diameter);
		double diameter = Math.abs(this.diameter);

		return c.isPointInside(x1 + diameter/2, y1) && c.isPointInside(x2, y1 + diameter /2 ) && c.isPointInside(x1 + diameter/2, y2) && c.isPointInside(x1, y1 + diameter/2);
	}

	@Override
	/**	 
	 * Returns the x center coordinate of the circle
	 * 
	 * @return the center x coordinate
	 */
	public double getCenterX() {
		return getX() + diameter /2;
	}

	@Override
	/**	 
	 * Returns the y center coordinate of the circle
	 * 
	 * @return the center y coordinate
	 */	public double getCenterY() {
		return getY() + diameter / 2;
	}

	@Override
	/**
	 * Returns the rectangle bounding the circle
	 * 
	 * @return the bounds of the rectangle of which this circle is circumscribed
	 */
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), diameter, diameter);
	}

	@Override
	public double getWidth() {
		return diameter;
	}

	@Override
	public double getHeight() {
		return diameter;
	}



}
