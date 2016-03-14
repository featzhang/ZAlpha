package problem.MOP;

import java.util.ArrayList;

import problem.Problem;
import solution.Individual;

public class MOP1 extends Problem {
	public MOP1() {
		name = "MOP1";
		numberOfVariables = 1;
		numberOfObjectives = 2;
		upBound = new double[numberOfVariables];
		lowBound = new double[numberOfVariables];
		for (int i = 0; i < numberOfVariables; i++) {
			lowBound[i] = -5;
			upBound[i] = 7;
		}
		idealIndividuals = new ArrayList<Double>();
		idealIndividuals.add(0.0);
		idealIndividuals.add(0.0);
	}

	@Override
	public void calculatorObject(Individual individual) {
		ArrayList<Double> functionVector = new ArrayList<Double>();
		ArrayList<Double> variableVector = individual.getVariableVector();
		double x = variableVector.get(0).doubleValue();
		double y1 = x * x;
		double y2 = (x - 2) * (x - 2);
		functionVector.add(y1);
		functionVector.add(y2);
		individual.setFunctionVector(functionVector);
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

}
