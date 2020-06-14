package display;

import processing.core.PApplet;
import andres.shapes.*;
public class Graph extends PApplet{

	private double[] x, y,theta;
	private static final float s = 10;
	public Graph(double x[], double y[], double theta[]) {
		this.x = x;
		this.y = y;
		this.theta =theta;
	}

	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {

	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		fill(color(0));
		translate(20,250);
		scale(1,-1);
		
		stroke(1);
		
		Line.constructLineWithAngle(0, -2*s,HALF_PI , 30*s).draw(this);
		Line.constructLineWithAngle(-2*s,0,0 , 50*s).draw(this);

		
		for(int i =0; i< x.length;i++)
			circle((float)x[i]*s, (float)y[i]*s,4 );
		
		Line.constructLineWithAngle(0,(float)theta[0]*s,(float)Math.atan(theta[1]) , 500*s).draw(this);
		
	}
	
	
}