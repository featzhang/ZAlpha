package paint;

import Util.OutPut;
import Util.ZFile;
import Util.ZMatrix;
import Util.ZResource;
import algorithm.Algorithm;
import algorithm.moead.*;
import algorithm.nsgaII.NSGAII;
import decomposition.*;
import indicate.IGD;
import indicate.Indicate;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import problem.DTLZ.*;
import problem.MOP.MOP1;
import problem.Problem;
import problem.UF.UF1;
import problem.UF.UF2;
import problem.ZDT.*;
import solution.Individual;

public class PaintDialog extends javax.swing.JFrame {

    public PaintDialog() {
        algorithms.add(new MOEADpro());
        algorithms.add(new MOEAD_ORI());
        algorithms.add(new MOEAD_DE());
        algorithms.add(new MOEAD_DRA());
        algorithms.add(new NSGAII());
        algorithms.add(new MOEADpro2());
        problems.add(new ZDT1());
        problems.add(new ZDT2());
        problems.add(new ZDT3());
        problems.add(new ZDT4());
        problems.add(new ZDT6());
        problems.add(new DTLZ1());
        problems.add(new DTLZ2());
        problems.add(new DTLZ3());
        problems.add(new DTLZ4());
        problems.add(new DTLZ5());
        problems.add(new UF1());
        problems.add(new UF2());
        problems.add(new MOP1());

        paintTableModel = new PaintTableModel();
        initComponents();
        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        algorithmComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        problemComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        decomposionComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        objectNumTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        hFormattedTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        popSizeTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        TFormattedTextField = new javax.swing.JTextField();
        initButton = new javax.swing.JButton();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        currentGeneticTimesFormattedTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        evalueTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        eliminationNumTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        idealTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        IGDTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        givenNextGeneticTimeFormattedTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        nextGeneritionButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        rotatePanel = new javax.swing.JPanel();
        rotateVelocitySpinner = new javax.swing.JSpinner();
        autoButton = new javax.swing.JButton();
        defaultTurnButton = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        showOldDataCheckBox = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        xPlusyEqual1CheckBox = new javax.swing.JCheckBox();
        intersectionWithXplusYequal1CheckBox = new javax.swing.JCheckBox();
        showParetoFrontCheckBox = new javax.swing.JCheckBox();
        xySameCheckBox = new javax.swing.JCheckBox();
        showDrawBorderCheckBox = new javax.swing.JCheckBox();
        saveDataButton = new javax.swing.JButton();
        pbiLabel = new javax.swing.JLabel();
        pbiUTextField = new javax.swing.JTextField();
        at_pLabel1 = new javax.swing.JLabel();
        at_pTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        openFileButton = new javax.swing.JButton();
        IGDButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree(dirRoot);
        javax.swing.JTabbedPane jTabbedPane2 = new javax.swing.JTabbedPane();
        mainDrawPanel = new javax.swing.JPanel();
        paintPanel2D = new paint.PaintPanel2D();
        paintPanel3D = new paint.PaintPanel3D();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(ZResource.getAppTitle());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(6);
        jSplitPane1.setOneTouchExpandable(true);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jLabel1.setText("算法");

        algorithmComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        algorithmComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                algorithmComboBoxItemStateChanged(evt);
            }
        });

        jLabel2.setText("问题");

        problemComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        problemComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                problemComboBoxItemStateChanged(evt);
            }
        });

        jLabel3.setText("聚合方法");

        decomposionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        decomposionComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                decomposionComboBoxItemStateChanged(evt);
            }
        });

        jLabel4.setText("目标个数");

        objectNumTextField.setText("2");

        jLabel5.setText("H");

        hFormattedTextField.setText("10");
        hFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hFormattedTextFieldFocusLost(evt);
            }
        });

        jLabel6.setText("种群大小");

        popSizeTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                popSizeTextFieldFocusLost(evt);
            }
        });

        jLabel7.setText("邻居个数T");

        TFormattedTextField.setText("2");
        TFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TFormattedTextFieldFocusLost(evt);
            }
        });

        initButton.setText("初始化");
        initButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("当前：第");

        currentGeneticTimesFormattedTextField.setText("0");

        jLabel9.setText("代");

        jLabel10.setText("评价");

        evalueTextField.setText("0");

        jLabel11.setText("次");

        jLabel12.setText("淘汰个数");

        eliminationNumTextField.setText("0");

        jLabel13.setText("参考点");

        jLabel14.setText("IGD");

        jLabel15.setText("进化到第");

        givenNextGeneticTimeFormattedTextField.setText("1");
        givenNextGeneticTimeFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                givenNextGeneticTimeFormattedTextFieldFocusLost(evt);
            }
        });
        givenNextGeneticTimeFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                givenNextGeneticTimeFormattedTextFieldKeyPressed(evt);
            }
        });

        jLabel16.setText("代");

        nextGeneritionButton.setText("进化");
        nextGeneritionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextGeneritionButtonActionPerformed(evt);
            }
        });

        jButton3.setText("停止");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        rotateVelocitySpinner.setModel(new javax.swing.SpinnerNumberModel(0.02d, 0.005d, 0.15d, 0.001d));
        rotateVelocitySpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rotateVelocitySpinner.setName("");
        rotateVelocitySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rotateVelocitySpinnerStateChanged(evt);
            }
        });

        autoButton.setText("旋转");
        autoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoButtonActionPerformed(evt);
            }
        });

        defaultTurnButton.setText("O");
        defaultTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultTurnButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rotatePanelLayout = new javax.swing.GroupLayout(rotatePanel);
        rotatePanel.setLayout(rotatePanelLayout);
        rotatePanelLayout.setHorizontalGroup(
            rotatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rotatePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(defaultTurnButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rotateVelocitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoButton)
                .addGap(22, 22, 22))
        );
        rotatePanelLayout.setVerticalGroup(
            rotatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rotatePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(rotatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(defaultTurnButton)
                    .addComponent(autoButton)
                    .addComponent(rotateVelocitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jSlider1.setMaximum(30);
        jSlider1.setMinimum(1);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setValue(6);
        jSlider1.setFocusCycleRoot(true);
        jSlider1.setValueIsAdjusting(true);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        showOldDataCheckBox.setSelected(true);
        showOldDataCheckBox.setText("显示淘汰个体");
        showOldDataCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                showOldDataCheckBoxItemStateChanged(evt);
            }
        });

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("绘制权重向量");
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });

        jCheckBox3.setSelected(true);
        jCheckBox3.setText("绘制指定权重向量");
        jCheckBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox3ItemStateChanged(evt);
            }
        });

        jCheckBox4.setSelected(true);
        jCheckBox4.setText("显示个体坐标");
        jCheckBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox4ItemStateChanged(evt);
            }
        });

        jCheckBox5.setSelected(true);
        jCheckBox5.setText("十字线");
        jCheckBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox5ItemStateChanged(evt);
            }
        });

        xPlusyEqual1CheckBox.setSelected(true);
        xPlusyEqual1CheckBox.setText("x+y=1");
        xPlusyEqual1CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                xPlusyEqual1CheckBoxItemStateChanged(evt);
            }
        });

        intersectionWithXplusYequal1CheckBox.setSelected(true);
        intersectionWithXplusYequal1CheckBox.setText("在x+y=1上的分布");
        intersectionWithXplusYequal1CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                intersectionWithXplusYequal1CheckBoxItemStateChanged(evt);
            }
        });

        showParetoFrontCheckBox.setSelected(true);
        showParetoFrontCheckBox.setText("最优Pareto面");
        showParetoFrontCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                showParetoFrontCheckBoxItemStateChanged(evt);
            }
        });

        xySameCheckBox.setSelected(true);
        xySameCheckBox.setText("坐标轴一致");
        xySameCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                xySameCheckBoxItemStateChanged(evt);
            }
        });

        showDrawBorderCheckBox.setText("显示绘图边界");
        showDrawBorderCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                showDrawBorderCheckBoxItemStateChanged(evt);
            }
        });

        saveDataButton.setText("保存数据");
        saveDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDataButtonActionPerformed(evt);
            }
        });

        pbiLabel.setText("PBI_u:");

        pbiUTextField.setText("5");
        pbiUTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pbiUTextFieldFocusLost(evt);
            }
        });

        at_pLabel1.setText("AT_p:");

        at_pTextField.setText("0.75");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eliminationNumTextField))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(evalueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currentGeneticTimesFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idealTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(givenNextGeneticTimeFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nextGeneritionButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rotatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(showOldDataCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(intersectionWithXplusYequal1CheckBox)
                                    .addComponent(xPlusyEqual1CheckBox)
                                    .addComponent(jCheckBox3)
                                    .addComponent(jCheckBox2)
                                    .addComponent(jCheckBox5)
                                    .addComponent(jCheckBox4)
                                    .addComponent(xySameCheckBox)
                                    .addComponent(showDrawBorderCheckBox)
                                    .addComponent(showParetoFrontCheckBox)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(popSizeTextField))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(hFormattedTextField)
                                                        .addComponent(objectNumTextField))))
                                            .addComponent(initButton)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(pbiLabel)
                                                        .addGap(22, 22, 22))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(at_pLabel1)
                                                        .addGap(28, 28, 28)))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(TFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                                    .addComponent(pbiUTextField)
                                                    .addComponent(at_pTextField))))
                                        .addGap(1, 1, 1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(decomposionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(saveDataButton)
                                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(algorithmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(problemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(algorithmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(problemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(decomposionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(objectNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(hFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(popSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pbiLabel)
                    .addComponent(pbiUTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(at_pLabel1)
                    .addComponent(at_pTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(initButton)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(currentGeneticTimesFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(evalueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(eliminationNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(idealTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(IGDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(givenNextGeneticTimeFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(nextGeneritionButton)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rotatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(showOldDataCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xPlusyEqual1CheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intersectionWithXplusYequal1CheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showParetoFrontCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xySameCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showDrawBorderCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveDataButton))
        );

        jScrollPane2.setViewportView(jPanel1);

        jTabbedPane1.addTab("算法", jScrollPane2);

        openFileButton.setText("打开");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });

        IGDButton.setText("IGD");
        IGDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IGDButtonActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addTreeExpansionListener(new javax.swing.event.TreeExpansionListener() {
            public void treeCollapsed(javax.swing.event.TreeExpansionEvent evt) {
            }
            public void treeExpanded(javax.swing.event.TreeExpansionEvent evt) {
                jTree1TreeExpanded(evt);
            }
        });
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(openFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IGDButton)
                        .addGap(0, 58, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openFileButton)
                    .addComponent(IGDButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel2);

        jTabbedPane1.addTab("文件", jScrollPane3);

        jSplitPane1.setLeftComponent(jTabbedPane1);

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        mainDrawPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout paintPanel2DLayout = new javax.swing.GroupLayout(paintPanel2D);
        paintPanel2D.setLayout(paintPanel2DLayout);
        paintPanel2DLayout.setHorizontalGroup(
            paintPanel2DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );
        paintPanel2DLayout.setVerticalGroup(
            paintPanel2DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 834, Short.MAX_VALUE)
        );

        mainDrawPanel.add(paintPanel2D, "card2");

        paintPanel3D.setName("");
        paintPanel3D.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paintPanel3DMousePressed(evt);
            }
        });
        paintPanel3D.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                paintPanel3DMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout paintPanel3DLayout = new javax.swing.GroupLayout(paintPanel3D);
        paintPanel3D.setLayout(paintPanel3DLayout);
        paintPanel3DLayout.setHorizontalGroup(
            paintPanel3DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );
        paintPanel3DLayout.setVerticalGroup(
            paintPanel3DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 834, Short.MAX_VALUE)
        );

        mainDrawPanel.add(paintPanel3D, "card3");

        jTabbedPane2.addTab("绘图", mainDrawPanel);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(paintTableModel);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setAutoscrolls(false);
        jScrollPane1.setViewportView(jTable1);

        jTabbedPane2.addTab("数据", null, jScrollPane1, "");

        jSplitPane1.setRightComponent(jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void algorithmComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_algorithmComboBoxItemStateChanged
        if (canRun) {
            canRun = false;
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
        int selectedIndex = algorithmComboBox.getSelectedIndex();
        selectedIndex = selectedIndex == -1 ? 0 : selectedIndex;
        algorithm = algorithms.get(selectedIndex);
        if (algorithm instanceof MOEAD1) {
            // popSizeTextField.setEditable(false);
            popSizeTextField.setEditable(false);
            hFormattedTextField.setVisible(true);
            hFormattedTextField.setVisible(true);
            TFormattedTextField.setEditable(true);
        } else if (algorithm instanceof MOEADpro) {
            popSizeTextField.setEditable(false);
            hFormattedTextField.setVisible(true);
            hFormattedTextField.setVisible(true);
            TFormattedTextField.setEditable(true);
        } else if (algorithm instanceof NSGAII) {
            popSizeTextField.setEditable(true);
            hFormattedTextField.setVisible(false);
            hFormattedTextField.setVisible(false);
            TFormattedTextField.setEditable(false);
            decomposition = null;
        }
    }//GEN-LAST:event_algorithmComboBoxItemStateChanged

    private void autoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoButtonActionPerformed
        autoRotate = !autoRotate;
        if (autoRotate) {
            autoButton.setText("停止");
        } else {
            autoButton.setText("旋转");
        }
        startAutoRotate();
    }//GEN-LAST:event_autoButtonActionPerformed

    private void decomposionComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_decomposionComboBoxItemStateChanged
        int selectedIndex = decomposionComboBox.getSelectedIndex();
        if (selectedIndex < 4 && selectedIndex >= 0) {
            System.out.println("分解方法：" + decomposionName[selectedIndex]);
        }
        switch (selectedIndex) {
            case 0:
                decomposition = new WeightedSum();
                pbiUTextField.setEditable(false);
                at_pTextField.setEditable(false);
                break;
            case 1:
                decomposition = new Tchebycheff();
                pbiUTextField.setEditable(false);
                at_pTextField.setEditable(false);
                break;
            case 2:
                decomposition = new PenaltybasedBoundaryIntersection();
                pbiUTextField.setEditable(true);
                at_pTextField.setEditable(false);
                break;
            case 3:
                decomposition = new AugmentedTchebycheff();
                pbiUTextField.setEditable(false);
                at_pTextField.setEditable(true);
                break;
            default:
                return;
        }
        pbiUTextField.setVisible(selectedIndex == 2);
        pbiLabel.setVisible(selectedIndex == 2);
        at_pLabel1.setVisible(selectedIndex == 3);
        at_pTextField.setVisible(selectedIndex == 3);
        algorithm.setDecomposition(decomposition);
    }//GEN-LAST:event_decomposionComboBoxItemStateChanged

    private void defaultTurnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultTurnButtonActionPerformed
        PaintPanel.clearTransferMatrix();
        PaintDialog.this.repaint();
    }//GEN-LAST:event_defaultTurnButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        canRun = false;
        autoRotate = false;
    }//GEN-LAST:event_formWindowClosing

    private void givenNextGeneticTimeFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_givenNextGeneticTimeFormattedTextFieldFocusLost
        String text = givenNextGeneticTimeFormattedTextField.getText();
        double parseDouble = Double.parseDouble(text);
        givenNextGeneticTime = (int) parseDouble;
    }//GEN-LAST:event_givenNextGeneticTimeFormattedTextFieldFocusLost

    private void givenNextGeneticTimeFormattedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_givenNextGeneticTimeFormattedTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            nextGeneritionButton.requestFocus();
            String text = givenNextGeneticTimeFormattedTextField.getText();
            double parseDouble = Double.parseDouble(text);
            givenNextGeneticTime = (int) parseDouble;
            evoluation();
        }
    }//GEN-LAST:event_givenNextGeneticTimeFormattedTextFieldKeyPressed

    private void hFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hFormattedTextFieldFocusLost
        String text = hFormattedTextField.getText();
        H = Integer.parseInt(text);
        System.out.println("设置H：" + H);
    }//GEN-LAST:event_hFormattedTextFieldFocusLost

    private void initButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initButtonActionPerformed
        System.out.println("初始化");
        algorithm.setmaxGenerationTimes(0);
        problem.setEvaluateTimes(0);
        currentGeneriticTime = 0;
        algorithm.setCurrentGeneriticTime(currentGeneriticTime);
        algorithm.setProblem(problem);
        algorithm.setDecomposition(decomposition);
        algorithm.setH(H);
        ArrayList<ArrayList<Double>> initWeights = initWeights();
        if (T > initWeights.size()) {
            System.out.println("邻域大小超过种群大小被重置");
            T = initWeights.size();
            TFormattedTextField.setText("" + initWeights.size());
        }
        algorithm.setNeiboringNum(T);
        algorithm.init();
        {// 输出相关信息
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(algorithm.getName()).append("\t");
            stringBuilder.append(problem.getName()).append("\t");
            stringBuilder.append(
                    decomposition == null ? "" : decomposition.getName()).append("\t");
            stringBuilder.append(algorithm.getH()).append("\t");
            stringBuilder.append(algorithm.getPopulationSize()).append("\t");
            stringBuilder.append(algorithm.getNeiboringNum()).append("\t");
            System.out.println(stringBuilder.toString());
        }
        int populationSize = algorithm.getPopulationSize();
        popSizeTextField.setText("" + populationSize);
        int objectNum = problem.getObjectNum();
        autoRotate = false;
        autoButton.setText("旋转");
        if (objectNum == 2) {// 二维
            paintPanel = paintPanel2D;
            CardLayout layout = (CardLayout) mainDrawPanel.getLayout();
            layout.first(mainDrawPanel);
            autoButton.setEnabled(false);
            defaultTurnButton.setEnabled(false);
        } else if (objectNum == 3) {
            paintPanel = paintPanel3D;
            CardLayout layout = (CardLayout) mainDrawPanel.getLayout();
            layout.last(mainDrawPanel);
            autoButton.setEnabled(true);
            defaultTurnButton.setEnabled(true);
        }
        ArrayList<Individual> population = algorithm.getPopulation();
        paintPanel.setPopulation(population);
        paintTableModel.setPopulation(population);
        paintTableModel.fireTableStructureChanged();
        paintPanel.setWeights(initWeights());
        paintPanel.setOldPopulation(algorithm.getOldPopulation());
        ArrayList<Individual> referenceVector = algorithm.getReferenceVector();
        setReferenceVector(referenceVector);
        ArrayList<Double> idealIndividuals = algorithm.getIdealIndividuals();
        setIdealIndividuals(idealIndividuals);
        int evolutionTimes = algorithm.getEvaluateTimes();
        evalueTextField.setText("" + evolutionTimes);
        PaintDialog.this.repaint();
        nextGeneritionButton.setEnabled(true);
        currentGeneticTimesFormattedTextField.setText("" + currentGeneriticTime);
        ArrayList<ArrayList<Double>> functionToFunctionArray = OutPut.functionToFunctionArray(population);
        double[][] ds = ZMatrix.TDimensionalArrayTrance(functionToFunctionArray);
        double calculator = IGD.calculator(ds, truePF);
        IGDTextField.setText("" + calculator);
    }//GEN-LAST:event_initButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        canRun = false;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        System.out.print("绘制权重向量：");
        if (jCheckBox2.isSelected()) {
            System.out.println(true);
            PaintPanel.setDrawWeightEnable(true);
            PaintDialog.this.repaint();
        } else {
            System.out.println(false);
            PaintPanel.setDrawWeightEnable(false);
            PaintDialog.this.repaint();
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void jCheckBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox3ItemStateChanged
        System.out.print("绘制制定权重向量：");
        if (jCheckBox3.isSelected()) {
            System.out.println(true);
            PaintPanel.setGiveWeightEnable(true);
            PaintDialog.this.repaint();
        } else {
            System.out.println(false);
            PaintPanel.setGiveWeightEnable(false);
            PaintDialog.this.repaint();
        }
    }//GEN-LAST:event_jCheckBox3ItemStateChanged

    private void jCheckBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox4ItemStateChanged
        System.out.print("显示坐标：");
        if (jCheckBox4.isSelected()) {
            System.out.println("true");
            PaintPanel.setShowOrdinate(true);
            PaintDialog.this.repaint();
        } else {
            System.out.println("false");
            PaintPanel.setShowOrdinate(false);
            PaintDialog.this.repaint();
        }
    }//GEN-LAST:event_jCheckBox4ItemStateChanged

    private void jCheckBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox5ItemStateChanged
        System.out.print("显示十字线：");
        if (jCheckBox5.isSelected()) {
            System.out.println("true");
            PaintPanel.setCrossLineEnable(true);
            PaintDialog.this.repaint();
        } else {
            System.out.println("false");
            PaintPanel.setCrossLineEnable(false);
            PaintDialog.this.repaint();
        }
    }//GEN-LAST:event_jCheckBox5ItemStateChanged

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        PaintPanel.setRadiusofCircle(jSlider1.getValue());
        SDPoint.setRadiusofCircle(jSlider1.getValue());
        PaintDialog.this.repaint();
    }//GEN-LAST:event_jSlider1StateChanged

    private void nextGeneritionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextGeneritionButtonActionPerformed
        System.out.println("执行进化");
        canRun = true;
        evoluation();
    }//GEN-LAST:event_nextGeneritionButtonActionPerformed

    private void pbiUTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pbiUTextFieldFocusLost
        String text = pbiUTextField.getText();
        double parseDouble = Double.parseDouble(text);
        PenaltybasedBoundaryIntersection.setU(parseDouble);
    }//GEN-LAST:event_pbiUTextFieldFocusLost

    private void popSizeTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_popSizeTextFieldFocusLost
        String text = popSizeTextField.getText();
        try {
            int parseInt = Integer.parseInt(text);
            algorithm.setPopulationSize(parseInt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(paintPanel, "种群大小设置错误！");
        }
    }//GEN-LAST:event_popSizeTextFieldFocusLost

    private void problemComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_problemComboBoxItemStateChanged
        int selectedIndex = problemComboBox.getSelectedIndex();
        selectedIndex = selectedIndex < 0 ? 0 : selectedIndex;
        problem = problems.get(selectedIndex);
        System.out.println("测试问题：" + problem.getName());
        numberofObject = problem.getObjectNum();
        objectNumTextField.setText("" + numberofObject);
        if (numberofObject == 2) {
            paintPanel = paintPanel2D;
            CardLayout layout = (CardLayout) mainDrawPanel.getLayout();
            layout.first(mainDrawPanel);
            autoButton.setEnabled(false);
            defaultTurnButton.setEnabled(false);
            autoRotate = false;
            ArrayList<ArrayList<Double>> trueParetoFront = problem.calculateParetoFrontbyMathMethod(1000);
            truePF = ZMatrix.TDimensionalArrayTrance(trueParetoFront);
            paintPanel.setParetoFront(ZMatrix.matrixTransposition(truePF));
        } else if (numberofObject == 3) {
            paintPanel = paintPanel3D;
            CardLayout layout = (CardLayout) mainDrawPanel.getLayout();
            layout.last(mainDrawPanel);
            autoButton.setEnabled(true);
            defaultTurnButton.setEnabled(true);
            ArrayList<ArrayList<Double>> trueParetoFront = problem.calculateParetoFrontbyMathMethod(500);
            truePF = ZMatrix.TDimensionalArrayTrance(trueParetoFront);
            paintPanel.setParetoFront(truePF);
        }
        rotatePanel.setVisible(numberofObject == 3);
        PaintDialog.this.repaint();
    }//GEN-LAST:event_problemComboBoxItemStateChanged

    private void rotateVelocitySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rotateVelocitySpinnerStateChanged
        SpinnerModel model = rotateVelocitySpinner.getModel();
        String value = model.getValue().toString();
        double parseDouble = Double.parseDouble(value);
        paintPanel.setRotateVelocityValue(parseDouble);
    }//GEN-LAST:event_rotateVelocitySpinnerStateChanged

    private void saveDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDataButtonActionPerformed
        System.out.println("保存数据");
        algorithm.printFunctionToFile(currentGeneriticTime, "");
        algorithm.printValueToFile(currentGeneriticTime, "");
        algorithm.printWeightVectorToFile(currentGeneriticTime, "");
    }//GEN-LAST:event_saveDataButtonActionPerformed

    private void showDrawBorderCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_showDrawBorderCheckBoxItemStateChanged
        paintPanel.setShowDrawBorder(showDrawBorderCheckBox.isSelected());
        PaintDialog.this.repaint();
    }//GEN-LAST:event_showDrawBorderCheckBoxItemStateChanged

    private void showOldDataCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_showOldDataCheckBoxItemStateChanged
        System.out.print("显示淘汰个体:");
        if (showOldDataCheckBox.isSelected()) {
            System.out.println("true");
            PaintPanel.setShowOldPopulationEnable(true);
            PaintDialog.this.repaint();
        } else {
            System.out.println("false");
            PaintPanel.setShowOldPopulationEnable(false);
            PaintDialog.this.repaint();
        }
    }//GEN-LAST:event_showOldDataCheckBoxItemStateChanged

    private void showParetoFrontCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_showParetoFrontCheckBoxItemStateChanged
        if (showParetoFrontCheckBox.isSelected()) {
            PaintPanel.setShowParetoFrontEnable(true);
            PaintDialog.this.repaint();
        } else {
            PaintPanel.setShowParetoFrontEnable(false);
            PaintDialog.this.repaint();
        }
    }//GEN-LAST:event_showParetoFrontCheckBoxItemStateChanged

    private void TFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFormattedTextFieldFocusLost
        String text = TFormattedTextField.getText();
        T = Integer.parseInt(text);
        System.out.println("设置T:" + T);
    }//GEN-LAST:event_TFormattedTextFieldFocusLost

    private void xPlusyEqual1CheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_xPlusyEqual1CheckBoxItemStateChanged
        System.out.print("显示x+y=1：");
        if (xPlusyEqual1CheckBox.isSelected()) {
            System.out.println("true");
            PaintPanel.setShowXplusYequal1Enable(true);
            PaintDialog.this.repaint();
        } else {
            System.out.println("false");
            PaintPanel.setShowXplusYequal1Enable(false);
            PaintDialog.this.repaint();
        }
    }//GEN-LAST:event_xPlusyEqual1CheckBoxItemStateChanged

    private void xySameCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_xySameCheckBoxItemStateChanged
        if (xySameCheckBox.isSelected()) {
            PaintPanel.setXySameEnable(true);
            PaintDialog.this.repaint();
        } else {
            PaintPanel.setXySameEnable(false);
            PaintDialog.this.repaint();
        }
    }//GEN-LAST:event_xySameCheckBoxItemStateChanged

    private void paintPanel3DMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paintPanel3DMousePressed
        // 鼠标按下时，记录位置
        if (problem.getObjectNum() == 3) {
            Point mousePoint = evt.getPoint();
            Point paintPanel3DLocation = paintPanel3D.getLocation();
            double x = mousePoint.getX() - paintPanel3DLocation.getX();
            double y = mousePoint.getY() - paintPanel3DLocation.getY();
            currentLocation[0] = x;
            currentLocation[1] = y;
            System.out.println("鼠标点击");
        }
    }//GEN-LAST:event_paintPanel3DMousePressed

    private void paintPanel3DMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paintPanel3DMouseDragged

        if (numberofObject != 3) {
            return;
        }
        // 拖动事件：拖动标志位置1，实时计算旋转角度和大小，重置初始点，获取新位置，计算转换的角度和大小，执行拖拽。
        Point mousePoint = evt.getPoint();
        Point paintPanel3DLocation = paintPanel3D.getLocation();
        double x = mousePoint.getX() - paintPanel3DLocation.getX();// x轴方向偏移量
        double y = mousePoint.getY() - paintPanel3DLocation.getY();// y轴方向偏移量
        double drawWidth = paintPanel3D.drawWidth;
        double d = drawWidth / 360.0;// x方向分度
        double xd = (x - currentLocation[0]) / d;
        if (xd > 1.0) {
            direction = -1;
            PaintPanel.rotateAboutYAxesLeftTransferMatrix();
            currentLocation[0] = x;
            currentLocation[1] = y;
            PaintDialog.this.repaint();
            // System.out.println("鼠标拖动");
        } else if (xd < -1.0) {
            direction = 1;
            PaintPanel.rotateAboutYAxesRightTransferMatrix();
            currentLocation[0] = x;
            currentLocation[1] = y;
            PaintDialog.this.repaint();
            // System.out.println("鼠标拖动");
        }
    }//GEN-LAST:event_paintPanel3DMouseDragged

    private void intersectionWithXplusYequal1CheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_intersectionWithXplusYequal1CheckBoxItemStateChanged
        System.out.print("显示与x+y=1的交点：");
        if (intersectionWithXplusYequal1CheckBox.isSelected()) {
            System.out.println("true");
            PaintPanel.setShowIntersectionWithXplusYequal1Enable(true);
            PaintDialog.this.repaint();
        } else {
            System.out.println("false");
            PaintPanel.setShowIntersectionWithXplusYequal1Enable(false);
            PaintDialog.this.repaint();
        }
    }//GEN-LAST:event_intersectionWithXplusYequal1CheckBoxItemStateChanged

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setCurrentDirectory(currentPath);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int showOpenDialog = fileChooser.showOpenDialog(this);
        if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            currentPath = /*
                     * file.isFile() ? file.getParentFile() :
                     */ file;
            ArrayList<ArrayList<Double>> datas = null;
            if (file != null) {
                if (file.isFile()) {
                    datas = ZFile.readDataToDoubleArrayList(file);
                    int maxColumn = datas.get(0).size();
                    if (datas.isEmpty() || maxColumn < 2) {
                        return;
                    }
                    ArrayList<Individual> pArrayList = new ArrayList<Individual>();
                    for (ArrayList<Double> rowData : datas) {
                        Individual individual = new Individual();
                        individual.setFunctionVector(rowData);
                        pArrayList.add(individual);
                    }
                    numberofObject = maxColumn;
                    if (maxColumn == 2) {// 二维
                        paintPanel = paintPanel2D;
                        CardLayout layout = (CardLayout) mainDrawPanel.getLayout();
                        layout.first(mainDrawPanel);
                        autoButton.setEnabled(false);
                        defaultTurnButton.setEnabled(false);
                        rotatePanel.setVisible(false);
                    } else if (maxColumn == 3) {
                        paintPanel = paintPanel3D;
                        CardLayout layout = (CardLayout) mainDrawPanel.getLayout();
                        layout.last(mainDrawPanel);
                        autoButton.setEnabled(true);
                        defaultTurnButton.setEnabled(true);
                        rotatePanel.setVisible(true);
                    }
                    setPopulation(pArrayList);

                } else if (file.isDirectory()) {
                    // 初始化jtree
                    DefaultMutableTreeNode driveNode = new DefaultMutableTreeNode(
                            new PaintDialog.MyFile(currentPath));
                    iterateDir(currentPath, driveNode);
                    dirRoot = driveNode;
                    DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
                    model.setRoot(dirRoot);
                }
                PaintDialog.this.repaint();
            }
        }
    }//GEN-LAST:event_openFileButtonActionPerformed

    private void IGDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IGDButtonActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File("."));
        int showOpenDialog = fileChooser.showOpenDialog(null);
        if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile);
            // 如果是文件，直接评价文件。如果是文件夹，将评价整个文件个内的左右的文件。如果有子文件夹将直接到最低层。
            Indicate.indicateFile(selectedFile);
        }
    }//GEN-LAST:event_IGDButtonActionPerformed

    private void jTree1TreeExpanded(javax.swing.event.TreeExpansionEvent evt) {//GEN-FIRST:event_jTree1TreeExpanded
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) evt.getPath().getLastPathComponent();
        if (node == null) {
            return;
        }
        // get the children of the node
        int count = node.getChildCount();
        for (int i = 0; i < count; i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            // check if the child has a stub
            if (child.getChildCount() != 1) {
                continue;
            }
            Object obj = ((DefaultMutableTreeNode) child.getChildAt(0)).getUserObject();
            if (!(obj instanceof MyFile)) { // a stub found
                child.removeAllChildren();
                File childFile = ((MyFile) child.getUserObject()).file;
                iterateDir(childFile, child);
            }
        }
    }//GEN-LAST:event_jTree1TreeExpanded

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        // get the list of files in the current directory
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) evt.getPath().getLastPathComponent();
        if (node == null) {
            return;
        }
        Object obj = node.getUserObject();
        if (!(obj instanceof MyFile)) {
            return; // selected the very root
        }
        File dir = ((MyFile) obj).file;
        ArrayList<ArrayList<Double>> datas = null;
        if (dir != null && dir.isFile()) {
            datas = ZFile.readDataToDoubleArrayList(dir);
            int maxColumn = datas.get(0).size();
            if (datas.isEmpty() || maxColumn < 2) {
                return;
            }
            ArrayList<Individual> pArrayList = new ArrayList<Individual>();
            for (ArrayList<Double> arrayList : datas) {
                Individual individual = new Individual();
                individual.setFunctionVector(arrayList);
                pArrayList.add(individual);
            }
            numberofObject = maxColumn;
            if (maxColumn == 2) {// 浜岀淮
                paintPanel = paintPanel2D;
                CardLayout layout = (CardLayout) mainDrawPanel.getLayout();
                layout.first(mainDrawPanel);
                autoButton.setEnabled(false);
                defaultTurnButton.setEnabled(false);
                rotatePanel.setVisible(false);
            } else if (maxColumn == 3) {
                paintPanel = paintPanel3D;
                CardLayout layout = (CardLayout) mainDrawPanel.getLayout();
                layout.last(mainDrawPanel);
                autoButton.setEnabled(true);
                defaultTurnButton.setEnabled(true);
                rotatePanel.setVisible(true);
            }
            setPopulation(pArrayList);
            PaintDialog.this.repaint();
        }
    }//GEN-LAST:event_jTree1ValueChanged

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        PaintDialog paintWindow = new PaintDialog();
        paintWindow.setIconImage(ZResource.getAppImage());
        paintWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        paintWindow.setSize((int) (screenSize.width), (int) (screenSize.height));
        paintWindow.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IGDButton;
    private javax.swing.JTextField IGDTextField;
    private javax.swing.JTextField TFormattedTextField;
    private javax.swing.JComboBox algorithmComboBox;
    private javax.swing.JLabel at_pLabel1;
    private javax.swing.JTextField at_pTextField;
    private javax.swing.JButton autoButton;
    private javax.swing.JTextField currentGeneticTimesFormattedTextField;
    private javax.swing.JComboBox decomposionComboBox;
    private javax.swing.JButton defaultTurnButton;
    private javax.swing.JTextField eliminationNumTextField;
    private javax.swing.JTextField evalueTextField;
    private javax.swing.JTextField givenNextGeneticTimeFormattedTextField;
    private javax.swing.JTextField hFormattedTextField;
    private javax.swing.JTextField idealTextField;
    private javax.swing.JButton initButton;
    private javax.swing.JCheckBox intersectionWithXplusYequal1CheckBox;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    private javax.swing.JPanel mainDrawPanel;
    private javax.swing.JButton nextGeneritionButton;
    private javax.swing.JTextField objectNumTextField;
    private javax.swing.JButton openFileButton;
    private paint.PaintPanel2D paintPanel2D;
    private paint.PaintPanel3D paintPanel3D;
    private javax.swing.JLabel pbiLabel;
    private javax.swing.JTextField pbiUTextField;
    private javax.swing.JTextField popSizeTextField;
    private javax.swing.JComboBox problemComboBox;
    private javax.swing.JPanel rotatePanel;
    private javax.swing.JSpinner rotateVelocitySpinner;
    private javax.swing.JButton saveDataButton;
    private javax.swing.JCheckBox showDrawBorderCheckBox;
    private javax.swing.JCheckBox showOldDataCheckBox;
    private javax.swing.JCheckBox showParetoFrontCheckBox;
    private javax.swing.JCheckBox xPlusyEqual1CheckBox;
    private javax.swing.JCheckBox xySameCheckBox;
    // End of variables declaration//GEN-END:variables
    private boolean canRun = true;
    private PaintPanel paintPanel = null;
    private int direction = -1;// 默认是向左
    private File currentPath = new File(System.getProperty("user.dir"));
    private PaintTableModel paintTableModel = null;
    private ArrayList<Algorithm> algorithms = new ArrayList<Algorithm>();
    private ArrayList<Problem> problems = new ArrayList<Problem>();
    private String[] decomposionName = {"权重聚合", "切比雪夫", "PBI", "AT"};
    private Algorithm algorithm = null;
    private Problem problem = null;
    private Decomposition decomposition = null;
    private int T = 2;
    private int H = 10;
    private int givenNextGeneticTime = 0;
    private int currentGeneriticTime = 0;
    private TreeNode dirRoot;
    /**
     * 自动旋转
     */
    private boolean autoRotate = false;
    /**
     * 真实Pareto面
     */
    double[][] truePF = null;
    int numberofObject;
    /**
     * 3D界面鼠标移动事件初始位置
     */
    private double[] currentLocation = new double[2];

    private void loadData() {
        algorithmComboBox.removeAllItems();
        for (Algorithm algorithm1 : algorithms) {
            algorithmComboBox.addItem(algorithm1.getName());
        }
        problemComboBox.removeAllItems();
        for (Problem problem1 : problems) {
            problemComboBox.addItem(problem1.getName());
        }
        decomposionComboBox.removeAllItems();
        for (int i = 0; i < decomposionName.length; i++) {
            String string = decomposionName[i];
            decomposionComboBox.addItem(string);
        }
    }

    private void startAutoRotate() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }

        if (direction == -1) {
            PaintPanel.rotateAboutYAxesLeftTransferMatrix();
        } else {
            PaintPanel.rotateAboutYAxesRightTransferMatrix();
        }
        PaintDialog.this.repaint();
        if (autoRotate) {
            new Thread() {

                @Override
                public void run() {
                    startAutoRotate();
                    String name = Thread.currentThread().getName();
                    System.out.print(name + " Rotate");
                }
            }.start();
        }
    }

    private void evoluation() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        algorithm.evoluationOneTime();
        ArrayList<Individual> population = algorithm.getPopulation();
        paintPanel.setPopulation(population);
        paintTableModel.setPopulation(population);
        paintTableModel.fireTableStructureChanged();
        ArrayList<Individual> referenceVector = algorithm.getReferenceVector();
        setReferenceVector(referenceVector);
        ArrayList<Double> idealIndividuals = algorithm.getIdealIndividuals();
        setIdealIndividuals(idealIndividuals);
        int evolutionTimes = algorithm.getEvaluateTimes();
        evalueTextField.setText("" + evolutionTimes);
        currentGeneriticTime = algorithm.getCurrentGeneriticTime();
        currentGeneticTimesFormattedTextField.setText("" + currentGeneriticTime);
        ArrayList<ArrayList<Double>> functionToFunctionArray = OutPut.functionToFunctionArray(population);
        double[][] ds = ZMatrix.TDimensionalArrayTrance(functionToFunctionArray);

        double calculator = IGD.calculator(ds, truePF);
        IGDTextField.setText("" + calculator);
        if (givenNextGeneticTime - currentGeneriticTime <= 1) {
            canRun = false;
        }
        if (givenNextGeneticTime <= currentGeneriticTime) {
            givenNextGeneticTime = currentGeneriticTime + 1;
            givenNextGeneticTimeFormattedTextField.setText(""
                    + givenNextGeneticTime);
        }
        if (algorithm.getOldPopulation() != null) {
            eliminationNumTextField.setText(""
                    + algorithm.getOldPopulation().size());
        }
        PaintDialog.this.repaint();
        if (canRun) {
            new Thread() {

                @Override
                public void run() {
                    evoluation();
                    String name = Thread.currentThread().getName();
                    System.out.println(name + " evoluation");
                }
            }.start();
        }
    }

    public ArrayList<ArrayList<Double>> initWeights() {
        if (problem.getObjectNum() == 2) {
            ArrayList<ArrayList<Double>> weights = new ArrayList<ArrayList<Double>>();
            for (double i = 0; i <= H; i++) {
                ArrayList<Double> arrayList = new ArrayList<Double>();
                arrayList.add(i / H);
                arrayList.add(1 - i / H);
                weights.add(arrayList);
            }
            return weights;
        } else if (problem.getObjectNum() == 3) {
            ArrayList<ArrayList<Double>> weights = new ArrayList<ArrayList<Double>>();
            for (double i = 0; i <= H; i++) {
                for (double j = 0; j <= H; j++) {
                    if (i + j <= H) {
                        ArrayList<Double> arrayList = new ArrayList<Double>();
                        arrayList.add(i / H);
                        arrayList.add(j / H);
                        arrayList.add(1 - (i + j) / H);
                        weights.add(arrayList);
                    }
                }
            }
            return weights;
        }
        return null;
    }

    private void iterateDir(File parentDir, DefaultMutableTreeNode parentNode) {
        File[] files = parentDir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.exists() && file.isDirectory()) {
                DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(
                        new MyFile(file));
                // create a child stub
                iterateDir(file, dirNode);
                parentNode.add(dirNode);
            } else if (file.exists() && file.isFile()) {
                DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(
                        new MyFile(file));
                parentNode.add(dirNode);
            }
        }
    }

    public void setIdealIndividuals(ArrayList<Double> idealIndividuals) {
        if (idealIndividuals == null) {
            return;
        }
        paintTableModel.setIdealArrayList(idealIndividuals);
        paintPanel.setIdeal(idealIndividuals);
        String s = "(";
        DecimalFormat myformat = new DecimalFormat("#####0.00");
        for (int i = 0; i < idealIndividuals.size() - 1; i++) {
            double doubleValue = idealIndividuals.get(i).doubleValue();
            String douString = myformat.format(doubleValue);
            s += douString + ",";
        }
        if (idealIndividuals != null && idealIndividuals.size() > 0) {
            double doubleValue = idealIndividuals.get(
                    idealIndividuals.size() - 1).doubleValue();
            String douString = myformat.format(doubleValue);
            s += douString + ")";
        }
        idealTextField.setText(s);
    }

    private void setPopulation(ArrayList<Individual> population) {
        paintPanel.setPopulation(population);
        paintTableModel.setPopulation(population);
        paintTableModel.fireTableStructureChanged();
    }

    public void setReferenceVector(ArrayList<Individual> referenceVector) {
        paintTableModel.setReferenceVector(referenceVector);
    }

    private class MyFile {

        private File file;

        public MyFile(File file) {
            this.file = file;
        }

        @Override
        public String toString() {
            String name = file.getName().trim();
            if (name.length() == 0) {
                name = file.getAbsolutePath().trim();
            }
            return name;
        }
    }
}
