package algorithm.nsgaII;

import java.util.ArrayList;

import operate.Operate;
import solution.Individual;
import algorithm.Algorithm;

import comparator.DominatedComparator;

public class NSGAII extends Algorithm {

    DominatedComparator dominatedComparator = null;

    public NSGAII() {
        algorithmName = "NSGAII";
    }

    /**
     * 计算个体间的聚集距离
     *
     * @param P
     *            需要计算的集合
     */
    private void crowdingDistanceAssignment(ArrayList<Individual> P) {
        int N = P.size();
        for (int i = 0; i < N; i++) {
            P.get(i).setCrowdingDistance(0);
        }
        // if (N > 0) {
        // P.get(0).setCrowdingDistance(Double.MAX_VALUE);
        // P.get(N - 1).setCrowdingDistance(Double.MAX_VALUE);
        // }
        int objectNum = problem.getObjectNum();// 目标个数
        for (int m = 0; m < objectNum; m++) {
            P = sortScalarValueByObject(P, m);
            for (int i = 1; i < N - 1; i++) {
                Individual individual = P.get(i);
                double crowdingDistance = individual.getCrowdingDistance();
                crowdingDistance += P.get(i + 1).getFunctionVector().get(m)
                        - P.get(i - 1).getFunctionVector().get(m);
                individual.setCrowdingDistance(crowdingDistance);
            }
        }// end for object m
        if (N > 0) {
            P.get(0).setCrowdingDistance(Double.MAX_VALUE);
            P.get(N - 1).setCrowdingDistance(Double.MAX_VALUE);
        }
        return;
    }

    @Override
    public void evoluationOneTime() {
        currentGeneriticTime++;
        dominatedComparator = new DominatedComparator(problem.getObjectNum());// 支配关系比较器
        System.out.println("第" + currentGeneriticTime + "代");
        ArrayList<Individual> union = new ArrayList<Individual>();// 混合种群
        for (int i = 0; i < populationSize / 2; i++) {
            // 每次进化
            int p = (int) (Math.random() * populationSize);
            int q = (int) (Math.random() * populationSize);
            while (p == q) {
                q = (int) (Math.random() * populationSize);
            }
            Individual parent1 = populationVector.get(p);
            Individual parent2 = populationVector.get(q);
            Individual child1 = new Individual();
            Individual child2 = new Individual();
            operate.Operate.realBinaryCrossover(problem, parent1, parent2,
                    child1, child2);// 交叉
            Operate.realMutation(problem, child1);// 新个体1变异
            Operate.realMutation(problem, child2);// 新个体2变异
            problem.calculatorObject(child1);// 评价个体1
            problem.calculatorObject(child2);// 评价个体2
            evolutionTime++;
            evolutionTime++;
            union.add(child1);// 新个体1添加到新种群
            union.add(child2);// 新个体2添加到新种群
        }
        for (Individual individual : populationVector) {
            union.add(individual);
        }

        // ///////////// 快速非支配排序
        ArrayList<ArrayList<Individual>> F = fastNonDominatedSort(union);

        // /////////////构造新种群
        populationVector = new ArrayList<Individual>();
        int i = 0;
        ArrayList<Individual> arrayList = F.get(i);// 获取第i层
        while (populationVector.size() + arrayList.size() <= populationSize) {
            for (Individual individual : arrayList) {
                populationVector.add(individual);
            }
            i++;
            arrayList = F.get(i);
        }// end while
        // ///////////// 按照偏序关系排序
        crowdingDistanceAssignment(arrayList);// 计算聚集距离
        sortByPartialOrder(arrayList);
        for (int j = 0; populationVector.size() < populationSize; j++) {
            populationVector.add(arrayList.get(j));
        }
        // if (currentGeneriticTime % 20 == 0) {
        // printFunctionToFile(currentGeneriticTime / 20, "");
        // }
    }

    @Override
    public void evoluation() {

        currentGeneriticTime = 1;// 遗传代数设置为1
        printFunctionToFile(0, "");// 输出随机初始化目标值向量
        for (; currentGeneriticTime <= maxGeneration;) {
            evoluationOneTime();
        }
        printFunctionToFile();
    }

    /**
     * 快速非支配排序
     *
     * @param union
     *            混合种群
     * @return
     */
    private ArrayList<ArrayList<Individual>> fastNonDominatedSort(
            ArrayList<Individual> union) {
        int size = union.size();
        ArrayList<Integer> Pi = new ArrayList<Integer>();// 第i层的个体序列
        ArrayList<Individual> p0Individuals = new ArrayList<Individual>();// 第i层个体
        // ///////////计算n和s
        int[] n = new int[size];// n[i]支配个体i的个体数目
        ArrayList<ArrayList<Individual>> ranks = new ArrayList<ArrayList<Individual>>();// 分层后的解集总集
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] s = new ArrayList[size];// s[i] i支配的个体
        for (int i = 0; i < size; i++) {
            Individual p = union.get(i);
            s[i] = new ArrayList<Integer>();
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    continue;
                }
                Individual q = union.get(j);
                int compare = dominatedComparator.compare(p, q);
                if (compare == DominatedComparator.Domination) {// p支配q
                    s[i].add(j);
                } else if (compare == DominatedComparator.Dominated) {// p被q支配
                    n[i]++;
                }// end if
            }// end for q
            if (n[i] == 0) {
                Pi.add(i);
                Individual individual = union.get(i);
                individual.setRank(0);
                p0Individuals.add(individual);
            }
        }// end for p
        ranks.add(p0Individuals);
        int i = 1;
        // //////////计算非支配集P0、P1......
        while (!Pi.isEmpty()) {
            ArrayList<Integer> Hh = new ArrayList<Integer>();
            ArrayList<Individual> piIndividuals2 = new ArrayList<Individual>();// 第i层个体
            for (int j = 0; j < Pi.size(); j++) {
                // System.out.println();
                int p = Pi.get(j).intValue();
                ArrayList<Integer> Sp = s[p];
                for (int k = 0; k < Sp.size(); k++) {
                    int q = Sp.get(k).intValue();
                    n[q]--;
                    if (n[q] == 0) {
                        Hh.add(q);
                        Individual individual = union.get(q);
                        individual.setRank(i);
                        piIndividuals2.add(individual);
                        // System.out.print(q + "\t");
                    }
                }// end for q
            }// end for p
            i++;
            Pi = Hh;
            ranks.add(piIndividuals2);
        }// end while
        return ranks;
    }// end sort

    /**
     * 按照偏序关系排序
     *
     * @param f
     *            需要排序的集合
     */
    private void sortByPartialOrder(ArrayList<Individual> f) {
        // {
        // System.out.println();
        // for (Individual individual : f) {
        // System.out.print(individual + "\n");
        // }
        // }
        int size = f.size();// 解集的大小
        for (int i = 0; i < size - 1; i++) {
            Individual individuali = f.get(i);
            for (int j = i + 1; j < size; j++) {
                Individual individualj = f.get(j);
                boolean replaceable = false;
                int ranki = individuali.getRank();
                int rankj = individualj.getRank();
                if (rankj < ranki) {
                    replaceable = true;
                } else if (ranki == rankj) {
                    if (individualj.getCrowdingDistance() > individuali.getCrowdingDistance()) {
                        replaceable = true;
                    }
                }
                if (replaceable) {
                    f.set(i, individualj);
                    f.set(j, individuali);
                    individuali = f.get(i);
                }
            }// end for j
        }// end for i
        // {
        // System.out.println();
        // for (Individual individual : f) {
        // System.out.print(individual.getRank() + "_"
        // + individual.getCrowdingDistance() + "\t");
        // }
        // }
        // {
        // System.out.println("\n=============================");
        // for (Individual individual : f) {
        // System.out.print(individual + "\n");
        // }
        // }
        return;
    }

    /**
     * 按照m目标的函数值对解集排序
     *
     * @param p
     *            解集
     * @param m
     *            目标维数（第几维）
     * @return 排序后的解集
     */
    private ArrayList<Individual> sortScalarValueByObject(
            ArrayList<Individual> p, int m) {
        int size = p.size();// 解集的大小
        for (int i = 0; i < size - 1; i++) {
            double double1 = p.get(i).getFunctionVector().get(m).doubleValue();
            for (int j = i + 1; j < size; j++) {
                double double2 = p.get(j).getFunctionVector().get(m).doubleValue();
                if (double1 > double2) {// 执行交换
                    Individual individual1 = p.get(i);
                    Individual individual2 = p.get(j);
                    p.set(i, individual2);
                    p.set(j, individual1);
                    double1 = double2;
                }
            }
        }
        return p;
    }
}