package algorithm.vega;

import java.util.Vector;

import problem.Problem;
import solution.Individual;
import algorithm.Algorithm;

/**
 * 向量评价进化算法 基本思想：针对不同目标采用比例选择法，分别产生r个子群体，每个子群体大小N/r。
 * 分别进化后合并，并执行进化操作（选择、交叉、变异），重复上述过程直至满足 终止条件。
 *
 * @author 张作峰
 */
public class VEGA extends Algorithm {
	/**
	 * 进化代数，默认为250代
	 */
	private int geneticTimes = 250;
	/**
	 * 种群
	 */
	private Vector<Vector<Individual>> population = null;
	/**
	 * 种群大小，默认种群大小是120
	 */
	private int populationSize = 120;
	/**
	 * 测试问题
	 */
	private Problem problem = null;
	/**
	 * 子种群大小
	 */
	private int subPopulationSize = -1;

	public VEGA() {
		population = new Vector<Vector<Individual>>();
		// 默认测试问题DTLZ1
		problem = new problem.DTLZ.DTLZ1();

	}

	@Override
	public void evoluation() {
	}

	/**
	 * 获取进化代数
	 *
	 * @return 进化代数
	 */
	public int getGeneticTimes() {
		return geneticTimes;
	}

	/**
	 * 获取种群大小
	 *
	 * @return 种群大小
	 */
	public int getPopulationSize() {
		return populationSize;
	}

	/**
	 * 获取测试问题
	 *
	 * @return 测试问题
	 */
	public Problem getProblem() {
		return problem;
	}

	/**
	 * 执行进化
	 */
	@SuppressWarnings("unused")
	public void runEvolution() {
		// 初始化
		int objectNumber = problem.getObjectNum();// 计算目标个数
		subPopulationSize = populationSize / objectNumber;// 计算子种群大小
		for (int i = 0; i < objectNumber; i++) {
			Vector<Individual> subPopulation = new Vector<Individual>();
			for (int j = 0; j < subPopulationSize; j++) {
				Individual individual = new Individual();
				problem.initData(individual);
				problem.calculatorObject(individual);
				subPopulation.add(individual);
			}
			population.add(subPopulation);
		}
		// 选择,使用锦标赛选择
		for (int i = 0; i < population.size(); i++) {

			Vector<Individual> subPopulation = population.get(i);
			Vector<Individual> newSubPopulation = new Vector<Individual>();
			Individual individual1 = subPopulation
					.get((int) (Math.random() * subPopulationSize));
			Individual individual2 = subPopulation
					.get((int) (Math.random() * subPopulationSize));
			// Dominated comparator = new Dominated(2);
			// comparator.setComparateObject(i + 1);
			// int c = comparator.compare(individual1, individual2);
			// if (c == 1) {
			//
			// } else {
			//
			// }
		}
		// 交叉
		// 变异
	}

	/**
	 * 设置进化代数
	 *
	 * @param geneticTimes
	 *            进化代数
	 */
	public void setGeneticTimes(int geneticTimes) {
		this.geneticTimes = geneticTimes;
	}

	/**
	 * 设置种群大小
	 *
	 * @param populationSize
	 *            种群大小
	 */
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
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

}