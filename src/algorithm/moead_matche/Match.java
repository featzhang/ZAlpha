package algorithm.moead_matche;

import java.util.ArrayList;

import solution.Individual;
import decomposition.Decomposition;

/**
 * @author 张作峰
 *
 */
public class Match {
	private int objectNum = 0;

	private ArrayList<Double> weightVector = null;

	private Individual individual = null;

	private double fitness = Double.MAX_VALUE;
	private Decomposition decomposition = null;
	private int[] neiboringTable = null;

	public Match() {
	}

	public Match(ArrayList<Double> weightVector, Individual individual,
			Decomposition decomposition) {
		super();
		this.weightVector = weightVector;
		this.individual = individual;
		this.decomposition = decomposition;
	}

	public double comput(ArrayList<Double> idealIndividuals,
			ArrayList<Individual> referenceVector) {
		double comput = decomposition.comput(objectNum,
				individual.getFunctionVector(), weightVector, idealIndividuals,
				referenceVector);
		fitness = comput;
		return fitness;
	}

	public double comput(int objectNum, ArrayList<Double> functionVector,
			ArrayList<Double> weightVector, ArrayList<Double> idealIndividuals,
			ArrayList<Individual> referenceVector) {
		double comput = decomposition.comput(objectNum, functionVector,
				weightVector, idealIndividuals, referenceVector);
		fitness = comput;
		return fitness;
	}

	public double comput(int objectNum, ArrayList<Double> idealIndividuals,
			ArrayList<Individual> referenceVector) {
		double comput = decomposition.comput(objectNum,
				individual.getFunctionVector(), weightVector, idealIndividuals,
				referenceVector);
		fitness = comput;
		return fitness;
	}

	public double getFitness() {
		return fitness;
	}

	public Individual getIndividual() {
		return individual;
	}

	public int[] getNeiboringTable() {
		return neiboringTable;
	}

	public int getObjectNum() {
		return objectNum;
	}

	public ArrayList<Double> getWeightVector() {
		return weightVector;
	}

	public void setDecomposition(Decomposition decomposition) {
		this.decomposition = decomposition;
	}

	public void setIndividual(Individual individual) {
		this.individual = individual;
	}

	public void setNeiboringTable(int[] neiboringTable) {
		this.neiboringTable = neiboringTable;
	}

	public void setObjectNum(int objectNum) {
		this.objectNum = objectNum;
	}

	public void setWeightVector(ArrayList<Double> weightVector) {
		this.weightVector = weightVector;
	}
}