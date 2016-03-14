package Util;

import java.util.ArrayList;

import solution.Individual;

public class Distance {

	/**
	 * 欧几里德距离
	 * 
	 * @param individual1
	 *            个体1
	 * @param individual2
	 *            个体2
	 * @return 欧几里德距离
	 */
	public static double EDiastance(Individual individual1,
			Individual individual2) {
		double distance = 0;
		int objectNum = individual1.getObjectNum();
		ArrayList<Double> functionVector = individual1.getFunctionVector();
		ArrayList<Double> functionVector2 = individual2.getFunctionVector();
		for (int i = 0; i < objectNum; i++) {
			Double double2 = functionVector.get(i);
			Double double3 = functionVector2.get(i);
			distance += Math.pow((double2 - double3), 2);
		}
		distance = Math.sqrt(distance);
		return distance;
	}

	/**
	 * 欧几里德距离
	 * 
	 * @param vector1
	 *            向量1
	 * @param vector2
	 *            向量2
	 * @return 欧几里德距离
	 */
	public static double EDiastance(ArrayList<Double> vector1,
			ArrayList<Double> vector2) {
		double distance = 0;
		int objectNum = vector1.size();
		for (int i = 0; i < objectNum; i++) {
			Double double2 = vector1.get(i);
			Double double3 = vector2.get(i);
			distance += Math.pow((double2 - double3), 2);
		}
		distance = Math.sqrt(distance);
		return distance;
	}

	public static double EDiastance(double[] d1, double[] d2) {
		double d = 0;
		for (int i = 0; i < d1.length; i++) {
			d += Math.pow(d1[i] - d2[i], 2);
		}
		return Math.sqrt(d);
	}
}