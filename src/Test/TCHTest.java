package Test;

import java.util.ArrayList;

import Util.OutPut;
import Util.ZFile;

import indicate.Indicate;
import decomposition.Tchebycheff;
import problem.Problem;
import problem.ZDT.ZDT1;
import solution.Individual;

public class TCHTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Problem problem = new ZDT1();
		Tchebycheff tchebycheff = new Tchebycheff();
		ArrayList<Double> weightVector = new ArrayList<Double>();
		weightVector.add(0.3);
		weightVector.add(0.7);
		ArrayList<Double> idealIndividuals = new ArrayList<Double>();
		idealIndividuals.add(0.0);
		idealIndividuals.add(0.0);
		ArrayList<ArrayList<Double>> cc = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < 100000;) {
			Individual individual = new Individual();
			individual.setWeightVector(weightVector);
			problem.initData(individual);
			problem.calculatorObject(individual);
			ArrayList<Double> fun = individual.getFunctionVector();
			double comput = tchebycheff.comput(2, fun, weightVector,
					idealIndividuals, null);
			// System.out.println(comput);
			if (comput <= 2.1422323800774473 && comput >= 2.1322323800774473) {
				// System.out.println(fun);
				cc.add(fun);
				i++;
			}
		}
		OutPut.wirte2DToFile(cc, "2.5.txt");
	}
}
