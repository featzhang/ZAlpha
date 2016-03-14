package indicate;

import Util.Distance;

/**
 * Inverted generational distance
 * 
 * @author 张作峰
 */
public class IGD {
	/**
	 * 计算IGD值
	 * 
	 * @param p
	 *            待测试的数据
	 * @param px
	 *            真实Pareto面数据
	 * @return IGD值
	 */
	public static double calculator(double[][] p, double[][] px) {
		double distance = 0;
		for (int i = 0; i < px.length; i++) {
			double[] v = px[i];
			double d = findNearest(p, v);
			distance += d;
		}

		return distance / (p.length);
	}

	private static double findNearest(double[][] p, double[] v) {
		double nearsetValue = Double.MAX_VALUE;
		for (int i = 0; i < p.length; i++) {
			double[] ds = p[i];
			double eDiastance = Distance.EDiastance(ds, v);
			if (eDiastance < nearsetValue) {
				nearsetValue = eDiastance;
			}
		}
		return nearsetValue;
	}

}
