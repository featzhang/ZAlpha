/**
 * 
 */
package problem.DTLZ;

import Util.ZMatrix;
import java.util.ArrayList;

import problem.Problem;
import solution.Individual;

public class DTLZ5 extends Problem {

    public DTLZ5() {

        name = "DTLZ5";
        numberOfVariables = 12;
        numberOfObjectives = 3;
        upBound = new double[numberOfVariables];
        lowBound = new double[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i++) {
            lowBound[i] = 0;
            upBound[i] = 1;
        }
        idealIndividuals = new ArrayList<Double>();
        idealIndividuals.add(0.0);
        idealIndividuals.add(0.0);
        idealIndividuals.add(0.0);
    }

    @Override
    public void calculatorObject(Individual individual) {

        double sum = 0.0;
        double g;
        double[] x = new double[numberOfObjectives - 1];

        for (int j = 0; j < numberOfVariables; j++) {
            Double double1 = individual.getVariableVector().get(j);
            sum += (double1 - 0.5) * (double1 - 0.5);
        }
        for (int i = 0; i < numberOfObjectives - 1; i++) {
            Double double1 = individual.getVariableVector().get(i);
            x[i] = Math.PI / (4 * (1 + sum)) * (1 + 2 * sum * double1);
        }
        g = sum + 1;
        ArrayList<Double> functionVector = new ArrayList<Double>();
        Double x_var0 = individual.getVariableVector().get(0);
        Double x_var1 = individual.getVariableVector().get(1);
        functionVector.add(Math.cos(x_var0 * Math.PI / 2) * Math.cos(x_var1)
                * g);
        functionVector.add(Math.cos(x_var0 * Math.PI / 2) * Math.sin(x_var1)
                * g);
        functionVector.add(Math.sin(x_var0 * Math.PI / 2) * g);
        individual.setFunctionVector(functionVector);
        evaluateTimes++;
    }

    @Override
    public void initData(Individual individual) {
        ArrayList<Double> variableVector = individual.getVariableVector();
        for (int i = 0; i < numberOfVariables; i++) {
            double temp = random.nextDouble();
            variableVector.add(temp);
        }
        individual.setVariableVector(variableVector);
        individual.setVariableNum(numberOfVariables);
        individual.setObjectNum(numberOfObjectives);
    }

    @Override
    public ArrayList<ArrayList<Double>> calculateParetoFrontbyMathMethod(int num) {
        double[][] obj = new double[500][numberOfObjectives];
        double x1, x2, x3;
        int i, j, k;
        x3 = Math.PI / 4;
        for (i = 0; i <= num; i++) {
            x1 = (1.0 - 0.0) * i / 499;
            obj[i][0] = Math.cos(x1 * Math.PI / 2.0) * Math.cos(x3);
            obj[i][1] = Math.cos(x1 * Math.PI / 2.0) * Math.sin(x3);
            obj[i][2] = Math.sin(x1 * Math.PI / 2.0);
        }
        ArrayList<ArrayList<Double>> TDimensionalArrayTrance = ZMatrix.TDimensionalArrayTrance(obj);
        return TDimensionalArrayTrance;
    }
}
