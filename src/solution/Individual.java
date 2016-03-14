package solution;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import Util.ZResource;

/**
 * 个体类 (变量编码，目标函数值，邻域表，权重向量)
 * 
 * @author Aaron
 */
public class Individual implements Cloneable {

	/**
	 * 目标个数
	 */
	private static int neiboringNum = -1;

	public static int getNeighbouringNum() {
		return neiboringNum;
	}

	public static void setNeiboringNum(int neiboringNum1) {
		neiboringNum = neiboringNum1;
	}

	/**
	 * 聚集距离，用于NSGAII
	 */
	private double crowdingDistance = -1;
	/**
	 * 适应度
	 */
	private double fitness;
	/**
	 * 目标函数值向量
	 */
	private ArrayList<Double> functionVector;
	private int[] neiboringTable = null;
	/**
	 * 目标个数
	 */
	private int objectNum;
	private ArrayList<Double> ownWeightVector = null;
	/**
	 * 非支配排序
	 */
	private int rank = -1;
	/**
	 * 变量个数
	 */
	private int variableNum;
	/**
	 * 变量向量
	 */
	private ArrayList<Double> variableVector = null;

	/**
	 * 权重向量
	 */
	private ArrayList<Double> weightVector = null;

	public Individual() {
		// System.out.println(new GregorianCalendar().getTimeInMillis());
//		if (new GregorianCalendar().getTimeInMillis() >= 1326512000000l) {
//			JOptionPane
//					.showConfirmDialog(
//							null,
//							"\u514d\u8d39\u671f\u9650\u5df2\u5230\uff0c\u8bf7\u8054\u7cfb\u8d2d\u4e70\uff01");
//			try {
//				Desktop.getDesktop()
//						.browse(new URI(ZResource.getAppHomePage()));
//			} catch (IOException e) {
//			} catch (URISyntaxException e) {
//			}
//			System.exit(0);
//		}
		variableVector = new ArrayList<Double>();
		functionVector = new ArrayList<Double>();
		weightVector = new ArrayList<Double>();
	}

	@Override
	public Individual clone() throws CloneNotSupportedException {
		return (Individual) super.clone();
	}

	/**
	 * 产生目标函数值字符串
	 * 
	 * @return 目标函数值字符串
	 */
	public String functionToString() {
		StringBuilder stringBuffer = new StringBuilder();
		int size = functionVector.size();
		for (int i = 0; i < size - 1; i++) {
			double d = functionVector.get(i);
			stringBuffer.append(d).append("\t");
		}
		if (size > 0) {
			stringBuffer.append(functionVector.get(size - 1));
		}
		return stringBuffer.toString();
	}

	public double getCrowdingDistance() {
		return crowdingDistance;
	}

	public double getFitness() {
		return fitness;
	}

	/**
	 * 获取函数值向量
	 * 
	 * @return 函数值向量
	 */
	public ArrayList<Double> getFunctionVector() {
		return functionVector;
	}

	/**
	 * 获取邻域表数组
	 * 
	 * @return 邻域表数组
	 */
	public int[] getNeiboringTable() {
		return neiboringTable;
	}

	public double getObjective(int nObj) {
		return functionVector.get(nObj);
	}

	/**
	 * 返回目标个数
	 * 
	 * @return 目标个数
	 */
	public int getObjectNum() {
		return objectNum;
	}

	public ArrayList<Double> getOwnWeightVector() {
		return ownWeightVector;
	}

	public int getRank() {
		return rank;
	}

	/**
	 * 获取自变量数目
	 * 
	 * @return 自变量数目
	 */
	public int getVariableNum() {
		return variableNum;
	}

	/**
	 * 获取自变量向量
	 * 
	 * @return 自变量向量
	 */
	public ArrayList<Double> getVariableVector() {
		return variableVector;
	}

	/**
	 * 获取权重向量
	 * 
	 * @return 权重向量
	 */
	public ArrayList<Double> getWeightVector() {
		return weightVector;
	}

	/**
	 * 返回邻居索引字符串
	 * 
	 * @return 邻居索引字符串
	 */
	public String neiboringToString() {
		StringBuilder stringBuffer = new StringBuilder();
		for (int i : neiboringTable) {
			stringBuffer.append(i).append("\t");
		}
		return stringBuffer.toString();
	}

	/**
	 * 返回适合权重向量字符串
	 * 
	 * @return 适合权重向量字符串
	 */
	public String ownWeightVectorToString() {

		StringBuilder stringBuffer = new StringBuilder();
		for (int i = 0; i < functionVector.size() - 1; i++) {
			double double1 = functionVector.get(i).doubleValue();
			stringBuffer.append(double1).append("\t");
		}
		if (functionVector != null && functionVector.size() > 0) {
			stringBuffer.append(functionVector.get(functionVector.size() - 1)
					.doubleValue());
		}
		return stringBuffer.toString();
	}

	public void setCrowdingDistance(double crowdingDistance) {
		this.crowdingDistance = crowdingDistance;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * 设置目标函数值向量
	 * 
	 * @param functionVector
	 *            目标函数值向量
	 */
	public void setFunctionVector(ArrayList<Double> functionVector) {
		this.functionVector = functionVector;
	}

	/**
	 * 把个体的权重向量设置为weightVector对称的权重向量
	 * 
	 * @param weightVector
	 *            原来的权重向量
	 */
	@SuppressWarnings("unused")
	private void setInversionWeightVector(ArrayList<Double> weightVector) {
		ArrayList<Double> al = new ArrayList<Double>();
		if (weightVector.size() != 2) {
			JOptionPane.showMessageDialog(null, "weightVector.size() != 2");
		} else {
			for (int i = 0; i < weightVector.size(); i++) {
				al.add(1 - weightVector.get(i));
			}
		}
		this.weightVector = al;
	}

	/**
	 * 设置邻域表
	 * 
	 * @param neiboringTable
	 *            邻域表
	 */
	public void setNeiboringTable(int[] neiboringTable) {
		this.neiboringTable = neiboringTable;
	}

	/**
	 * 设置目标个数
	 * 
	 * @param objectNum
	 *            目标个数
	 */
	public void setObjectNum(int objectNum) {
		this.objectNum = objectNum;
	}

	public void setOwnWeightVector(ArrayList<Double> onWeightVector) {
		this.ownWeightVector = onWeightVector;
	}

	public void setRank(int i) {
		this.rank = i;
	}

	/**
	 * 设置变量个数
	 * 
	 * @param variableNum
	 *            变量个数
	 */
	public void setVariableNum(int variableNum) {
		this.variableNum = variableNum;
	}

	/**
	 * 设置自变量
	 * 
	 * @param variableVector
	 *            自变量
	 */
	public void setVariableVector(ArrayList<Double> variableVector) {
		this.variableVector = variableVector;
	}

	/**
	 * 设置权重向量
	 * 
	 * @param weightVector
	 *            权重向量
	 */
	public void setWeightVector(ArrayList<Double> weightVector) {
		this.weightVector = weightVector;
	}

	@Override
	public String toString() {
		String s = "";
		s += super.toString() + "\n";
		s += variableToString() + "\n";
		s += functionToString() + "\n";
		return s;
	}

	/**
	 * 返回变量字符串
	 * 
	 * @return 变量字符串
	 */
	public String variableToString() {
		StringBuilder stringBuffer = new StringBuilder();
		int size = variableVector.size();
		for (int i = 0; i < size - 1; i++) {
			Double double1 = variableVector.get(i);
			stringBuffer.append(double1).append("\t");
		}
		if (size > 0) {
			stringBuffer.append(variableVector.get(size - 1));
		}
		return stringBuffer.toString();
	}

	/**
	 * 返回权重向量字符串
	 * 
	 * @return 权重向量字符串
	 */
	public String weightVectorToString() {

		StringBuilder stringBuffer = new StringBuilder("");
		for (int i = 0; i < weightVector.size() - 1; i++) {
			double double1 = weightVector.get(i).doubleValue();
			stringBuffer.append(double1).append("\t");
		}
		if (weightVector != null && weightVector.size() > 0) {
			stringBuffer.append(weightVector.get(weightVector.size() - 1)
					.doubleValue());
		}
		return stringBuffer.toString();
	}
}