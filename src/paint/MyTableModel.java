package paint;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import solution.Individual;

public class MyTableModel extends AbstractTableModel {

    /**
     * 模式
     */
    private int mode = -1;
    private static final long serialVersionUID = 1L;
    /**
     * 显示函数值模式
     */
    public static final int FUNCTIONMODE = 2;
    /**
     * 显示自变量模式
     */
    public static final int VALUEMODE = 1;
    public static final int WEIGHTMODE = 0;
    /**
     * 种群<code>ArrayList<Individual></code>
     */
    private ArrayList<Individual> population = null;

    public MyTableModel(ArrayList<Individual> population, int mode) {
        super();
        this.population = population;
        this.mode = mode;
    }

    @Override
    public int getRowCount() {
        if (population != null) {
            return population.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        if (population != null) {
            Individual individual = population.get(0);
            if (mode == FUNCTIONMODE || mode == WEIGHTMODE) {
                return individual.getFunctionVector().size();
            } else if (mode == VALUEMODE) {
                return individual.getVariableNum();
            }
        }
        return 0;
    }

    @Override
    public String getColumnName(int columnIndex) {

        return "" + (columnIndex + 1);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (population != null) {
            Individual individual = population.get(rowIndex);
            if (mode == FUNCTIONMODE) {
                return individual.getFunctionVector().get(columnIndex);
            } else if (mode == VALUEMODE) {
                return individual.getVariableVector().get(columnIndex);
            } else if (mode == WEIGHTMODE) {
                return individual.getWeightVector().get(columnIndex);
            }
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
}