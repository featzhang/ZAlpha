package indicate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import problem.Problem;
import Util.OutPathManager;
import Util.OutPut;
import Util.ZFile;
import Util.ZMatrix;

public class Indicate {

	private static ArrayList<String> averageSummary = new ArrayList<String>();

	/**
	 * 接收文件夹
	 * 
	 * @param file
	 *            文件夹
	 */
	private static void calculaterFold(File file) {
		// 如果发现文件，直接将文件放入。
		ArrayList<Double> result = new ArrayList<Double>();
		ArrayList<File> files = new ArrayList<File>();
		File[] listFiles = file.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			File file2 = listFiles[i];
			if (file2.isFile() && file2.getName().endsWith(".pf")) {
				files.add(file2);
			} else if (file2.isDirectory()) {
				calculaterFold(file2);
			}
		}
		if (files.size() > 0) {
			File file2 = files.get(0);
			OutPathManager outPathManager = new OutPathManager();
			outPathManager.setFile(file2);
			Problem problem = outPathManager.getProblem();
			ArrayList<ArrayList<Double>> readData = ZFile.readDataToDoubleArrayList(file2);
			double[][] tDimensionalArrayTrance = ZMatrix
					.TDimensionalArrayTrance(readData);
			double[][] trueParetoFront = problem.getParetoFrontFromFile();
			if (trueParetoFront == null) {
				return;
			}
			for (int i = 0; i < files.size(); i++) {
				File file3 = files.get(i);
				readData = ZFile.readDataToDoubleArrayList(file3);
				tDimensionalArrayTrance = ZMatrix
						.TDimensionalArrayTrance(readData);
				double calculator = IGD.calculator(tDimensionalArrayTrance,
						trueParetoFront);
				result.add(calculator);
			}
		}
		if (result.size() > 0) {
			double average = 0;
			for (int i = 0; i < result.size(); i++) {
				average += result.get(i).doubleValue();
			}
			average /= (result.size());
			String path = file.getPath();

			OutPut.wirteToFile(result, path + "\\indicate.txt");
			wirteToFile(average, path + "\\indicate.txt");
			averageSummary.add(path.substring(path.toUpperCase().indexOf(
					"RESULT") + 7)
					+ "\t" + average);
		}
	}

	/**
	 * 评价文件
	 * 
	 * @param file
	 *            待测试文件
	 */
	public static void indicateFile(File file) {
		if (file.isFile()) {
			// 从文件中获取数据
			ArrayList<ArrayList<Double>> readData = ZFile.readDataToDoubleArrayList(file);
			double[][] tDimensionalArrayTrance = ZMatrix
					.TDimensionalArrayTrance(readData);
			// 分析文件名称，获取相关信息
			OutPathManager outPathManager = new OutPathManager();
			outPathManager.setFile(file);
			Problem problem = outPathManager.getProblem();
			double[][] tDimensionalArrayTrance2 = problem
					.getParetoFrontFromFile();
			double calculator = IGD.calculator(tDimensionalArrayTrance,
					tDimensionalArrayTrance2);
			System.out.println(calculator);

		} else if (file.isDirectory()) {
			// 同一个目录下的同一个测试问题的文件将结果进行统计。
			// 扫描所有的文件，寻找同一个文件夹下面的文件，同时计算。
			calculaterFold(file);
			OutPut.wirteToFile(averageSummary, file.getPath()
					+ "\\indicate.txt");
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(true);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setCurrentDirectory(new File("."));
		int showOpenDialog = fileChooser.showOpenDialog(null);
		if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println(selectedFile);
			// 如果是文件，直接评价文件。如果是文件夹，将评价整个文件个内的左右的文件。如果有子文件夹将直接到最低层。
			indicateFile(selectedFile);
		}
	}

	private static void wirteToFile(double result, String string) {
		File file = new File(string);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file, true);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					fileOutputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(
					outputStreamWriter);
			bufferedWriter.write(" " + result);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}
}
