import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import lab.AutoBoot;

@SuppressWarnings("serial")
public class AutoBootApplet extends javax.swing.JApplet {
	public AutoBootApplet() {
	}

	@Override
	public void init() {
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}
		try {
			java.awt.EventQueue.invokeAndWait(new Runnable() {

				public void run() {
					initComponents();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initComponents() {

		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				formMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 76,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 28,
				Short.MAX_VALUE));
	}

	private void formMouseClicked(java.awt.event.MouseEvent evt) {
		JDialog frame = new JDialog();
		frame.setTitle("机器人 什么的");
		final AutoBoot autoBoot = new AutoBoot();
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				autoBoot.setAaa(false);
			}
		});
		frame.getContentPane().add(autoBoot);
		frame.pack();
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.repaint();
	}
}