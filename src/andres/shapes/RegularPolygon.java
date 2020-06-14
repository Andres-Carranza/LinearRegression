package andres.shapes;
import processing.core.PApplet;

/**
 *  This class represens a regular polygon
 *  
 *  @author Andres Carranza
 *  @version 10/8/2019
 */
public class RegularPolygon extends Shape2D{
	private int numSides;
	private double sideLength;
	
	/**
	 * Constructs a triangle with sides of 100
	 * 
	 * @param x the center x 
	 * @param y the center y
	 */
	public RegularPolygon(double x, double y){
		super(x,y);
		numSides = 3;
		sideLength = 100;
	 } 
	
	/**
	 * Constructs a regular polygon 
	 * 
	 * @param x the center x 
	 * @param y the center y
	 * @param numSides the number of sides
	 * @param sideLength the length of each side
	 */
	public RegularPolygon(double x, double y, int numSides, double sideLength ){
		super(x,y);
		
		this.sideLength = sideLength;
		this.numSides = numSides;
	 } 

	/**
	 * Calculates r
	 * 
	 * @return the radius of the inscribed circle
	 */
	public double calcr(){
		return .5 *sideLength / Math.tan(Math.PI / numSides);
	 } 
	
	/**
	 * Calculates R
	 * 
	 * @return the radius of the circumscribed circle
	 */
	public double calcR(){
		return .5 *sideLength / Math.sin(Math.PI / numSides);
	 } 
	
	/**
	 * Calculates the angle of the vertex angles
	 * 
	 * @return the angles in radians
	 */
	public double calcVertexAngle(){
		return (numSides - 2. ) / numSides * Math.PI; 
	 } 


	/**
	 * Return the number of sides of the polygon
	 * 
	 * @return numsides
	 */
	public int getNumSides(){
		return numSides;
	 } 
	
	/**
	 * Return the length of the sides of the polygon
	 * 
	 * @return sidelength
	 */
	public double getSideLength(){
		return sideLength;
	 } 
	
	/** Draws the inscribed and circumscribed circles of the polygon
	 * 
	 * @param marker the PApplet to be used to draw the shape
	 */
	public void drawBoundingCircles(PApplet drawer){
		Circle C = getCircumscribedCircle();
		C.setFill(false);
		C.draw(drawer);
		

		Circle c = getInscribedCircle();
		c.setFill(false);
		c.draw(drawer);	 
		} 
	
	/** Draws the shape with (x,y) as the center of the polygon
	 * with the predefined processing settings 
	 * 
	 * @param marker the PApplet to be used to draw the shape
	 */
	public void draw(PApplet drawer) {
		super.draw(drawer);
		
		Line bounds [] = new Line[numSides];
		
		double [] x = new double[2];
		double [] y = new double[2];
		
		double centerAngle = 2 * Math.PI / numSides;
		
		for(int i = 1; i <= numSides; i++) {
			Line l1 = Line.constructLineWithAngle((float)getX(),(float) getY(), (float) (i * centerAngle),(float) calcR());
			Line l2 = Line.constructLineWithAngle((float)getX(),(float) getY(), (float) ((i + 1) * centerAngle),(float) calcR());

			x[0] = l1.getX2();
			y[0] = l1.getY2();
			
			x[1] = l2.getX2();
			y[1] = l2.getY2();
			
			
			bounds[i-1] = new Line((float)x[0],(float)y[0],(float)x[1],(float)y[1]);
			bounds[i-1].draw(drawer);
			
		}
		
		drawer.pop();
	}
	
	@Override
	public double getPerimeter() {
		return numSides * sideLength;
	}

	@Override
	public double getArea() {
		return .5 * numSides * calcR() * calcR() * Math.sin(2 * Math.PI / numSides);
	}

	@Override
	public boolean isInside(Rectangle r) {
		return getCircumscribedCircle().isInside(r);
	}

	@Override
	public boolean isInside(Circle c) {
		return getCircumscribedCircle().isInside(c);
	}

	@Override
	public boolean isPointInside(double x, double y) {
		return getCircumscribedCircle().isPointInside(x,y);
	}

	@Override
	public double getCenterX() {
		return getX();
	}

	@Override
	public double getCenterY() {
		return getY();
	}

	@Override
	public Rectangle getBounds() {
		return getCircumscribedCircle().getBounds();
	}

	public Circle getCircumscribedCircle() {

		double lx = getX() - calcR();
		double ty = getY() - calcR();
		return new Circle(lx, ty, calcR()  * 2);
	}
	
	public Circle getInscribedCircle() {

		double lx = getX() - calcr();
		double ty = getY() - calcr();
		return new Circle(lx, ty, calcr()  * 2);
	}
	
	@Override
	public double getWidth() {
		return getBounds().getWidth();
	}

	@Override
	public double getHeight() {
		return getBounds().getHeight();
	}

}
