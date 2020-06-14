package linear_algebra;

import java.util.Arrays;

public class Matrix {
	private double [][] matrix;

	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public int getRows() {
		return matrix.length;
	}

	public int getCols() {
		return matrix[0].length;
	}

	public Matrix transpose() {
		double [][]t = new double[getCols()][getRows()];

		for ( int i = 0; i < getRows(); i++)
			for (int j = 0; j < getCols(); j++)
				t[j][i] = matrix[i][j];

		return new Matrix(t);
	}

	public Matrix multiply(Matrix m) {
		if( getCols() != m.getRows())
			throw new IllegalArgumentException("Coloumns of matrix and vector must match");

		double [][] res = new double[getRows()][m.getCols()];

		for ( int i = 0; i < getRows(); i++)
			for (int j = 0; j < m.getCols(); j++)	
				res[i][j] = getRowVectors()[i].dotProduct(m.getColummVectors()[j]);

		return new Matrix(res);
	}

	public Vector[] getColummVectors() {
		return transpose().getRowVectors();
	}

	public Vector multiply(Vector v) {
		if( getCols() != v.getCols())
			throw new IllegalArgumentException("Coloumns of matrix and vector must match");
		double res [] = new double[getRows()];

		for(int i = 0; i< getRows(); i++) {
			res[i] = getRowVectors()[i].dotProduct(v);
		}

		return new Vector(res);

	}

	public  double calcDeterminant() {
		if(getCols()!=getRows())
			throw new IllegalArgumentException("matrix must be square");

		return new Determinant(matrix,getCols()).calcDeterminant();
	}   

	public Matrix calcInverse() {
		if(getCols()!=getRows())
			throw new IllegalArgumentException("matrix must be square");
		return new Matrix(Inverse.invert(matrix));
		
	}
	
	public Vector[] getRowVectors() {
		Vector [] rows = new Vector[matrix.length];

		for(int i = 0; i< matrix.length; i++)
			rows[i] = new Vector(matrix[i]);
		return rows;
	}


	public double[] getRow(int i) {
		return matrix[i];
	}

	public double getIndex(int i, int j) {
		return matrix[i][j];
	}

	public String toString() {
		String s = "";

		for(double row[] : matrix)
			s+=Arrays.toString(row) + "\n";
		return s;
	}

}
