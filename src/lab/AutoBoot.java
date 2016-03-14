/**
 *
 */
package lab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AutoBoot extends JPanel {

    private static final long serialVersionUID = 1L;

    public AutoBoot() {

        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    aaa = false;
                } else {
                    aaa = true;
                    move();
                }
            }
        });
    }
    private boolean aaa = true;

    public void setAaa(boolean aaa) {
        this.aaa = aaa;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.cyan);
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.fill(zaw);
        graphics2d.fill(zaw2);
        g.setColor(Color.red);
        graphics2d.fill(arc2d);
    }
    private double v = 1;
    private double ax = 0;
    private double ay = 0;
    private Rectangle2D zaw = new Rectangle2D.Double(160, 370, 160, 60);
    Path2D path2D=null;
    private Arc2D zaw2 = new Arc2D.Double(360, 170, 160, 60, 0, 320, Arc2D.PIE);
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Arc2D arc2d = new Arc2D.Double(ax, ay, 2, 2, 0, 320, Arc2D.PIE);

    public void setAx(double ax, double ay) {
        this.ax = ax;
        this.ay = ay;
    }
    private double d = 45;

    public void move() {
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
        double ayy = v * Math.sin(d);
        double axx = v * Math.cos(d);
        int width2 = getWidth();
        int height2 = getHeight();
        if (ax + axx < 0 || axx + ax > width2) {
            d = Math.random() * 180;
            move();
        } else if (ay + ayy < 0 || ay + ayy > height2) {
            d = Math.random() * 180;
            move();
        } else if (zaw.contains(axx + ax, ay + ayy)
                || zaw2.contains(axx + ax, ay + ayy)) {
            d = Math.random() * 360;
            move();
        } else {
            ax += axx;
            ay += ayy;
            arc2d.setArc(ax, ay, 2, 2, 0, 320, Arc2D.PIE);
            repaint();
            if (aaa) {

                new Thread() {

                    @Override
                    public void run() {
                        move();
                    }
                }.start();
            }
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("机器人 什么的");
        AutoBoot autoBoot = new AutoBoot();
        frame.add(autoBoot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}