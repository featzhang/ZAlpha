package Test;

import java.io.File;
import java.util.ArrayList;

public class NewClass2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("weight\\PBI");
		File[] listFiles = file.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			File pathFile = listFiles[i];
			File[] listFiles2 = pathFile.listFiles();
			for (int j = 0; j < listFiles2.length; j++) {
				File file2 = listFiles2[j];
				if (file2.getAbsolutePath().endsWith("result.pp")) {
					System.out.println(file2.getAbsolutePath());
					tongji(file2);
				}
			}
		}
	}

	private static void tongji(File file2) {
		ArrayList<ArrayList<Double>> tj = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> s = NewClass.readFile(file2);
		for (int i = 0; i < s.size(); i++) {
			ArrayList<Double> r = s.get(i);
			ArrayList<Double> tjj = new ArrayList<Double>();
			if (r.size() == 5) {
				double d1 = r.get(1).doubleValue();
				double d2 = r.get(2).doubleValue();
				double d3 = r.get(3).doubleValue();
				double d4 = r.get(4).doubleValue();
				tjj.add(Math.abs(d3 - d1));
				tjj.add(Math.abs(d4 - d2));
				tj.add(tjj);
			} else if (r.size() == 7) {
				double d1 = r.get(1).doubleValue();
				double d2 = r.get(2).doubleValue();
				double d3 = r.get(3).doubleValue();
				double d4 = r.get(4).doubleValue();
				double d5 = r.get(5).doubleValue();
				double d6 = r.get(6).doubleValue();
				tjj.add(Math.abs(d4 - d1));
				tjj.add(Math.abs(d5 - d2));
				tjj.add(Math.abs(d6 - d3));
				tj.add(tjj);
			}
		}
		int size = tj.size();
		double sum = 0;
		for (ArrayList<Double> arrayList : tj) {
			for (Double double1 : arrayList) {
				sum += double1;
			}
		}
		int size2 = tj.get(0).size();
		if (size2 == 5) {
			sum /= 2 * size;
		} else {
			sum /= 3 * size;
		}
		ArrayList<Double> doubles = new ArrayList<Double>();
		doubles.add(sum);
		tj.add(doubles);
		NewClass.save(tj, file2.getParent() + "\\tj.pp");
	}
}
