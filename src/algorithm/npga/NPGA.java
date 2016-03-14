package algorithm.npga;

import java.util.ArrayList;
import java.util.Random;

import operate.Operate;

import solution.Individual;
import Util.Distance;
import algorithm.Algorithm;

import comparator.DominatedComparator;

/**
 * a niched pareto genetic algorithm for multiobjective<br>
 * 小生境pareto多目标遗传算法
 *
 * @author 张作峰
 *
 */
public class NPGA extends Algorithm {
	public NPGA() {
		algorithmName = "NPGA";
	}

	/**
	 * 支配关系比较器
	 */
	DominatedComparator dominatedComparator = null;

	@Override
	public void evoluation() {
		dominatedComparator = new DominatedComparator(problem.getObjectNum());

		for (generationTime = 1; generationTime <= maxGeneration; generationTime++) {
			System.out.println("第" + generationTime + "代");
			// ////////////////////选择
			populationVector = selectBasedOnNondominated(populationVector);
			// ////////////////////交叉
			Random random = new Random();
			int i = random.nextInt(populationSize);
			int j = random.nextInt(populationSize);
			Individual parent1 = populationVector.get(i);
			Individual parent2 = populationVector.get(j);
			Individual child1 = new Individual();
			Individual child2 = new Individual();
			operate.Operate.realBinaryCrossover(problem, parent1, parent2,
					child1, child2);
			// ////////////////////变异
			Operate.realMutation(problem, child1);
			Operate.realMutation(problem, child2);

		}
	}

	/**
	 * @param populationVector
	 * @return
	 */
	private ArrayList<Individual> selectBasedOnNondominated(
			ArrayList<Individual> populationVector) {
		ArrayList<Individual> arrayList = new ArrayList<Individual>();
		int csSize = 10;
		for (int ii = 0; ii < populationSize; ii++) {
			Random random = new Random();
			int i = random.nextInt(populationSize);
			int j = random.nextInt(populationSize);
			ArrayList<Individual> compareSet = new ArrayList<Individual>();
			for (int j2 = 0; j2 < csSize; j2++) {
				int nextInt = random.nextInt(populationSize);
				compareSet.add(populationVector.get(nextInt));
			}
			Individual individual = selectOne(compareSet,
					populationVector.get(i), populationVector.get(j));
			arrayList.add(individual);
		}
		return arrayList;
	}

	private Individual selectOne(ArrayList<Individual> compareSet,
			Individual individual1, Individual individual2) {
		boolean nonDominatedable1 = true;// 不受支配
		boolean nonDominatedable2 = true;// 不受支配
		for (Individual individual3 : compareSet) {
			int compare = dominatedComparator.compare(individual1, individual3);
			if (compare == DominatedComparator.Dominated) {
				nonDominatedable1 = false;
			}
			compare = dominatedComparator.compare(individual2, individual3);
			if (compare == DominatedComparator.Dominated) {
				nonDominatedable2 = false;
			}
		}
		if (nonDominatedable1 && !nonDominatedable2) {// 1不被支配，2被支配
			return individual1;
		} else if (nonDominatedable2 && !nonDominatedable1) {// 2不被支配，1被支配
			return individual2;
		} else {// 都不被支配或都受支配
			double d1 = nichedShareFitness(individual1);
			double d2 = nichedShareFitness(individual2);
			if (d1 < d2) {
				return individual2;
			} else if (d2 < d1) {
				return individual1;
			}
		}
		return null;
	}

	/**
	 * 共享适应度
	 */
	private double sharingRadius = 0.5;

	/**
	 * 计算共享适应度
	 *
	 * @param individual
	 *            个体
	 * @return 共享适应度
	 */
	private double nichedShareFitness(Individual individual) {
		double shareFitness = 0;
		for (Individual individual2 : populationVector) {
			double d = Distance.EDiastance(individual, individual2);
			double sf = 0;
			if (d >= sharingRadius) {
				sf = 0;
			} else {
				sf = 1 - d / sharingRadius;
			}
			shareFitness += sf;
		}
		// TODO 共享适应度应该等于fi/shareFitness
		return shareFitness;
	}
}