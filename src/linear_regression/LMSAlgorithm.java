package linear_regression;

import linear_algebra.*;

public class LMSAlgorithm {
	private Matrix x;
	private Vector y;
	
	public LMSAlgorithm(double[][] trainingExamples, double[] values) {
		if(trainingExamples[0].length != values.length)
			throw new IllegalArgumentException("invalid data");
		
		double data[][] = new double[trainingExamples.length+1][trainingExamples[0].length];
		for(int i = 0; i< data[0].length; i++) 
			data[0][i] = 1;
		for(int i = 1; i< data.length; i++) 
			data[i] = trainingExamples[i-1];
		
		x = new Matrix(data).transpose();
		y = new Vector(values);
		
	}
	
	public void train(double data) {
		
	}
	
	public double predict(double data) {
		return 0;
	}
	
	public Vector calcThetas() {
		return x.transpose().multiply(x).calcInverse().multiply(x.transpose().multiply(y));
	}
	
}
