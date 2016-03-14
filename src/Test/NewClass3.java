package Test;

import java.io.File;
import java.util.ArrayList;

import Util.OutPut;

public class NewClass3 {
	public static void main(String[] args) {
		ArrayList<String> result = new ArrayList<String>();
		String[] pathStrings = { "weight\\PBI", "weight\\Tchebycheff", "weight\\WeightedSum" };
		for (String pathString : pathStrings) {
			File path = new File(pathString);
			File[] listFiles = path.listFiles();
			for (File file : listFiles) {
				File[] listFiles2 = file.listFiles();
				for (File file2 : listFiles2) {
					if (file2.getAbsolutePath().endsWith("tj.pp")) {
						ArrayList<ArrayList<Double>> dat = NewClass
								.readFile(file2);
						double doubleValue = dat.get(dat.size() - 1).get(0)
								.doubleValue();
						String string = "";
						int indexOf = file.getAbsolutePath()
								.indexOf("ZALPHAme") + 9;
						string += file.getAbsolutePath().substring(indexOf)
								+ ":\t";
						string += doubleValue + "\t";
						string += doubleValue / 5;
						result.add(string);
					}
				}
			}
		}
		OutPut.wirteToFile(result, "result.txt");
	}
}
