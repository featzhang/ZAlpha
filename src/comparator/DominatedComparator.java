package comparator;

import java.util.ArrayList;
import java.util.Comparator;

import solution.Individual;

/**
 * 支配关系比较器，个体1支配个体2返回1，否则返回0；
 *
 * @author 张作峰
 *
 */
public class DominatedComparator implements Comparator<Individual> {
	/**
	 * 相等
	 */
	public static final int EQUAL = 0;

	/**
	 * 个体1支配个体2
	 */
	public static final int Domination = 1;
	/**
	 * 个体1被个体2支配
	 */
	public static final int Dominated = -1;

	/**
	 * 互补支配
	 */
	public static final int DisDominationRelation = -2;
	/**
	 * 目标个数
	 */
	private int objectiveNum = 2;

	public DominatedComparator(int objectiveNum) {
		this.objectiveNum = objectiveNum;
	}

	/*
	 * 判断个体间的支配关系
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Individual individual1, Individual individual2) {
		// 个体1支配个体2返回1，否则返回0；
		ArrayList<Double> functionVector1 = individual1.getFunctionVector();
		ArrayList<Double> functionVector2 = individual2.getFunctionVector();
		// System.out.println(functionVector1);
		// System.out.println(functionVector2);
		if (functionVector1.size() <= 0 || functionVector1.size() <= 0) {
			System.err
					.println("functionVector1.size() <= 0||functionVector1.size() <= 0\n请确认是否已经评价！");
		}

		// //////////判断相互支配关系
		boolean bigable = false;
		boolean smallable = false;
		boolean equation = false;
		for (int i = 0; i < objectiveNum; i++) {
			double double1 = functionVector1.get(i).doubleValue();
			double double2 = functionVector2.get(i).doubleValue();
			if (double1 > double2) {
				bigable = true;
			} else if (double1 < double2) {
				smallable = true;
			} else if (double1 == double2) {
				equation = true;
			}
		}
		if (equation && !bigable && !smallable) {
			return EQUAL;
		} else if (smallable && !bigable) {
			return Domination;
		} else if (bigable && !smallable) {
			return Dominated;
		} else if (bigable && smallable) {
			return DisDominationRelation;
		}
		return DisDominationRelation;
	}
}