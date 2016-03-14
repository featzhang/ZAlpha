package Test;

import indicate.Indicate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Hashtable;

import problem.Problem;
import problem.DTLZ.DTLZ1;
import problem.DTLZ.DTLZ2;
import problem.DTLZ.DTLZ3;
import problem.DTLZ.DTLZ4;
import problem.DTLZ.DTLZ5;
import problem.UF.UF1;
import problem.UF.UF2;
import problem.ZDT.ZDT1;
import problem.ZDT.ZDT2;
import problem.ZDT.ZDT3;
import problem.ZDT.ZDT4;
import problem.ZDT.ZDT6;
import Util.OutPut;
import algorithm.Algorithm;
import algorithm.moead.MOEAD1;
import algorithm.moead.MOEADpro;
import decomposition.Decomposition;
import decomposition.PenaltybasedBoundaryIntersection;
import decomposition.Tchebycheff;
import decomposition.WeightedSum;

public class algrithmTest {
	public static void main(String[] args) {
		int testTimes = 30;
		int evaluaterTimes = 2000;
		Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
		ht.put(100, 100);
		ht.put(200, 200);
		ht.put(300, 300);
		ht.put(500, 500);
		ht.put(800, 800);
		ht.put(1000, 1000);
		ht.put(1200, 1200);
		ht.put(1300, 1300);
		ht.put(1500, 1500);
		ht.put(2000, 2000);
		Algorithm[] algorithms = new Algorithm[2];
		algorithms[0] = new MOEAD1();
		algorithms[1] = new MOEADpro();
		Problem[] problems = new Problem[12];
		problems[0] = new ZDT1();
		problems[1] = new ZDT2();
		problems[2] = new ZDT3();
		problems[3] = new ZDT4();
		problems[4] = new ZDT6();
		problems[5] = new DTLZ1();
		problems[6] = new DTLZ2();
		problems[7] = new DTLZ3();
		problems[8] = new DTLZ4();
		problems[9] = new DTLZ5();
		problems[10] = new UF1();
		problems[11] = new UF2();
		Decomposition[] decompositions = new Decomposition[3];
		decompositions[0] = new WeightedSum();
		decompositions[1] = new Tchebycheff();
		decompositions[2] = new PenaltybasedBoundaryIntersection();
		for (int testTime = 0; testTime < testTimes; testTime++) {
			// 第一次测试
			for (int algorithmIndex = 0; algorithmIndex < algorithms.length; algorithmIndex++) {
				Algorithm algorithm = algorithms[algorithmIndex];
				for (int j2 = 0; j2 < decompositions.length; j2++) {
					Decomposition decomposition = decompositions[j2];
					for (int problemIndex = 0; problemIndex < 10; problemIndex++) {
						Problem problem = problems[problemIndex];
						algorithm.setDecomposition(decomposition);
						algorithm.setProblem(problem);
						algorithm.setNeiboringNum(20);
						algorithm.setH(problem.getObjectNum() == 2 ? 99 : 13);
						algorithm.setCurrentGeneriticTime(0);
						System.out.println(algorithm.getName() + "_"
								+ problem.getName() + "_"
								+ decomposition.getName() + "_" + testTime);
						algorithm.init();
						try {
							for (int k = 0; k < evaluaterTimes; k++) {
								algorithm.evoluationOneTime();
								if (ht.containsKey(k + 1)) {
									// 输出
									System.out.println("输出！！！！！！！！！！！！！！！！！！！");
									String fileNameString = "Result" + "\\";
									fileNameString += algorithm.getName()
											+ "\\";
									fileNameString += problem.getName() + "_"
											+ problem.getObjectNum() + "\\";
									fileNameString += decomposition.getName()
											+ "\\";
									fileNameString += (k + 1) + "\\";
									fileNameString += algorithm.getName() + "_";
									fileNameString += problem.getName() + "_"
											+ problem.getObjectNum() + "_";
									fileNameString += decomposition.getName()
											+ "_";
									fileNameString += (k + 1) + "_";
									fileNameString += testTime + ".pf";

									OutPut.printfunctionToFile(
											algorithm.getPopulation(),
											fileNameString);
								}
							}
						} catch (Exception e) {
							String fileNameString = "Result" + "\\";
							fileNameString += algorithm.getName() + "_";
							fileNameString += problem.getName() + "_"
									+ problem.getObjectNum() + "_";
							fileNameString += decomposition.getName() + "_";
							fileNameString += testTime + ".pf";

							File file = new File("alarm");
							if (!file.exists()) {
								try {
									file.createNewFile();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
							try {
								FileOutputStream fileOutputStream = new FileOutputStream(
										file, true);
								OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
										fileOutputStream);
								BufferedWriter bufferedWriter = new BufferedWriter(
										outputStreamWriter);
								bufferedWriter.write(fileNameString);
								bufferedWriter.newLine();
								bufferedWriter.flush();
								bufferedWriter.close();
							} catch (FileNotFoundException eee) {
								eee.printStackTrace();
							} catch (IOException ee) {
							}
						}
					}
				}
			}
		}
		Indicate.indicateFile(new File("Result"));
		System.out.println("wondefult 全部完成！");
	}
}