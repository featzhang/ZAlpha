package algorithm.moead;

import algorithm.Algorithm;
import decomposition.Tchebycheff;
import java.util.ArrayList;

import problem.DTLZ.DTLZ1;
import solution.Individual;

/**
 * 基于匹配的MOEAD改进2 未完成
 *
 * @author 张作峰
 *
 */
public class MOEADpro2 extends Algorithm {

    public MOEADpro2() {
        algorithmName = "MOEADpro2";
    }

    @Override
    public void init() {
        idealIndividuals = problem.getIdealIndividuals();
        populationVector = new ArrayList<Individual>();
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual();
            problem.initData(individual);
            problem.calculatorObject(individual);
            calculatOwnWeight(individual);
            populationVector.add(individual);
        }
    }
    private double distance = 0;

    private void calculatOwnWeight(Individual individual) {
        double sum = 0;
        ArrayList<Double> functionVector = individual.getFunctionVector();
        int objectNum = problem.getObjectNum();
        ArrayList<Double> idealIndividuals2 = problem.getIdealIndividuals();
        for (int i = 0; i < objectNum; i++) {
            double doubleValue = functionVector.get(i).doubleValue()
                    - idealIndividuals2.get(i).doubleValue();
            sum += doubleValue;
        }
        ArrayList<Double> arrayList = new ArrayList<Double>();
        for (int i = 0; i < objectNum; i++) {
            double doubleValue = functionVector.get(i).doubleValue()
                    - idealIndividuals2.get(i).doubleValue();
            arrayList.add(doubleValue / sum);
        }
        individual.setOwnWeightVector(arrayList);
    }

    @Override
    public void evoluation() {
    }

    public static void main(String[] args) {
        Algorithm algorithm = new MOEADpro2();
        algorithm.setProblem(new DTLZ1());
        algorithm.setCurrentGeneriticTime(0);
        algorithm.setDecomposition(new Tchebycheff());
        algorithm.setPopulationSize(100);
        algorithm.setmaxGenerationTimes(1000);
        algorithm.init();
        algorithm.evoluation();
    }
}