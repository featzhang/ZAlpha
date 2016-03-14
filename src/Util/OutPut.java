package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import solution.Individual;

public class OutPut {

	private static File createFile(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			fileCheck(file);
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		return file;
	}

	private static boolean fileCheck(File file) {
		if (file == null) {
			return true;
		}
		if (file.exists()) {
			return true;
		} else {
			File parentFile = file.getParentFile();
			boolean exist = fileCheck(parentFile);
			if (!exist) {
				parentFile.mkdirs();
			}
			return false;
		}
	}

	public static void printfunctionToFile(
			ArrayList<Individual> popIndividuals, String fileName) {
		File file = createFile(fileName);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					fileOutputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(
					outputStreamWriter);
			for (Individual individual : popIndividuals) {
				String variableToString = individual.functionToString();
				bufferedWriter.write(variableToString);
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}

	public static void printVariableToFile(
			ArrayList<Individual> popIndividuals, String fileName) {
		// System.out.println("输出数据：" + fileName);
		File file = createFile(fileName);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					fileOutputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(
					outputStreamWriter);
			for (Individual individual : popIndividuals) {
				String variableToString = individual.variableToString();
				bufferedWriter.write(variableToString);
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	public static void printWeightVectorToFile(
			ArrayList<Individual> popIndividuals, String fileName) {
		File file = createFile(fileName);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					fileOutputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(
					outputStreamWriter);
			for (Individual individual : popIndividuals) {
				String weightVectorToString = individual.weightVectorToString();
				bufferedWriter.write(weightVectorToString);
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * 输出适合进化方向
	 * 
	 * @param popIndividuals
	 *            种群
	 * @param fileName
	 *            文件名
	 */
	public static void printOwnWeightVectorToFile(
			ArrayList<Individual> popIndividuals, String fileName) {
		File file = createFile(fileName);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					fileOutputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(
					outputStreamWriter);
			for (Individual individual : popIndividuals) {
				String weightVectorToString = individual
						.ownWeightVectorToString();
				bufferedWriter.write(weightVectorToString);
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	public static void printArray(double[] ideal) {
		int length = ideal.length;
		for (int i = 0; i < length - 1; i++) {
			System.out.print(ideal[i] + "\t");
		}
		if (length > 0) {
			System.out.print(ideal[length - 1] + "");
		}
	}

	/**
	 * 种群的所有函数值转换为函数值动态数组
	 * 
	 * @param population
	 *            种群
	 * @return 函数值动态数组
	 */
	public static ArrayList<ArrayList<Double>> functionToFunctionArray(
			ArrayList<Individual> population) {
		ArrayList<ArrayList<Double>> arrayList = null;
		if (population == null) {
			return null;
		}
		arrayList = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < population.size(); i++) {
			ArrayList<Double> functionVector = population.get(i)
					.getFunctionVector();
			ArrayList<Double> arrayList2 = new ArrayList<Double>();
			for (int j = 0; j < functionVector.size(); j++) {
				arrayList2.add(functionVector.get(j).doubleValue());
			}
			arrayList.add(arrayList2);
		}
		return arrayList;
	}

	public static void wirteToFile(ArrayList<?> result, String string) {
		File file = createFile(string);
		if (file.exists()) {
			try {
				file.delete();
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
			for (int i = 0; i < result.size(); i++) {
				// double doubleValue = result.get(i).doubleValue();
				bufferedWriter.write("" + (i + 1) + "\t" + result.get(i));
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}

	public static void wirte2DToFile(ArrayList<ArrayList<Double>> result,
			String string) {
		File file = new File(string);
		if (file.exists()) {
			try {
				file.delete();
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
			for (int i = 0; i < result.size(); i++) {
				// double doubleValue = result.get(i).doubleValue();
				ArrayList<Double> arrayList = result.get(i);
				for (int j = 0; j < arrayList.size(); j++) {
					bufferedWriter.write(arrayList.get(j).doubleValue() + "\t");
				}
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}
}