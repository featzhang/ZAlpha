package algorithm.mogls;

import java.util.ArrayList;
import java.util.Arrays;

import operate.Operate;
import problem.Problem;
import problem.ZDT.ZDT1;
import solution.Individual;
import Util.OutPut;
import algorithm.Algorithm;

import comparator.DominatedComparator;

import decomposition.Tchebycheff;

class ComparatorByTchebycheff implements java.util.Comparator<Individual> {
	private ArrayList<Double> idealIndividuals = null;
	ArrayList<Individual> referenceVector = null;

	private Tchebycheff tchebycheff = new Tchebycheff();

	public ComparatorByTchebycheff(ArrayList<Double> idealIndividuals,
			ArrayList<Individual> referenceVector) {
		this.idealIndividuals = idealIndividuals;
		this.referenceVector = referenceVector;
	}

	@Override
	public int compare(Individual o1, Individual o2) {
		double tch1 = tchebycheff.comput(o1.getObjectNum(),
				o1.getFunctionVector(), o1.getWeightVector(), idealIndividuals,
				referenceVector);
		double tch2 = tchebycheff.comput(o2.getObjectNum(),
				o2.getFunctionVector(), o2.getFunctionVector(),
				idealIndividuals, referenceVector);
		if (tch1 < tch2) {
			return 1;
		}
		return 0;
	}
}

/**
 *
 * Jaszkiewicz‘s version of MOGLS （The multiObjective genetic local search）
 *
 * @author 张作峰
 *
 */
public class MOGLS extends Algorithm {

	public static void main(String[] args) {

		MOGLS mogls = new MOGLS();
		mogls.setMaxGeneration(1);
		mogls.setCurrentSetNum(4);
		mogls.setTemporaryElitePopulationNum(2);
		mogls.init();
		// mogls.evoluation();
	}

	/**
	 * currentSet的大小，默认是100
	 */
	private int currentSetNum = 100;

	/**
	 * TEP临时最优群体大小，默认是30
	 */
	private int temporaryElitePopulationNum = 30;

	/**
	 * currentSet 当前种群
	 */
	private ArrayList<Individual> currentSet = null;

	/**
	 * 参考集
	 */
	private ArrayList<Double> z = null;

	/**
	 * external population扩展种群
	 */
	private ArrayList<Individual> ep = null;

	/**
	 * temporary elite population 临时最优种群
	 */
	private ArrayList<Individual> tep = null;

	/**
	 * 测试问题,默认是ZDT1
	 */
	private Problem problem = new ZDT1();

	private int maxGeneration = 10;

	public MOGLS() {
	}

	/**
	 * 进化：选择/交叉/变异
	 */
	public void evoluation() {
		for (int g = 0; g < maxGeneration; g++) {
			System.out.println("第" + (g + 1) + "代");
			ArrayList<Double> lambda = new ArrayList<Double>();
			// 随机产生lambda
			double random = Math.random();
			lambda.add(random);
			lambda.add(1 - random);
			for (int i = 0; i < currentSet.size(); i++) {
				Individual individual = currentSet.get(i);
				individual.setWeightVector(lambda);
				currentSet.set(i, individual);
			}
			// 从CS中选择K个切比雪夫最优解组成TEP
			tep = new ArrayList<Individual>();
			ComparatorByTchebycheff mComparator = new ComparatorByTchebycheff(
					z, null);
			int size = currentSet.size();
			Individual[] individuals = new Individual[size];
			for (int i = 0; i < size; i++) {
				individuals[i] = currentSet.get(i);
			}
			Arrays.sort(individuals, mComparator);
			for (int i = 0; i < temporaryElitePopulationNum; i++) {
				tep.add(individuals[i]);
			}

			// 从TEP中随机选择两个个体交叉变异产生y
			int r1 = (int) (Math.random() * temporaryElitePopulationNum);
			int r2 = (int) (Math.random() * temporaryElitePopulationNum);
			while (r1 == r2) {
				r2 = (int) (Math.random() * temporaryElitePopulationNum);
			}
			Individual oldIndividual1 = tep.get(r1);
			Individual oldIndividual2 = tep.get(r2);
			Individual y = new Individual();
			Operate.crossover(problem, oldIndividual1, oldIndividual2, y);
			y.setWeightVector(lambda);
			Operate.realMutation(problem, y);
			problem.calculatorObject(y);
			// 根据问题或者启发式由y产生y'
			// 更新参考集Z
			updateZ(y);
			// 更新TEP：如果y'比TEP最差解好，且不同于TEP中的其他解加入CS，如果CS大于K×S，删除最差解
			updateTEP(y);
			// 更新EP：移除被f(y')支配的解，如果F(y')非支配增加
			updateEP(y);

		}
		String fileSep = System.getProperty("file.separator");
		String fileName = "MOGLS" + fileSep + problem.getName() + fileSep
				+ "MOGLS_" + problem.getName();
		System.out.println(ep.size());
		OutPut.printfunctionToFile(ep, fileName);
	}

	/**
	 * 初始化
	 */
	public void init() {
		// 1.1 基于测试问题随机产生CS
		currentSet = new ArrayList<Individual>();
		Individual individual = null;
		for (int i = 0; i < currentSetNum; i++) {
			individual = new Individual();
			problem.initData(individual);
			problem.calculatorObject(individual);
			currentSet.add(individual);
			System.out.println(individual.functionToString());
		}
		// 1.2 初始化参考集Z
		z = new ArrayList<Double>();
		int objectNum = problem.getObjectNum();
		for (int i = 0; i < objectNum; i++) {
			z.add(Double.MAX_VALUE);
		}
		for (int i = 0; i < currentSetNum; i++) {
			individual = currentSet.get(i);
			ArrayList<Double> functionVector = individual.getFunctionVector();
			for (int j = 0; j < objectNum; j++) {
				Double double1 = functionVector.get(j);
				if (z.get(j) > double1) {
					z.set(j, double1);
				}
			}
		}
		System.out.println("////////////// 参考集Z ////////////////////");
		for (Double iterable_element : z) {
			System.out.print(iterable_element + "   ");
		}
		// 1.3 CS的非支配解初始化EP扩展群体
		ep = new ArrayList<Individual>();
		DominatedComparator dominated = new DominatedComparator(objectNum);
		for (int i = 0; i < currentSetNum; i++) {
			boolean needAdd = true;
			individual = currentSet.get(i);
			for (int j = 0; j < currentSetNum; j++) {
				if (i == j) {
					continue;
				}
				Individual newIndividual = currentSet.get(j);
				int compare = dominated.compare(newIndividual, individual);// 个体2被个体3支配，返回1
				if (compare > 0) {
					needAdd = false;
				}
			}
			if (needAdd) {// 个体不被支配
				try {
					ep.add(individual.clone());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		int epSize = ep.size();
		System.out.println("\nepSize:" + epSize);

	}

	/**
	 * 设置当前解集的大小
	 *
	 * @param s
	 *            currentSet的大小
	 */
	public void setCurrentSetNum(int s) {
		this.currentSetNum = s;
	}

	/**
	 * 设置最大进化代数
	 *
	 * @param maxGeneration
	 *            最大进化代数
	 */
	public void setMaxGeneration(int maxGeneration) {
		this.maxGeneration = maxGeneration;
	}

	/**
	 * 设置测试问题
	 *
	 * @param problem
	 *            测试问题
	 */
	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	/**
	 * 设置临时最优群体大小
	 *
	 * @param k
	 *            TEP临时最优群体大小
	 */
	public void setTemporaryElitePopulationNum(int k) {
		this.temporaryElitePopulationNum = k;
	}

	/**
	 * 更新EP：移除被f(y')支配的解，如果F(y')非支配增加
	 *
	 * @param y
	 *            新个体
	 */
	private void updateEP(Individual y) {
		// 移除被f(y')支配的解
		int epSize = ep.size();
		DominatedComparator dominated = new DominatedComparator(
				problem.getObjectNum());
		for (int i = 0; i < epSize; i++) {
			Individual individual = ep.get(i);
			int compare = dominated.compare(y, individual);
			if (compare > 0) {
				ep.set(i, ep.get(epSize - 1));
				ep.remove(epSize - 1);
				epSize--;
			}
		}
		// 如果F(y')非支配增加
		boolean needAdd = true;
		for (int i = 0; i < epSize; i++) {
			Individual individual = ep.get(i);
			int compare = dominated.compare(individual, y);
			if (compare > 0) {
				needAdd = false;
			}
		}
		if (needAdd) {
			try {
				ep.add((Individual) y.clone());
			} catch (CloneNotSupportedException e) {
			}
		}
	}

	/**
	 * 更新TEP：如果y'比TEP最差（切比雪夫）解好，且不同于TEP中的其他解加入CS，如果CS大于K×S，删除最差解(切比雪夫)
	 *
	 * @param y
	 *            新个体
	 * @param lambda
	 *            权重向量
	 */
	private void updateTEP(Individual y) {
		int objectNum = problem.getObjectNum();
		ArrayList<Double> yFun = y.getFunctionVector();
		int tepSize = tep.size();
		Individual werstIndividual = tep.get(tepSize - 1);
		Tchebycheff tchebycheff = new Tchebycheff();
		double comput1 = tchebycheff.comput(objectNum,
				werstIndividual.getFunctionVector(),
				werstIndividual.getWeightVector(), z, null);
		double comput2 = tchebycheff.comput(objectNum, yFun,
				y.getWeightVector(), z, null);
		if (comput2 < comput1) {
			boolean canADD = true;
			for (int i = 0; i < currentSetNum; i++) {
				Individual individual = currentSet.get(i);
				ArrayList<Double> functionVector = individual
						.getFunctionVector();
				boolean same = true;
				for (int j = 0; j < objectNum; j++) {
					if (yFun.get(j) != functionVector.get(j)) {
						same = false;
					}
				}
				if (same) {
					canADD = false;
				}
			}
			if (canADD) {
				currentSet.add(y);
			}
		}
		int size;
		while ((size = currentSet.size()) > currentSetNum * tepSize) {
			// 找到CS中的最差解并删除
			Individual individual = currentSet.get(0);
			double worstComput = tchebycheff.comput(objectNum,
					individual.getFunctionVector(),
					individual.getWeightVector(), z, null);
			int worstIndex = 0;
			for (int i = 1; i < size; i++) {
				individual = currentSet.get(i);
				double newComput = tchebycheff.comput(objectNum,
						individual.getFunctionVector(),
						individual.getWeightVector(), z, null);
				if (newComput > worstComput) {
					worstComput = newComput;
					worstIndex = i;
				}
			}
			currentSet.set(worstIndex, currentSet.get(size - 1));
			currentSet.remove(size - 1);
		}
	}

	/**
	 * 更新参考集
	 *
	 * @param y
	 *            新个体
	 */
	private void updateZ(Individual y) {
		int objectNum = problem.getObjectNum();
		ArrayList<Double> functionVector = y.getFunctionVector();
		for (int i = 0; i < objectNum; i++) {
			Double double1 = functionVector.get(i);
			if (double1 < z.get(i)) {
				z.set(i, double1);
			}
		}
	}
}