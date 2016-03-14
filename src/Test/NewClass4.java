package Test;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
import solution.Individual;
import algorithm.moead.MOEAD1;
import decomposition.Decomposition;
import decomposition.PenaltybasedBoundaryIntersection;
import decomposition.Tchebycheff;
import decomposition.WeightedSum;

public class NewClass4 {
	public static void main(String[] args) {
		ArrayList<Decomposition> decompositions = new ArrayList<Decomposition>();
		decompositions.add(new WeightedSum());
		decompositions.add(new Tchebycheff());
		decompositions.add(new PenaltybasedBoundaryIntersection());
		ArrayList<Problem> problems = new ArrayList<Problem>();
		problems.add(new ZDT1());
		problems.add(new ZDT2());
		problems.add(new ZDT3());
		problems.add(new ZDT4());
		problems.add(new ZDT6());
		problems.add(new DTLZ1());
		problems.add(new DTLZ2());
		problems.add(new DTLZ3());
		problems.add(new DTLZ4());
		problems.add(new DTLZ5());
		for (Decomposition decomposition : decompositions) {
			for (Problem problem : problems) {
				for (int i = 0; i < 30; i++) {
					MOEAD1 moead = new MOEAD1();
					moead.setDecomposition(decomposition);
					moead.setProblem(problem);
					if (problem.getObjectNum() == 2) {
						moead.setH(99);
					} else {
						moead.setH(13);
					}
					moead.setNeiboringNum(30);
					moead.setmaxGenerationTimes(3000);
					moead.init();
					moead.evoluation();
					// 计算所在位置
					ArrayList<Individual> population = moead.getPopulation();
					for (Individual individual : population) {
						problem.calculatOwnWeightRelativeIdealpoint(individual);
					}
					moead.outputWeight(i);
				}
			}
		}
		JOptionPane.showMessageDialog(null, "结束！Perfect！");
	}
}
