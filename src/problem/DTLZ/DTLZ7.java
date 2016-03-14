/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problem.DTLZ;

import Util.ZMatrix;
import java.util.ArrayList;
import solution.Individual;

/**
 *
 * @author AAron
 */
public class DTLZ7 extends problem.Problem {

    @Override
    public void calculatorObject(Individual individual) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void initData(Individual individual) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<ArrayList<Double>> calculateParetoFrontbyMathMethod(int num) {
        double[][] obj = new double[500][numberOfObjectives];
        double x1, x2;
        int i, j, k;
        for (i = 0; i <= 19; i++) {
            x1 = (1.0 - 0.0) * i / 19;
            for (j = 0; j <= 24; j++) {
                x2 = (1.0 - 0.0) * j / 24;
                k = 25 * i + j;
                obj[k][0] = x1;
                obj[k][1] = x2;
                obj[k][2] = 2 * (3 - (x1 * (1 + Math.sin(3 * Math.PI * x1) / 2) + (x2 * (1 + Math.sin(3 * Math.PI * x2)) / 2)));
            }
        }
        ArrayList<ArrayList<Double>> TDimensionalArrayTrance = ZMatrix.TDimensionalArrayTrance(obj);
        return TDimensionalArrayTrance;
    }
}
