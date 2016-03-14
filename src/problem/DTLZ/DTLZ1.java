/**
 * 
 */
package problem.DTLZ;

import java.util.ArrayList;

import problem.Problem;
import solution.Individual;

/**
 * @author Aaron
 * 
 */
public class DTLZ1 extends Problem {

    /**
     * 
     */
    public DTLZ1() {
        name = "DTLZ1";
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

    public DTLZ1(int numOfVariables, int numberOfObjectives) {
        this.numberOfObjectives = numberOfObjectives;
        this.numberOfVariables = numOfVariables;
        name = "DTLZ1";
        upBound = new double[numberOfVariables];
        lowBound = new double[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i++) {
            lowBound[i] = 0;
            upBound[i] = 1;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see problem.Problem#calculatorObject(MOEAD.Individual)
     */
    @Override
    public void calculatorObject(Individual individual) {
        ArrayList<Double> variableVector = individual.getVariableVector();
        ArrayList<Double> functionVector = new ArrayList<Double>();
        double[] x = new double[numberOfVariables];
        double[] f = new double[numberOfObjectives];
        int k = numberOfVariables - numberOfObjectives + 1;

        for (int i = 0; i < numberOfVariables; i++) {
            x[i] = variableVector.get(i);
        }

        double g = 0.0;
        for (int i = numberOfVariables - k; i < numberOfVariables; i++) {
            g += (x[i] - 0.5) * (x[i] - 0.5)
                    - Math.cos(20.0 * Math.PI * (x[i] - 0.5));
        }

        g = 100 * (k + g);
        for (int i = 0; i < numberOfObjectives; i++) {
            f[i] = (1.0 + g) * 0.5;
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            for (int j = 0; j < numberOfObjectives - (i + 1); j++) {
                f[i] *= x[j];
            }
            if (i != 0) {
                int aux = numberOfObjectives - (i + 1);
                f[i] *= 1 - x[aux];
            } // if
        }// for

        for (int i = 0; i < numberOfObjectives; i++) {
            functionVector.add(f[i]);
        }
        individual.setFunctionVector(functionVector);
        evaluateTimes++;
    }

    /*
     * (non-Javadoc)
     * 
     * @see problem.Problem#initData(MOEAD.Individual)
     */
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
        double x1, x2;
        int i, j, k;
        ArrayList<ArrayList<Double>> obj = new ArrayList<ArrayList<Double>>();
        for (i = 0; i <= 19; i++) {
            x1 = (1.0 - 0.0) * i / 19;
            for (j = 0; j <= 24; j++) {
                ArrayList<Double> al = new ArrayList<Double>();
                x2 = (1.0 - 0.0) * j / 24;
                k = 25 * i + j;
//                obj[k][0] = 0.5 * x1 * x2;
                al.add(0.5 * x1 * x2);
//                obj[k][1] = 0.5 * x1 * (1 - x2);
                al.add(0.5 * x1 * (1 - x2));
//                obj[k][2] = 0.5 * (1.0 - x1);
                al.add(0.5 * (1.0 - x1));
                obj.add(al);
            }
        }
        return obj;
    }
}
