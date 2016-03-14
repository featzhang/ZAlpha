package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * 1.统计同一个文件夹中的文件，每个文件每一行中每一个单元格C，求C的算数平均数<br>
 * 2.
 * 
 * @author 张作峰
 * 
 */
public class NewClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File path = new File("weight\\WeightedSum");
		File[] listFiles = path.listFiles();
		for (File file : listFiles) {
			System.out.println(file.getAbsolutePath());
			if (file.isDirectory()) {
				File[] lf = file.listFiles();// Tweight下的文件
				ArrayList<ArrayList<Double>> readFile2 = readFile(lf[0]);
				for (int i = 1; i < lf.length; i++) {
					File file2 = lf[i];
					if (file2.getAbsolutePath().endsWith(".pp")) {
						continue;
					}
					ArrayList<ArrayList<Double>> readFile = readFile(file2);
					readFile2 = add(readFile, readFile2);
				}
				readFile2 = divide(readFile2, lf.length);
				save(readFile2, file.getAbsolutePath() + "\\result.pp");
			}
		}
	}

	public static void save(ArrayList<ArrayList<Double>> s, String string) {
		File reFile = new File(string);
		try {
			reFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(reFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bWriter = new BufferedWriter(osw);
			for (int i = 0; i < s.size(); i++) {
				ArrayList<Double> r = s.get(i);
				String ssString = "";
				for (int j = 0; j < r.size(); j++) {
					double d = r.get(j).doubleValue();
					ssString += d + "\t";
				}
				bWriter.write(ssString);
				bWriter.newLine();
			}
			bWriter.flush();
			bWriter.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 从文件中读取数据
	 * 
	 * @param file
	 *            文件
	 * @return 数据
	 */
	public static ArrayList<ArrayList<Double>> readFile(File file) {
		ArrayList<ArrayList<Double>> dLists = new ArrayList<ArrayList<Double>>();
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				ArrayList<Double> doubles = new ArrayList<Double>();
				String[] split = line.split("\t");
				for (int i = 0; i < split.length; i++) {
					String string = split[i];
					try {
						double parseDouble = Double.parseDouble(string);
						doubles.add(parseDouble);
					} catch (Exception e) {
						System.out.println(string + "转换成Double错误！");
					}
				}
				dLists.add(doubles);
			}
			br.close();
		} catch (Exception e) {
		}
		return dLists;
	}

	/**
	 * 叠加两个ArrayList<ArrayList<Double>>
	 * 
	 * @param a
	 * @param b
	 * @return 和
	 */
	public static ArrayList<ArrayList<Double>> add(
			ArrayList<ArrayList<Double>> a, ArrayList<ArrayList<Double>> b) {
		ArrayList<ArrayList<Double>> plus = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < a.size(); i++) {
			ArrayList<Double> r1 = a.get(i);
			ArrayList<Double> r2 = b.get(i);
			ArrayList<Double> cs = new ArrayList<Double>();
			for (int j = 0; j < r1.size(); j++) {
				double c1 = r1.get(j).doubleValue();
				double c2 = r2.get(j).doubleValue();
				cs.add(c1 + c2);
			}
			plus.add(cs);
		}
		return plus;
	}

	/**
	 * @param s
	 * @return
	 */
	public static ArrayList<ArrayList<Double>> divide(
			ArrayList<ArrayList<Double>> s, int n) {
		ArrayList<ArrayList<Double>> o = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < s.size(); i++) {
			ArrayList<Double> dd = new ArrayList<Double>();
			ArrayList<Double> r = s.get(i);
			for (int j = 0; j < r.size(); j++) {
				double doubleValue = r.get(j).doubleValue();
				dd.add(doubleValue / n);
			}
			o.add(dd);
		}
		return o;
	}
}
