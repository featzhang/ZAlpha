package algorithm.moead_matche;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import operate.Operate;
import problem.Problem;
import problem.ZDT.ZDT1;
import solution.Individual;
import Util.OutPut;
import algorithm.Algorithm;
import decomposition.Decomposition;
import decomposition.PenaltybasedBoundaryIntersection;

/**
 * 种群
 *
 * @author 张作峰
 * */
public class MOEAD_Match extends Algorithm {
	public MOEAD_Match() {
		algorithmName = "MOEAD_Match";
	}

	/**
	 * 理想点，用于存储理想点目标值
	 */
	private ArrayList<Double> idealIndividuals = null;
	/**
	 * 邻居集大小
	 */
	private int neiboringNum = -1;

	/**
	 * 参考集
	 */
	private ArrayList<Individual> referenceVector = null;

	/**
	 * 从sourceArray中获得最小的neightbourNum个系数
	 *
	 * @param sourceArray
	 * @return
	 */
	private int[] createNeiboringArray(double[] sourceArray) {
		int length1 = sourceArray.length;
		int indexArray[] = new int[length1];
		for (int i = 0; i < length1; i++) {
			indexArray[i] = i;
		}
		for (int i = 0; i < length1; i++) {
			for (int j = 0; j < length1 - i - 1; j++) {
				if (sourceArray[j] > sourceArray[j + 1]) {
					double temp = sourceArray[j];
					sourceArray[j] = sourceArray[j + 1];
					sourceArray[j + 1] = temp;

					int ddi = indexArray[j];
					indexArray[j] = indexArray[j + 1];
					indexArray[j + 1] = ddi;
				}
			}
		}
		int[] b = new int[neiboringNum];
		System.arraycopy(indexArray, 0, b, 0, neiboringNum);

		return b;
	}

	/**
	 * 计算权重向量之间的距离
	 *
	 * @param weightVector2
	 *            权重向量2
	 * @param weightVector1
	 *            权重向量1
	 * @return 权重向量之间的欧式距离
	 */
	public double distance_weightVector(ArrayList<Double> weightVector2,
			ArrayList<Double> weightVector1) {
		double d = 0.0;
		for (int i = 0; i < problem.getObjectNum(); i++) {
			d += Math.pow(weightVector1.get(i) - weightVector2.get(i), 2);
		}
		d = Math.sqrt(d);
		return d;
	}

	/**
	 * 进化 1.选择、交叉、变异 2.
	 */
	public void evoluation() {
		for (int genratei = 1; genratei <= maxGeneration; genratei++) {
			System.out.println("第" + genratei + "代");
			for (int j = 0; j < populationSize; j++) {
				// System.out.print("个体：" + j + ":  ");
				Individual individual = populationVector.get(j);
				int[] neiboringTable = individual.getNeiboringTable();
				int p1 = (int) (Math.random() * neiboringNum);
				int p2 = (int) (Math.random() * neiboringNum);
				while (p1 == p2) {
					p2 = (int) (Math.random() * neiboringNum);
				}
				int q1 = neiboringTable[p1];
				int q2 = neiboringTable[p2];
				Individual oldIndividual1 = populationVector.get(q1);
				Individual oldIndividual2 = populationVector.get(q2);
				Individual newchildIndividual = new Individual();
				Operate.crossover(problem, oldIndividual1, oldIndividual2,
						newchildIndividual);
				Operate.realMutation(problem, newchildIndividual);
				problem.calculatorObject(newchildIndividual);
				update_problem(newchildIndividual, q2);
				update_reference(newchildIndividual);
			}
			// matchWeightAndIndividual();
			// printFunctionToFile(genratei);
			// System.out.println(populationVector.get(5).functionToString());
		}
		printFunctionToFile();
	}

	/**
	 * 获取理想点向量
	 *
	 * @return the idealIndividuals 理想点double向量
	 */
	public ArrayList<Double> getIdealIndividuals() {
		return idealIndividuals;
	}

	/**
	 * 种群的初始化-生成populationSize大小的种群
	 *
	 */
	public void init() {
		// 生成populationSize大小的种群
		populationVector = new ArrayList<Individual>();
		if (problem.getObjectNum() == 2) {
			Individual individual = null;
			ArrayList<Double> weightVector = null;
			for (int i = 0; i < populationSize; i++) {
				individual = new Individual();
				weightVector = new ArrayList<Double>();
				weightVector.add((double) (i + 1) / populationSize);
				weightVector.add((double) (populationSize - 1 - i)
						/ populationSize);
				individual.setWeightVector(weightVector);
				populationVector.add(individual);
			}
		} else if (problem.getObjectNum() == 3) {
			int oldPopSize = 23;
			Individual individual = null;
			ArrayList<Double> weightVector = null;
			for (int i = 0; i <= oldPopSize; i++) {
				for (int j = 0; j <= oldPopSize; j++) {
					if (i + j <= oldPopSize) {
						int k = oldPopSize - i - j;
						individual = new Individual();
						weightVector = new ArrayList<Double>();
						double[] weight = new double[3];
						weight[0] = i / (double) oldPopSize;
						weight[1] = j / (double) oldPopSize;
						weight[2] = k / (double) oldPopSize;
						weightVector.add(weight[0]);
						weightVector.add(weight[1]);
						weightVector.add(weight[2]);
						individual.setWeightVector(weightVector);
						populationVector.add(individual);
					}
				}
			}

			populationSize = populationVector.size();
		}
		// /////////////////////输出权重向量文件
		String fileName = "";
		OutPut.printWeightVectorToFile(populationVector, fileName
				+ "_weightVector.txt");
		System.out.println("输出权重向量文件");
		// //////////////初始化自变量及计算函数值
		for (int i = 0; i < populationSize; i++) {
			Individual individual = populationVector.get(i);
			if (problem != null) {// 个体编码的初始化
				problem.initData(individual);
				problem.calculatorObject(individual);
			} else {
				JOptionPane.showMessageDialog(null,
						"请在调用Population.init()之前指定测试问题!");
				return;
			}
		}
		// /////////////////////初始化邻域
		initNeightbouringTable();
		// //////////////////////初始化参考点和理想点
		initReferenceVectorAndIdealVector();
		// /////////////////////// 匹配权重向量和个体
		// matchWeightAndIndividual();
		System.out.println(populationSize);
	}

	/**
	 * 通过读入权重向量进行初始化
	 */
	public void initByRead() {
		String fileSep = System.getProperty("file.separator");
		String fileName;
		fileName = "MOEAD" + fileSep + problem.getName() + fileSep + "MOEAD_"
				+ problem.getName() + "_Variable.txt";
		File file = new File(fileName);
		if (!file.exists()) {
			// JOptionPane.showMessageDialog(null, "没有发现数据" + fileName + "文件");
			int showConfirmDialog = JOptionPane.showConfirmDialog(null,
					"没有发现数据" + fileName + "文件,\n是否重新随机生成个体编码数据？");
			if (showConfirmDialog == 0) {
				init();
				return;
			} else {
				System.exit(0);
			}
		}
		populationVector = new ArrayList<Individual>();
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(
					fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String readLine = "";
			while ((readLine = bufferedReader.readLine()) != null) {
				if (!readLine.isEmpty()) {
					Individual individual = new Individual();
					ArrayList<Double> variableVector = new ArrayList<Double>();
					String[] split = readLine.split("\t");
					for (int j = 0; j < split.length; j++) {
						String string = split[j];
						double parseDouble = Double.parseDouble(string);
						variableVector.add(parseDouble);
					}
					individual.setVariableVector(variableVector);
					problem.calculatorObject(individual);
					populationVector.add(individual);
					System.out.println(readLine);
				}
			}
		} catch (Exception e) {
		}
		populationSize = populationVector.size();
		System.out.println(populationSize);
		fileName = "MOEAD_Match" + fileSep + problem.getName() + fileSep
				+ "MOEAD_Match_" + problem.getName();
		file = new File(fileName + "_weightVector.txt");
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(
					fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String readLine = "";
			int line = 0;
			while ((readLine = bufferedReader.readLine()) != null) {
				if (!readLine.isEmpty()) {
					String[] split = readLine.split("\t");
					ArrayList<Double> weightVector = new ArrayList<Double>();
					for (int i = 0; i < split.length; i++) {
						String string = split[i];
						System.out.print(string + "\t");
						double parseDouble = Double.parseDouble(string);
						weightVector.add(parseDouble);
					}
					System.out.println();
					populationVector.get(line).setWeightVector(weightVector);
					line++;
				}
			}
		} catch (Exception e) {
		}
		initNeightbouringTable();
		initReferenceVectorAndIdealVector();
	}

	/**
	 * 邻居集的初始化
	 */
	public void initNeightbouringTable() {
		for (int i = 0; i < populationSize; i++) {
			double[] distanceArray = new double[populationSize];
			Individual individual = populationVector.get(i);
			ArrayList<Double> weightVector1 = individual.getWeightVector();
			for (int j = 0; j < populationSize; j++) {
				if (i == j) {
					distanceArray[j] = 0;
					continue;
				}
				ArrayList<Double> weightVector2 = populationVector.get(j)
						.getWeightVector();
				distanceArray[j] = distance_weightVector(weightVector2,
						weightVector1);
			}
			int[] createNeiboringArray = createNeiboringArray(distanceArray);
			individual.setNeiboringTable(createNeiboringArray);
			populationVector.set(i, individual);
		}
	}

	/**
	 * 初始化理想点和参考点
	 */
	private void initReferenceVectorAndIdealVector() {
		if (neiboringNum == -1) {
			JOptionPane
					.showMessageDialog(null,
							"请在使用Population.initReferenceVectorAndIdealVector()之前设置邻居集大小!");
			System.err
					.println("Population.initReferenceVectorAndIdealVector() break;");
		}
		idealIndividuals = new ArrayList<Double>();
		referenceVector = new ArrayList<Individual>();
		for (int i = 0; i < problem.getObjectNum(); i++) {
			Individual individual = populationVector.get(0);
			referenceVector.add(individual);
			Double double1 = individual.getFunctionVector().get(i);
			idealIndividuals.add(double1);
		}
	}

	public void matchWeightAndIndividual() {
		System.out.print("matchWeightAndIndividual:");
		populationSize = populationVector.size();
		/*
		 * 将所有的组合放入matchs数组中
		 */
		int matchSize = populationSize * populationSize;
		Match[] matchs = new Match[matchSize];
		int objectNum = problem.getObjectNum();
		for (int i = 0; i < populationSize; i++) {
			Individual individual = populationVector.get(i);
			Individual individual2 = null;
			for (int j = 0; j < populationSize; j++) {
				individual2 = populationVector.get(j);
				ArrayList<Double> weightVector = individual2.getWeightVector();
				int[] neiboringTable = individual2.getNeiboringTable();
				Match match = new Match();
				match.setWeightVector(weightVector);
				match.setIndividual(individual);
				match.setNeiboringTable(neiboringTable);
				match.setObjectNum(objectNum);
				match.setDecomposition(decomposition);
				match.comput(idealIndividuals, referenceVector);
				matchs[i * populationSize + j] = match;
			}
		}
		/*
		 * 查找最好的个体，并将相关项置空
		 */
		System.out.println("查找最好的个体，并将相关项置空:");
		populationVector = new ArrayList<Individual>();// 清空population
		// ////////////////////////////////
		for (int index = 0; index < populationSize; index++) {

			// ///////////// 查找最好的个体
			int minIndex = -1;
			double minValue = Double.MAX_VALUE;
			for (int i = 0; i < matchs.length; i++) {
				Match match = matchs[i];
				if (match != null) {
					double fitness = match.getFitness();
					if (minValue > fitness) {
						minValue = fitness;
						minIndex = i;
					}
				}
			}
			// ////////////////////////////////// 更新 population,增加最好的个体
			ArrayList<Double> weightVector = null;
			Match match = matchs[minIndex];
			Individual oldIndividual = match.getIndividual();
			@SuppressWarnings("unchecked")
			ArrayList<Double> clone = (ArrayList<Double>) match
					.getWeightVector().clone();
			weightVector = clone;
			// individual.setWeightVector(weightVector);
			Individual individual = new Individual();
			@SuppressWarnings("unchecked")
			ArrayList<Double> functionVector = (ArrayList<Double>) oldIndividual
					.getFunctionVector().clone();
			individual.setFunctionVector(functionVector);
			int[] neiboringTable = match.getNeiboringTable().clone();
			individual.setNeiboringTable(neiboringTable);
			individual.setObjectNum(objectNum);
			individual.setVariableNum(oldIndividual.getVariableNum());
			@SuppressWarnings("unchecked")
			ArrayList<Double> variableVector = (ArrayList<Double>) oldIndividual
					.getVariableVector().clone();
			individual.setVariableVector(variableVector);
			individual.setWeightVector(weightVector);

			populationVector.add(individual);
			// System.out.println("populationSize:" + populationVector.size());
			// /////////////////////// 删除相关项,变量向量和权重向量，寻找此方向上最好的点
			// ArrayList<Double> variableVector =
			// individual.getVariableVector();
			for (int i = 0; i < matchs.length; i++) {
				Match match2 = matchs[i];
				if (match2 != null) {// 不为空
					// ArrayList<Double> variableVector2 =
					// match2.getIndividual()
					// .getVariableVector();
					ArrayList<Double> weightVector2 = match2.getWeightVector();
					if (weightVector2.equals(weightVector)) {
						matchs[i] = null;
					}
				}
			}
			// ////////////////////////
		}

		// ////////////////////////////////////
	}

	public void matchWeightAndIndividual2() {
		printFunctionToFile(1, "");
		int objectNum = problem.getObjectNum();
		ArrayList<Individual> pop = new ArrayList<Individual>();
		for (int i = 0; i < populationSize; i++) {
			Individual individual = populationVector.get(i);
			ArrayList<Double> weightVector = individual.getWeightVector();
			int[] neiboringTable = individual.getNeiboringTable();
			Match[] matchs = new Match[populationSize];
			for (int j = 0; j < populationSize; j++) {
				Individual individual2 = populationVector.get(j);
				Match match = new Match();
				match.setDecomposition(decomposition);
				match.setIndividual(individual2);
				match.setNeiboringTable(neiboringTable);
				match.setObjectNum(objectNum);
				match.setWeightVector(weightVector);
				match.comput(idealIndividuals, referenceVector);
				matchs[j] = match;
			}
			System.out.println();
			int minIndex = -1;
			double minValue = Double.MAX_VALUE;
			for (int j = 0; j < matchs.length; j++) {
				double fitness = matchs[j].getFitness();
				if (fitness < minValue) {
					minValue = fitness;
					minIndex = j;
				}
				System.out.printf("%f\t", fitness);
			}
			Individual bestIndividual = new Individual();
			Match match = matchs[minIndex];
			Individual individual3 = match.getIndividual();
			ArrayList<Double> functionVector = individual3.getFunctionVector();
			ArrayList<Double> variableVector = individual3.getVariableVector();
			bestIndividual.setFunctionVector(functionVector);
			bestIndividual.setVariableVector(variableVector);
			bestIndividual.setNeiboringTable(neiboringTable);
			bestIndividual.setObjectNum(objectNum);
			bestIndividual.setWeightVector(weightVector);
			pop.add(bestIndividual);
		}
		populationVector = pop;
		printFunctionToFile(2, "");
	}

	/**
	 * 设置分解方法
	 *
	 * @param decomposition
	 *            分解方法
	 */
	public void setDecomposition(Decomposition decomposition) {
		this.decomposition = decomposition;
	}

	/**
	 * 设置邻居个体大小
	 *
	 * @param neiboringNum
	 *            邻域大小
	 */
	public void setNeightbouringNum(int neiboringNum) {
		Individual.setNeiboringNum(neiboringNum);
		this.neiboringNum = neiboringNum;
	}

	/**
	 * 设置测试问题
	 *
	 * @param problem
	 */
	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	/**
	 * update the best solutions of neighboring subproblems
	 *
	 * @param indiv
	 * @param id
	 */
	public void update_problem(Individual indiv, int id) {
		for (int i = 0; i < Individual.getNeighbouringNum(); i++) {
			int k = populationVector.get(id).getNeiboringTable()[i];
			double f1, f2;
			f1 = decomposition.comput(problem.getObjectNum(), populationVector
					.get(k).getFunctionVector(), populationVector.get(k)
					.getWeightVector(), idealIndividuals, referenceVector);
			f2 = decomposition.comput(problem.getObjectNum(), indiv
					.getFunctionVector(), populationVector.get(k)
					.getWeightVector(), idealIndividuals, referenceVector);
			// System.out.println("Tchebycheff: " + f1 + "   " + f2);
			if (f2 < f1) {
				Individual individual = populationVector.get(k);
				individual.setVariableVector(indiv.getVariableVector());
				individual.setFunctionVector(indiv.getFunctionVector());
				populationVector.set(k, individual);
			}
		}
	}

	/**
	 * 更新参考点
	 *
	 * @param ind
	 *            新加入的点
	 */
	public void update_reference(Individual ind) {
		for (int n = 0; n < problem.getObjectNum(); n++) {
			if (ind.getFunctionVector().get(n) < idealIndividuals.get(n)) {
				idealIndividuals.set(n, ind.getFunctionVector().get(n));
				referenceVector.set(n, ind);
			}
		}
	}

	/**
	 * 升级参考点和理想点
	 */
	public void updateReferenceVectorAndIdealVector() {
		for (int i = 1; i < populationSize; i++) {
			Individual individual = populationVector.get(i);
			ArrayList<Double> functionVector = individual.getFunctionVector();
			for (int j = 0; j < problem.getObjectNum(); j++) {
				Double double1 = functionVector.get(j);
				Double double2 = idealIndividuals.get(j);
				if (double2 > double1) {// 最小化问题
					idealIndividuals.set(j, double1);
					referenceVector.set(j, individual);
				}
			}
		}
	}

	public static void main(String[] args) {
		MOEAD_Match moead_Match = new MOEAD_Match();
		moead_Match.setDecomposition(new PenaltybasedBoundaryIntersection());
		moead_Match.setMaxGeneration(1);
		moead_Match.setPopulationSize(100);
		moead_Match.setProblem(new ZDT1());
		moead_Match.setNeightbouringNum(10);
		moead_Match.init();
		moead_Match.matchWeightAndIndividual2();

	}
}