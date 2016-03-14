package problem.UF;

import java.util.ArrayList;

import problem.Problem;
import solution.Individual;

public class UF1 extends Problem {
	public UF1() {
		name = "UF1";
		numberOfVariables = 30;
		numberOfObjectives = 2;
		upBound = new double[numberOfVariables];
		lowBound = new double[numberOfVariables];
		lowBound[0] = -1;
		upBound[0] = 1;
		for (int i = 1; i < numberOfVariables; i++) {
			lowBound[i] = -1;
			upBound[i] = 1;
		}
		idealIndividuals = new ArrayList<Double>();
		idealIndividuals.add(0.0);
		idealIndividuals.add(0.0);
	}

	@Override
	public void calculatorObject(Individual individual) {
		ArrayList<Double> variableVector = individual.getVariableVector();
		double[] x = new double[numberOfVariables];
		for (int i = 0; i < numberOfVariables; i++)
			x[i] = variableVector.get(i).doubleValue();

		int count1, count2;
		double sum1, sum2, yj;
		sum1 = sum2 = 0.0;
		count1 = count2 = 0;

		for (int j = 2; j <= numberOfVariables; j++) {
			yj = x[j - 1]
					- Math.sin(6.0 * Math.PI * x[0] + j * Math.PI
							/ numberOfVariables);
			yj = yj * yj;
			if (j % 2 == 0) {
				sum2 += yj;
				count2++;
			} else {
				sum1 += yj;
				count1++;
			}
		}
		ArrayList<Double> functionVector = new ArrayList<Double>();
		functionVector.add(x[0] + 2.0 * sum1 / (double) count1);
		functionVector
				.add(1.0 - Math.sqrt(x[0]) + 2.0 * sum2 / (double) count2);
		individual.setFunctionVector(functionVector);
	}

	@Override
	public void initData(Individual individual) {

		ArrayList<Double> variableVector = new ArrayList<Double>();
		double temp1 = random.nextDouble();
		variableVector.add(temp1);
		for (int i = 0; i < numberOfVariables; i++) {
			double temp = random.nextDouble() * 2 - 1;
			variableVector.add(temp);
		}
		individual.setVariableVector(variableVector);
		individual.setVariableNum(numberOfVariables);
		individual.setObjectNum(numberOfObjectives);
	}

}
