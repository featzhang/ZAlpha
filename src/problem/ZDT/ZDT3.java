/**
 * 
 */
package problem.ZDT;

import java.util.ArrayList;

import problem.Problem;
import solution.Individual;

/**
 * @author Aaron
 * 
 */
public class ZDT3 extends Problem {

    /**
     * 
     */
    public ZDT3() {
        numberOfVariables = 30;
        numberOfObjectives = 2;
        name = "ZDT3";
        upBound = new double[numberOfVariables];
        lowBound = new double[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i++) {
            lowBound[i] = 0;
            upBound[i] = 1;
        }
        idealIndividuals = new ArrayList<Double>();
        idealIndividuals.add(0.0);
        idealIndividuals.add(-0.8);
    }

    @Override
    public void calculatorObject(Individual individual) {
        ArrayList<Double> variableVector = individual.getVariableVector();
        double sum = 0.0;
        for (int i = 1; i < numberOfVariables; i++) {
            sum += variableVector.get(i);
        }
        double g = 1 + 9 * sum / (numberOfVariables - 1);
        double x1 = variableVector.get(0);
        double x2 = g
                * (1 - Math.pow((x1 / g), 2) - (x1 / g)
                * Math.sin(10 * Math.PI * x1));
        ArrayList<Double> functionVector = new ArrayList<Double>();
        functionVector.add(x1);
        functionVector.add(x2);
        individual.setFunctionVector(functionVector);
        evaluateTimes++;
    }

    @Override
    public void initData(Individual individual) {
        ArrayList<Double> variableVector = individual.getVariableVector();
        for (int i = 0; i < numberOfVariables; i++) {
            double temp = random.nextDouble();
            // System.out.println(temp);
            variableVector.add(temp);
        }
        individual.setVariableVector(variableVector);
        individual.setVariableNum(numberOfVariables);
        individual.setObjectNum(numberOfObjectives);
    }

    @Override
    public ArrayList<ArrayList<Double>> calculateParetoFrontbyMathMethod(int num) {
        ArrayList<ArrayList<Double>> obj = new ArrayList<ArrayList<Double>>();
        int i;
        double gx;
        double mean_x;

        for (i = 0; i < num; i++) {
            ArrayList<Double> arrayList = new ArrayList<Double>();
            gx = 1;
            mean_x = (1.0 - 0) * i / (num - 1);
//            obj[i][0] = mean_x;
            arrayList.add(mean_x);
//            obj[i][1] = 1.0 - sqrt(mean_x / gx) - mean_x * sin(10.0 * PI * mean_x) / gx;
            arrayList.add(1.0 - Math.sqrt(mean_x / gx) - mean_x * Math.sin(10.0 * Math.PI * mean_x) / gx);
            obj.add(arrayList);
        }
        return obj;
    }
}
