package paint;

import Util.ZResource;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class PaintFrame extends javax.swing.JFrame {

    private PaintDialog paintWindow = null;

    public PaintFrame() {
        initComponents();
        setIconImage(ZResource.getAppImage());
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        systemMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        anasysMenu = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        algorithmMenu = new javax.swing.JMenu();
        algorithmCompareMenuItem = new javax.swing.JMenuItem();
        summaryMenu = new javax.swing.JMenu();
        evaluationMenu = new javax.swing.JMenu();
        igdMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Util/resource/ZALPHA"); // NOI18N
        setTitle(bundle.getString("Application.title")); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        systemMenu.setText("系统");

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setText("退出");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        systemMenu.add(exitMenuItem);

        jMenuBar1.add(systemMenu);

        anasysMenu.setText("分析");

        jMenuItem5.setText("测试问题");
        anasysMenu.add(jMenuItem5);

        jMenuItem1.setText("选择算子");
        anasysMenu.add(jMenuItem1);

        jMenuItem2.setText("交叉算子");
        anasysMenu.add(jMenuItem2);

        jMenuItem3.setText("变异算子");
        anasysMenu.add(jMenuItem3);

        jMenuItem4.setText("适应度");
        anasysMenu.add(jMenuItem4);

        jMenuBar1.add(anasysMenu);

        algorithmMenu.setText("算法");

        algorithmCompareMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        algorithmCompareMenuItem.setText("对比");
        algorithmCompareMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithmCompareMenuItemActionPerformed(evt);
            }
        });
        algorithmMenu.add(algorithmCompareMenuItem);

        jMenuBar1.add(algorithmMenu);

        summaryMenu.setText("统计");
        jMenuBar1.add(summaryMenu);

        evaluationMenu.setText("评价");

        igdMenuItem.setText("IGD");
        evaluationMenu.add(igdMenuItem);

        jMenuBar1.add(evaluationMenu);

        helpMenu.setText("帮助");

        aboutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        aboutMenuItem.setText("关于");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void algorithmCompareMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmCompareMenuItemActionPerformed
        if (paintWindow == null) {
            paintWindow = new PaintDialog();
            paintWindow.setIconImage(ZResource.getAppImage());
            paintWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            paintWindow.setSize((int) (screenSize.width), (int) (screenSize.height));
            paintWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        paintWindow.setVisible(true);
    }//GEN-LAST:event_algorithmCompareMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        AboutDialog dialog = new AboutDialog(PaintFrame.this, false);
        dialog.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PaintFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem algorithmCompareMenuItem;
    private javax.swing.JMenu algorithmMenu;
    private javax.swing.JMenu anasysMenu;
    private javax.swing.JMenu evaluationMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem igdMenuItem;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu summaryMenu;
    private javax.swing.JMenu systemMenu;
    // End of variables declaration//GEN-END:variables
}