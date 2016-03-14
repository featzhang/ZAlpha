package Util;

import java.io.File;

import problem.Problem;

/**
 * 输出路径管理
 * 
 * @author 张作峰
 * 
 */
public class OutPathManager {
	private String fileName = null;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFile(File file) {
		this.fileName = file.getPath();
		System.out.println(fileName);
		analysis();
	}

	/**
	 * 文件名分析
	 */
	private void analysis() {
		String fileSeparator = System.getProperty("file.separator");
		String[] split = fileName.split("\\" + fileSeparator);
		int length = split.length;
		String string = split[length - 1];
		String[] split2 = string.split("_");
		for (String string2 : split2) {
			System.out.println(string2);
		}
		algorithmName = split2[0];
		problemName = split2[1];
		numberofObject = Integer.parseInt(split2[2]);
		decompostionName = split2[3];
		geneticTimes = Integer.parseInt(split2[4]);
		times = Integer
				.parseInt(split2[5].substring(0, split2[5].indexOf(".")));
	}

	private String algorithmName;
	private String problemName;
	private String decompostionName;
	private int numberofObject;
	private int geneticTimes;
	private int times;

	public Problem getProblem() {
		Problem problem = null;
		try {
			Class<?> forName = Class.forName("problem." + problemName);
			problem = (Problem) forName.newInstance();
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			Class<?> forName1;
			try {
				String className = "problem."
						+ problemName.substring(0, problemName.length() - 1)
						+ "." + problemName;
				System.out.println(className);
				forName1 = Class.forName(className);
				problem = (Problem) forName1.newInstance();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e.printStackTrace();
			} catch (IllegalAccessException e1) {
				e.printStackTrace();
			}

		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		return problem;
	}

}
