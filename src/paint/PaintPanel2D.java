package paint;

import Util.ZResource;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import solution.Individual;

public class PaintPanel2D extends PaintPanel {

	private static final long serialVersionUID = 1L;

	public PaintPanel2D() {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		width2 = getWidth();
		height2 = getHeight();
		// 计算绘图边界
		drawLeft = borderWidth;
		drawRight = width2 - borderWidth;
		drawTop = height2 - borderHeight;
		drawBottom = borderHeight;
		drawWidth = width2 - 2 * borderWidth;
		drawHeight = height2 - 2 * borderHeight;
		if (showDrawBorder) {
			g.drawRect((int) drawLeft, (int) drawBottom, (int) drawWidth,
					(int) drawHeight);

		}
		// 扫描所有的点获取绘图所需的参数
		xMin = Double.MAX_VALUE;
		xMax = Double.MIN_VALUE;
		yMin = Double.MAX_VALUE;
		yMax = Double.MIN_VALUE;
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
			g.setFont(new Font("楷体", Font.BOLD, 20));
			g.drawString("张作峰   湘潭大学信息工程学院", xxxx - 140, yyyy + 110);
			return;
		}
		for (Individual individual : population) {
			ArrayList<Double> functionVector = individual.getFunctionVector();
			double x = functionVector.get(0);
			double y = functionVector.get(1);
			if (x > xMax) {
				xMax = x;
			}
			if (x < xMin) {
				xMin = x;
			}
			if (y > yMax) {
				yMax = y;
			}
			if (y < yMin) {
				yMin = y;
			}
		}
		yMax = yMax * 1.1;
		xMax = xMax * 1.1;
		if (xMax < 0) {
			xMax = 0;
		}
		if (xMin > 0) {
			xMin = 0;
		}
		if (yMin > 0) {
			yMin = 0;
		}
		if (yMax < 0) {
			yMax = 0;
		}
		if (xySameEnable) {
			xMax = yMax > xMax ? yMax : xMax;
			yMax = xMax;
			xMin = yMin < xMin ? yMin : xMin;
			yMin = xMin;
		}
		// x,y坐标放大倍数
		x_amplificationTime = (drawRight - drawLeft) / (xMax - xMin);
		y_amplificationTime = (drawTop - drawBottom) / (yMax - yMin);
		// 计算原点坐标
		drawX0 = (int) ((0 - xMin) * x_amplificationTime + drawLeft);
		drawY0 = drawTop
				- (int) ((0 - yMin) * y_amplificationTime + drawBottom);
		g.setColor(Color.red);
		g.drawArc((int) (drawX0 - 2), (int) (drawY0 - 2), 4, 4, 0, 360);
		g.drawString("0", (int) (drawX0 - 10), (int) (drawY0 + 10));
		// 绘制坐标轴
		g.setColor(Color.black);
		g.drawLine((int) drawLeft, (int) drawY0, (int) drawRight, (int) drawY0);
		g.drawLine((int) drawRight - 5, (int) drawY0 - 5, (int) drawRight,
				(int) drawY0);
		g.drawLine((int) (drawRight - 5), (int) (drawY0 + 5), (int) drawRight,
				(int) drawY0);
		g.drawLine((int) drawX0, (int) drawTop, (int) drawX0, (int) drawBottom);
		g.drawLine((int) drawX0 - 5, (int) drawBottom + 5, (int) drawX0,
				(int) drawBottom);
		g.drawLine((int) drawX0 + 5, (int) drawBottom + 5, (int) drawX0,
				(int) drawBottom);
		// 绘制最优Pareto面
		if (showParetoFrontEnable && paretoFront != null) {
			int[][] transformParetoFront = transformParetoFront();
			g.setColor(Color.red);
			g.drawPolyline(transformParetoFront[0], transformParetoFront[1],
					transformParetoFront[0].length);
		}

		// 绘制权重向量
		if (drawWeightEnable && weights != null) {
			g.setColor(Color.red);
			for (ArrayList<Double> weightVector : weights) {
				int w1 = (int) (weightVector.get(0).doubleValue()
						* x_amplificationTime * 20 + drawLeft);
				int w2 = (int) (drawTop - (weightVector.get(1).doubleValue()
						* y_amplificationTime * 20 + drawBottom));
				g.setColor(Color.PINK);
				g.drawLine((int) drawX0, (int) drawY0, w1, w2);
			}
		}
		// 显示被淘汰的个体
		if (showOldPopulationEnable && eliminatePopulation != null) {
			Color oldColor = Color.BLUE;
			for (int i = 0; i < eliminatePopulation.size(); i++) {
				Individual individual = eliminatePopulation.get(i);
				ArrayList<Double> functionVector = individual
						.getFunctionVector();
				double x = functionVector.get(0);
				double y = functionVector.get(1);
				int xx = (int) (drawLeft + x_amplificationTime * (x - xMin));
				int yy = (int) (drawTop - (int) (drawBottom + y_amplificationTime
						* (y - yMin)));
				g.setColor(oldColor);
				g.drawArc((int) (xx - radiusofCircle),
						(int) (yy - radiusofCircle),
						(int) (2 * radiusofCircle), (int) (2 * radiusofCircle),
						0, 360);
				// 绘制坐标
				if (showOrdinate) {
					g.setColor(oldColor);
					g.drawString("" + i + " [" + myformat.format(x) + ","
							+ myformat.format(y) + "]",
							(int) (xx - 2 * radiusofCircle),
							(int) (yy + 10 * radiusofCircle));
				}
				// 绘制坐标轴上的坐标
				{
					g.setColor(oldColor);
					// x坐标
					g.drawString("" + myformat.format(x), xx,
							(int) (drawY0 + 15));
					g.drawLine(xx, (int) drawY0, xx, (int) (drawY0 + 5));
					// y坐标
					g.drawString("" + myformat.format(y),
							(int) (drawX0 - borderWidth), yy);
					g.drawLine((int) drawX0, yy, (int) (drawX0 - 5), yy);

				}
				// 绘制指定权重向量
				if (giveWeightEnable) {
					ArrayList<Double> weightVector = individual
							.getWeightVector();
					int w1 = (int) (xx + weightVector.get(0).doubleValue()
							* x_amplificationTime / 10);
					int w2 = (int) (yy - weightVector.get(1).doubleValue()
							* y_amplificationTime / 10);

					g.setColor(oldColor);
					g.drawLine(xx, yy, w1, w2);
				}
				// 绘制十字线
				if (crossLineEnable) {
					g.setColor(oldColor);
					g.drawLine(xx, yy, xx, (int) drawY0);
					g.drawLine(xx, yy, (int) drawX0, yy);
				}
			}
		}
		// 绘制 x+y=1

		if (showXplusYequal1Enable) {
			int i = 0;
			while (true) {
				i++;
				int y0 = i;
				int x1 = i;
				int yy0 = (int) (drawTop - (int) (drawBottom + y_amplificationTime
						* (y0 - yMin)));
				int xx1 = (int) (drawLeft + x_amplificationTime * (x1 - xMin));
				g.setColor(Color.BLACK);
				g.drawLine((int) drawX0, yy0, xx1, (int) drawY0);
				if (i >= 1) {
					break;
				}
			}
		}
		// 绘制所有的个体
		for (int i = 0; i < population.size(); i++) {
			Graphics2D gd = (Graphics2D) g;
			Individual individual = population.get(i);
			// 绘制权重向量与x+y=1的焦点
			if (showIntersectionWithXplusYequal1Enable
					&& individual.getOwnWeightVector() != null) {
				ArrayList<Double> weightVector = individual
						.getOwnWeightVector();
				int ww1 = (int) (drawLeft + x_amplificationTime
						* (weightVector.get(0).doubleValue() - xMin));
				int ww2 = (int) (drawTop - (drawBottom + y_amplificationTime
						* (weightVector.get(1).doubleValue() - yMin)));
				Ellipse2D ellipse = new Ellipse2D.Double(ww1 - radiusofCircle,
						ww2 - radiusofCircle, 2 * radiusofCircle,
						2 * radiusofCircle);
				g.setColor(Color.lightGray);
				gd.draw(ellipse);
				gd.fill(ellipse);
				g.drawLine((int) drawX0, (int) drawY0, ww1, ww2);
			}

			ArrayList<Double> functionVector = individual.getFunctionVector();
			double x = functionVector.get(0);
			double y = functionVector.get(1);
			int xx = (int) (drawLeft + x_amplificationTime * (x - xMin));
			int yy = (int) (drawTop - (drawBottom + y_amplificationTime
					* (y - yMin)));
			// 绘制十字线
			if (crossLineEnable) {
				g.setColor(Color.LIGHT_GRAY);
				g.drawLine(xx, yy, xx, (int) drawY0);
				g.drawLine(xx, yy, (int) drawX0, yy);
			}// 绘制坐标
			if (showOrdinate) {
				g.setColor(Color.CYAN);
				g.drawString("" + (i + 1) + " [" + myformat.format(x) + ","
						+ myformat.format(y) + "]",
						(int) (xx - 2 * radiusofCircle),
						(int) (yy + 10 * radiusofCircle));
			}
			// 绘制所有个体
			Ellipse2D ellipse1 = new Ellipse2D.Double(xx - radiusofCircle, yy
					- radiusofCircle, 2 * radiusofCircle, 2 * radiusofCircle);
			g.setColor(Color.BLACK);
			gd.draw(ellipse1);
			gd.fill(ellipse1);
			// 绘制坐标轴上的坐标
			{
				g.setColor(Color.black);
				// x坐标
				g.drawString("" + myformat.format(x), xx, (int) drawY0 + 15);
				g.drawLine(xx, (int) drawY0, xx, (int) drawY0 + 5);
				// y坐标
				g.drawString("" + myformat.format(y),
						(int) (drawX0 - borderWidth), yy);
				g.drawLine((int) drawX0, yy, (int) (drawX0 - 5), yy);

			}
			// 绘制指定权重向量
			if (giveWeightEnable && individual.getWeightVector() != null
					&& !individual.getWeightVector().isEmpty()) {
				ArrayList<Double> weightVector = individual.getWeightVector();
				int w1 = (int) (xx + weightVector.get(0).doubleValue()
						* drawWidth / 10);
				int w2 = (int) (yy - weightVector.get(1).doubleValue()
						* drawHeight / 10);
				g.setColor(Color.red);
				g.drawLine(xx, yy, w1, w2);
			}
		}
		// 绘制参考点
		if (ideal != null && ideal.size() > 0) {
			double xD = ideal.get(0).doubleValue();
			double yD = ideal.get(1).doubleValue();
			int xx = (int) (drawLeft + x_amplificationTime * (xD - xMin));
			int yy = (int) (drawTop - (int) (drawBottom + y_amplificationTime
					* (yD - yMin)));
			g.setColor(Color.red);
			g.drawArc((int) (xx - radiusofCircle), (int) (yy - radiusofCircle),
					(int) (2 * radiusofCircle), (int) (2 * radiusofCircle), 0,
					360);
		}

	}

	/**
	 * 转换X坐标
	 * 
	 * @param d
	 *            真实坐标
	 * @return 绘图坐标
	 */
	private int transformCoordinateX(double d) {
		return (int) (drawLeft + x_amplificationTime * (d - xMin));
	}

	/**
	 * 转换Y坐标
	 * 
	 * @param d
	 *            真实坐标
	 * @return 绘图坐标
	 */
	private int transformCoordinateY(double d) {
		return (int) (drawTop - (drawBottom + y_amplificationTime * (d - yMin)));
	}

	protected int[][] transformParetoFront() {
		int row = paretoFront.length;
		int column = paretoFront[0].length;
		int[][] draw = new int[row][column];
		for (int j = 0; j < column; j++) {
			draw[0][j] = transformCoordinateX(paretoFront[0][j]);
			draw[1][j] = transformCoordinateY(paretoFront[1][j]);
		}
		return draw;
	}
}