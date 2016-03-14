package indicate;

import java.io.File;
import java.util.ArrayList;

import Util.OutPut;
import Util.ZFile;

public class Statistics {
	public static void main(String[] args) {
		File path = new File("Result2");
		iteratorPath(path);
		statistic();
		OutPut.wirteToFile(statisticMessage, "statisticMessage.txt");
	}

	private static void statistic() {
		for (int i = 0; i < files.size(); i++) {
			File file = files.get(i);
			System.out.print((i + 1) + ":");
			// System.out.println((i + 1) + ":" + file.getAbsolutePath());
			ArrayList<ArrayList<Double>> fileData = ZFile.readDataToDoubleArrayList(file);
			double min = Double.MAX_VALUE;
			double max = Double.MIN_VALUE;
			double average = 0;
			for (int j = 0; j < fileData.size(); j++) {
				ArrayList<Double> arrayList = fileData.get(j);
				if (arrayList.size() < 2) {
					average = arrayList.get(0).doubleValue();
				} else {
					// double doubleValue = arrayList.get(0).doubleValue();
					double doubleValue2 = arrayList.get(1).doubleValue();
					min = min > doubleValue2 ? doubleValue2 : min;
					max = max < doubleValue2 ? doubleValue2 : max;
				}
			}
			String string = file.getPath() + "\t" + min + "\t" + average + "\t"
					+ max;
			System.out.println(string);
			statisticMessage.add(string);
		}
	}

	private static ArrayList<File> files = new ArrayList<File>();
	private static ArrayList<String> statisticMessage = new ArrayList<String>();

	private static void iteratorPath(File path) {
		if (path.exists()) {
			if (path.isFile()) {
				if (path.getAbsolutePath().endsWith("indicate.txt")) {
					files.add(path);
					// System.out.println(files.size() + ":"
					// + path.getAbsolutePath());
				}
			} else if (path.isDirectory()) {
				File[] listFiles = path.listFiles();
				for (int i = 0; i < listFiles.length; i++) {
					iteratorPath(listFiles[i]);
				}
			}
		} else {
			return;
		}
	}
}
