package decomposition;

import java.util.ArrayList;

import solution.Individual;

public abstract class Decomposition {
	/**
	 * 分解方法名称
	 */
	protected String name = null;

	/**
	 * 计算分解方法值
	 *
	 * @param objectNum
	 *            目标个数
	 * @param functionVector
	 *            目标值向量
	 * @param weightVector
	 *            权重向量
	 * @param idealIndividuals
	 *            理想点
	 * @return 分解方法值
	 */
	public abstract double comput(int objectNum,
			ArrayList<Double> functionVector, ArrayList<Double> weightVector,
			ArrayList<Double> idealIndividuals,
			ArrayList<Individual> referenceVector);

	/**
	 * 获取分解方法名称
	 *
	 * @return 分解方法名称
	 */
	public String getName() {
		return name;
	}
}