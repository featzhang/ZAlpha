package paint;

/**
 * 转换矩阵管理器
 *
 * @author 张作峰
 *
 */
public class TransferMatrixManager {

    /**
     * 旋转矩阵
     */
    private double[][] transferMatrix = {{1, 0, 0, 0}, {0, 1, 0, 0},
        {0, 0, 1, 0}, {0, 0, 0, 1}};

    public TransferMatrixManager() {
    }

    /**
     * 增加旋转矩阵
     *
     * @param newTransferMatrix
     *            新旋转矩阵
     * @return 旋转矩阵
     */
    public double[][] addTransferMatrix(double[][] newTransferMatrix) {
        transferMatrix = tranceform(transferMatrix, newTransferMatrix);
        return transferMatrix;
    }

    /**
     * 清空转换矩阵
     *
     * @return 转换矩阵
     */
    public double[][] clear() {
        transferMatrix = new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0},
            {0, 0, 1, 0}, {0, 0, 0, 1}};
        return transferMatrix;
    }

    /**
     * 获取旋转矩阵
     *
     * @return 旋转矩阵
     */
    public double[][] getTransferMatrix() {

        return transferMatrix;
    }

    /**
     * 沿x轴左旋
     *
     * @return 旋转矩阵
     */
    public double[][] rotateAboutXAxesLeft() {
        return rotateAboutXAxes(rotateVelocityValue);
    }

    /**
     * 沿x轴右旋
     *
     * @return 旋转矩阵
     */
    public double[][] rotateAboutXAxesRight() {
        return rotateAboutXAxes(-rotateVelocityValue);
    }

    /**
     * 沿y轴左旋
     *
     * @return 旋转矩阵
     */
    public double[][] rotateAboutYAxesLeft() {
        return rotateAboutYAxes(rotateVelocityValue);
    }

    /**
     * 沿y轴右旋
     *
     * @return 旋转矩阵
     */
    public double[][] rotateAboutYAxesRight() {
        return rotateAboutYAxes(-rotateVelocityValue);
    }

    /**
     * 沿z轴左旋
     *
     * @return 旋转矩阵
     */
    public double[][] rotateAboutZAxesLeft() {
        return rotateAboutZAxes(rotateVelocityValue);
    }

    /**
     * 沿z轴右旋
     *
     * @return 旋转矩阵
     */
    public double[][] rotateAboutZAxesRight() {
        return rotateAboutZAxes(-rotateVelocityValue);
    }

    /**
     * 围绕x轴旋转
     *
     * @param a
     * @return
     */
    public double[][] rotateAboutXAxes(double a) {
        double sin = Math.sin(a);
        double cos = Math.cos(a);
        double[][] tt = new double[][]{{1, 0, 0, 0}, {0, cos, sin, 0},
            {0, -sin, cos, 0}, {0, 0, 0, 1}};
        transferMatrix = tranceform(transferMatrix, tt);
        return transferMatrix;
    }

    /**
     * 围绕y轴旋转
     *
     * @param a
     * @return
     */
    public double[][] rotateAboutYAxes(double a) {
        double sin = Math.sin(a);
        double cos = Math.cos(a);
        double[][] tt = new double[][]{{cos, 0, -sin, 0}, {0, 1, 0, 0},
            {sin, 0, cos, 0}, {0, 0, 0, 1}};
        transferMatrix = tranceform(transferMatrix, tt);
        return transferMatrix;
    }

    /**
     * 围绕z轴旋转
     *
     * @param a
     * @return
     */
    public double[][] rotateAboutZAxes(double a) {
        double sin = Math.sin(a);
        double cos = Math.cos(a);
        double[][] tt = new double[][]{{cos, sin, 0, 0},
            {-sin, cos, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        transferMatrix = tranceform(transferMatrix, tt);
        return transferMatrix;
    }
    /**
     * 旋转角度
     */
    private double rotateVelocityValue = 0.02;

    public void setRotateVelocityValue(double rotateVelocityValue) {
        this.rotateVelocityValue = rotateVelocityValue;
    }

    /**
     * 对种群矩阵按照tt转换矩阵转换<br>
     * 执行矩阵相乘
     *
     * @param tt
     *            转换矩阵
     */
    private double[][] tranceform(double[][] sourse, double[][] tt) {
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
}