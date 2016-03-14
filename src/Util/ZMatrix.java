/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.ArrayList;

/**
 * 数学扩展
 * 
 * @author 张作峰
 */
public class ZMatrix {

	/**
	 * 数组到数组列表的转换
	 * 
	 * @param source
	 *            数组
	 * @return 数组列表
	 */
	public static ArrayList<ArrayList<Double>> TDimensionalArrayTrance(
			double[][] source) {
		ArrayList<ArrayList<Double>> object = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < source.length; i++) {
			ArrayList<Double> al = new ArrayList<Double>();
			for (int j = 0; j < source[i].length; j++) {
				double d = source[i][j];
				al.add(d);
			}
			object.add(al);
		}
		return object;
	}

	/**
	 * 数组列表到数组的转换
	 * 
	 * @param source
	 *            二维数组
	 * @return 数组列表
	 */
	public static double[][] TDimensionalArrayTrance(
			ArrayList<ArrayList<Double>> source) {
		int row = source.size();
		double[][] obj = new double[row][];
		for (int i = 0; i < source.size(); i++) {
			ArrayList<Double> arrayList = source.get(i);
			double[] d = new double[arrayList.size()];
			for (int j = 0; j < arrayList.size(); j++) {
				d[j] = arrayList.get(j).doubleValue();
			}
			obj[i] = d;
		}
		return obj;
	}

	/**
	 * 矩阵转置,列数取决于首行
	 * 
	 * @param source
	 *            原矩阵
	 */
	public static double[][] matrixTransposition(double[][] source) {
		int row = source.length;
		int column = source[0].length;
		double[][] reslut = new double[column][row];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				reslut[j][i] = source[i][j];
			}
		}
		return reslut;
	}
}
