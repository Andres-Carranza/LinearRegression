package linear_regression_test;

import java.awt.Dimension;

import javax.swing.JFrame;
import display.Graph;
import linear_regression.LMSAlgorithm;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class LinearRegressionGraphTester {
	
	public static void main(String args[]) {
		
		double age[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
	//	double friends[] = {0,1,3,4,12,5,6,7 };
		
		double height[] = {0,4,2,3,5,8,10,9,12,15,13,14,17,16,0};
		

		
		LMSAlgorithm ai = new LMSAlgorithm(new double[][] {age}, height);
		
		Graph drawing = new Graph(age,height,ai.calcThetas().getRow(0));

		setUpGraph(drawing);
	}
	
	public static void setUpGraph(Graph drawing){
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(400, 300);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		
	}
}
