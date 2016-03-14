package decomposition;

import java.util.ArrayList;

import solution.Individual;

public class Tchebycheff extends Decomposition {

    public Tchebycheff() {
        name = "Tchebycheff";
    }

    @Override
    public double comput(int objectNum, ArrayList<Double> functionVector,
            ArrayList<Double> weightVector, ArrayList<Double> idealIndividuals,
            ArrayList<Individual> referenceVector) {
        double fvalue = 0;
        double max_fun = -1.0e+30;
        for (int i = 0; i < objectNum; i++) {
            double diff = Math.abs(functionVector.get(i) - idealIndividuals.get(i));
            double feval;
            if (weightVector.get(i) == 0) {
                feval = 0.00001 * diff;
            } else {
                feval = diff * weightVector.get(i);
            }
            if (feval > max_fun) {
                max_fun = feval;
            }
        }
        fvalue = max_fun;
        return fvalue;
    }
}