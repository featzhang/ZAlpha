package decomposition;

import java.util.ArrayList;

import solution.Individual;

public class NormalizedTchebycheff extends Decomposition {

    public NormalizedTchebycheff() {
        name = "NormalizedTchebycheff";
    }

    @Override
    public double comput(int objectNum, ArrayList<Double> functionVector,
            ArrayList<Double> weightVector, ArrayList<Double> idealIndividuals,
            ArrayList<Individual> referenceVector) {
        ArrayList<Double> scale = new ArrayList<Double>();
        for (int i = 0; i < objectNum; i++) {
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            for (int j = 0; j < objectNum; j++) {
                double tp = referenceVector.get(j).getFunctionVector().get(i);
                if (tp > max) {
                    max = tp;
                }
                if (tp < min) {
                    min = tp;
                }
            }
            scale.add(max - min);
            if (max - min == 0) {
                return Double.MAX_VALUE;
            }
        }
        double max_fun = Double.MIN_VALUE;
        for (int n = 0; n < objectNum; n++) {
            double diff = functionVector.get(n) - idealIndividuals.get(n)
                    / scale.get(n);
            double feval;
            if (weightVector.get(n) == 0) {
                feval = 0.0001 * diff;
            } else {
                feval = diff * weightVector.get(n);
            }
            if (feval > max_fun) {
                max_fun = feval;
            }
        }
        return max_fun;
    }
}
