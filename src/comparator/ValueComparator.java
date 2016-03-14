package comparator;

import java.util.Comparator;

public class ValueComparator implements Comparator<Double> {

	@Override
	public int compare(Double o1, Double o2) {
		double doubleValue = o1.doubleValue();
		double doubleValue2 = o2.doubleValue();
		if (doubleValue > doubleValue2) {
			return 1;
		}
		return 0;
	}

}
