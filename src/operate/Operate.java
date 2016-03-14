package operate;

import java.util.ArrayList;
import java.util.Random;

import problem.Problem;
import solution.Individual;

public class Operate {

	/**
	 * 多项式交叉
	 * 
	 * @param problem
	 *            测试问题
	 * @param parent1
	 *            父个体1
	 * @param parent2
	 *            父个体2
	 * @param child1
	 *            交叉产生的新个体
	 */
	public static void crossover(Problem problem, Individual parent1,
			Individual parent2, Individual child1) {
		double rand;
		double y1, y2, yLow, yu;
		double c1, c2;
		double alpha, beta, betaq;
		double id_cx = 20;
		double eta_c = id_cx;
		double EPS = 1.2e-7;
		Random rg = new Random();

		int numVariables = problem.getVariableNum();
		ArrayList<Double> child1VariableVector = new ArrayList<Double>();
		for (int i = 0; i < problem.getVariableNum(); i++) {
			child1VariableVector.add((double) i);
		}
		if (rg.nextDouble() <= 1.0) {
			for (int i = 0; i < numVariables; i++) {
				double parent1variable = parent1.getVariableVector().get(i);
				double parent2variable = parent2.getVariableVector().get(i);

				if (rg.nextDouble() <= 0.5) {
					if (Math.abs(parent1variable - parent2variable) > EPS) {
						if (parent1variable < parent2variable) {
							y1 = parent1variable;
							y2 = parent2variable;
						} else {
							y1 = parent2variable;
							y2 = parent1variable;
						}
						yLow = problem.getLowBound()[i];
						yu = problem.getUpBound()[i];
						rand = rg.nextDouble();
						beta = 1.0 + (2.0 * (y1 - yLow) / (y2 - y1));
						alpha = 2.0 - Math.pow(beta, -(eta_c + 1.0));
						if (rand <= (1.0 / alpha)) {
							betaq = Math.pow((rand * alpha),
									(1.0 / (eta_c + 1.0)));
						} else {
							betaq = Math.pow((1.0 / (2.0 - rand * alpha)),
									(1.0 / (eta_c + 1.0)));
						}
						c1 = 0.5 * ((y1 + y2) - betaq * (y2 - y1));
						beta = 1.0 + (2.0 * (yu - y2) / (y2 - y1));
						alpha = 2.0 - Math.pow(beta, -(eta_c + 1.0));
						if (rand <= (1.0 / alpha)) {
							betaq = Math.pow((rand * alpha),
									(1.0 / (eta_c + 1.0)));
						} else {
							betaq = Math.pow((1.0 / (2.0 - rand * alpha)),
									(1.0 / (eta_c + 1.0)));
						}
						c2 = 0.5 * ((y1 + y2) + betaq * (y2 - y1));
						if (c1 < yLow)
							c1 = yLow;
						if (c2 < yLow)
							c2 = yLow;
						if (c1 > yu)
							c1 = yu;
						if (c2 > yu)
							c2 = yu;
						if (rg.nextDouble() <= 0.5) {
							child1VariableVector.set(i, c2);
							if (Double.isNaN(c2)) {
								System.out.println(c2);
							}
						} else {
							child1VariableVector.set(i, c1);
							if (Double.isNaN(c1)) {
								System.out.println(c1);
							}
						}
					} else {
						child1VariableVector.set(i, parent1variable);
					}
				} else {
					child1VariableVector.set(i, parent1variable);
				}
			}
		} else {
			for (int i = 0; i < numVariables; i++) {
				child1VariableVector.set(i, parent1.getVariableVector().get(i));
			}
		}
		child1.setVariableVector(child1VariableVector);
	}

	/**
	 * 模拟二进制交叉
	 * 
	 * @param problem
	 *            测试问题
	 * @param parent1
	 *            父个体1
	 * @param parent2
	 *            父个体2
	 * @param child1
	 *            交叉后的子个体1
	 * @param child2
	 *            交叉后的子个体2
	 */
	public static void realBinaryCrossover(Problem problem, Individual parent1,
			Individual parent2, Individual child1, Individual child2) {
		double id_cx = 20;
		double rand;
		double y1, y2, ylowBound, yupBound;
		double c1, c2;
		double alpha, beta, betaq;
		double eta_c = id_cx;
		double EPS = 1.2e-7;
		ArrayList<Double> child1VariableVector = new ArrayList<Double>();
		ArrayList<Double> child2VariableVector = new ArrayList<Double>();
		for (int i = 0; i < problem.getVariableNum(); i++) {
			child1VariableVector.add((double) i);
			child2VariableVector.add((double) i);
		}
		if (Math.random() * 1.2 <= 1.0) {
			for (int i = 0; i < problem.getVariableNum(); i++) {
				Double parent1Variablei = parent1.getVariableVector().get(i);
				Double parent2Variablei = parent2.getVariableVector().get(i);
				if (Math.random() <= 0.5) {

					if (Math.abs(parent1Variablei - parent2Variablei) > EPS) {
						if (parent1Variablei < parent2Variablei) {// 保证y1小，y2大
							y1 = parent1Variablei;
							y2 = parent2Variablei;
						} else {
							y1 = parent2Variablei;
							y2 = parent1Variablei;
						}
						ylowBound = problem.getLowBound()[i];
						yupBound = problem.getUpBound()[i];
						rand = Math.random();
						beta = 1.0 + (2.0 * (y1 - ylowBound) / (y2 - y1));
						alpha = 2.0 - Math.pow(beta, -(eta_c + 1.0));
						if (rand <= (1.0 / alpha)) {
							betaq = Math.pow((rand * alpha),
									(1.0 / (eta_c + 1.0)));
						} else {
							betaq = Math.pow((1.0 / (2.0 - rand * alpha)),
									(1.0 / (eta_c + 1.0)));
						}
						c1 = 0.5 * ((y1 + y2) - betaq * (y2 - y1));
						beta = 1.0 + (2.0 * (yupBound - y2) / (y2 - y1));
						alpha = 2.0 - Math.pow(beta, -(eta_c + 1.0));
						if (rand <= (1.0 / alpha)) {
							betaq = Math.pow((rand * alpha),
									(1.0 / (eta_c + 1.0)));
						} else {
							betaq = Math.pow((1.0 / (2.0 - rand * alpha)),
									(1.0 / (eta_c + 1.0)));
						}
						c2 = 0.5 * ((y1 + y2) + betaq * (y2 - y1));
						if (c1 < ylowBound) {
							c1 = ylowBound + Math.random()
									* (yupBound - ylowBound);
						}
						if (c2 < ylowBound) {
							c2 = ylowBound + Math.random()
									* (yupBound - ylowBound);
						}
						if (c1 > yupBound) {
							c1 = yupBound - Math.random()
									* (yupBound - ylowBound);
						}
						if (c2 > yupBound) {
							c2 = yupBound - Math.random()
									* (yupBound - ylowBound);
						}

						if (Math.random() <= 0.5) {
							child1VariableVector.set(i, c2);
							child2VariableVector.set(i, c1);
						} else {
							child1VariableVector.set(i, c1);
							child2VariableVector.set(i, c2);
						}
					} else {
						child1VariableVector.set(i, parent1Variablei);
						child2VariableVector.set(i, parent2Variablei);
					}

				} else {
					child1VariableVector.set(i, parent1Variablei);
					child2VariableVector.set(i, parent2Variablei);
				}
			}
		} else {
			for (int i = 0; i < problem.getVariableNum(); i++) {
				child1VariableVector.set(i, parent1.getVariableVector().get(i));
				child2VariableVector.set(i, parent2.getVariableVector().get(i));
			}
		}
		child1.setVariableVector(child1VariableVector);
		child2.setVariableVector(child2VariableVector);
		// System.out.println("Oprate");
		// System.out.println(child1.variableToString());
	}

	/**
	 * 实数变异
	 * 
	 * @param problem
	 *            测试问题
	 * @param ind
	 *            待变异个体
	 */
	public static void realMutation(Problem problem, Individual ind) {
		double rnd, delta1, delta2, mut_pow, deltaq;
		double y, yl, yu, val, xy;
		double eta_m = 20;
		int numVariables = problem.getVariableNum();
		double rate = (double) 1 / numVariables;
		ArrayList<Double> variableVector = ind.getVariableVector();
		Random random = new Random();
		for (int j = 0; j < numVariables; j++) {
			double nextDouble = random.nextDouble();
			// System.out.println(nextDouble+"    " +rate);
			if (nextDouble <= rate) {
				y = ind.getVariableVector().get(j);
				yl = problem.getLowBound()[j];
				yu = problem.getUpBound()[j];
				delta1 = (y - yl) / (yu - yl);
				delta2 = (yu - y) / (yu - yl);
				rnd = random.nextDouble();
				mut_pow = 1.0 / (eta_m + 1.0);
				if (rnd <= 0.5) {
					xy = 1.0 - delta1;
					val = 2.0 * rnd + (1.0 - 2.0 * rnd)
							* (Math.pow(xy, (eta_m + 1.0)));
					deltaq = Math.pow(val, mut_pow) - 1.0;
				} else {
					xy = 1.0 - delta2;
					val = 2.0 * (1.0 - rnd) + 2.0 * (rnd - 0.5)
							* (Math.pow(xy, (eta_m + 1.0)));
					deltaq = 1.0 - (Math.pow(val, mut_pow));
				}
				y = y + deltaq * (yu - yl);
				if (y < yl) {
					y = yl;
				}
				if (y > yu) {
					y = yu;
				}
				variableVector.set(j, y);
				// System.out.println("realMutation:"+j+"   "+y);
			}
		}
		ind.setVariableVector(variableVector);
		return;
	}

	/**
	 * 差分交叉
	 * 
	 * @param problem
	 *            测试问题，用于确定问题的上下限
	 * @param individual1
	 *            个体1
	 * @param individual2
	 *            个体2
	 * @param individual3
	 *            个体3
	 * @param newIndividual
	 *            新个体
	 */
	public static void diffEvolutionCrossover(Problem problem,
			Individual individual1, Individual individual2,
			Individual individual3, Individual newIndividual) {
		int nvar = problem.getVariableNum();
		double rate = 0.5;
		ArrayList<Double> variableVector1 = individual1.getVariableVector();
		ArrayList<Double> variableVector2 = individual2.getVariableVector();
		ArrayList<Double> variableVector3 = individual3.getVariableVector();
		ArrayList<Double> newVector3 = new ArrayList<Double>();
		for (int i = 0; i < problem.getVariableNum(); i++) {
			newVector3.add((double) i);
		}
		double[] lowBound = problem.getLowBound();
		double[] upBound = problem.getUpBound();

		for (int n = 0; n < nvar; n++) {
			newVector3
					.set(n,
							variableVector1.get(n)
									+ rate
									* (variableVector3.get(n) - variableVector2
											.get(n)));

			if (newVector3.get(n) < lowBound[n]) {
				double rnd = Math.random();
				newVector3.set(n, lowBound[n] + rnd
						* (variableVector1.get(n) - lowBound[n]));
			}
			if (newVector3.get(n) > upBound[n]) {
				double rnd = Math.random();
				newVector3.set(n, upBound[n] - rnd
						* (upBound[n] - variableVector1.get(n)));
			}
		}
		newIndividual.setVariableVector(newVector3);
	}
}