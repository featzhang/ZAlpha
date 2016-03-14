package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ZFile {
	/**
	 * 从文件中读取数据放到数组或arraylist中
	 * 
	 * @param file
	 *            文件名
	 */
	public static ArrayList<ArrayList<Double>> readDataToDoubleArrayList(
			File file) {
		// 默认放到arrayList中
		ArrayList<ArrayList<Double>> datas = null;
		if (file != null) {
			try {
				int maxColumn = -1;
				FileInputStream fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						fis));
				datas = new ArrayList<ArrayList<Double>>();
				String readLine = null;
				while ((readLine = br.readLine()) != null) {
					int c = 0;
					ArrayList<Double> arrayList = new ArrayList<Double>();
					String[] split = readLine.trim().split(" ");
					for (String string : split) {
						String[] split1 = string.trim().split("\t");
						for (String string1 : split1) {
							double parseDouble = 0;
							try {
								parseDouble = Double.parseDouble(string1);
								arrayList.add(parseDouble);
								c++;
								maxColumn = c > maxColumn ? c : maxColumn;
							} catch (Exception e) {
							}
						}
					}
					datas.add(arrayList);
				}
				if (datas.isEmpty() || maxColumn < 2) {
					return datas;
				}
			} catch (IOException ex) {
			}
		}
		return datas;
	}

	/**
	 * 从文件中读取数据放到数组或arraylist中
	 * 
	 * @param file
	 *            文件名
	 */
	public static ArrayList<ArrayList<String>> readDataToStringArrayList(
			File file) {
		// 默认放到arrayList中
		ArrayList<ArrayList<String>> datas = null;
		if (file != null) {
			try {
				int maxColumn = -1;
				FileInputStream fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						fis));
				datas = new ArrayList<ArrayList<String>>();
				String readLine = null;
				while ((readLine = br.readLine()) != null) {
					int c = 0;
					ArrayList<String> arrayList = new ArrayList<String>();
					String[] split = readLine.trim().split(" ");
					for (String string : split) {
						String[] split1 = string.trim().split("\t");
						for (String string1 : split1) {
							arrayList.add(string1);
							c++;
							maxColumn = c > maxColumn ? c : maxColumn;
						}
					}
					datas.add(arrayList);
				}
				if (datas.isEmpty() || maxColumn < 2) {
					return datas;
				}
			} catch (IOException ex) {
			}
		}
		return datas;
	}
}
