package Test;

import java.util.ArrayList;

import Util.OutPut;

import problem.Problem;
import problem.ZDT.ZDT1;

public class NNN1 {
	public static void main(String[] args) {
		Problem problem = new ZDT1();
		ArrayList<ArrayList<Double>> calculateParetoFrontbyMathMethod = problem
				.calculateParetoFrontbyMathMethod(500);
		OutPut.wirte2DToFile(calculateParetoFrontbyMathMethod, "1.txt");

	}
}
