package problem.DTLZ;

import Util.ZMatrix;
import java.util.ArrayList;

import problem.Problem;
import solution.Individual;

public class DTLZ4 extends Problem {

    public DTLZ4() {
        name = "DTLZ4";
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
        double gx = 0.0;
        int j = 0;
        double[] x_var = new double[numberOfObjectives];
        ArrayList<Double> functionVector = new ArrayList<Double>();
        for (j = 2; j < numberOfVariables; j++) {
            double var = individual.getVariableVector().get(j);
            sum += (var - 0.5) * (var - 0.5);
        }
        for (int i = 0; i < numberOfObjectives - 1; i++) {
            double var = individual.getVariableVector().get(i);
            x_var[i] = Math.pow(var, 12.0);
        }
        for (j = 0; j < 3 - 1; j++) {
            gx = sum + 1.0;
        }
        functionVector.add(Math.cos(x_var[0] * Math.PI / 2)
                * Math.cos(x_var[1] * Math.PI / 2) * gx);
        functionVector.add(Math.cos(x_var[0] * Math.PI / 2)
                * Math.sin(x_var[1] * Math.PI / 2) * gx);
        functionVector.add(Math.sin(x_var[0] * Math.PI / 2) * gx);
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

        double x1, x2;
        int i, j, k;

        int a = 100;
        for (i = 0; i <= 19; i++) {
            x1 = (1.0 - 0.0) * i / 19;
            for (j = 0; j <= 24; j++) {
                x2 = (1.0 - 0.0) * j / 24;
                k = 25 * i + j;
                obj[k][0] = Math.cos(Math.pow(x1, a) * Math.PI / 2.0) * Math.cos(Math.pow(x2, a) * Math.PI / 2.0);
                obj[k][1] = Math.cos(Math.pow(x1, a) * Math.PI / 2.0) * Math.sin(Math.pow(x2, a) * Math.PI / 2.0);
                obj[k][2] = Math.sin(Math.pow(x1, a) * Math.PI / 2.0);
            }
        }
        ArrayList<ArrayList<Double>> TDimensionalArrayTrance = ZMatrix.TDimensionalArrayTrance(obj);
        return TDimensionalArrayTrance;
    }
}
