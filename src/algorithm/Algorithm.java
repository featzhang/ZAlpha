package algorithm;

import Util.OutPut;
import decomposition.Decomposition;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import problem.Problem;
import solution.Individual;

/**
 * 算法类 (尽量的减少参数设定)
 *
 * @author 张作峰
 *
 */
public abstract class Algorithm {

    public Algorithm(String name) {
        this.algorithmName = name;
    }

    public Algorithm() {
    }
    /**
     * 分解方法，默认是Tchebycheff分解方法
     */
    protected Decomposition decomposition = null;
    /**
     * 种群向量
     */
    protected ArrayList<Individual> populationVector = null;
    /**
     * 最大评价次数
     */
    protected int evolutionMaxTimes = 0;
    /**
     * 当前评价次数
     */
    protected int evolutionTime = 1;
    /**
     * 测试问题,默认是<code>new DTLZ1()</code>
     */
    protected Problem problem = null;
    /**
     * 种群大小，默认是100个个体
     */
    protected int populationSize = 100;
    /**
     * 算法名称
     */
    protected String algorithmName = null;
    /**
     * 遗传代数
     */
    protected int maxGeneration = -1;
    /**
     * 当前遗传代数
     */
    protected int generationTime = -1;
    protected int H = 10;
    /**
     * 邻居集大小
     */
    protected int neiboringNum = -1;
    /**
     * 理想点，用于存储理想点目标值
     */
    protected ArrayList<Double> idealIndividuals = null;
    /**
     * 参考集
     */
    protected ArrayList<Individual> referenceVector = null;
    /**
     * 现在进化到的代数
     */
    protected int currentGeneriticTime = 0;
    /**
     * 旧数据，保存已被删除的数据
     */
    protected ArrayList<Individual> oldPopulation = null;

    /**
     * 进化，调用<code>public void evoluationOneTime()</code>方法</br> 调用前将
     * <code>currentGeneriticTime</code>置零</br> 进化 1.选择、交叉、变异 2.
     */
    public abstract void evoluation();

    /**
     * 进化一次
     */
    public void evoluationOneTime() {
    }

    /**
     * 获取当前进化代数
     *
     * @return 当前进化代数
     */
    public int getCurrentGeneriticTime() {
        return currentGeneriticTime;
    }

    /**
     * 获取评价次数
     *
     * @return 评价次数
     */
    public int getEvaluateTimes() {
        return problem.getEvaluateTimes();
    }

    /**
     * 获取理想点
     *
     * @return 理想点
     */
    public ArrayList<Double> getIdealIndividuals() {
        return idealIndividuals;
    }

    /**
     * 获取最大评价次数
     *
     * @return 评价次数
     */
    public int getMaxEvolutionTimes() {
        return evolutionMaxTimes;
    }

    /**
     * 获取旧数据
     *
     * @return 旧数据
     */
    public ArrayList<Individual> getOldPopulation() {
        return oldPopulation;
    }

    /**
     * 获取种群
     *
     * @return 种群
     */
    public ArrayList<Individual> getPopulation() {
        return populationVector;
    }

    /**
     * 种群大小
     *
     * @return 种群大小
     */
    public int getPopulationSize() {
        return populationSize;
    }

    /**
     * @return
     */
    public ArrayList<Individual> getReferenceVector() {
        return referenceVector;
    }

    public void init() {

        evolutionTime = 0;
        problem.setEvaluateTimes(0);
        // 生成populationSize大小的种群
        populationVector = new ArrayList<Individual>();
        if (problem == null) {
            JOptionPane.showMessageDialog(null, "problem is missing!");
            System.exit(0);
        }
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual();
            problem.initData(individual);
            problem.calculatorObject(individual);
            problem.calculatOwnWeight(individual);
            // System.out.println(problem);
            evolutionTime++;
            populationVector.add(individual);
        }
    }

    public final void printFunctionToFile() {
        printFunctionToFile(-1);
    }

    /**
     * 把目标值写到文件中
     *
     * @param generationTime 遗传代数
     */
    public final void printFunctionToFile(int generationTime,
            String... moreParameter) {
        printToFile(0, generationTime, moreParameter);
    }

    /**
     * 输出适合权重向量
     */
    protected void printOwnWeightVectorToFile() {
    }

    /**
     * 写入文件
     *
     * @param mode 0-函数值/1-变量值
     * @param geneticTime 遗传代数
     */
    private void printToFile(int mode, int geneticTime, String... moreParameter) {
        String fileSeparator = System.getProperty("file.separator");
        String fileName = "result";
        fileName += fileSeparator + algorithmName;
        fileName += fileSeparator + problem.getName();
        fileName += fileSeparator + algorithmName;
        fileName += "_" + problem.getName();

        if (decomposition != null) {
            fileName += "_" + decomposition.getName();
        }
        for (String string : moreParameter) {
            if (string != null && string.trim().equals("")) {
                fileName += "_" + string;
            }
        }
        if (geneticTime != -1) {
            fileName += "_" + geneticTime;
        } else {
            fileName += "_Final";
        }
        if (mode == 0) {// 函数值
            fileName += "_Function.txt";

            OutPut.printfunctionToFile(populationVector, fileName);
        } else if (mode == 1) {// 变量
            fileName += "_Value.txt";
            OutPut.printVariableToFile(populationVector, fileName);
        } else if (mode == 2) {// 权重向量
            fileName += "_Weight.txt";
            OutPut.printWeightVectorToFile(populationVector, fileName);
        } else if (mode == 3) {// 适合的权重向量
            fileName += "_OwnWeight.txt";
            OutPut.printOwnWeightVectorToFile(populationVector, fileName);
        }

    }

    public void printValueToFile() {
        printValueToFile(-1);
    }

    /**
     * 把变量写到文件中
     *
     * @param geneticTime 遗传代数
     */
    public final void printValueToFile(int geneticTime, String... moreParameter) {
        printToFile(1, geneticTime, moreParameter);
    }

    public void printWeightVectorToFile() {
        printWeightVectorToFile(-1);
    }

    public final void printWeightVectorToFile(int geneticTime,
            String... moreParameter) {
        printToFile(2, geneticTime, moreParameter);
    }

    /*
     * 获取参考点
     */
    public void setCurrentGeneriticTime(int currentGeneriticTime) {
        this.currentGeneriticTime = currentGeneriticTime;
    }

    /*
     * 进化一次
     */
    public void setDecomposition(Decomposition decomposition) {
        this.decomposition = decomposition;
    }

    /**
     * 设置评价次数
     *
     * @param evolutionTimes 设置评价次数
     */
    public void setEvolutionTimes(int evolutionTimes) {
        this.evolutionMaxTimes = evolutionTimes;
    }

    /*
     * 获取当前进化到的代数
     */
    /**
     * 设置进化代数
     *
     * @param genetionTimes 进化代数
     */
    public void setmaxGenerationTimes(int genetionTimes) {
        this.maxGeneration = genetionTimes;
    }

    /*
     * 设置当前进化代数
     */
    public void setH(int H) {
        this.H = H;
    }

    public int getH() {
        return H;
    }

    public void setMaxGeneration(int maxGeneration) {
        this.maxGeneration = maxGeneration;
    }

    public void setNeiboringNum(int neiboringNum) {
        Individual.setNeiboringNum(neiboringNum);
        this.neiboringNum = neiboringNum;
    }

    public int getNeiboringNum() {
        return neiboringNum;
    }

    /**
     * 设置种群大小
     *
     * @param populationSize 种群大小
     */
    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    /**
     * 设置测试问题
     *
     * @param problem 测试问题
     */
    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    protected void addIndividualToOldPopulation(Individual oldIndividual) {
        try {
            Individual clone = oldIndividual.clone();
            oldPopulation.add(clone);
        } catch (CloneNotSupportedException ex) {
        }
    }

    public String getName() {
        return algorithmName;
    }
}