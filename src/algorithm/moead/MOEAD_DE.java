package algorithm.moead;

import Util.Distance;
import algorithm.Algorithm;
import decomposition.Decomposition;
import decomposition.Tchebycheff;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import operate.Operate;
import problem.Problem;
import problem.ZDT.ZDT1;
import solution.Individual;

/**
 * MOEAD_DE:与MOEAD_original相比：增加了匹配约束和差分。
 *
 * @author 张作峰
 *
 */
public class MOEAD_DE extends Algorithm {

    public MOEAD_DE() {
        algorithmName = "MOEAD_DE";
    }

    public MOEAD_DE(String name) {
        super();
    }

    /**
     * 从sourceArray中获得最小的neightbourNum个系数
     *
     * @param sourceArray
     * @return
     */
    private int[] createNeiboringArray(double[] sourceArray) {
        int length1 = sourceArray.length;
        int indexArray[] = new int[length1];
        for (int i = 0; i < length1; i++) {
            indexArray[i] = i;
        }
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length1 - i - 1; j++) {
                if (sourceArray[j] > sourceArray[j + 1]) {
                    double temp = sourceArray[j];
                    sourceArray[j] = sourceArray[j + 1];
                    sourceArray[j + 1] = temp;

                    int ddi = indexArray[j];
                    indexArray[j] = indexArray[j + 1];
                    indexArray[j + 1] = ddi;
                }
            }
        }
        int[] b = new int[neiboringNum];
        System.arraycopy(indexArray, 0, b, 0, neiboringNum);

        return b;
    }

    /**
     * 进化一次
     */
    @Override
    public void evoluationOneTime() {
        oldPopulation.clear();
        for (int j = 0; j < populationSize; j++) {
            int[] nt = populationVector.get(j).getNeiboringTable();
            int neiborSize = nt.length;
            if (neiborSize <= 2) {
                JOptionPane.showMessageDialog(null, "neiborSize<=2");
                System.exit(0);
            }
            int ib, ic;
            double random = Math.random();
            if (random < 0.9) {// 在邻域内选择
                ib = (int) (Math.random() * neiborSize);
                ic = (int) (Math.random() * neiborSize);
                while (j == nt[ib]) {
                    ib = (int) (Math.random() * neiborSize);
                }
                while (j == nt[ic] || ic == ib) {
                    ic = (int) (Math.random() * neiborSize);
                }
                ib = nt[ib];
                ic = nt[ic];

            } else {// 在全局选择
                ib = (int) (Math.random() * populationSize);
                ic = (int) (Math.random() * populationSize);
                while (j == ib) {
                    ib = (int) (Math.random() * populationSize);
                }
                while (j == ic || ic == ib) {
                    ic = (int) (Math.random() * populationSize);
                }
            }
            Individual oldIndividual1 = populationVector.get(j);
            Individual oldIndividual2 = populationVector.get(ib);
            Individual oldIndividual3 = populationVector.get(ic);
            Individual newchildIndividual = new Individual();
            Operate.diffEvolutionCrossover(problem, oldIndividual1,
                    oldIndividual2, oldIndividual3, newchildIndividual);

            Operate.realMutation(problem, newchildIndividual);
            problem.calculatorObject(newchildIndividual);
            update_problem(newchildIndividual, j);
            update_reference(newchildIndividual);
        }
    }

    /**
     * 进化 1.选择、交叉、变异 2.
     */
    @Override
    public void evoluation() {
        for (currentGeneriticTime = 1; currentGeneriticTime <= maxGeneration; currentGeneriticTime++) {
            evoluationOneTime();
        }
    }

    /**
     * 种群的初始化-生成populationSize大小的种群
     *
     */
    @Override
    public void init() {
        // 生成populationSize大小的种群
        populationVector = new ArrayList<Individual>();
        oldPopulation = new ArrayList<Individual>();
        if (problem.getObjectNum() == 2) {
            Individual individual = null;
            ArrayList<Double> weightVector = null;
            for (int i = 0; i < H; i++) {
                individual = new Individual();
                weightVector = new ArrayList<Double>();
                weightVector.add((double) (i + 1) / H);
                weightVector.add((double) (H - 1 - i)
                        / H);
                individual.setWeightVector(weightVector);
                populationVector.add(individual);
            }
        } else if (problem.getObjectNum() == 3) {
            Individual individual = null;
            ArrayList<Double> weightVector = null;
            for (int i = 0; i <= H; i++) {
                for (int j = 0; j <= H; j++) {
                    if (i + j <= H) {
                        int k = H - i - j;
                        individual = new Individual();
                        weightVector = new ArrayList<Double>();
                        double[] weight = new double[3];
                        weight[0] = i / (double) H;
                        weight[1] = j / (double) H;
                        weight[2] = k / (double) H;
                        weightVector.add(weight[0]);
                        weightVector.add(weight[1]);
                        weightVector.add(weight[2]);
                        individual.setWeightVector(weightVector);
                        populationVector.add(individual);
                    }
                }
            }
        }

        populationSize = populationVector.size();

        for (int i = 0; i < populationSize; i++) {
            Individual individual = populationVector.get(i);
            if (problem != null) {// 个体编码的初始化
                problem.initData(individual);
                problem.calculatorObject(individual);
            } else {
                JOptionPane.showMessageDialog(null,
                        "请在调用Population.init()之前指定测试问题!");
                return;
            }
        }
        initNeightbouringTable();
        initReferenceVectorAndIdealVector();
        System.out.println(populationSize);
    }

    /**
     * 邻居集的初始化
     */
    public void initNeightbouringTable() {
        for (int i = 0; i < populationSize; i++) {
            double[] distanceArray = new double[populationSize];
            Individual individual = populationVector.get(i);
            ArrayList<Double> weightVector1 = individual.getWeightVector();
            for (int j = 0; j < populationSize; j++) {
                if (i == j) {
                    distanceArray[j] = 0;
                    continue;
                }
                ArrayList<Double> weightVector2 = populationVector.get(j).getWeightVector();
                distanceArray[j] = Distance.EDiastance(weightVector1,
                        weightVector2);
            }
            int[] createNeiboringArray = createNeiboringArray(distanceArray);
            individual.setNeiboringTable(createNeiboringArray);
            populationVector.set(i, individual);
        }
    }

    /**
     * 初始化理想点和参考点
     */
    private void initReferenceVectorAndIdealVector() {
        if (neiboringNum == -1) {
            JOptionPane.showMessageDialog(null,
                    "请在使用Population.initReferenceVectorAndIdealVector()之前设置邻居集大小!");
            System.err.println("Population.initReferenceVectorAndIdealVector() break;");
        }
        idealIndividuals = new ArrayList<Double>();
        referenceVector = new ArrayList<Individual>();
        for (int i = 0; i < problem.getObjectNum(); i++) {
            Individual individual = populationVector.get(0);
            referenceVector.add(individual);
            Double double1 = individual.getFunctionVector().get(i);
            idealIndividuals.add(double1);
        }
    }

    public void print() {
        for (int i = 0; i < populationVector.size(); i++) {
            Individual individual = populationVector.get(i);
            System.out.println("个体 " + i);
            String variableToString = individual.variableToString();
            System.out.println("变量\n" + variableToString);
            String functionToString = individual.functionToString();
            System.out.println("目标值\n" + functionToString);
            String neiboringToString = individual.neiboringToString();
            System.out.println("邻居\n" + neiboringToString);
        }
    }

    /**
     * 设置理想点
     *
     * @param idealIndividuals 理想点向量 the idealIndividuals to set
     */
    public void setIdealIndividuals(ArrayList<Double> idealIndividuals) {
        this.idealIndividuals = idealIndividuals;
    }

    /**
     * 设置参考点
     *
     * @param referenceVector 参考点
     */
    public void setReferenceVector(ArrayList<Individual> referenceVector) {
        this.referenceVector = referenceVector;
    }

    /**
     * update the best solutions of neighboring subproblems
     *
     * @param indiv
     * @param id
     */
    public void update_problem(Individual indiv, int id) {
        for (int i = 0; i < neiboringNum; i++) {
            int k = populationVector.get(id).getNeiboringTable()[i];
            double f1, f2;
            f1 = decomposition.comput(problem.getObjectNum(), populationVector.get(k).getFunctionVector(), populationVector.get(k).getWeightVector(), idealIndividuals, referenceVector);
            f2 = decomposition.comput(problem.getObjectNum(), indiv.getFunctionVector(), populationVector.get(k).getWeightVector(), idealIndividuals, referenceVector);
            if (f2 < f1) {
                Individual individual = populationVector.get(k);
                try {
                    Individual oldIndividual = individual.clone();
                    oldPopulation.add(oldIndividual);
                } catch (CloneNotSupportedException ex) {
                }
                individual.setVariableVector(indiv.getVariableVector());
                individual.setFunctionVector(indiv.getFunctionVector());
                populationVector.set(k, individual);
            }
        }
    }

    /**
     * 更新参考点
     *
     * @param ind 新加入的点
     */
    public void update_reference(Individual ind) {
        for (int n = 0; n < problem.getObjectNum(); n++) {
            if (ind.getFunctionVector().get(n) < idealIndividuals.get(n)) {
                idealIndividuals.set(n, ind.getFunctionVector().get(n));
                referenceVector.set(n, ind);
            }
        }
    }

    /**
     * 升级参考点和理想点
     */
    public void updateReferenceVectorAndIdealVector() {
        for (int i = 1; i < populationSize; i++) {
            Individual individual = populationVector.get(i);
            ArrayList<Double> functionVector = individual.getFunctionVector();
            for (int j = 0; j < problem.getObjectNum(); j++) {
                Double double1 = functionVector.get(j);
                Double double2 = idealIndividuals.get(j);
                if (double2 > double1) {// 最小化问题
                    idealIndividuals.set(j, double1);
                    referenceVector.set(j, individual);
                }
            }
        }
    }

    public static void main(String[] args) {
        MOEAD_DE moead_DE = new MOEAD_DE();
        Problem problem = new ZDT1();
        Decomposition decomposition = new Tchebycheff();
        moead_DE.setProblem(problem);
        moead_DE.setDecomposition(decomposition);
        moead_DE.setH(99);
        moead_DE.setNeiboringNum(20);
        moead_DE.init();
        moead_DE.setmaxGenerationTimes(100);
        moead_DE.evoluation();
        moead_DE.printFunctionToFile();
    }
}
