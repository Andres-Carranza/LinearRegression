package andres.shapes;

/**
 *  This class is the blueprint for 2 dimensional geometric shapes
 *  
 *  @author Andres carrranza
 *  @version 10/8/2019
 */
public abstract class Shape2D extends Shape{

	/**
	 * Creates a new Shape2D
	 * 
	 * @param x the top left x coordinate of the bounding rectangle of the shape
	 * @param y the top left y coordinate of the bounding rectangle of the shape
	 */
	public Shape2D(double x, double y) {
		super(x, y);
	}
	
	/**
	 * Returns the perimeter of the shape in double precision
	 * 
	 * @return The perimeter of the the shape 
	 */
	public abstract double getPerimeter();
	

	/**
	 * Returns the area of the shape in double precision
	 * @return The area of the the shape 
	 */	
	public abstract double getArea();
	
	

	/**
	 * Determines whether the shape is contained inside the Rectangle r
	 * 
	 * @param r The rectangle to be checked
	 * @return true if the shape is inside r or on r. false otherwise
	 */
	public abstract boolean isInside(Rectangle r);
	
	/**
	 * Determines whether the shape is contained inside the Circle c
	 * 
	 * @param c The circle to be checked
	 * @return true if the shape is inside c or on c. false otherwise
	 */
	public abstract boolean isInside(Circle c);
	
}
