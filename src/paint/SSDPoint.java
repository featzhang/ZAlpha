package paint;

import java.awt.geom.Ellipse2D;

/**
 * 简化的3D点类
 * 
 * @author 张作峰
 * 
 */
public class SSDPoint {
	/**
	 * 绘图用的x坐标
	 */
	protected double drawMainX;

	/**
	 * 绘图用的y坐标
	 */
	protected double drawMainY;
	/**
	 * 在XOY平面上的投影x值
	 */
	protected double drawX_Xoy;
	/**
	 * 在xoy平面上的投影y值
	 */
	protected double drawY_Xoy;
	/**
	 * 在xoz平面上的投影x值
	 */
	protected double drawX_Xoz;
	/**
	 * 在xoz平面上的投影x值
	 */
	protected double drawY_Xoz;
	/**
	 * 在yoz平面上的投影x值
	 */
	protected double drawX_Yoz;
	/**
	 * 在yoz平面上的投影y值
	 */
	protected double drawY_Yoz;
	/**
	 * 绘图区上边界
	 */
	protected static double drawTop;
	/**
	 * 最小的y坐标
	 */
	protected static double y_min;
	/**
	 * y坐标放大倍数
	 */
	protected static double y_amplificationTime;
	/**
	 * 绘图区左边界
	 */
	protected static double drawLeft;
	/**
	 * 最小的x坐标
	 */
	protected static double x_min;
	/**
	 * x坐标放大倍数
	 */
	protected static double x_amplificationTime;
	/**
	 * Panel高度
	 */
	protected static double height2;

	protected static double radiusofCircle = 6;

	public static void setDrawLeft(double drawLeft) {
		SDPoint.drawLeft = drawLeft;
	}

	public static void setDrawTop(double drawTop) {
		SDPoint.drawTop = drawTop;
	}

	public static void setHeight2(double height2) {
		SDPoint.height2 = height2;
	}

	public static void setRadiusofCircle(double radiusofCircle) {
		SDPoint.radiusofCircle = radiusofCircle;
	}

	public static void setX_amplificationTime(double x_amplificationTime) {
		SDPoint.x_amplificationTime = x_amplificationTime;
	}

	public static void setX_min(double x_min) {
		SDPoint.x_min = x_min;
	}

	public static void setY_amplificationTime(double y_amplificationTime) {
		SDPoint.y_amplificationTime = y_amplificationTime;
	}

	public static void setY_min(double y_min) {
		SDPoint.y_min = y_min;
	}

	/**
	 * 对种群矩阵按照tt转换矩阵转换<br>
	 * 执行矩阵相乘
	 * 
	 * @param tt
	 *            转换矩阵
	 */
	public static double[] tranceform(double[] source, double[][] tt) {
		double[] a = source;
		double[][] t = tt;
		int aLengh = a.length;
		double[] newArrays = new double[aLengh];
		for (int i = 0; i < aLengh; i++) {
			double d = 0;
			for (int j = 0; j < aLengh; j++) {
				d += a[j] * t[j][i];
			}
			newArrays[i] = d;
		}
		return newArrays;
	}

	/**
	 * 对种群矩阵按照tt转换矩阵转换<br>
	 * 执行矩阵相乘
	 * 
	 * @param tt
	 *            转换矩阵
	 */
	public static double[][] tranceform(double[][] sourse, double[][] tt) {
		double[][] a = sourse;
		double[][] t = tt;
		int am = a.length;
		int an = a[0].length;
		int tn = t[0].length;
		double[][] newArrays = new double[am][tn];
		for (int i = 0; i < am; i++) {
			for (int j = 0; j < tn; j++) {
				double d = 0;
				for (int k = 0; k < an; k++) {
					d += a[i][k] * t[k][j];
				}
				newArrays[i][j] = d;
			}
		}
		return newArrays;
	}

	/**
	 * 原始矩阵，种群转换的矩阵
	 */
	protected double[] originalMatrix = null;

	/**
	 * 转换后的三维矩阵
	 */
	protected double[] afterTransformMatrix = null;
	/**
	 * 斜二测画法转换后的矩阵
	 */
	protected double[] inclined2MeasureLawMatrix = null;
	/**
	 * 转换矩阵
	 */
	protected static double[][] transferMatrix = null;

	/**
	 * 设置转换矩阵
	 * 
	 * @param transferMatrix
	 *            转换矩阵
	 */
	public static void setTransferMatrix(double[][] transferMatrix) {
		SSDPoint.transferMatrix = transferMatrix;
	}

	public SSDPoint() {

	}

	public SSDPoint(double[] originalMatrix) {
		this.originalMatrix = originalMatrix;
	}

	/**
	 * 获取主点圆对象
	 * 
	 * @return 主点圆对象
	 */
	public Ellipse2D getMainCircle() {
		Ellipse2D ellipse2d = new Ellipse2D.Double(drawMainX - radiusofCircle
				/ 2, drawMainY - radiusofCircle / 2, radiusofCircle,
				radiusofCircle);
		return ellipse2d;
	}

	/**
	 * 斜二测画法
	 */
	public void Inclined2MeasureLawTransformMainMatrix() {
		double[][] tt = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 },
				{ -0.3535, -0.3535, 0, 0 }, { 0, 0, 0, 1 } };
		inclined2MeasureLawMatrix = tranceform(afterTransformMatrix, tt);
	}

	/**
	 * 按照转换矩阵对点矩阵进行转换
	 */
	public void TTransformMainMatrix() {
		afterTransformMatrix = tranceform(originalMatrix, transferMatrix);
	}

	/**
	 * 二维平面坐标向绘图坐标转换
	 */
	public void wtvMain() {
		double x = inclined2MeasureLawMatrix[0];
		double y = inclined2MeasureLawMatrix[1];
		drawMainX = x_amplificationTime * (x - x_min) + drawLeft;
		drawMainY = height2 - (y_amplificationTime * (y - y_min) + drawTop);
	}

}
