package algorithm.myAlgorithm;

import java.util.ArrayList;

import operate.Operate;

import problem.ZDT.ZDT1;
import solution.Individual;
import algorithm.Algorithm;

public class MyAlgorithm extends Algorithm {
	public MyAlgorithm() {
		algorithmName = "MyAlgorithm";
	}

	/**
	 * 理想点，用于存储理想点目标值
	 */
	ArrayList<Double> idealIndividuals = null;

	public void evoluation() {
		// ////初始化
		problem = new ZDT1();
		populationSize = 100;
		populationVector = new ArrayList<Individual>();
		for (int i = 0; i < populationSize; i++) {
			Individual individual = new Individual();
			problem.initData(individual);
			problem.calculatorObject(individual);
			populationVector.add(individual);
		}
		for (int generation = 0; generation < 1500; generation++) {
			System.out.println("第" + generation + "代");
			System.out.println(populationVector.size());
			// 选择.
			ArrayList<Individual> al = new ArrayList<Individual>();

			for (int i = 0; i < problem.getObjectNum(); i++) {
				ArrayList<Individual> sssvbo = sortScalarValueByObject(
						populationVector, i, populationSize / 4);
				for (Individual individual : sssvbo) {
					if (!al.contains(individual)) {
						al.add(individual);
					}
				}
			}
			populationVector = al;
			System.out.println(populationVector.size());
			// /交叉
			for (; populationVector.size() < populationSize;) {
				int p = (int) (Math.random() * populationVector.size());
				int q = (int) (Math.random() * populationVector.size());
				while (p == q) {
					q = (int) (Math.random() * populationVector.size());
				}
				// System.out.println(i + "");
				Individual parent1 = populationVector.get(p);
				Individual parent2 = populationVector.get(q);
				Individual child1 = new Individual();
				Individual child2 = new Individual();
				Operate.realBinaryCrossover(problem, parent1, parent2, child1,
						child2);
				Operate.realMutation(problem, child2);
				problem.calculatorObject(child1);
				problem.calculatorObject(child2);
				populationVector.add(child1);
				populationVector.add(child2);
			}
		}
		// ////////变异
		printFunctionToFile();
		System.out.println("完成。");
	}

	public static void main(String[] args) {
		MyAlgorithm myAlgorithm = new MyAlgorithm();
		myAlgorithm.evoluation();
	}

	/**
	 * 按照目标m从小到大排序，并获取前f项
	 *
	 * @param p
	 * @param m
	 * @param f
	 * @return
	 */
	private ArrayList<Individual> sortScalarValueByObject(
			ArrayList<Individual> p, int m, int f) {
		ArrayList<Individual> newpArrayList = new ArrayList<Individual>();
		int size = p.size();// 解集的大小
		for (int i = 0; i < size - 1; i++) {
			double double1 = p.get(i).getFunctionVector().get(m).doubleValue();
			for (int j = i + 1; j < size; j++) {
				double double2 = p.get(j).getFunctionVector().get(m)
						.doubleValue();
				if (double1 > double2) {// 执行交换
					Individual individual1 = p.get(i);
					Individual individual2 = p.get(j);
					p.set(i, individual2);
					p.set(j, individual1);
					double1 = double2;
				}
			}
		}
		for (int i = 0; i < f; i++) {
			Individual individual = p.get(i);
			newpArrayList.add(individual);
		}
		return newpArrayList;
	}
}