package decomposition;

import java.util.ArrayList;

import solution.Individual;

public class myDecomposition extends Decomposition {
	public myDecomposition() {
		name = "myDecomposition";
	}

	@Override
	public double comput(int objectNum, ArrayList<Double> functionVector,
			ArrayList<Double> weightVector, ArrayList<Double> idealIndividuals,
			ArrayList<Individual> referenceVector) {

		double feval = 0;
		for (int i = 0; i < objectNum; i++) {
			double diff = Math.abs(functionVector.get(i)
					- idealIndividuals.get(i));
			if (weightVector.get(i) == 0) {
				feval += Math.pow(0.00001 * diff, 50);
			} else {
				feval += Math.pow(diff * weightVector.get(i), 50);
			}
		}
		return Math.pow(feval, 1.0 / 50.0);
	}

}
