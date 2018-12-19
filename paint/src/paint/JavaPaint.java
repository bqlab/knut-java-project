package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class JavaPaint extends JPanel {
	int oldX, oldY;
	int curX, curY;
	JRadioButton black,white;
	Vector<Point> tmp = new Vector<Point>();
	Vector<Vector> list = new Vector<Vector>();
	int x[], y[];
	public JavaPaint() {
		ButtonGroup rb = new ButtonGroup();
		this.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {}
			public void mouseDragged(MouseEvent e) {
				curX = e.getX();
				curY = e.getY();
				tmp.add(new Point(curX, curY));
				JavaPaint.this.getGraphics().drawLine(oldX, oldY, curX, curY);
				oldX = curX;
				oldY = curY;
				}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				oldX = e.getX();
				oldY = e.getY();
				tmp.add(new Point(oldX, oldY));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				list.add(tmp);
				tmp = new Vector<Point>();
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for (Vector v : list) {
			Iterator i = v.iterator();
			x = new int[v.size()];
			y = new int[v.size()];
			int k = 0;
			while (i.hasNext()) {
				Point p = (Point) i.next();
				x[k] = p.x;
				y[k] = p.y;
				k++;
			}
			g.drawPolyline(x, y, x.length);
		}
	}
	public static void main(String[] args) {
		JavaPaint j = new JavaPaint();
		JFrame frame = new JFrame();
		frame.add(j);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
