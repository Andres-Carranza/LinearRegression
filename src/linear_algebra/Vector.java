package linear_algebra;

public class Vector extends Matrix{


	public Vector (double[] vector) {
		super(new double[][] {vector});
	}

	public double dotProduct(Vector v) {
		if(v.getCols() != getCols())
			throw new IllegalArgumentException("Both vectors must be same size");
		
		float val = 0;
		for(int i = 0; i< getCols(); i++) {
				val+= getIndex(i) * v.getIndex(i);
		}
		
		return val;
	}
	
	public double getIndex(int i) {
		return super.getIndex(0, i);
	}
	
	
	
}
