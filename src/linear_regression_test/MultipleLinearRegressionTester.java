package linear_regression_test;

import linear_regression.LMSAlgorithm;

public class MultipleLinearRegressionTester {
	
	public static void main(String[] args) {
	double age[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
	double friends[] = {0,1,3,4,12,5,6,7,9,10,11,12,13,20 };
	
	double height[] = {0,4,2,3,5,8,10,9,12,15,13,14,17,16};
	

	
	LMSAlgorithm ai = new LMSAlgorithm(new double[][] {friends,age}, height);
	
	System.out.println(ai.calcThetas());
	}
}


