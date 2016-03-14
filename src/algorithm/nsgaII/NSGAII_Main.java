package algorithm.nsgaII;

import problem.Problem;
import problem.DTLZ.DTLZ5;

public class NSGAII_Main {
	public static void main(String[] args) {
		NSGAII nsgaii = new NSGAII();
		Problem problem = new DTLZ5();
		nsgaii.setProblem(problem);
		nsgaii.setMaxGeneration(3000);
		nsgaii.setPopulationSize(500);
		nsgaii.init();
		nsgaii.evoluation();
	}
}
