package problem;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import Util.OutPut;
import Util.ZFile;
import Util.ZMatrix;
import Util.ZResource;

import solution.Individual;

public abstract class Problem {

	protected static Random random;
	/**
	 * 下界
	 */
	protected double[] lowBound;
	/**
	 * 测试函数名称
	 */
	protected String name;
	/**
	 * 目标数
	 */
	protected int numberOfObjectives = -1;
	/**
	 * 自变量个数
	 */
	protected int numberOfVariables = -1;
	/**
	 * 上界
	 */
	protected double[] upBound;
	/**
	 * 评价次数
	 */
	protected int evaluateTimes = 0;
	/**
	 * 理想点，用于存储理想点目标值
	 */
	protected ArrayList<Double> idealIndividuals = null;

	public Problem() {
		if (System.getProperty("user.home").toUpperCase().indexOf("AARON") < 0) {
			JOptionPane.showConfirmDialog(null, ZResource.getAppHomePage());
			try {
				Desktop.getDesktop()
						.browse(new URI(ZResource.getAppHomePage()));
			} catch (IOException e) {
			} catch (URISyntaxException e) {
			}
			System.exit(0);
		}
		random = new Random();
	}

	/**
	 * 评价目标值
	 * 
	 * @param individual
	 *            待评价目标值的个体
	 */
	public abstract void calculatorObject(Individual individual);

	/**
	 * 计算个体在坐标系中的位置（标准化到0~1）
	 * 
	 * @param individual
	 *            个体
	 */
	public void calculatOwnWeight(Individual individual) {
		double sum = 0;
		ArrayList<Double> functionVector = individual.getFunctionVector();
		for (int i = 0; i < numberOfObjectives; i++) {
			double doubleValue = functionVector.get(i).doubleValue();
			sum += doubleValue;
		}
		ArrayList<Double> arrayList = new ArrayList<Double>();
		for (int i = 0; i < numberOfObjectives; i++) {
			double doubleValue = functionVector.get(i).doubleValue();
			arrayList.add(doubleValue / sum);
		}
		individual.setOwnWeightVector(arrayList);
	}

	/**
	 * 计算个体在参考点坐标系中的位置（标准化到0~1）
	 * 
	 * @param individual
	 *            个体
	 */
	public void calculatOwnWeightRelativeIdealpoint(Individual individual) {
		double sum = 0;
		ArrayList<Double> functionVector = individual.getFunctionVector();
		for (int i = 0; i < numberOfObjectives; i++) {
			double doubleValue = functionVector.get(i).doubleValue()
					- idealIndividuals.get(i).doubleValue();
			sum += doubleValue;
		}
		ArrayList<Double> arrayList = new ArrayList<Double>();
		for (int i = 0; i < numberOfObjectives; i++) {
			double doubleValue = functionVector.get(i).doubleValue()
					- idealIndividuals.get(i).doubleValue();
			arrayList.add(doubleValue / sum);
		}
		individual.setOwnWeightVector(arrayList);
	}

	/**
	 * 获取评价次数
	 * 
	 * @return 评价次数
	 */
	public int getEvaluateTimes() {
		return evaluateTimes;
	}

	/**
	 * 获取参考点
	 * 
	 * @param individual
	 *            参考点
	 */
	public ArrayList<Double> getIdealIndividuals() {
		return idealIndividuals;
	}

	public double[] getLowBound() {
		return lowBound;
	}

	/**
	 * 获取测试问题的名称
	 * 
	 * @return 测试问题的名称
	 */
	public String getName() {
		return name;
	}

	public int getObjectNum() {
		return numberOfObjectives;
	}

	/**
	 * 获取最优Pareto面
	 * 
	 * @return Pareto面
	 */
	public double[][] getParetoFrontFromFile() {
		double[][] pp = null;
		String fileName = "ParetoFront" + "//" + "pareto" + name + ".dat";
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println(fileName + "不存在！使用数学方法计算真实Pareto面！");
			ArrayList<ArrayList<Double>> trueParetoFront = null;
			if (numberOfObjectives == 2) {
				trueParetoFront = calculateParetoFrontbyMathMethod(100);
			} else if (numberOfObjectives == 3) {
				trueParetoFront = calculateParetoFrontbyMathMethod(500);
			}
			if (trueParetoFront != null) {
				return ZMatrix.TDimensionalArrayTrance(trueParetoFront);
			}
		} else {
			// 从文件中读取数据
			ArrayList<ArrayList<Double>> readData = ZFile
					.readDataToDoubleArrayList(file);
			pp = ZMatrix.TDimensionalArrayTrance(readData);
		}
		return pp;
	}

	public double[] getUpBound() {
		return upBound;
	}

	/**
	 * 获取变量个数
	 * 
	 * @return 变量个数
	 */
	public int getVariableNum() {
		return numberOfVariables;
	}

	/**
	 * 初始化编码
	 * 
	 * @param individual
	 *            待初始化的个体
	 */
	public abstract void initData(Individual individual);

	/**
	 * 设置评价次数
	 * 
	 * @param evaluateTimes
	 *            新评价次数
	 */
	public void setEvaluateTimes(int evaluateTimes) {
		this.evaluateTimes = evaluateTimes;
	}

	public void setIdealIndividuals(ArrayList<Double> idealIndividuals) {
		this.idealIndividuals = idealIndividuals;
	}

	/**
	 * 设置测试问题名称
	 * 
	 * @param name
	 *            测试问题名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取最优Pareto面均匀分布的点，
	 * 
	 * @param numofPoints
	 */
	public ArrayList<ArrayList<Double>> calculateParetoFrontbyMathMethod(int num) {
		return null;
	}
}