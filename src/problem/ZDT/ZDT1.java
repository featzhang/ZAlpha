package problem.ZDT;

import java.util.ArrayList;

import problem.Problem;
import solution.Individual;

public class ZDT1 extends Problem {

    public ZDT1() {
        numberOfVariables = 30;
        numberOfObjectives = 2;
        name = "ZDT1";
        upBound = new double[numberOfVariables];
        lowBound = new double[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i++) {
            lowBound[i] = 0;
            upBound[i] = 1;
        }
        idealIndividuals = new ArrayList<Double>();
        idealIndividuals.add(0.0);
        idealIndividuals.add(0.0);
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
        double x2 = g * (1 - Math.sqrt(x1 / g));
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
            variableVector.add(temp);
        }
        individual.setVariableVector(variableVector);
        individual.setVariableNum(numberOfVariables);
        individual.setObjectNum(numberOfObjectives);
    }

    @Override
    public ArrayList<ArrayList<Double>> calculateParetoFrontbyMathMethod(int num) {
        int i;
        double gx;
        double mean_x;
        ArrayList<ArrayList<Double>> obj = new ArrayList<ArrayList<Double>>();
        for (i = 0; i < num; i++) {
            ArrayList<Double> arrayList = new ArrayList<Double>();
            gx = 1;
            mean_x = (1.0 - 0) * i / (num - 1);
            arrayList.add(mean_x);
            double d = gx * (1.0 - Math.sqrt(mean_x / gx));
            arrayList.add(d);
            obj.add(arrayList);
        }
        return obj;
    }
}
