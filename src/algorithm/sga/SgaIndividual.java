package algorithm.sga;

public class SgaIndividual implements Cloneable {
	/**
	 * 编码长度
	 */
	private static int chromeLength = 20;
	/**
	 * 评价代数
	 */
	private static int evaluationTimes = 0;

	public static void setChromeLength(int chromeLength2) {
		SgaIndividual.chromeLength = chromeLength2;
	}

	/**
	 * 染色体
	 */
	private int[] chrome = null;

	/**
	 * 适应度
	 */
	private double fitness = 0;
	/**
	 * 自变量值
	 */
	private double value = 0;

	/**
	 * 函数值
	 */
	private double fun = 0;

	/**
	 * 计算对应自变量函数值
	 *
	 * @return 自变量函数值
	 */
	public double evaluate() {
		evaluationTimes++;

		value = 0;
		for (int i = 0; i < chromeLength; i++) {
			value += chrome[i] * Math.pow(2, (chromeLength - i - 1));
		}
		double d = Math.pow(2, chromeLength);
		value = 2 - 4 * (d - value) / (d - 1);
		fun = value * value + 2 * value + 1;

		fitness = (double) (1) / Math.abs(fun);
		return fun;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		SgaIndividual individual = new SgaIndividual();
		int[] chrome2 = chrome.clone();
		individual.setChrome(chrome2);
		individual.evaluate();
		return individual;
	}

	/**
	 * 获取染色体编码
	 *
	 * @return 染色体编码
	 */
	public int[] getChrome() {
		return chrome;
	}

	/**
	 * 获取适应度
	 *
	 * @return 适应度
	 */
	public double getfitness() {
		return fitness;
	}

	/**
	 * 获取函数值
	 *
	 * @return 函数值
	 */
	public double getFun() {
		return fun;
	}

	/**
	 * 随机初始化编码
	 */
	public void initCode() {
		chrome = new int[chromeLength];
		for (int i = 0; i < chromeLength; i++) {
			int d = (int) (Math.random() * 2);
			chrome[i] = d;
		}
	}

	/**
	 * 设置染色体编码
	 *
	 * @param chrome
	 *            染色体编码
	 */
	public void setChrome(int[] chrome) {
		this.chrome = chrome;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < chromeLength; i++) {
			s += chrome[i] + "  ";
		}
		s += "  ";
		// s += value + "\t";
		s += fun + "\t";
		s += evaluationTimes + "";
		// s += fitness;
		return s;
	}

	public static void setEvaluationTime(int i) {
		evaluationTimes = i;
	}

	public static int getEvaluationTime() {
		return evaluationTimes;
	}
}