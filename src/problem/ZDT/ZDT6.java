/**
 * 
 */
package problem.ZDT;

import Util.ZMatrix;
import java.util.ArrayList;

import problem.Problem;
import solution.Individual;

/**
 * @author Aaron
 * 
 */
public class ZDT6 extends Problem {

    /**
     * 
     */
    public ZDT6() {
        name = "ZDT6";

        numberOfVariables = 10;
        numberOfObjectives = 2;
        upBound = new double[numberOfVariables];
        lowBound = new double[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i++) {
            lowBound[i] = 0;
            upBound[i] = 1;
        }
        idealIndividuals = new ArrayList<Double>();
        idealIndividuals.add(0.28);
        idealIndividuals.add(0.0);
    }

    @Override
    public void calculatorObject(Individual individual) {

        ArrayList<Double> variableVector = individual.getVariableVector();
        ArrayList<Double> functionVector = new ArrayList<Double>();
        double[] x = new double[numberOfVariables];
        double[] f = new double[numberOfObjectives];
        for (int i = 0; i < numberOfVariables; i++) {
            x[i] = variableVector.get(i);
        }
        double g = 0;
        for (int n = 1; n < numberOfVariables; n++) {
            g += x[n] / (numberOfVariables - 1);
        }
        g = 1 + 9 * Math.pow(g, 0.25);

        f[0] = 1 - Math.exp(-4 * x[0])
                * Math.pow(Math.sin(6 * Math.PI * x[0]), 6);
        f[1] = g * (1 - Math.pow(f[0] / g, 2));
        for (int i = 0; i < numberOfObjectives; i++) {
            functionVector.add(f[i]);
        }
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
        double[][] obj = new double[num][2];
        int i;
        double gx;
        double mean_x;

        for (i = 0; i < num; i++) {
            gx = 1;
            mean_x = (1.0 - 0) * i / (num - 1);
            obj[i][0] = mean_x;
        }
        for (i = 0; i < num; i++) {
            obj[i][0] = 1 - Math.exp(-4 * obj[i][0]) * Math.pow(Math.sin(6 * Math.PI * obj[i][0]), 6);
        }
        for (i = 0; i < num; i++) {
            obj[i][1] = 1 - Math.pow(obj[i][0], 2);
        }
        ArrayList<ArrayList<Double>> TDimensionalArrayTrance = ZMatrix.TDimensionalArrayTrance(obj);
        return TDimensionalArrayTrance;
    }
}
