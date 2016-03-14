package algorithm.moead;

import Util.OutPut;
import algorithm.Algorithm;
import decomposition.Tchebycheff;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import operate.Operate;
import problem.ZDT.ZDT1;
import solution.Individual;

/**
 * <code>MultiObjective evolution algorithm based on decomposition<br>
 * author Qinfu Zhang
 * </code>
 * 
 * @author 张作峰
 * 
 */
public class MOEAD1 extends Algorithm {

	public MOEAD1() {
		algorithmName = "MOEAD";
	}

	public static void main(String[] args) {
		MOEAD1 moead1 = new MOEAD1();
		moead1.setH(99);
		moead1.setNeiboringNum(30);
		moead1.setProblem(new ZDT1());
		moead1.setDecomposition(new Tchebycheff());
		moead1.setmaxGenerationTimes(2000);
		moead1.init();
		moead1.evoluation();
	}

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
	@Override
	public void evoluation() {
		for (currentGeneriticTime = 1; currentGeneriticTime <= maxGeneration; currentGeneriticTime++) {
			evoluationOneTime();
		}
		// printFunctionToFile();
	}

	@Override
	public void evoluationOneTime() {
		// System.out.println("第" + currentGeneriticTime + "代");
		oldPopulation.clear();
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
			problem.calculatOwnWeight(newchildIndividual);

			update_problem(newchildIndividual, q2);
			update_reference(newchildIndividual);
		}
		currentGeneriticTime++;
		// printToFile(genratei);
		// System.out.println(populationVector.get(5).functionToString());
	}

	/**
	 * 种群的初始化-生成populationSize大小的种群
	 * 
	 */
	@Override
	public void init() {
		problem.setEvaluateTimes(0);
		// 生成populationSize大小的种群
		populationVector = new ArrayList<Individual>();
		oldPopulation = new ArrayList<Individual>();
		if (problem.getObjectNum() == 2) {
			Individual individual = null;
			ArrayList<Double> weightVector = null;
			for (int i = 0; i <= H; i++) {
				individual = new Individual();
				weightVector = new ArrayList<Double>();
				weightVector.add((double) (i) / H);
				weightVector.add((double) (H - i) / H);
				individual.setWeightVector(weightVector);
				populationVector.add(individual);
			}
		} else if (problem.getObjectNum() == 3) {
			Individual individual = null;
			ArrayList<Double> weightVector = null;
			for (int i = 0; i <= H; i++) {
				for (int j = 0; j <= H; j++) {
					if (i + j <= H) {
						int k = H - i - j;
						individual = new Individual();
						weightVector = new ArrayList<Double>();
						double[] weight = new double[3];
						weight[0] = i / (double) H;
						weight[1] = j / (double) H;
						weight[2] = k / (double) H;
						weightVector.add(weight[0]);
						weightVector.add(weight[1]);
						weightVector.add(weight[2]);
						individual.setWeightVector(weightVector);
						populationVector.add(individual);
					}
				}
			}
		}
		// // 输出权重向量文件
		// String fileSep = System.getProperty("file.separator");
		// String fileName = "result" + fileSep;
		// fileName += "MOEAD" + fileSep + problem.getName() + fileSep;
		// OutPut.printWeightVectorToFile(populationVector, fileName
		// + "weightVector.txt");
		// System.out.println("输出权重向量文件");

		populationSize = populationVector.size();

		for (int i = 0; i < populationSize; i++) {
			Individual individual = populationVector.get(i);
			if (problem != null) {// 个体编码的初始化
				problem.initData(individual);
				problem.calculatorObject(individual);
				// problem.calculatOwnWeight(individual);
			} else {
				JOptionPane.showMessageDialog(null,
						"请在调用Population.init()之前指定测试问题!");
				return;
			}
		}
		// printToFile(-1);
		initNeightbouringTable();
		initReferenceVectorAndIdealVector();
		updateReferenceVectorAndIdealVector();
		// System.out.println(populationSize);
	}

	/**
	 * 通过读入权重向量进行初始化
	 */
	public void initByRead() {
		String fileSep = System.getProperty("file.separator");
		String fileName = "result" + fileSep;
		fileName += "MOEAD" + fileSep + problem.getName() + fileSep
				+ "weightVector.txt";
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
		populationVector.clear();
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
		fileName = "MOEAD" + fileSep + problem.getName() + fileSep + "MOEAD_"
				+ problem.getName();
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

	public void print() {
		for (int i = 0; i < populationVector.size(); i++) {
			Individual individual = populationVector.get(i);
			System.out.println("个体 " + i);
			String variableToString = individual.variableToString();
			System.out.println("变量\n" + variableToString);
			String functionToString = individual.functionToString();
			System.out.println("目标值\n" + functionToString);
			String neiboringToString = individual.neiboringToString();
			System.out.println("邻居\n" + neiboringToString);
		}
	}

	/**
	 * 设置理想点
	 * 
	 * @param idealIndividuals
	 *            理想点向量 the idealIndividuals to set
	 */
	public void setIdealIndividuals(ArrayList<Double> idealIndividuals) {
		this.idealIndividuals = idealIndividuals;
	}

	/**
	 * 设置参考点
	 * 
	 * @param referenceVector
	 *            参考点
	 */
	public void setReferenceVector(ArrayList<Individual> referenceVector) {
		this.referenceVector = referenceVector;
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
				try {
					Individual oldIndividual = individual.clone();
					oldPopulation.add(oldIndividual);
				} catch (CloneNotSupportedException ex) {
				}
				individual.setVariableVector(indiv.getVariableVector());
				individual.setFunctionVector(indiv.getFunctionVector());
				individual.setOwnWeightVector(indiv.getOwnWeightVector());
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

	/**
	 * 输出指定的权重向量和适合的权重向量
	 */
	public void outputWeight(int ii) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < populationSize; i++) {
			String string = "";
			Individual individual = populationVector.get(i);
			ArrayList<Double> weightVector = individual.getWeightVector();
			ArrayList<Double> ownWeightVector = individual.getOwnWeightVector();
			for (int j = 0; j < problem.getObjectNum(); j++) {
				double doubleValue = weightVector.get(j).doubleValue();
				string += doubleValue + "\t";
			}
			for (int j = 0; j < problem.getObjectNum(); j++) {
				double doubleValue = ownWeightVector.get(j).doubleValue();
				string += doubleValue + "\t";
			}
			arrayList.add(string);
		}
		String filename = "weight\\" + decomposition.getName() + "\\"
				+ problem.getName() + "\\" + (ii + 1) + ".txt";
		System.out.println(filename);
		OutPut.wirteToFile(arrayList, filename);
	}

}