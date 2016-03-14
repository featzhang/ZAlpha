package paint;

import decomposition.Decomposition;
import decomposition.PenaltybasedBoundaryIntersection;
import decomposition.Tchebycheff;
import decomposition.WeightedSum;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import solution.Individual;

/**
 * 
 * @author 张作峰
 */
@SuppressWarnings("serial")
public class PaintTableModel extends AbstractTableModel {

	private ArrayList<Individual> population = null;
	ArrayList<Individual> referenceVector = null;
	private ArrayList<Double> idealArrayList = null;
	private Decomposition decomposition = null;
	private int functionSize = 0;

	public void setReferenceVector(ArrayList<Individual> referenceVector) {
		this.referenceVector = referenceVector;
	}

	public void setPopulation(ArrayList<Individual> population) {
		this.population = population;
	}

	public void setIdealArrayList(ArrayList<Double> idealArrayList) {
		this.idealArrayList = idealArrayList;
	}

	@Override
	public int getRowCount() {
		if (population != null) {
			return population.size();
		} else {
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		if (population == null) {
			return 0;
		} else {
			Individual individual = population.get(0);
			if (individual == null) {
				return 0;
			}
			int columnI = 0;
			columnI += 1;// 序号
			functionSize = individual.getFunctionVector().size();
			columnI += functionSize;
			columnI += 1;// 指定权重向量
			columnI += 3;// 三种聚合方法
			columnI += 1;// 适合权重向量
			columnI += 3;// 三种聚合方法
			columnI += 1;// 邻居
			return columnI;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (population == null) {
			return null;
		}
		if (columnIndex == 0) {
			// 行号
			return rowIndex;
		} else if (columnIndex < 1 + functionSize) {
			// 函数值
			return population.get(rowIndex).getFunctionVector()
					.get(columnIndex - 1);
		} else if (columnIndex == 1 + functionSize) {
			// 指定權重向量
			ArrayList<Double> weightVector = population.get(rowIndex)
					.getWeightVector();
			if (weightVector == null) {
				return "";
			}
			String s = "[";
			int i;
			for (i = 0; i < weightVector.size() - 1; i++) {
				double doubleValue = weightVector.get(i).doubleValue();
				s += doubleValue + ",";
			}
			if (i == weightVector.size() - 1) {
				double doubleValue = weightVector.get(i).doubleValue();
				s += doubleValue + "]";
			}
			return s;
		} else if (columnIndex < 1 + functionSize + 3 + 1) {
			// 三种分解方法
			int i = columnIndex - 1 - functionSize - 1;
			Individual individual = population.get(rowIndex);
			ArrayList<Double> weightVector = individual.getWeightVector();
			if (weightVector == null || weightVector.size() <= 0) {
				return "";
			}
			ArrayList<Double> functionVector = individual.getFunctionVector();
			double comput = 0;
			switch (i) {
			case 0:
				decomposition = new WeightedSum();
				break;
			case 1:
				decomposition = new Tchebycheff();
				break;
			case 2:
				decomposition = new PenaltybasedBoundaryIntersection();
				break;

			}
			comput = decomposition.comput(functionSize, functionVector,
					weightVector, idealArrayList, referenceVector);
			return comput;
		} else if (columnIndex == 1 + functionSize + 3 + 1) {
			return population.get(rowIndex).getOwnWeightVector();
		} else if (columnIndex < 1 + functionSize + 3 + 1 + 3 + 1) {
			int i = columnIndex - 1 - functionSize - 3 - 1 - 1;
			Individual individual = population.get(rowIndex);
			ArrayList<Double> weightVector = individual.getOwnWeightVector();
			if (weightVector == null) {
				return "";
			}
			if (decomposition == null) {
				return null;
			}
			ArrayList<Double> functionVector = individual.getFunctionVector();
			double comput = 0;
			switch (i) {
			case 0:
				decomposition = new WeightedSum();
				break;
			case 1:
				decomposition = new Tchebycheff();
				break;
			case 2:
				decomposition = new PenaltybasedBoundaryIntersection();
				break;

			}
			comput = decomposition.comput(functionSize, functionVector,
					weightVector, idealArrayList, referenceVector);
			return comput;
		} else {
			// System.out.println(rowIndex);
			int[] neiboringTable = population.get(rowIndex).getNeiboringTable();
			if (neiboringTable == null) {
				return "";
			}
			String s = "[";
			for (int i = 0; i < neiboringTable.length - 1; i++) {
				int j = neiboringTable[i];
				s += (1 + j) + ",";
			}
			if (neiboringTable != null && neiboringTable.length > 0) {
				s += (neiboringTable[neiboringTable.length - 1] + 1) + "]";
				// TO DO
			}
			return s;
		}
	}

	@Override
	public String getColumnName(int column) {
		if (population == null) {
			return "";
		}
		functionSize = population.get(0).getFunctionVector().size();
		if (column == 0) {
			return "序号";
		} else if (column < functionSize + 1) {
			return "函数值" + column;
		} else if (column == functionSize + 1) {
			return "指定权重向量";
		} else if (column < functionSize + 5) {
			switch (column - functionSize - 2) {
			case 0:
				return "权重聚合";
			case 1:
				return "切比雪夫";
			case 2:
				return "PBI";
			}
		} else if (column == functionSize + 5) {
			return "适合权重向量";
		} else if (column < functionSize + 9) {
			switch (column - functionSize - 6) {
			case 0:
				return "权重聚合";
			case 1:
				return "切比雪夫";
			case 2:
				return "PBI";
			}
		} else {
			return "邻居";
		}
		return "";
	}
}