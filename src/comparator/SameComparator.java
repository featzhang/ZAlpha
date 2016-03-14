/**
 * 
 */
package comparator;

import java.util.ArrayList;
import java.util.Comparator;

import solution.Individual;

/**
 * 比较器，判断是否相同 相同返回1，不同返回0
 * 
 * @author 张作峰
 * 
 */
public class SameComparator implements Comparator<Individual> {

	@Override
	public int compare(Individual arg0, Individual arg1) {
		ArrayList<Double> functionVector = arg0.getFunctionVector();
		ArrayList<Double> functionVector2 = arg1.getFunctionVector();
		for (int i = 0; i < functionVector.size(); i++) {
			double doubleValue = functionVector.get(i).doubleValue();
			double doubleValue2 = functionVector2.get(i).doubleValue();
			if (doubleValue != doubleValue2) {
				return 0;
			}
		}
		return 1;
	}

}
