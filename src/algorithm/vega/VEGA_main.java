package algorithm.vega;

import problem.DTLZ.DTLZ1;

/**
 * @author 张作峰
 *
 */
public class VEGA_main {

	public static void main(String[] args) {
		VEGA vega = new VEGA();
		vega.setPopulationSize(100);
		vega.setProblem(new DTLZ1());
		vega.setGeneticTimes(250);
		vega.runEvolution();
	}
}