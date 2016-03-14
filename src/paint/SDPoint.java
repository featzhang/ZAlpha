package paint;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 保存点的所有信息和对三维矩阵的转换<br>
 * 1.存储种群转换的三维坐标及在坐标平面投影矩阵<br>
 * 2.经过矩阵转换后的三维坐标矩阵及投影矩阵<br>
 * 3.斜二测画法转换后的二维坐标
 * 
 * @author 张作峰
 */
public class SDPoint extends SSDPoint {
	/**
	 * 在坐标平面的投影矩阵
	 */
	private double[][] shadowMatrix = null;
	/**
	 * 转换后的投影矩阵
	 */
	private double[][] afterTransformShadowMatrix = null;
	/**
	 * 斜二测画法转换后的投影
	 */
	private double[][] inclined2MeasureLawShadowMatrix = null;

	public SDPoint(ArrayList<Double> functionVector) {
		setPopulation(functionVector);
	}

	public SDPoint(double[] originalMatrix) {
		this.originalMatrix = originalMatrix;
	}

	/**
	 * 创建投影矩阵，在xoy、xoz、yoz平面上的投影
	 */
	public void createShadowMatrix() {
		double[][] tt = new double[][] { { 1, 0, 0, 0 }, { 0, 1, 0, 0 },
				{ 0, 0, 0, 0 }, { 0, 0, 0, 1 } };
		double[] xoy = tranceform(originalMatrix, tt);
		tt = new double[][] { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 },
				{ 0, 0, 0, 1 } };
		double[] xoz = tranceform(originalMatrix, tt);
		tt = new double[][] { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 },
				{ 0, 0, 0, 1 } };
		double[] yoz = tranceform(originalMatrix, tt);
		shadowMatrix = new double[3][];
		shadowMatrix[0] = xoy;
		shadowMatrix[1] = xoz;
		shadowMatrix[2] = yoz;
	}

	/**
	 * 获取斜二测画法转换后的x坐标
	 * 
	 * @return 斜二测画法转换后的x坐标
	 */
	public double getI2ML2DX() {
		return inclined2MeasureLawMatrix[0];
	}

	/**
	 * 获取斜二测画法转换后的y坐标
	 * 
	 * @return 斜二测画法转换后的y坐标
	 */
	public double getI2ML2DY() {
		return inclined2MeasureLawMatrix[1];
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
	 * @return
	 */
	public double getMainDrawX() {
		return drawMainX;
	}

	public double getMainDrawY() {
		return drawMainY;
	}

	/**
	 * 获取绘图2D点
	 * 
	 * @return 2D点
	 */
	public Point2D getMainPoint2d() {
		return new Point2D.Double(drawMainX, drawMainY);
	}

	public double getOriginalX() {
		return originalMatrix[0];
	}

	/**
	 * 在xoy平面上投影的x值
	 * 
	 * @return 在xoy平面上投影的x值
	 */
	public double getOriginalX_XOY() {
		return shadowMatrix[0][0];
	}

	/**
	 * 在xoz平面上投影的x值
	 * 
	 * @return 在xoz平面上投影的x值
	 */
	public double getOriginalX_XOZ() {
		return shadowMatrix[1][0];
	}

	public double getOriginalY() {
		return originalMatrix[1];
	}

	/**
	 * 在xoy平面上投影的y值
	 * 
	 * @return 在xoy平面上投影的y值
	 */
	public double getOriginalY_XOY() {
		return shadowMatrix[0][1];
	}

	/**
	 * 在yoz平面上投影的y值
	 * 
	 * @return 在yoz平面上投影的y值
	 */
	public double getOriginalY_YOZ() {
		return shadowMatrix[2][1];
	}

	public double getOriginalZ() {
		return originalMatrix[2];
	}

	/**
	 * 在xoz平面上投影的y值
	 * 
	 * @return 在xoz平面上投影的y值
	 */
	public double getOriginalZ_XOZ() {
		return shadowMatrix[1][2];
	}

	/**
	 * 在yoz平面上投影的z值
	 * 
	 * @return 在yoz平面上投影的z值
	 */
	public double getOriginalZ_YOZ() {
		return shadowMatrix[2][2];
	}

	/**
	 * 获取xoy平面投影点圆对象
	 * 
	 * @return 主点圆对象
	 */
	public Ellipse2D getShadowXoYCircle() {
		Ellipse2D ellipse2d = new Ellipse2D.Double(drawX_Xoy - radiusofCircle
				/ 2, drawY_Xoy - radiusofCircle / 2, radiusofCircle,
				radiusofCircle);
		return ellipse2d;
	}

	/**
	 * 获取xoz平面投影点点圆对象
	 * 
	 * @return 主点圆对象
	 */
	public Ellipse2D getShadowXoZCircle() {
		Ellipse2D ellipse2d = new Ellipse2D.Double(drawX_Xoz - radiusofCircle
				/ 2, drawY_Xoz - radiusofCircle / 2, radiusofCircle,
				radiusofCircle);
		return ellipse2d;
	}

	/**
	 * 获取yoz平面投影点点圆对象
	 * 
	 * @return 主点圆对象
	 */
	public Ellipse2D getShadowYoZCircle() {
		Ellipse2D ellipse2d = new Ellipse2D.Double(drawX_Yoz - radiusofCircle
				/ 2, drawY_Yoz - radiusofCircle / 2, radiusofCircle,
				radiusofCircle);
		return ellipse2d;
	}

	/**
	 * 获取在xoy平面上的投影线
	 * 
	 * @return
	 */
	public Line2D getXoyShadowLine() {
		Line2D line2d = new Line2D.Double(drawMainX, drawMainY, drawX_Xoy,
				drawY_Xoy);
		return line2d;
	}

	/**
	 * 获取在xoy平面上的投影线
	 * 
	 * @return
	 */
	public Line2D getXozShadowLine() {
		Line2D line2d = new Line2D.Double(drawMainX, drawMainY, drawX_Xoz,
				drawY_Xoz);
		return line2d;
	}

	/**
	 * 获取在xoy平面上的投影线
	 * 
	 * @return
	 */
	public Line2D getYozShadowLine() {
		Line2D line2d = new Line2D.Double(drawMainX, drawMainY, drawX_Yoz,
				drawY_Yoz);
		return line2d;
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
	 * 斜二测画法
	 */
	public void Inclined2MeasureLawTransformShadowMatrix() {
		double[][] tt = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 },
				{ -0.3535, -0.3535, 0, 0 }, { 0, 0, 0, 1 } };
		inclined2MeasureLawShadowMatrix = tranceform(
				afterTransformShadowMatrix, tt);
	}

	public void setPopulation(ArrayList<Double> population) {
		if (population == null || population.isEmpty()) {
			JOptionPane.showMessageDialog(null, "function == null");
		}
		// 初始化原始矩阵
		originalMatrix = new double[4];
		originalMatrix[0] = population.get(0).doubleValue();
		originalMatrix[1] = population.get(1).doubleValue();
		originalMatrix[2] = population.get(2).doubleValue();
		originalMatrix[3] = 0;
	}

	/**
	 * 按照转换矩阵对点矩阵进行转换
	 */
	public void TTransformMainMatrix() {
		afterTransformMatrix = tranceform(originalMatrix, transferMatrix);
	}

	/**
	 * 按照转换矩阵对投影矩阵进行转换
	 */
	public void TTransformShadowMatrix() {
		afterTransformShadowMatrix = tranceform(shadowMatrix, transferMatrix);
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

	/**
	 * 二维平面坐标向绘图坐标转换
	 */
	public void wtvShadow() {
		double x = inclined2MeasureLawMatrix[0];
		double y = inclined2MeasureLawMatrix[1];
		drawMainX = x_amplificationTime * (x - x_min) + drawLeft;
		drawMainY = height2 - (y_amplificationTime * (y - y_min) + drawTop);
		double xXoy = inclined2MeasureLawShadowMatrix[0][0];
		double yXoy = inclined2MeasureLawShadowMatrix[0][1];
		drawX_Xoy = x_amplificationTime * (xXoy - x_min) + drawLeft;
		drawY_Xoy = height2 - (y_amplificationTime * (yXoy - y_min) + drawTop);
		double xXoz = inclined2MeasureLawShadowMatrix[1][0];
		double yXoz = inclined2MeasureLawShadowMatrix[1][1];
		drawX_Xoz = x_amplificationTime * (xXoz - x_min) + drawLeft;
		drawY_Xoz = height2 - (y_amplificationTime * (yXoz - y_min) + drawTop);
		double xYoz = inclined2MeasureLawShadowMatrix[2][0];
		double yYoz = inclined2MeasureLawShadowMatrix[2][1];
		drawX_Yoz = x_amplificationTime * (xYoz - x_min) + drawLeft;
		drawY_Yoz = height2 - (y_amplificationTime * (yYoz - y_min) + drawTop);
	}
}