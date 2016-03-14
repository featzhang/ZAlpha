package algorithm.moead;

import java.util.ArrayList;

import operate.Operate;
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
import Util.Distance;
import algorithm.Algorithm;
import decomposition.Decomposition;
import decomposition.NormalizedTchebycheff;
import decomposition.PenaltybasedBoundaryIntersection;
import decomposition.Tchebycheff;
import decomposition.WeightedSum;
import decomposition.myDecomposition;

/**
 * 基于匹配的MOEAD改进
 *
 * @author 张作峰
 *
 */
public class MOEADpro extends Algorithm {

	/**
	 * 每个权重向量上的个体序号
	 */
	private ArrayList<ArrayList<Integer>> individualsInWeightCounter = null;
	/**
	 * 权重向量列表
	 */
	private ArrayList<ArrayList<Double>> allWeights = null;
	public MOEADpro() {
		algorithmName = "MOEAD-OSD";
	}

	/**
	 * 向权重列表的nearestIndex增加个体</br> 每添加一个个体，删除一个个体</br>
	 * （如果该方向上已经有个体，删除差的个体。如果该方向上面没有个体，保留该个体，并从个体最多的方向上删除最差的个体）
	 *
	 * @param newIndividualGivedindex
	 *            已指定的新个体在Population中的序号
	 * @param nearestIndex
	 *            新个体最近的方向序号
	 * @param newInd
	 *            新个体
	 * @param cheackable
	 *            是否需要检查已存在的个体
	 */
	private void addIndividualIntoWeight(int newIndividualGivedindex,
			int nearestIndex, Individual newInd, boolean cheackable) {
		if (!cheackable) {// 不检查已存在的个体直接添加个体
			ArrayList<Integer> a = individualsInWeightCounter.get(nearestIndex);// nearestIndex方向上所有的个体
			a.add(newIndividualGivedindex);
		} else {
			ArrayList<Integer> al = individualsInWeightCounter
					.get(nearestIndex);// nearestIndex方向上个体列表
			if (al.isEmpty()) {
				// 如果nearestIndex上没有个体,加入新个体并查找个体最多的方向，删除最差的个体
				int maxNumWeightIndex = findMaxNumWeightIndex();
				int insteadIndex = findWorstIndividualOfWeight(maxNumWeightIndex);// 查找个体最多的方向上最差的个体
				int intValue = individualsInWeightCounter
						.get(maxNumWeightIndex).get(insteadIndex).intValue();// 最差个体在pop中的序号
				Individual oldInd = populationVector.get(intValue);// 最差个体
				addIndividualToOldPopulation(oldInd);
				// 从weightCounter中删除旧的，添加新的
				deleteWeight(maxNumWeightIndex, insteadIndex);
				individualsInWeightCounter.get(nearestIndex).add(intValue);
				// 为oldInd赋新值
				oldInd.setVariableVector(newInd.getVariableVector());
				oldInd.setFunctionVector(newInd.getFunctionVector());
				// oldInd.setNeiboringTable(neiboringList[nearestIndex]);
				oldInd.setWeightVector(allWeights.get(nearestIndex));
				oldInd.setOwnWeightVector(newInd.getOwnWeightVector());
			} else {
				// 使用好的个体取代差的个体
				int worstIndinWeight = findWorstIndividualOfWeight(nearestIndex);// 查找最近方向上最差个体
				int intValue = individualsInWeightCounter.get(nearestIndex)
						.get(worstIndinWeight).intValue();// 最差个体在pop中的序号
				Individual oldInd = populationVector.get(intValue);// 最差个体
				ArrayList<Double> weightVector = new ArrayList<Double>();
				// if (false) {//全部采用同样的方向
				// if (problem.getObjectNum() == 2) {
				// weightVector.add(0.5);
				// weightVector.add(0.5);
				// } else if (problem.getObjectNum() == 3) {
				// weightVector.add(1.0 / 3);
				// weightVector.add(1.0 / 3);
				// weightVector.add(1.0 / 3);
				// }
				// }
				weightVector = oldInd.getWeightVector();
				double fitness1 = 1 / decomposition.comput(
						problem.getObjectNum(), oldInd.getFunctionVector(),
						weightVector, idealIndividuals, referenceVector);
				double fitness2 = 1 / decomposition.comput(
						problem.getObjectNum(), newInd.getFunctionVector(),
						weightVector, idealIndividuals, referenceVector);
				if (fitness1 < fitness2) {
					addIndividualToOldPopulation(oldInd);
					// 为oldInd赋新值
					oldInd.setVariableVector(newInd.getVariableVector());
					oldInd.setFunctionVector(newInd.getFunctionVector());
					// oldInd.setFitness(fitness2);
					oldInd.setOwnWeightVector(newInd.getOwnWeightVector());
				} else if (fitness1 == fitness2) {
					fitness1 = 1 / decomposition.comput(problem.getObjectNum(),
							oldInd.getFunctionVector(),
							transformWeight(weightVector), idealIndividuals,
							referenceVector);
					fitness2 = 1 / decomposition.comput(problem.getObjectNum(),
							newInd.getFunctionVector(),
							transformWeight(weightVector), idealIndividuals,
							referenceVector);
					if (fitness1 < fitness2) {
						addIndividualToOldPopulation(oldInd);
						// 为oldInd赋新值
						System.out.print(oldInd.weightVectorToString());
						System.out.print("个体" + oldInd.functionToString());
						oldInd.setVariableVector(newInd.getVariableVector());
						oldInd.setFunctionVector(newInd.getFunctionVector());
						// oldInd.setFitness(fitness2);
						oldInd.setOwnWeightVector(newInd.getOwnWeightVector());
						System.out.println("被" + oldInd.functionToString()
								+ "取代");
					}
				} else {
					// addIndividualToOldPopulation(newInd);
				}
			}
		}
	}

	/**
	 * 从individualsInWeightCounter中删除index
	 *
	 * @param rowIndex
	 *            需要删除的列序号（在weightCounter中的列序号）
	 * @param columnIndex
	 *            需要删除的行序号（在weightCounter中的行序号）
	 */
	private void deleteWeight(int rowIndex, int columnIndex) {
		ArrayList<Integer> arrayList = individualsInWeightCounter.get(rowIndex);
		arrayList.remove(columnIndex);
	}

	@Override
	public void evoluation() {
		currentGeneriticTime = 0;
		for (; currentGeneriticTime < maxGeneration;) {
			evoluationOneTime();
		}
		printFunctionToFile();
		// printOwnWeightVectorToFile();
		// printValueToFile();
		// printWeightVectorToFile();
	}

	@Override
	public void evoluationOneTime() {
		System.out.println("第" + (currentGeneriticTime + 1) + "代");
		if (oldPopulation != null) {
			oldPopulation.clear();
		}
		currentGeneriticTime++;
		for (int i = 0; i < populationSize; i++) {
			// ArrayList<Double> weightVector = parent1.getWeightVector();
			// int findNearestWeight = findNearestWeight(weightVector);//
			// 找到个体所在的方向
			// int[] js = neiboringList[findNearestWeight];// 邻域
			// while (pp != i) {
			// int bi = (int) (Math.random() * neiboringNum);// 随机选择邻域中的序号
			// int j = js[bi];// 随机选择邻域内的方向
			// ArrayList<Integer> arrayList = individualsInWeightCounter.get(j);
			// if (!arrayList.isEmpty()) {
			// int bii = (int) (Math.random() * arrayList.size());// 随机选择方向内的个体
			// pp = arrayList.get(bii).intValue();
			// }
			// }
			int p = (int) (Math.random() * populationSize);
			int q = (int) (Math.random() * populationSize);
			while (p == q) {
				q = (int) (Math.random() * populationSize);
			}
			Individual parent1 = populationVector.get(p);
			Individual parent2 = populationVector.get(q);
			Individual child1 = new Individual();
			Operate.crossover(problem, parent1, parent2, child1);
			Operate.realMutation(problem, child1);
			problem.calculatorObject(child1);
			calculatOwnWeight(child1);
			ArrayList<Double> ownWeightVector = child1.getOwnWeightVector();
			int nearestWeightIndex = findNearestWeight(ownWeightVector);// 查找最近的进化方向
			child1.setWeightVector(allWeights.get(nearestWeightIndex));
			// child1.setNeiboringTable(neiboringList[nearestWeightIndex]);
			// update_reference(child1);
			// 取代
			addIndividualIntoWeight(-1, nearestWeightIndex, child1, true);
		}
	}

	/**
	 * 查找个体最多的方向
	 *
	 * @return
	 */
	private int findMaxNumWeightIndex() {
		int maxNum = individualsInWeightCounter.get(0).size();
		int maxIndex = 0;
		for (int i = 1; i < populationSize; i++) {
			int size = individualsInWeightCounter.get(i).size();
			if (size > maxNum) {
				maxNum = size;
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	/**
	 * 根据与权重列表的距离查找最近的权重序号
	 *
	 * @param ownWeightVector
	 *            权重
	 * @return 权重序号
	 */
	private int findNearestWeight(ArrayList<Double> ownWeightVector) {
		int index = 0;
		ArrayList<Double> weightArrayList = null;
		if (decomposition instanceof WeightedSum) {
			weightArrayList = transformWeight(ownWeightVector);
		} else if (decomposition instanceof Tchebycheff) {
			weightArrayList = transformWeight(ownWeightVector);
		} else if (decomposition instanceof decomposition.PenaltybasedBoundaryIntersection) {
			weightArrayList = ownWeightVector;
		} else if (decomposition instanceof NormalizedTchebycheff) {
			weightArrayList = transformWeight(ownWeightVector);
		}
		double min = Distance.EDiastance(weightArrayList, allWeights.get(0));
		for (int i = 1; i < allWeights.size(); i++) {
			double aa = Distance.EDiastance(allWeights.get(i), weightArrayList);
			if (aa < min) {
				min = aa;
				index = i;
			}
		}
		return index;
	}

	/**
	 * 查找index方向上最差的个体在index方向上的序号
	 *
	 * @param weightIndex
	 *            需要查找的方向
	 * @return maxNumWeightIndex方向上最差的个体序号
	 */
	private int findWorstIndividualOfWeight(int weightIndex) {
		ArrayList<Integer> arrayList = individualsInWeightCounter
				.get(weightIndex);
		Individual individual = populationVector.get(arrayList.get(0)
				.intValue());
		double minfitness = 1 / decomposition.comput(problem.getObjectNum(),
				individual.getFunctionVector(), allWeights.get(weightIndex),
				idealIndividuals, referenceVector);
		int minIndex = 0;
		for (int i = 1; i < arrayList.size(); i++) {
			Individual individual1 = populationVector.get(arrayList.get(i)
					.intValue());
			double fitness = 1 / decomposition.comput(problem.getObjectNum(),
					individual1.getFunctionVector(),
					allWeights.get(weightIndex), idealIndividuals,
					referenceVector);
			if (fitness < minfitness) {
				minfitness = fitness;
				minIndex = i;
			}
		}
		return minIndex;
	}

	@Override
	public void init() {
		// 初始化权重向量
		allWeights = new ArrayList<ArrayList<Double>>();
		idealIndividuals = problem.getIdealIndividuals();
		if (problem.getObjectNum() == 2) {
			for (double i = 0; i <= H; i++) {
				ArrayList<Double> weightList = new ArrayList<Double>();
				weightList.add(i / H);
				weightList.add(1 - i / H);
				allWeights.add(weightList);
			}
		} else if (problem.getObjectNum() == 3) {
			for (double i = 0; i <= H; i++) {
				for (double j = 0; j <= H; j++) {
					if (i + j <= H) {
						ArrayList<Double> weightList = new ArrayList<Double>();
						weightList.add(i / H);
						weightList.add(j / H);
						weightList.add(1 - (i + j) / H);
						allWeights.add(weightList);
					}
				}
			}
		}
		populationSize = allWeights.size();
		// 初始化权重向量个体列表
		individualsInWeightCounter = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < populationSize; i++) {
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			individualsInWeightCounter.add(arrayList);
		}
		// // 初始化临域列表
		// neiboringList = new int[populationSize][neiboringNum];
		// for (int i = 0; i < populationSize; i++) {
		// ArrayList<Double> weight1 = allWeights.get(i);
		// double[] distance = new double[populationSize];
		// for (int j = 0; j < allWeights.size(); j++) {
		// if (i == j) {
		// distance[j] = 0;
		// continue;
		// }
		// ArrayList<Double> weight2 = allWeights.get(j);
		// double d = Distance.EDiastance(weight1, weight2);
		// distance[j] = d;
		// }
		// // 对distance排序，并获得前neiboringNum个个体
		// int[] index = new int[populationSize];
		// for (int j = 0; j < index.length; j++) {
		// index[j] = j;
		// }
		// // 对ditance按照由小到大排序
		// for (int j = 0; j < index.length - 1; j++) {
		// for (int k = j + 1; k < index.length; k++) {
		// if (distance[j] > distance[k]) {// 交换
		// double dd = distance[j];
		// distance[j] = distance[k];
		// distance[k] = dd;
		// int ii = index[j];
		// index[j] = index[k];
		// index[k] = ii;
		// }
		// }
		// }
		// neiboringList[i] = Arrays.copyOf(index, neiboringNum);
		// }
		// init population
		populationVector = new ArrayList<Individual>();
		for (int i = 0; i < populationSize; i++) {
			Individual individual = new Individual();
			problem.initData(individual);
			problem.calculatorObject(individual);
			calculatOwnWeight(individual);
			populationVector.add(individual);
		}
		// 按照个体的适合权重给每个个体赋指定权重向量
		for (int i = 0; i < populationSize; i++) {
			Individual individual = populationVector.get(i);
			ArrayList<Double> ownWeightVector = individual.getOwnWeightVector();
			int nearestIndex = findNearestWeight(ownWeightVector);
			// individual.setNeiboringTable(neiboringList[nearestIndex]);
			individual.setWeightVector(allWeights.get(nearestIndex));
			addIndividualIntoWeight(i, nearestIndex, individual, false);
		}
		// initReferenceVectorAndIdealVector();
		// updateReferenceVectorAndIdealVector();
		oldPopulation = new ArrayList<Individual>();
	}

	private void calculatOwnWeight(Individual individual) {
		double sum = 0;
		ArrayList<Double> functionVector = individual.getFunctionVector();
		int objectNum = problem.getObjectNum();
		ArrayList<Double> idealIndividuals2 = problem.getIdealIndividuals();
		for (int i = 0; i < objectNum; i++) {
			double doubleValue = functionVector.get(i).doubleValue()
					- idealIndividuals2.get(i).doubleValue();
			sum += doubleValue;
		}
		ArrayList<Double> arrayList = new ArrayList<Double>();
		for (int i = 0; i < objectNum; i++) {
			double doubleValue = functionVector.get(i).doubleValue()
					- idealIndividuals2.get(i).doubleValue();
			arrayList.add(doubleValue / sum);
		}
		individual.setOwnWeightVector(arrayList);
	}

	// /**
	// * 初始化理想点和参考点
	// */
	// private void initReferenceVectorAndIdealVector() {
	// if (neiboringNum == -1) {
	// JOptionPane
	// .showMessageDialog(null,
	// "请在使用Population.initReferenceVectorAndIdealVector()之前设置邻居集大小!");
	// System.err
	// .println("Population.initReferenceVectorAndIdealVector() break;");
	// }
	// idealIndividuals = new ArrayList<Double>();
	// referenceVector = new ArrayList<Individual>();
	// for (int i = 0; i < problem.getObjectNum(); i++) {
	// Individual individual = populationVector.get(0);
	// referenceVector.add(individual);
	// double double1 = individual.getFunctionVector().get(i)
	// .doubleValue();
	// idealIndividuals.add(double1);
	// }
	// }

	/**
	 * 转换权重向量
	 *
	 * @param oldWeight
	 *            原权重向量
	 * @return 新权重向量
	 */
	private ArrayList<Double> transformWeight(ArrayList<Double> oldWeight) {
		ArrayList<Double> weight = null;
		weight = new ArrayList<Double>();
		if (oldWeight.size() != 2) {
			weight.add(oldWeight.get(2));
			weight.add(oldWeight.get(1));
			weight.add(oldWeight.get(0));
		} else {
			weight.add(oldWeight.get(1));
			weight.add(oldWeight.get(0));
		}
		return weight;
	}

	// /**
	// * 更新参考点
	// *
	// * @param ind
	// * 新加入的点
	// */
	// public void update_reference(Individual ind) {
	// for (int n = 0; n < problem.getObjectNum(); n++) {
	// if (ind.getFunctionVector().get(n) < idealIndividuals.get(n)) {
	// idealIndividuals.set(n, ind.getFunctionVector().get(n));
	// referenceVector.set(n, ind);
	// }
	// }
	// }

	// /**
	// * 升级参考点和理想点
	// */
	// public void updateReferenceVectorAndIdealVector() {
	// for (int i = 1; i < populationSize; i++) {
	// Individual individual = populationVector.get(i);
	// ArrayList<Double> functionVector = individual.getFunctionVector();
	// for (int j = 0; j < problem.getObjectNum(); j++) {
	// Double double1 = functionVector.get(j);
	// Double double2 = idealIndividuals.get(j);
	// if (double2 > double1) {// 最小化问题
	// idealIndividuals.set(j, double1);
	// referenceVector.set(j, individual);
	// }
	// }
	// }
	// }

	public static void main(String[] args) {
		int j = 1;
		while (j < 4) {
			int i = 0;
			while (i < 11) {
				MOEADpro moead = new MOEADpro();
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
				switch (i) {
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
				moead.setMaxGeneration(2000);
				if (problem.getObjectNum() == 2) {
					moead.setH(299);
				} else {
					moead.setH(23);
				}
				moead.setNeiboringNum(300);
				moead.setDecomposition(decomposition);
				moead.setProblem(problem);
				System.out.println(decomposition.getName());
				System.out.println(problem.getName());
				moead.init();
				// moead.initByRead();
				moead.evoluation();
				i++;
			}
			j++;
		}
	}
}