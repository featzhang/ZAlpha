package algorithm.sga;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * 简单遗传算法
 *
 * @author 张作峰
 *
 */
public class MySGA {
	public static void main(String[] args) {
		for (int j = 0; j < 10; j++) {
			try {
				String pathname = "SGA_" + j + ".txt";
				File file = new File(pathname);
				System.setOut(new PrintStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			for (int i = 2; i < 1000; i++) {
				MySGA mySGA = new MySGA();
				mySGA.setMaxGeneration(1000);
				mySGA.setPopulationSize(i);
				mySGA.evolution();
				System.out.println((i + 1) + "\t"
						+ SgaIndividual.getEvaluationTime());
			}
		}

	}

	private int chromeLength = 30;

	/**
	 * 种群大小
	 */
	private int populationSize = 5;

	/**
	 * 进化代数
	 */
	private int maxGeneration = Integer.MAX_VALUE;

	/**
	 * 种群
	 */
	ArrayList<SgaIndividual> population = null;

	/**
	 * 最优个体
	 */
	private SgaIndividual bestIndividual = null;

	/**
	 * 最优个体保留
	 */
	public void bestResave() {
		if (bestIndividual == null) {
			try {
				bestIndividual = (SgaIndividual) population.get(0).clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		double bestFitness = bestIndividual.getfitness();
		int bestIndex = -1;
		double worstFitness = bestFitness;
		int worstIndex = -1;
		int counter = 0;
		for (int i = 0; i < populationSize; i++) {
			SgaIndividual individual = population.get(i);
			double fitness = individual.getfitness();
			if (fitness > bestFitness) {
				bestFitness = fitness;
				bestIndex = i;
				counter = 1;
			} else if (fitness == bestFitness) {
				counter++;
			}
			if (fitness < worstFitness) {
				worstFitness = fitness;
				worstIndex = i;
			}

		}
		if (bestIndex != -1) {// 存在更好的
			try {
				// System.out.println("change");
				bestIndividual = (SgaIndividual) population.get(bestIndex).clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		if (worstIndex > -1 && counter < populationSize / 3) {// 存在更差的，将使用最好的替代最差的
			try {
				population.set(worstIndex, (SgaIndividual) bestIndividual.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 交叉
	 */
	public void crossOver() {
		for (int i = 0; i < populationSize; i++) {
			int p = (int) (Math.random() * populationSize);
			int q = (int) (Math.random() * populationSize);
			while (p == q) {
				q = (int) (Math.random() * populationSize);
			}
			SgaIndividual individual1 = population.get(p);
			SgaIndividual individual2 = population.get(q);
			// System.out.println(individual.toString());
			SBX(individual1, individual2);
			individual1.evaluate();
			individual2.evaluate();
			// System.out.println(individual.toString());
		}
	}

	/**
	 * 执行进化
	 */
	public void evolution() {
		init();
		bestResave();
		// print();
		for (int i = 0; i < maxGeneration; i++) {
			// System.out.println("Generation:" + (i + 1));
			select();
			crossOver();
			mution();
			bestResave();
			// System.out.println("b:" + bestIndividual.toString());
			if (bestIndividual.getFun() == 0) {
				System.out.print("FOUNDED:\t");
				break;
			}
		}
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
	 * 初始化
	 */
	public void init() {
		SgaIndividual.setChromeLength(chromeLength);
		SgaIndividual.setEvaluationTime(0);
		population = new ArrayList<SgaIndividual>();
		for (int i = 0; i < populationSize; i++) {
			SgaIndividual individual = new SgaIndividual();
			individual.initCode();
			individual.evaluate();
			population.add(individual);
		}
	}

	/**
	 * 变异
	 */
	public void mution() {
		for (int i = 0; i < populationSize; i++) {
			SgaIndividual individual = population.get(i);
			int[] chrome = individual.getChrome();
			int index = (int) (Math.random() * chromeLength);
			chrome[index] = chrome[index] == 0 ? 1 : 0;
			individual.evaluate();
		}
	}

	/**
	 * 打印结果
	 */
	public void print() {
		for (int i = 0; i < populationSize; i++) {
			System.out.println((i + 1) + " " + population.get(i).toString());
		}
	}

	/**
	 * 模拟二进制交叉
	 *
	 * @param individual
	 *            个体一
	 * @param individual2
	 *            个体二
	 */
	private void SBX(SgaIndividual individual1, SgaIndividual individual2) {
		// System.out.println(individual1.toString());
		int[] chrome1 = individual1.getChrome();
		int[] chrome2 = individual2.getChrome();
		int r = (int) (Math.random() * chromeLength);
		for (int i = r; i < chromeLength; i++) {
			int d = chrome1[i];
			chrome1[i] = chrome2[i];
			chrome2[i] = d;
		}
		// System.out.println("交叉后");
		// System.out.println(individual1.toString());
	}

	/**
	 * 轮盘赌选择
	 */
	public void select() {
		double[] fitnesss = new double[populationSize];
		double d = 0;
		for (int i = 0; i < populationSize; i++) {
			fitnesss[i] = population.get(i).getfitness();
			d += fitnesss[i];
		}
		for (int i = 0; i < populationSize; i++) {
			fitnesss[i] = fitnesss[i] / d;
		}
		for (int i = 1; i < populationSize; i++) {
			fitnesss[i] = fitnesss[i] + fitnesss[i - 1];
		}
		ArrayList<SgaIndividual> newPopulation = new ArrayList<SgaIndividual>();
		for (int i = 0; i < populationSize; i++) {
			double p = Math.random();
			int selectIndex = -1;
			for (int j = 0; j < populationSize; j++) {
				if (p < fitnesss[j]) {
					selectIndex = j;
					break;
				}
			}
			SgaIndividual individual = null;
			try {
				individual = (SgaIndividual) population.get(selectIndex).clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			newPopulation.add(individual);
		}
		population = newPopulation;
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
	 * 设置种群大小
	 *
	 * @param populationSize
	 *            种群大小
	 */
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
}