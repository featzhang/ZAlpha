package indicate;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import Util.OutPut;
import Util.ZFile;

public class Statistics2 {
	public static void main(String[] args) {
		Hashtable<String, String> moeadproht = new Hashtable<String, String>();
		Hashtable<String, String> moeadht = new Hashtable<String, String>();
		ArrayList<String> results = new ArrayList<String>();
		File file = new File("Result2\\statisticMessage.txt");
		ArrayList<ArrayList<String>> readData = ZFile
				.readDataToStringArrayList(file);
		for (int i = 0; i < readData.size(); i++) {
			ArrayList<String> arrayList = readData.get(i);
			String string = arrayList.get(1);
			if (string.indexOf("MOEADpro") > 0) {
				String[] split = string.split("\\\\");
				String problemString = split[2];
				problemString = problemString.substring(0,
						problemString.indexOf("_"));
				String approachString = split[3];
				String evolutionTimeString = split[4];
				problemString += "\t" + approachString + "\t"
						+ evolutionTimeString;
				String string2 = arrayList.get(2) + "\t" + arrayList.get(3)
						+ "\t" + arrayList.get(4);
				moeadproht.put(problemString, string2);
			} else {
				String string2 = arrayList.get(1);
				String[] split = string2.split("\\\\");
				String problemString = split[2];
				problemString = problemString.substring(0,
						problemString.indexOf("_"));
				String approachString = split[3];
				String evolutionString = split[4];
				problemString += "\t" + approachString + "\t" + evolutionString;
				String string3 = arrayList.get(2) + "\t" + arrayList.get(3)
						+ "\t" + arrayList.get(4);
				moeadht.put(problemString, string3);
			}
		}
		Enumeration<String> keys = moeadht.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String moeadproValue = moeadproht.get(key);
			String moeadValue = moeadht.get(key);
			String resultString = key + "\t" + moeadValue + "\t"
					+ moeadproValue;
			results.add(resultString);
		}
		// OutPut.wirte2DToFile(result, string)
		OutPut.wirteToFile(results, "Result2\\statisticMessage2.txt");
	}
}
