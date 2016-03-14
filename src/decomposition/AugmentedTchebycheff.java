/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package decomposition;

import java.util.ArrayList;
import solution.Individual;

/**
 *
 * @author Aaron
 */
public class AugmentedTchebycheff extends Decomposition {

    private static double p = 0.75;

    public AugmentedTchebycheff() {
        name="AugmentedTchebycheff";
    }

    public static void setP(double p) {
        AugmentedTchebycheff.p = p;
    }

    @Override
    public double comput(int objectNum, ArrayList<Double> functionVector, ArrayList<Double> weightVector, ArrayList<Double> idealIndividuals, ArrayList<Individual> referenceVector) {

        Tchebycheff tchebycheff = new Tchebycheff();
        double tcD = tchebycheff.comput(objectNum, functionVector, weightVector, idealIndividuals, referenceVector);
        double sum = 0;
        for (int i = 0; i < objectNum; i++) {
            double f = functionVector.get(i).doubleValue();
            double z = idealIndividuals.get(i).doubleValue();
            double abs = Math.abs(z - f);
            sum += abs;
        }
        return tcD + p * sum;
    }
}
