package decomposition;

import java.util.ArrayList;
import java.util.Vector;

import solution.Individual;

/**
 * PenaltybasedBoundaryIntersection 基于惩罚的边界交叉
 *
 * @author 张作峰
 *
 */
public class PenaltybasedBoundaryIntersection extends Decomposition {

    private static double U = 5;

    public static void setU(double i) {
        U = i;
    }

    public PenaltybasedBoundaryIntersection() {
        name = "PBI";
    }

    @Override
    public double comput(int objectNum, ArrayList<Double> functionVector,
            ArrayList<Double> weightVector, ArrayList<Double> idealIndividuals,
            ArrayList<Individual> referenceVector) {
        // normalize the weight vector (line segment)标准化权重向量（线段）
        double sum1 = 0.0;
        for (int i = 0; i < weightVector.size(); i++) {
            sum1 += Math.pow(weightVector.get(i), 2);
        }
        double nd = Math.sqrt(sum1);
        // 标准化后的权重向量
        Vector<Double> namda = new Vector<Double>();
        for (int i = 0; i < objectNum; i++) {
            namda.add(weightVector.get(i) / nd);
        }
        Vector<Double> realA = new Vector<Double>();
        Vector<Double> realB = new Vector<Double>();
        // difference beween current point and reference point 当前点与参考点的差
        for (int n = 0; n < objectNum; n++) {
            realA.add(functionVector.get(n) - idealIndividuals.get(n));
        }
        // distance along the line segment 沿线段的距离
        double sum2 = 0.0;
        for (int i = 0; i < realA.size(); i++) {
            sum2 += realA.get(i) * namda.get(i);
        }
        double d1 = Math.abs(sum2);
        // distance to the line segment 与线段的距离
        for (int n = 0; n < objectNum; n++) {
            realB.add(functionVector.get(n)
                    - (idealIndividuals.get(n) + d1 * namda.get(n)));
        }
        double sum3 = 0;
        for (int i = 0; i < realB.size(); i++) {
            double d = realB.get(i);
            sum3 += d * d;
        }
        double d2 = Math.sqrt(sum3);
        double fvalue = d1 + U * d2;
        return fvalue;
    }
}