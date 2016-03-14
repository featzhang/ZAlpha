package paint;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

import solution.Individual;

/**
 *
 * @author 张作峰
 */
/**
 * @author 张作峰
 *
 */
public class PaintPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    /**
     * 转化矩阵管理器
     */
    protected static TransferMatrixManager transferMatrixManager = new TransferMatrixManager();
    /**
     * 启动绘制权重向量
     */
    protected static boolean drawWeightEnable = true;
    /**
     * 显示投影
     */
    protected static boolean showShadow = true;

    public static void clearTransferMatrix() {
        transferMatrixManager.clear();
    }

    public static void rotateAboutXAxesLeftTransferMatrix() {
        transferMatrixManager.rotateAboutXAxesLeft();
    }

    public static void rotateAboutXAxesRightTransferMatrix() {
        transferMatrixManager.rotateAboutXAxesRight();
    }

    public static void rotateAboutYAxesLeftTransferMatrix() {
        transferMatrixManager.rotateAboutYAxesLeft();
    }

    public static void rotateAboutYAxesRightTransferMatrix() {
        transferMatrixManager.rotateAboutYAxesRight();
    }

    public static void rotateAboutZAxesLeftTransferMatrix() {
        transferMatrixManager.rotateAboutZAxesLeft();
    }

    public static void rotateAboutZAxesRightTransferMatrix() {
        transferMatrixManager.rotateAboutZAxesRight();
    }
    /**
     * 指定均分空间的向量
     */
    protected ArrayList<ArrayList<Double>> weights = null;
    /**
     * 显示绘图区边界
     */
    protected boolean showDrawBorder = false;
    /**
     * 种群
     */
    protected ArrayList<Individual> population = null;
    /**
     * 边界宽度
     */
    protected double borderWidth = 30;
    /**
     * 边界长度
     */
    protected double borderHeight = 10;
    /**
     * panel宽度
     */
    protected double width2 = -1;
    /**
     * panel高度
     */
    protected double height2 = -1;
    /**
     * 绘图区域宽度
     */
    protected double drawWidth = -1;
    /**
     * 绘图区域高度
     */
    protected double drawHeight = -1;
    /**
     * 绘图区域左边界
     */
    protected double drawLeft = -1;
    /**
     * 绘图区域右边界
     */
    protected double drawRight = -1;
    /**
     * 绘图区域下边界
     */
    protected double drawBottom = -1;
    /**
     * 绘图区域上边界
     */
    protected double drawTop = -1;
    /**
     * 绘图原点x
     */
    protected double drawX0 = -1;
    /**
     * 绘图原点y
     */
    protected double drawY0 = -1;
    /**
     * 真实x值的最大值
     */
    protected double xMax = Double.MIN_VALUE;
    /**
     * 真实x值的最小值
     */
    protected double xMin = Double.MAX_VALUE;
    /**
     * 真实Y值的最大值
     */
    protected double yMax = Double.MIN_VALUE;
    /**
     * 真实Y值的最小值
     */
    protected double yMin = Double.MAX_VALUE;
    /**
     * 纵坐标放大倍数
     */
    protected double y_amplificationTime = 0;
    /**
     * 横坐标放大倍数
     */
    protected double x_amplificationTime = 0;
    /**
     * 个体圆的半径
     */
    protected static double radiusofCircle = 6;
    /**
     * 启用画各自指定进化方向
     */
    protected static boolean giveWeightEnable = true;
    /**
     * 启动显示坐标
     */
    protected static boolean showOrdinate = true;

    /**
     * 设置启动权重向量
     *
     * @param drawWeightEnable
     */
    public static void setDrawWeightEnable(boolean drawWeightEnable) {
        PaintPanel.drawWeightEnable = drawWeightEnable;
    }
    /**
     * 参考点
     */
    protected ArrayList<Double> ideal = null;
    /**
     * 启动十字线
     */
    protected static boolean crossLineEnable = true;

    /**
     * 设置启动十字线
     *
     * @param crossLineEnable 启动十字线
     */
    public static void setCrossLineEnable(boolean crossLineEnable) {
        PaintPanel.crossLineEnable = crossLineEnable;
        PaintPanel.showShadow = crossLineEnable;
    }

    /**
     * 设置启动指定权重向量
     *
     * @param giveWeightEnable 指定权重向量
     */
    public static void setGiveWeightEnable(boolean giveWeightEnable) {
        PaintPanel.giveWeightEnable = giveWeightEnable;
    }
    protected DecimalFormat myformat = new DecimalFormat("#####0.00");
    /**
     * 用于保存被淘汰的个体
     */
    protected ArrayList<Individual> eliminatePopulation = null;
    /**
     * 启动显示被淘汰的个体
     */
    protected static boolean showOldPopulationEnable = true;
    /**
     * 启动绘制X+Y=1
     */
    protected static boolean showXplusYequal1Enable = true;
    /**
     * 启动显示权重向量与x+y=1的交点
     */
    protected static boolean showIntersectionWithXplusYequal1Enable = true;
    /**
     * 显示最优Pareto面
     */
    protected static boolean showParetoFrontEnable = true;

    /**
     * 设置启动显示x+y=1
     *
     * @param showIntersectionWithXplusYequal1Enable 启动显示x+y=1
     */
    public static void setShowIntersectionWithXplusYequal1Enable(
            boolean showIntersectionWithXplusYequal1Enable) {
        PaintPanel.showIntersectionWithXplusYequal1Enable = showIntersectionWithXplusYequal1Enable;
    }

    /**
     * 设置启动显示被淘汰的个体种群
     *
     * @param showOldPopulationEnable 显示被淘汰的个体种群
     */
    public static void setShowOldPopulationEnable(
            boolean showOldPopulationEnable) {
        PaintPanel.showOldPopulationEnable = showOldPopulationEnable;
    }

    /**
     * 设置启动显示坐标
     *
     * @param showOrdinate 启动显示坐标
     */
    public static void setShowOrdinate(boolean showOrdinate) {
        PaintPanel.showOrdinate = showOrdinate;
    }
    /**
     * 最优Pareto面
     */
    protected double[][] paretoFront = null;
    /**
     * 启用xy标度一致
     */
    protected static boolean xySameEnable = true;

    /**
     * 设置个体点半径
     *
     * @param radiusofCircle 个体点半径
     */
    public static void setRadiusofCircle(double radiusofCircle) {
        PaintPanel.radiusofCircle = radiusofCircle;
    }

    /**
     * 设置启动显示最优Pareto面
     *
     * @param showParetoFrontEnable 显示最优Pareto面
     */
    public static void setShowParetoFrontEnable(boolean showParetoFrontEnable) {
        PaintPanel.showParetoFrontEnable = showParetoFrontEnable;
    }

    /**
     * 设置启动显示x+y=1
     *
     * @param showXplusYequal1Enable 启动显示x+y=1
     */
    public static void setShowXplusYequal1Enable(boolean showXplusYequal1Enable) {
        PaintPanel.showXplusYequal1Enable = showXplusYequal1Enable;
    }

    /**
     * 设置xy标度一致
     *
     * @param xySameEnable
     */
    public static void setXySameEnable(boolean xySameEnable) {
        PaintPanel.xySameEnable = xySameEnable;
    }

    /**
     * 获取试图区
     */
    protected void getViewArea() {
        width2 = getWidth();
        height2 = getHeight();
        borderWidth = width2 * 0.01;
        borderHeight = height2 * 0.01;
        drawLeft = borderWidth;
        drawRight = width2 - borderWidth;
        drawBottom = height2 - borderHeight;
        drawTop = borderHeight;
        drawWidth = drawRight - drawLeft;
        drawHeight = drawBottom - drawTop;
    }

    /**
     * 保存图像到文件
     *
     * @param file 文件
     */
    public void saveImage(File file) {/*
         * Rectangle rect = getBounds(); BufferedImage image = (BufferedImage)
         * createImage(rect.width, rect.height); Graphics g =
         * image.getGraphics(); paint(g); g.dispose(); try { FileOutputStream
         * fileStream = new FileOutputStream(file); JPEGImageEncoder encoder =
         * JPEGCodec.createJPEGEncoder(fileStream); encoder.encode(image);
         * fileStream.close(); } catch (Exception e) { System.out.println("Error
         * saving JPEG file!" ); }
         */

    }

    /**
     * 设置理想点
     *
     * @param ideal 理想点
     */
    public void setIdeal(ArrayList<Double> ideal) {
        this.ideal = ideal;
    }

    /**
     * 设置淘汰种群
     *
     * @param oldPopulation 淘汰种群
     */
    public void setOldPopulation(ArrayList<Individual> oldPopulation) {
        this.eliminatePopulation = oldPopulation;
    }

    /**
     * 设置Pareto最优面
     *
     * @param paretoFront Pareto最优面
     */
    public void setParetoFront(double[][] paretoFront) {
        this.paretoFront = paretoFront;
    }

    /**
     * 设置种群
     *
     * @param population 种群
     */
    public void setPopulation(ArrayList<Individual> population) {
        this.population = population;
    }

    public void setRotateVelocityValue(double rotateVelocityValue) {
        transferMatrixManager.setRotateVelocityValue(rotateVelocityValue);
    }

    /**
     * 设置显示绘图区边界
     *
     * @param showDrawBorder 启动显示绘图区边界
     */
    public void setShowDrawBorder(boolean showDrawBorder) {
        this.showDrawBorder = showDrawBorder;
    }

    /**
     * 显示权重向量
     *
     * @param weights 权重向量
     */
    public void setWeights(ArrayList<ArrayList<Double>> weights) {
        this.weights = weights;
    }
}