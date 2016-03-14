/**
 * 实验库：三维绘图
 */
package paint;

import Util.ZResource;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author 张作峰
 */
public class PaintPanel3D extends PaintPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * x+y=1
	 */
	private ArrayList<SDPoint> xPlusyEqul1Points = null;
	/**
	 * 原始x差
	 */
	private double x_difference;
	/**
	 * 原始y差
	 */
	private double y_difference;
	/**
	 * 淘汰个体点
	 */
	private ArrayList<SDPoint> eliminatePoints = null;
	/**
	 * 主要显示点
	 */
	private ArrayList<SDPoint> points = null;
	/**
	 * 坐标轴
	 */
	private ArrayList<SDPoint> coordinateAxis = null;
	/**
	 * 原点
	 */
	private SDPoint originalPoint = null;
	/**
	 * 原始3D最大的x
	 */
	private double maxx = Double.MIN_VALUE;
	/**
	 * 原始3D最小的x
	 */
	private double minx = Double.MAX_VALUE;
	/**
	 * 原始3D最大的y
	 */
	private double maxy = Double.MIN_VALUE;
	/**
	 * 原始3D最小的y
	 */
	private double miny = Double.MAX_VALUE;
	/**
	 * 原始3D最大的Z
	 */
	private double maxz = Double.MIN_VALUE;
	/**
	 * 原始3D最小的Z
	 */
	private double minz = Double.MAX_VALUE;

	/**
	 * 构造函数
	 */
	public PaintPanel3D() {
	}

	/**
	 * 分析绘图矩阵的参数，用于绘图
	 */
	private void analysisDrawMatrix() {
		xMax = Double.MIN_VALUE;
		xMin = Double.MAX_VALUE;
		yMax = Double.MIN_VALUE;
		yMin = Double.MAX_VALUE;
		int length = points.size();
		for (int i = 0; i < length; i++) {
			double x = points.get(i).getI2ML2DX();
			double y = points.get(i).getI2ML2DY();
			xMax = x > xMax ? x : xMax;
			xMin = x < xMin ? x : xMin;
			yMax = y > yMax ? y : yMax;
			yMin = y < yMin ? y : yMin;
		}
		if (showOldPopulationEnable && eliminatePopulation != null) {
			for (int i = 0; i < eliminatePopulation.size(); i++) {
				double x = eliminatePoints.get(i).getI2ML2DX();
				double y = eliminatePoints.get(i).getI2ML2DY();
				xMax = x > xMax ? x : xMax;
				xMin = x < xMin ? x : xMin;
				yMax = y > yMax ? y : yMax;
				yMin = y < yMin ? y : yMin;
			}
		}

		// 投影修正
		if (showShadow) {
		}
		// 保留原点
		{
			xMax = xMax < 0 ? 0 : xMax;
			xMin = xMin > 0 ? 0 : xMin;
			yMax = yMax < 0 ? 0 : yMax;
			yMin = yMin > 0 ? 0 : yMin;
		}

		// 使用坐标轴顶点修正
		{
			for (int i = 0; i < coordinateAxis.size(); i++) {
				double x = coordinateAxis.get(i).getI2ML2DX();
				double y = coordinateAxis.get(i).getI2ML2DY();
				xMax = xMax < x ? x : xMax;
				xMin = xMin > x ? x : xMin;
				yMax = yMax < y ? y : yMax;
				yMin = yMin > y ? y : yMin;
			}
		}
		// 扩张最值
		xMax *= 1.1;
		yMax *= 1.1;
		xMin *= 1.1;
		yMin *= 1.1;
		x_difference = xMax - xMin;
		y_difference = yMax - yMin;
		x_amplificationTime = drawWidth / x_difference;
		y_amplificationTime = drawHeight / y_difference;
	}

	/**
	 * 将旧种群信息转换为矩阵
	 */
	private void oldPopulationToMatrix() {
		if (eliminatePopulation == null) {
			System.out.println("oldPopulation == null");
			return;
		}
		eliminatePoints = new ArrayList<SDPoint>();
		int populationSize = eliminatePopulation.size();
		if (populationSize > 0) {
			ArrayList<Double> functionVector = eliminatePopulation.get(0)
					.getFunctionVector();
			if (functionVector == null || functionVector.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"eliminatePopulation.functionVector0 == null");
			} else {
				for (int i = 0; i < eliminatePopulation.size(); i++) {
					functionVector = eliminatePopulation.get(i)
							.getFunctionVector();
					SDPoint tdPoint1 = new SDPoint(functionVector);
					if (showShadow) {
						tdPoint1.createShadowMatrix();
					}
					eliminatePoints.add(tdPoint1);
				}
			}
		}
		// 计算原点和坐标轴顶点
		for (int i = 0; i < eliminatePopulation.size(); i++) {
			SDPoint sdPoint = eliminatePoints.get(i);
			double x = sdPoint.getOriginalX();
			double y = sdPoint.getOriginalY();
			double z = sdPoint.getOriginalZ();
			maxx = maxx < x ? x : maxx;
			minx = minx > x ? x : minx;
			maxy = maxy < y ? y : maxy;
			miny = miny > y ? y : miny;
			maxz = maxz < z ? z : maxz;
			minz = minz > z ? z : minz;
			if (showShadow) {
				double x_XOY = sdPoint.getOriginalX_XOY();
				maxx = maxx < x_XOY ? x_XOY : maxx;
				minx = minx > x_XOY ? x_XOY : minx;
				double y_XOY = sdPoint.getOriginalY_XOY();
				maxy = maxy < y_XOY ? y_XOY : maxy;
				miny = miny > y_XOY ? y_XOY : miny;
				double x_XOZ = sdPoint.getOriginalX_XOZ();
				maxz = maxz < x_XOZ ? x_XOZ : maxz;
				minz = minz > x_XOZ ? x_XOZ : minz;
				double z_XOZ = sdPoint.getOriginalZ_XOZ();
				maxz = maxz < z_XOZ ? z_XOZ : maxz;
				minz = minz > z_XOZ ? z_XOZ : minz;
				double y_YOZ = sdPoint.getOriginalY_YOZ();
				maxy = maxy < y_YOZ ? y_YOZ : maxy;
				miny = miny > y_YOZ ? y_YOZ : miny;
				double z_YOZ = sdPoint.getOriginalZ_YOZ();
				maxz = maxz < z_YOZ ? z_YOZ : maxz;
				minz = minz > z_YOZ ? z_YOZ : minz;
			}
		}

		maxx = maxx < 0 ? 0 : maxx;
		maxy = maxy < 0 ? 0 : maxy;
		maxz = maxz < 0 ? 0 : maxz;
		minx = minx > 0 ? 0 : minx;
		miny = miny > 0 ? 0 : miny;
		minz = minz > 0 ? 0 : minz;

		if (xySameEnable) {
			maxx = maxx < maxy ? maxy : maxx;
			maxx = maxx < maxz ? maxz : maxx;
			maxz = maxy = maxx;
			minx = minx > miny ? miny : minx;
			minx = minx > minz ? minz : minx;
			miny = minz = minx;
		}
		coordinateAxis = new ArrayList<SDPoint>();
		coordinateAxis.add(new SDPoint(new double[] { minx, 0, 0, 0 }));
		coordinateAxis.add(new SDPoint(new double[] { maxx, 0, 0, 0 }));
		coordinateAxis.add(new SDPoint(new double[] { 0, miny, 0, 0 }));
		coordinateAxis.add(new SDPoint(new double[] { 0, maxy, 0, 0 }));
		coordinateAxis.add(new SDPoint(new double[] { 0, 0, minz, 0 }));
		coordinateAxis.add(new SDPoint(new double[] { 0, 0, maxz, 0 }));
	}

	@Override
	public void paint(Graphics g) {

		Graphics2D graphics2d = (Graphics2D) g;
		// 获取绘图区参数
		getViewArea();
		if (showDrawBorder) {// 显示绘图区边界
			g.setColor(Color.LIGHT_GRAY);
			Rectangle2D rectangle2d = new Rectangle2D.Double(drawLeft, drawTop,
					drawWidth, drawHeight);
			graphics2d.fill(rectangle2d);
			graphics2d.setColor(Color.gray);
			graphics2d.draw(rectangle2d);
		}
		if (population == null) {
			Image appImage = ZResource.getWallpaper().getImage();
			int xxxx = (int) (drawWidth / 2 + drawLeft);
			int yyyy = (int) (drawHeight / 2 + drawBottom);
			g.drawImage(appImage, xxxx - 165, yyyy - 300, 331, 345,
					Color.white, null);
			g.setColor(Color.red);
			g.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 30));
			g.drawString("ZALPHA进化算法设计平台", xxxx - 180, yyyy + 70);
			g.setColor(Color.black);
			g.setFont(new Font("楷体", Font.BOLD | Font.PLAIN, 20));
			g.drawString("张作峰   湘潭大学信息工程学院", xxxx - 140, yyyy + 110);
			return;
		}
		int popSize = population.size();// 种群大小

		// 设置转换矩阵
		SDPoint.setTransferMatrix(transferMatrixManager.getTransferMatrix());
		// 种群到矩阵转换
		populationToMatrix();
		// 淘汰种群到矩阵的转换
		if (showOldPopulationEnable && eliminatePopulation != null) {
			oldPopulationToMatrix();
		}
		// 显示x+y=1
		if (showXplusYequal1Enable) {
			creatXPlusYEquel1Points();
		}
		// 种群按照转换矩阵转换,斜二测画法转换
		for (int i = 0; i < popSize; i++) {
			SDPoint tdPoint = points.get(i);
			tdPoint.TTransformMainMatrix();
			tdPoint.Inclined2MeasureLawTransformMainMatrix();
			if (showShadow) {
				tdPoint.TTransformShadowMatrix();
				tdPoint.Inclined2MeasureLawTransformShadowMatrix();
			}
		}
		// 淘汰种群按照转换矩阵转换,斜二测画法转换
		if (showOldPopulationEnable && eliminatePopulation != null) {
			int eliminatePopSize = eliminatePoints.size();
			for (int i = 0; i < eliminatePopSize; i++) {
				SDPoint tdPoint = eliminatePoints.get(i);
				tdPoint.TTransformMainMatrix();
				tdPoint.Inclined2MeasureLawTransformMainMatrix();
				if (showShadow) {
					tdPoint.TTransformShadowMatrix();
					tdPoint.Inclined2MeasureLawTransformShadowMatrix();
				}
			}
		}
		// 坐标轴按照转换矩阵转换,斜二测画法转换
		for (int i = 0; i < coordinateAxis.size(); i++) {
			SDPoint coordinateAxis1 = coordinateAxis.get(i);
			coordinateAxis1.TTransformMainMatrix();
			coordinateAxis1.Inclined2MeasureLawTransformMainMatrix();
		}
		// x+y=1，斜二测画法转换
		if (showXplusYequal1Enable) {
			for (int i = 0; i < xPlusyEqul1Points.size(); i++) {
				SDPoint coordinateAxis1 = xPlusyEqul1Points.get(i);
				coordinateAxis1.TTransformMainMatrix();
				coordinateAxis1.Inclined2MeasureLawTransformMainMatrix();
			}
		}
		// 原点按照转换矩阵转换,斜二测画法转换
		{
			originalPoint.TTransformMainMatrix();
			originalPoint.Inclined2MeasureLawTransformMainMatrix();
		}
		// 分析绘图参数
		analysisDrawMatrix();
		// 设置绘图参数
		SDPoint.setDrawLeft(drawLeft);
		SDPoint.setDrawTop(drawTop);
		SDPoint.setHeight2(height2);
		SDPoint.setX_amplificationTime(x_amplificationTime);
		SDPoint.setX_min(xMin);
		SDPoint.setY_amplificationTime(y_amplificationTime);
		SDPoint.setY_min(yMin);
		BasicStroke defaultPen = new BasicStroke();
		// 绘制最优Pareto面
		if (showParetoFrontEnable && paretoFront != null) {
			graphics2d.setStroke(defaultPen);
			graphics2d.setColor(Color.yellow);
			ArrayList<SSDPoint> paretoFrontList = new ArrayList<SSDPoint>();
			for (int i = 0; i < paretoFront.length; i++) {
				double[] ds = paretoFront[i];
				paretoFrontList.add(new SDPoint(ds));
			}
			for (int i = 0; i < paretoFrontList.size(); i++) {
				SSDPoint sdPoint = paretoFrontList.get(i);
				sdPoint.TTransformMainMatrix();
				sdPoint.Inclined2MeasureLawTransformMainMatrix();
				sdPoint.wtvMain();
				graphics2d.draw(sdPoint.getMainCircle());
			}
		}
		// 坐标轴wtv转换
		for (int i = 0; i < 6; i++) {
			SDPoint tdPoint1 = coordinateAxis.get(i);
			tdPoint1.wtvMain();
		}
		// 绘制坐标轴
		g.setColor(Color.red);
		BasicStroke axisPen = new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_ROUND);
		graphics2d.setStroke(axisPen);
		for (int i = 0; i < 6; i++, i++) {
			Point2D point2d0 = coordinateAxis.get(i).getMainPoint2d();
			Point2D point2d1 = coordinateAxis.get(i + 1).getMainPoint2d();
			Line2D line2dx = new Line2D.Double(point2d0, point2d1);
			graphics2d.draw(line2dx);
		}
		// 原点wtv转换
		originalPoint.wtvMain();
		// 绘制原点
		graphics2d.fill(originalPoint.getMainCircle());
		graphics2d.drawString("0", (int) originalPoint.getMainDrawX() - 10,
				(int) originalPoint.getMainDrawY());
		// x+y=1 wtv转换并绘制
		if (showXplusYequal1Enable) {
			for (int i = 0; i < xPlusyEqul1Points.size(); i++) {
				SDPoint tdPoint1 = xPlusyEqul1Points.get(i);
				tdPoint1.wtvMain();
			}
			g.setColor(Color.green);
			graphics2d.setStroke(axisPen);
			Line2D xyLine2d0 = new Line2D.Double(xPlusyEqul1Points.get(0)
					.getMainPoint2d(), xPlusyEqul1Points.get(1)
					.getMainPoint2d());
			graphics2d.draw(xyLine2d0);
			Line2D xyLine2d1 = new Line2D.Double(xPlusyEqul1Points.get(0)
					.getMainPoint2d(), xPlusyEqul1Points.get(2)
					.getMainPoint2d());
			graphics2d.draw(xyLine2d1);
			Line2D xyLine2d2 = new Line2D.Double(xPlusyEqul1Points.get(2)
					.getMainPoint2d(), xPlusyEqul1Points.get(1)
					.getMainPoint2d());
			graphics2d.draw(xyLine2d2);
		}
		// 显示淘汰种群
		if (showOldPopulationEnable && eliminatePopulation != null) {
			graphics2d.setStroke(defaultPen);
			int eliminatePopSize = eliminatePoints.size();
			for (int i = 0; i < eliminatePopSize; i++) {
				g.setColor(Color.blue);
				SDPoint sdPoint = eliminatePoints.get(i);
				sdPoint.wtvMain();
				graphics2d.draw(sdPoint.getMainCircle());
				if (showShadow) {
					sdPoint.wtvShadow();
					g.setColor(Color.blue);
					graphics2d.draw(sdPoint.getShadowXoYCircle());
					graphics2d.draw(sdPoint.getShadowXoZCircle());
					graphics2d.draw(sdPoint.getShadowYoZCircle());
					// g.setColor(Color.cyan);
					graphics2d.draw(sdPoint.getXoyShadowLine());
					graphics2d.draw(sdPoint.getXozShadowLine());
					graphics2d.draw(sdPoint.getYozShadowLine());
				}
			}
		}
		// 点的wtv转换并绘制点

		graphics2d.setStroke(defaultPen);
		for (int i = 0; i < popSize; i++) {
			g.setColor(Color.black);
			SDPoint sdPoint = points.get(i);
			sdPoint.wtvMain();
			graphics2d.fill(sdPoint.getMainCircle());
			if (showShadow) {
				sdPoint.wtvShadow();
				g.setColor(Color.gray);
				graphics2d.draw(sdPoint.getShadowXoYCircle());
				graphics2d.draw(sdPoint.getShadowXoZCircle());
				graphics2d.draw(sdPoint.getShadowYoZCircle());
				// g.setColor(Color.cyan);
				graphics2d.draw(sdPoint.getXoyShadowLine());
				graphics2d.draw(sdPoint.getXozShadowLine());
				graphics2d.draw(sdPoint.getYozShadowLine());
			}
		}

	}

	/**
	 * 将种群信息转换为矩阵
	 */
	private void populationToMatrix() {
		if (population == null) {
			JOptionPane.showMessageDialog(null, "(population == null");
		}
		points = new ArrayList<SDPoint>();
		int populationSize = population.size();
		if (populationSize > 0) {
			ArrayList<Double> functionVector = population.get(0)
					.getFunctionVector();
			if (functionVector == null || functionVector.isEmpty()) {
				JOptionPane.showMessageDialog(null, "functionVector0 == null");
			} else {
				for (int i = 0; i < populationSize; i++) {
					functionVector = population.get(i).getFunctionVector();
					SDPoint tdPoint1 = new SDPoint(functionVector);
					if (showShadow) {
						tdPoint1.createShadowMatrix();
					}
					points.add(tdPoint1);
				}
			}
		}

		maxx = Double.MIN_VALUE;
		minx = Double.MAX_VALUE;
		maxy = Double.MIN_VALUE;
		miny = Double.MAX_VALUE;
		maxz = Double.MIN_VALUE;
		minz = Double.MAX_VALUE;
		// 计算原点和坐标轴顶点
		originalPoint = new SDPoint(new double[] { 0, 0, 0, 0 });
		for (int i = 0; i < populationSize; i++) {
			SDPoint sdPoint = points.get(i);
			double x = sdPoint.getOriginalX();
			double y = sdPoint.getOriginalY();
			double z = sdPoint.getOriginalZ();
			maxx = maxx < x ? x : maxx;
			minx = minx > x ? x : minx;
			maxy = maxy < y ? y : maxy;
			miny = miny > y ? y : miny;
			maxz = maxz < z ? z : maxz;
			minz = minz > z ? z : minz;
			if (showShadow) {
				double x_XOY = sdPoint.getOriginalX_XOY();
				maxx = maxx < x_XOY ? x_XOY : maxx;
				minx = minx > x_XOY ? x_XOY : minx;
				double y_XOY = sdPoint.getOriginalY_XOY();
				maxy = maxy < y_XOY ? y_XOY : maxy;
				miny = miny > y_XOY ? y_XOY : miny;
				double x_XOZ = sdPoint.getOriginalX_XOZ();
				maxz = maxz < x_XOZ ? x_XOZ : maxz;
				minz = minz > x_XOZ ? x_XOZ : minz;
				double z_XOZ = sdPoint.getOriginalZ_XOZ();
				maxz = maxz < z_XOZ ? z_XOZ : maxz;
				minz = minz > z_XOZ ? z_XOZ : minz;
				double y_YOZ = sdPoint.getOriginalY_YOZ();
				maxy = maxy < y_YOZ ? y_YOZ : maxy;
				miny = miny > y_YOZ ? y_YOZ : miny;
				double z_YOZ = sdPoint.getOriginalZ_YOZ();
				maxz = maxz < z_YOZ ? z_YOZ : maxz;
				minz = minz > z_YOZ ? z_YOZ : minz;
			}
		}

		maxx = maxx < 0 ? 0 : maxx;
		maxy = maxy < 0 ? 0 : maxy;
		maxz = maxz < 0 ? 0 : maxz;
		minx = minx > 0 ? 0 : minx;
		miny = miny > 0 ? 0 : miny;
		minz = minz > 0 ? 0 : minz;

		if (xySameEnable) {
			maxx = maxx < maxy ? maxy : maxx;
			maxx = maxx < maxz ? maxz : maxx;
			maxz = maxy = maxx;
			minx = minx > miny ? miny : minx;
			minx = minx > minz ? minz : minx;
			miny = minz = minx;
		}

		maxx *= 1.1;
		maxy *= 1.1;
		maxz *= 1.1;
		minx *= 1.1;
		miny *= 1.1;
		minz *= 1.1;
		coordinateAxis = new ArrayList<SDPoint>();
		coordinateAxis.add(new SDPoint(new double[] { minx, 0, 0, 0 }));
		coordinateAxis.add(new SDPoint(new double[] { maxx, 0, 0, 0 }));
		coordinateAxis.add(new SDPoint(new double[] { 0, miny, 0, 0 }));
		coordinateAxis.add(new SDPoint(new double[] { 0, maxy, 0, 0 }));
		coordinateAxis.add(new SDPoint(new double[] { 0, 0, minz, 0 }));
		coordinateAxis.add(new SDPoint(new double[] { 0, 0, maxz, 0 }));
	}

	/**
	 * 创建x+y=1平面交线
	 */
	private void creatXPlusYEquel1Points() {
		xPlusyEqul1Points = new ArrayList<SDPoint>();
		xPlusyEqul1Points.add(new SDPoint(new double[] { 1, 0, 0 }));
		xPlusyEqul1Points.add(new SDPoint(new double[] { 0, 1, 0 }));
		xPlusyEqul1Points.add(new SDPoint(new double[] { 0, 0, 1 }));
	}
}