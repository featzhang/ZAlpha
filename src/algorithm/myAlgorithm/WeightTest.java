package algorithm.myAlgorithm;

import java.util.ArrayList;

public class WeightTest {

    public static void main(String[] args) {
        ArrayList<ArrayList<Double>> aList = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                if (i + j <= 10) {
                    ArrayList<Double> arrayList = new ArrayList<Double>();
                    arrayList.add((double) i / 10);
                    arrayList.add((double) j / 10);
                    arrayList.add((double) (10 - i - j) / 10);
                    aList.add(arrayList);
                }
            }
        }
        for (ArrayList<Double> arrayList : aList) {
            System.out.println();
            for (Double double1 : arrayList) {
                System.out.print(double1 + "\t");
            }
        }

    }

    static ArrayList<Double> weightCompromise(
            ArrayList<Double> weight0, ArrayList<Double> weight1) {
        ArrayList<Double> arrayList = new ArrayList<Double>();
        for (int i = 0; i < weight0.size(); i++) {
            double doubleValue = weight0.get(i).doubleValue();
            double doubleValue2 = weight1.get(i).doubleValue();
            arrayList.add((doubleValue + doubleValue2) / 2);
        }
        return arrayList;
    }
}