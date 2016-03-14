import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.UIManager;
import paint.PaintDialog;

@SuppressWarnings("serial")
public class NewJApplet extends javax.swing.JApplet {

	private javax.swing.JButton jButton1;

	public NewJApplet() {
		getContentPane().setMinimumSize(new Dimension(10, 10));
	}

	@Override
	public void init() {
		try {
			javax.swing.UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}

		try {
			java.awt.EventQueue.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					initComponents();
				}
			});
		} catch (Exception ex) {
		}
	}

	private void initComponents() {

		jButton1 = new javax.swing.JButton();

		setBackground(new java.awt.Color(204, 255, 204));
		setForeground(new java.awt.Color(255, 51, 51));

		jButton1.setText("ZALPHA数据分析工具");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jButton1)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jButton1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 42,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		PaintDialog paintWindow = new PaintDialog();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		paintWindow.setSize((int) (screenSize.width),
				(int) (screenSize.getHeight() * 0.96));
		paintWindow.setVisible(true);
	}
}