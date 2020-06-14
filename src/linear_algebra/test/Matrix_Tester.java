package linear_algebra.test;

import linear_algebra.*;

public class Matrix_Tester {
	public static void main(String[] args) {
		Matrix x = new Matrix(new double[][]
				{new double[] {2,1},
				new double[] {3,3},
				new double[] {4,2}});
		
		Vector y = new Vector(new double[] {3,2,1});
		
		System.out.println(x.transpose());
		
		System.out.println(y);
		
		System.out.println(x.transpose().multiply(y));
	}
}
