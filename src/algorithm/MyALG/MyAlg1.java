package algorithm.MyALG;

import java.util.ArrayList;

import operate.Operate;
import problem.ZDT.ZDT1;
import solution.Individual;
import Util.OutPut;
import algorithm.Algorithm;

/**
 * @author 张作峰
 *
 */
public class MyAlg1 extends Algorithm {

    /**
     * @param args
     */
    public static void main(String[] args) {
        MyAlg1 myAlg1 = new MyAlg1();
        myAlg1.evoluation();
    }
    private double[] ideal;

    /**
     *
     */
    public MyAlg1() {
        algorithmName = "MyAlg1";
    }

//    private void calculateFitness(Individual individual) {
//        ArrayList<Double> functionVector = individual.getFunctionVector();
//        double fitness = 0;
//        for (int i = 0; i < ideal.length; i++) {
//            double doubleValue = functionVector.get(i).doubleValue();
//            fitness += Math.pow(doubleValue - ideal[i], 2);
//        }
//        fitness = 1 / fitness;
//        individual.setFitness(fitness);
//    }
    @Override
    public void evoluation() {
        populationVector = new ArrayList<Individual>();
        populationSize = 100;
        problem = new ZDT1();
        maxGeneration = 500;
        int objectNum = problem.getObjectNum();
        ideal = new double[objectNum];
        for (int i = 0; i < ideal.length; i++) {
            ideal[i] = Double.MAX_VALUE;
        }
        // 初始化
        System.out.println("初始化");
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual();
            problem.initData(individual);
            problem.calculatorObject(individual);
            problem.calculatOwnWeight(individual);
            populationVector.add(individual);
            for (int j = 0; j < ideal.length; j++) {
                double doubleValue = individual.getFunctionVector().get(j).doubleValue();
                if (doubleValue < ideal[j]) {
                    ideal[j] = doubleValue;
                }
            }
        }
        // 按照权重向量排序
        sortByWeight(populationVector);
        OutPut.printArray(ideal);
        printFunctionToFile();
        printWeightVectorToFile();
        printValueToFile();
        // 计算适应度
        System.out.println("计算适应度");
        for (int i = 0; i < populationSize; i++) {
            Individual individual = populationVector.get(i);
            if (i == populationSize - 1) {
                calculateFitness(populationVector, i, individual);
            } else {
                calculateFitness(populationVector, i, individual);
            }
        }

        for (int generation = 0; generation < maxGeneration; generation++) {
            System.out.println("第" + (generation + 1) + "代");
            // 选择
            populationVector = championShips(populationVector);
            sortByWeight(populationVector);
            for (int i = 0; i < populationSize; i++) {
                int p = (int) (Math.random() * populationSize);
                int q = (int) (Math.random() * populationSize);
                while (p == q) {
                    q = (int) (Math.random() * populationSize);
                }
                Individual parent1 = populationVector.get(p);
                Individual parent2 = populationVector.get(q);
                Individual child1 = new Individual();
                Individual child2 = new Individual();
                Operate.realBinaryCrossover(problem, parent1, parent2, child1,
                        child2);
                Operate.realMutation(problem, child1);
                problem.calculatorObject(child1);
                problem.calculatorObject(child2);
                problem.calculatOwnWeight(child1);
                problem.calculatOwnWeight(child2);
                calculateFitness(populationVector, p, child1);
                calculateFitness(populationVector, q, child2);
                if (child1.getFitness() > parent1.getFitness()) {
                    populationVector.set(p, child1);
                    for (int j = 0; j < ideal.length; j++) {
                        double doubleValue = child1.getFunctionVector().get(j).doubleValue();
                        if (doubleValue < ideal[j]) {
                            ideal[j] = doubleValue;
                        }
                    }
                    // 按照权重向量排序
                    sortByWeight(populationVector);
                }
                if (child2.getFitness() > parent1.getFitness()) {
                    populationVector.set(p, child2);
                    for (int j = 0; j < ideal.length; j++) {
                        double doubleValue = child2.getFunctionVector().get(j).doubleValue();
                        if (doubleValue < ideal[j]) {
                            ideal[j] = doubleValue;
                        }
                    }
                    // 按照权重向量排序
                    sortByWeight(populationVector);
                }
            }
        }
        sortByWeight(populationVector);
        OutPut.printArray(ideal);
        printFunctionToFile();
        printWeightVectorToFile();
        printValueToFile();
    }

    private void calculateFitness(ArrayList<Individual> populationVector,
            int index, Individual individual) {
        //第一步:计算参考点的距离
        double distance = -1;
        ArrayList<Double> functionVector = individual.getFunctionVector();
        for (int i = 0; i < functionVector.size(); i++) {
            double doubleValue = functionVector.get(i).doubleValue() - ideal[i];
            if (doubleValue <= 0) {
                distance = 1;
                break;
            } else if (doubleValue > distance) {
                distance = doubleValue;
            }
        }
        distance *= 10;
        //第二步：计算weight距离
        int currentI = -1;
        ArrayList<Double> weightVector = individual.getWeightVector();
        int i;
        int popSize = populationVector.size();
        for (i = 0; i < popSize; i++) {
            Individual individual1 = populationVector.get(i);
            ArrayList<Double> weightVector1 = individual1.getWeightVector();
            if (weightVectorCompear(weightVector, weightVector1)) {
                currentI = i;
                break;
            }
        }
        currentI--;
        if (i == popSize) {
            currentI = i;
        }
        double distance2 = 0;
        if (currentI < 0) {
            if (index == 0) {
                distance2 = distance(weightVector, populationVector.get(1).getWeightVector());
            } else {
                distance2 = distance(weightVector, populationVector.get(0).getWeightVector());
            }
        } else if (currentI >= popSize) {
            if (index == popSize - 1) {
                distance2 = distance(weightVector, populationVector.get(popSize - 2).getWeightVector());
            } else {
                distance2 = distance(weightVector, populationVector.get(popSize - 1).getWeightVector());
            }
        } else {
            int p1 = -1;
            int p2 = -2;
            if (currentI - 1 == index) {
                p1 = currentI - 2;
            } else {
                p1 = currentI - 1;
            }
            if (currentI + 1 == index) {
                p2 = currentI + 2;
            } else {
                p2 = currentI + 1;
            }
            if (p1 < 0) {
                distance2 = distance(weightVector, populationVector.get(p2).getWeightVector());
            } else if (p2 >= popSize) {
                distance2 = distance(weightVector, populationVector.get(p1).getWeightVector());
            } else {
                distance2 = distance(weightVector, populationVector.get(p1).getWeightVector());
                distance2 *= distance(weightVector, populationVector.get(p2).getWeightVector());
            }
            double fitness = 1000 * distance2 / distance;
            individual.setFitness(fitness);
        }
    }

    private void sortByWeight(ArrayList<Individual> populationVector) {
        System.out.println("sortByWeight");
        for (int i = 0; i < populationSize - 1; i++) {
            Individual individual1 = populationVector.get(i);
            ArrayList<Double> weightVector1 = individual1.getWeightVector();
            for (int j = i + 1; j < populationSize; j++) {
                Individual individual2 = populationVector.get(j);
                ArrayList<Double> weightVector2 = individual2.getWeightVector();
                if (weightVectorCompear(weightVector2, weightVector1)) {
                    populationVector.set(i, individual2);
                    populationVector.set(j, individual1);
                    individual1 = individual2;
                    weightVector1 = weightVector2;
                }
            }
        }
    }

    /**
     * 如果1比2小，返回true，否则返回false。
     * @param weight1
     * @param weight2
     * @return
     */
    private boolean weightVectorCompear(ArrayList<Double> weight1, ArrayList<Double> weight2) {
//        System.out.println("weightVectorCompear");
        int size = (weight1.size() + 1) / 2;
        int i = 0;
        while (i < size) {
            double doubleValue1 = weight1.get(i).doubleValue();
            double doubleValue2 = weight2.get(i).doubleValue();
            if (doubleValue1 < doubleValue2) {
                return true;
            }
            i++;
        }
        return false;
    }

    private double distance(ArrayList<Double> weightVector1, ArrayList<Double> weightVector2) {
        double distance = 0;
        for (int i = 0; i < weightVector1.size(); i++) {
            double pow = Math.pow(weightVector2.get(i) - weightVector1.get(i), 2);
            distance += pow;
        }
        distance = Math.sqrt(distance);
        return distance;
    }

    private ArrayList<Individual> championShips(ArrayList<Individual> popList) {
        sortByFitness(populationVector);
        double[] fitness = new double[populationSize];
        double sum = 0;

        for (int i = 0; i < populationSize; i++) {
            fitness[i] = popList.get(i).getFitness();
            sum += fitness[i];
        }
        for (int i = 0; i < populationSize; i++) {
            fitness[i] = fitness[i] / sum;
        }
        for (int i = 0; i < populationSize - 1; i++) {
            fitness[i + 1] = fitness[i + 1] + fitness[i];
        }

        ArrayList<Individual> al = new ArrayList<Individual>();
        for (int i = 0; i < populationSize; i++) {
            double random = Math.random();
            int j = 0;
            for (j = 0; j < populationSize; j++) {
                if (random < fitness[j]) {
                    break;
                }
            }
            al.add(popList.get(j));
        }
        return al;
    }

    private void sortByFitness(ArrayList<Individual> populationVector) {
        System.out.println("sortByWeight");
        for (int i = 0; i < populationSize - 1; i++) {
            Individual individual1 = populationVector.get(i);
            double fitness1 = individual1.getFitness();
            for (int j = i + 1; j < populationSize; j++) {
                Individual individual2 = populationVector.get(j);
                double fitness2 = individual2.getFitness();
                if (fitness1 < fitness2) {
                    populationVector.set(i, individual2);
                    populationVector.set(j, individual1);
                    individual1 = individual2;
                    fitness1 = fitness2;
                }
            }
        }
    }
}