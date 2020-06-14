package andres.shapes;
import processing.core.PApplet;


/**
 *  This class represents a double precision Rectangle. It can perform common calculations
 *   as well as drawing a representation of the rectangle to a Processing PApplet.
 *  
 *  @author Andres carrranza
 *  @version 9/27/2019
 */
public class Rectangle extends Shape2D{
	private double width, height;

	/**
	 * Constructs a new Rectangle, initialized to location (0,0) and size (0,0)
	 */
	public Rectangle() {
		this(0,0,0,0);
	}

	/**
	 * Constructs a new Rectangle, initialized to location (x,y) and size (width,height)
	 * 
	 * @param x The X coordinate of the top left corner of the Rectangle
	 * @param y The Y coordinate of the top  left corner of the Rectangle
	 * @param width The width of the Rectangle
	 * @param height The height of the Rectangle
	 */
	public Rectangle(double x, double y, double width, double height) {
		super(x,y);
		this.width = width;
		this.height = height;
	}


	@Override
	/**
	 * Returns the perimeter of the rectangle in double precision
	 * 
	 * @return The perimeter of the the rectangle 
	 */
	public double getPerimeter() {
		return 2 * (Math.abs(width) + Math.abs(height));
	}

	@Override
	/**
	 * Returns the area of the rectangle in double precision
	 * @return The area of the the rectangle 
	 */	
	public double getArea() {
		return Math.abs(width * height);
	}

	@Override
	/**
	 * Determines whether the point (x,y) is contained in the rectangle
	 * 
	 * @param x x - the X coordinate of the point
	 * @param y y - the Y coordinate of the point
	 * @return true if the rectangle contains the point (x,y), or if the point (x,y) is on the border of the Rectangle. false otherwise
	 */
	public boolean isPointInside(double x, double y) {
		double x1 = Math.min(getX(), getX() + width);
		double x2 = Math.max(getX(), getX() + width);
		double y1 = Math.min(getY(), getY() + height);
		double y2 = Math.max(getY(), getY() + height);
		return x >=  x1 && x <= x2 && y >= y1 && y <= y2;
	}





	@Override	
	/** Draws the rectangle with (x,y) as the top left corner of the bounding rectangle
	 * with the predefined processing settings 
	 * 
	 * @param marker the PApplet to be used to draw the rectangle
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.rectMode(PApplet.CORNER);
		marker.rect((float)getX(), (float)getY(), (float)width, (float)height);
		marker.pop();
	}


	@Override
	/**
	 * Determines whether the Rectangle is contained inside the Rectangle r
	 * 
	 * @param r The rectangle to be checked
	 * @return true if the Rectangle is inside r or on r. false otherwise
	 */
	public boolean isInside(Rectangle r) {
		double x1 = Math.min(getX(), getX() + width);
		double x2 = Math.max(getX(), getX() + width);
		double y1 = Math.min(getY(), getY() + height);
		double y2 = Math.max(getY(), getY() + height);

		return r.isPointInside(x1 ,y1) && r.isPointInside(x2, y2);

	}

	@Override
	/**
	 * Determines whether the Rectangle is contained inside the Circle c
	 * 
	 * @param c The circle to be checked
	 * @return true if the Rectangle is inside c or on c. false otherwise
	 */
	public boolean isInside(Circle c) {
		double x1 = Math.min(getX(), getX() + width);
		double x2 = Math.max(getX(), getX() + width);
		double y1 = Math.min(getY(), getY() + height);
		double y2 = Math.max(getY(), getY() + height);

		return c.isPointInside(x1 , y1) && c.isPointInside(x2, y1 ) && c.isPointInside(x2, y2) && c.isPointInside(x1, y2);
	}

	@Override
	/**
	 * Returns the x center coordinate of the rectangle
	 * 
	 * @return the center x coordinate
	 */
	public double getCenterX() {
		return 	getX() + width/2;
	}

	@Override
	/**	 
	 * Returns the y center coordinate of the rectangle
	 * 
	 * @return the center y coordinate
	 */
	public double getCenterY() {
		return getY() +  height/2;
	}

	@Override
	/**
	 * Returns the bounds of this rectangle
	 * 
	 * @return the bounding rectangle 
	 */
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), width, height);
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

}
