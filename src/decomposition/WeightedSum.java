package decomposition;

import java.util.ArrayList;

import solution.Individual;

public class WeightedSum extends Decomposition {

	public WeightedSum() {
		name = "WeightedSum";
	}

	@Override
	public double comput(int objectNum, ArrayList<Double> functionVector,
			ArrayList<Double> weightVector, ArrayList<Double> idealIndividuals,
			ArrayList<Individual> referenceVector) {
		double fvalue = 0.0;
		for (int i = 0; i < functionVector.size(); i++) {
			fvalue += functionVector.get(i) * weightVector.get(i);
		}
		return fvalue;
	}
	public double comput(ArrayList<Double> functionVector,ArrayList<Double> weightVector) {
		double fvalue = 0.0;
		for (int i = 0; i < functionVector.size(); i++) {
			fvalue += functionVector.get(i) * weightVector.get(i);
		}
		return fvalue;
	}
}
