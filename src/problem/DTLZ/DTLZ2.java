/**
 * 
 */
package problem.DTLZ;

import Util.ZMatrix;
import java.util.ArrayList;

import problem.Problem;
import solution.Individual;

/**
 * @author Aaron_1
 * 
 */
public class DTLZ2 extends Problem {

    public DTLZ2() {
        name = "DTLZ2";
        numberOfVariables = 7;
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

    public DTLZ2(int numOfVariables, int numberOfObjectives) {
        this.numberOfObjectives = numberOfObjectives;
        this.numberOfVariables = numOfVariables;
        name = "DTLZ2";
        upBound = new double[numberOfVariables];
        lowBound = new double[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i++) {
            lowBound[i] = 0;
            upBound[i] = 1;
        }
    }

    @Override
    public void calculatorObject(Individual individual) {
        ArrayList<Double> functionVector = new ArrayList<Double>();
        ArrayList<Double> variableVector2 = individual.getVariableVector();
        double[] x = new double[numberOfVariables];
        double[] f = new double[numberOfObjectives];
        int k = numberOfVariables - numberOfObjectives + 1;
        for (int i = 0; i < numberOfVariables; i++) {
            x[i] = variableVector2.get(i);
        }
        double g = 0.0;
        for (int i = numberOfVariables - k; i < numberOfVariables; i++) {
            g += (x[i] - 0.5) * (x[i] - 0.5);
        }
        for (int i = 0; i < numberOfObjectives; i++) {
            f[i] = 1.0 + g;
        }
        for (int i = 0; i < numberOfObjectives; i++) {
            for (int j = 0; j < numberOfObjectives - (i + 1); j++) {
                f[i] *= Math.cos(x[j] * 0.5 * Math.PI);
            }
            if (i != 0) {
                int aux = numberOfObjectives - (i + 1);
                f[i] *= Math.sin(x[aux] * 0.5 * Math.PI);
            } // if
        } // for
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
        
        double[][] obj = new double[500][numberOfObjectives];
        double x1, x2;
        int i, j, k;
        for (i = 0; i <= 19; i++) {
            x1 = (1.0 - 0.0) * i / 19;
            for (j = 0; j <= 24; j++) {
                x2 = (1.0 - 0.0) * j / 24;
                k = 25 * i + j;
                obj[k][0] = Math.cos(x1 * Math.PI / 2.0) * Math.cos(x2 * Math.PI / 2.0);
                obj[k][1] = Math.cos(x1 * Math.PI / 2.0) * Math.sin(x2 * Math.PI / 2.0);
                obj[k][2] = Math.sin(x1 * Math.PI / 2.0);
            }
        }
        ArrayList<ArrayList<Double>> TDimensionalArrayTrance = ZMatrix.TDimensionalArrayTrance(obj);
        return TDimensionalArrayTrance;
    }
}
