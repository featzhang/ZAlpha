package problem.ZDT;

import java.util.ArrayList;

import problem.Problem;
import solution.Individual;

public class ZDT4 extends Problem {

    public ZDT4() {
        name = "ZDT4";
        numberOfVariables = 10;
        numberOfObjectives = 2;
        upBound = new double[numberOfVariables];
        lowBound = new double[numberOfVariables];
        upBound[0] = 1;
        lowBound[0] = 0;
        for (int i = 1; i < numberOfVariables; i++) {
            upBound[i] = 5;
            lowBound[i] = -5;
        }
        idealIndividuals = new ArrayList<Double>();
        idealIndividuals.add(0.0);
        idealIndividuals.add(0.0);
    }

    @Override
    public void calculatorObject(Individual individual) {
        ArrayList<Double> variableVector = individual.getVariableVector();
        double[] var = new double[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i++) {
            var[i] = variableVector.get(i).doubleValue();
        }

        double g = 0;
        for (int i = 1; i < numberOfVariables; i++) {
            double x = 10 * (var[i] - 0.5);
            g += x * x - 10 * Math.cos(4 * Math.PI * x);
        }
        if (Double.isNaN(g)) {
            System.out.println(g);
        }
        g = 1 + 10 * (numberOfVariables - 1) + g;
        ArrayList<Double> functionVector = new ArrayList<Double>();
        functionVector.add(var[0]);
        functionVector.add(g * (1 - Math.sqrt(var[0] / g)));
        individual.setFunctionVector(functionVector);
        evaluateTimes++;
    }

    @Override
    public void initData(Individual individual) {
        ArrayList<Double> variableVector = individual.getVariableVector();
        double temp = random.nextDouble();
        variableVector.add(temp);
        for (int i = 1; i < numberOfVariables; i++) {
            temp = random.nextDouble() * 10 - 5;
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
            arrayList.add(mean_x);
//            obj[i][0] = mean_x;
            arrayList.add(gx * (1.0 - Math.sqrt(mean_x / gx)));
//            obj[i][1] = gx * (1.0 - sqrt(mean_x / gx));
            obj.add(arrayList);
        }
        return obj;
    }
}
