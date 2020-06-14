package andres.shapes;

import java.awt.Color;

import processing.core.PApplet;

/**
 *  This class is the blueprint for geometric shapes
 *  
 *  @author Andres Carranza
 *  @version 10/8/2019
 */
public abstract class Shape {
	private Color strokeColor;
	private Color fillColor;
	private boolean fill;
	private float strokeWeight;
	private double x,y;

	/**
	 * Creates a new shape
	 * 
	 * @param x the top left x coordinate of the bounding rectangle of the shape
	 * @param y the top left y coordinate of the bounding rectangle of the shape
	 */
	public Shape(double x, double y) {
		this.x = x; 
		this.y = y;

		strokeColor = Color.black;
		fillColor = Color.black;
		fill = true;
		strokeWeight = 1;
	}

	public void setStrokeColor(Color c) {
		strokeColor = c;
	}

	public void setFillColor(Color c) {
		fillColor = c;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public void setStrokeWeight(float weight) {
		strokeWeight = weight;
	}

	/** Draws the shape with (x,y) as the top left corner of the bounding rectangle
	 * with the predefined processing settings 
	 * 
	 * @param marker the PApplet to be used to draw the shape
	 */
	public void draw(PApplet drawer) {
		drawer.push();
		drawer.stroke(strokeColor.getRGB());
		drawer.strokeWeight(strokeWeight);
		if(fill)
			drawer.fill(fillColor.getRGB());
		else
			drawer.noFill();
	}

	/**
	 * Sets the top left point to (x,y)
	 * 
	 * @param x the new top left x coordinate of the shape
	 * @param y the new top left y coordinate of the shape
	 */
	public void setPoint(double x,double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter for the top left x coordinate of the shape
	 * 
	 * @return the top left x coordinate of the shape
	 */
	public double getX() {
		return x;
	}


	/**
	 * Getter for the top left y coordinate of the shape
	 * 
	 * @return the top left y coordinate of the shape
	 */
	public double getY() {
		return y;
	}

	/**
	 * Shifts the shape x units to the right, and y units down
	 * 
	 * @param x the change  in the x direction
	 * @param y the change in the y direction
	 */
	public void translate(double x, double y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * Determines whether the point (x,y) is contained in the shape
	 * 
	 * @param x x - the X coordinate of the point
	 * @param y y - the Y coordinate of the point
	 * @return true if the shape contains the point (x,y), or if the point (x,y) is on the border of the Rectangle. false otherwise
	 */
	public abstract boolean isPointInside(double x, double y);

	/**
	 * Returns the x center coordinate of the shape
	 * 
	 * @return the center x coordinate
	 */
	public abstract double getCenterX();

	/**
	 * Returns the y center coordinate of the shape
	 * 
	 * @return the center y coordinate
	 */
	public abstract double getCenterY();

	/**
	 * Returns the bounding rectangle of the shape
	 * 
	 * @return the bounding rectangle 
	 */
	public abstract Rectangle getBounds(); 

	/**
	 * Calculates the width of the shape
	 * @return width of shape
	 */
	public abstract double getWidth();
	
	/**
	 * Calculates the height of the shape
	 * @return height of shape
	 */
	public abstract double getHeight();
}
