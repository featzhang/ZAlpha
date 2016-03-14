package lab;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class DrawFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 500;

	public static final int DEFAULT_HEIGHT = 500;

	public DrawFrame() {
		setTitle("DrawFrame");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		DrawPanel panel = new DrawPanel();
		Container contentPane = getContentPane();
		contentPane.add(panel);
	}
}

@SuppressWarnings("serial")
class DrawPanel extends JPanel {
	private class MouseHandler implements MouseListener {
		public void mouseClicked(MouseEvent event) {

		}

		public void mouseEntered(MouseEvent event) {

		}

		public void mouseExited(MouseEvent event) {

		}

		public void mousePressed(MouseEvent event) {
			centerX = event.getX();
			centerY = event.getY();
		}

		public void mouseReleased(MouseEvent event) {

		}
	}

	private class MouseMotionHandler implements MouseMotionListener {
		public void mouseDragged(MouseEvent event) {
			nowX = event.getX();
			nowY = event.getY();

			if (Math.abs(nowX - centerX) > Math.abs(nowY - centerY))
				radius = Math.abs(nowX - centerX);
			else
				radius = Math.abs(nowY - centerY);

			repaint();
		}

		public void mouseMoved(MouseEvent event) {

		}
	}

	private int radius;
	private int centerX;
	private int centerY;
	private int nowX;
	private int nowY;

	public DrawPanel() {
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Line2D line=new Line2D.Double(centerX,centerY,nowX,nowY);
		// g2.draw(line);

		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(centerX, centerY, centerX - radius, centerY
				- radius);
		g2.draw(circle);

		// g2.draw(circle.getBounds2D());
	}
}

public class MouseDrawCircle {
	public static void main(String[] args) {
		DrawFrame frame = new DrawFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}