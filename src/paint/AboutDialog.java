package paint;

import java.net.URL;
import javax.swing.UIManager;

public class AboutDialog extends javax.swing.JDialog {

    private String path = null;

    public AboutDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        path = "/paint/about/index.html";
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        URL url = null;
        try {
            try {
                url = getClass().getResource(path);
            } catch (Exception e) {
                System.err.println("Failed to open " + path);
                url = null;
            }
        } catch (Exception e) {
            System.out.println("IOException: " + e);
        }
        try{
            if(url==null){
                jEditorPane1 = new javax.swing.JEditorPane();
            }else{
                jEditorPane1 = new javax.swing.JEditorPane(url);

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("关于");
                setLocationByPlatform(true);
                setType(java.awt.Window.Type.POPUP);

            }
        }catch(Exception e){
        }
        jEditorPane1.setBorder(null);
        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AboutDialog dialog = new AboutDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
