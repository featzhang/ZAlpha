package algorithm.moead_matche;

import problem.Problem;
import problem.DTLZ.DTLZ1;
import problem.DTLZ.DTLZ2;
import problem.DTLZ.DTLZ3;
import problem.DTLZ.DTLZ4;
import problem.DTLZ.DTLZ5;
import problem.ZDT.ZDT1;
import problem.ZDT.ZDT2;
import problem.ZDT.ZDT3;
import problem.ZDT.ZDT4;
import problem.ZDT.ZDT6;
import decomposition.Decomposition;
import decomposition.NormalizedTchebycheff;
import decomposition.PenaltybasedBoundaryIntersection;
import decomposition.Tchebycheff;
import decomposition.WeightedSum;
import decomposition.myDecomposition;

/**
 * @author 张作峰
 *
 */
public class Moead_Match_main {
	public static void main(String[] args) {
		int j = 1;
		while (j < 2) {
			int problemi = 6;
			while (problemi < 7) {
				MOEAD_Match moead = new MOEAD_Match();
				Problem problem = null;
				Decomposition decomposition = null;
				switch (j) {
				case 0:
					decomposition = new NormalizedTchebycheff();
					break;
				case 1:
					decomposition = new PenaltybasedBoundaryIntersection();
					break;
				case 2:
					decomposition = new Tchebycheff();
					break;
				case 3:
					decomposition = new WeightedSum();
					break;
				case 4:
					decomposition = new myDecomposition();
					break;
				}
				moead.setMaxGeneration(1);
				moead.setPopulationSize(300);
				moead.setNeightbouringNum(10);
				moead.setDecomposition(decomposition);
				System.out.println(decomposition.getName());
				switch (problemi) {
				case 0:
					problem = new DTLZ1();
					break;
				case 1:
					problem = new DTLZ2();
					break;
				case 2:
					problem = new DTLZ3();
					break;
				case 3:
					problem = new DTLZ4();
					break;
				case 4:
					problem = new DTLZ5();
					break;
				case 5:
					problem = new DTLZ3();
					break;
				case 6:
					problem = new ZDT1();
					break;
				case 7:
					problem = new ZDT2();
					break;
				case 8:
					problem = new ZDT3();
					break;
				case 9:
					problem = new ZDT4();
					break;
				case 10:
					problem = new ZDT6();
					break;

				}
				moead.setProblem(problem);
				moead.init();
				// population.initByRead();
				moead.evoluation();
				problemi++;
			}
			j++;
		}
	}
}