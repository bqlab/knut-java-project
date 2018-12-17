package paint;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JRadioButton;

public class JavaPaint {
	int oldX, oldY;
	int curX, curY;
	JRadioButton black,white;
	Color color;
	Vector<point> tmp = new Vector<point>();
	Vector<Vector> list = new Vector<Vector>();
	int a[], b[];
	public JavaPaint() {
		black = new JRadioButton("°ËÁ¤»ö");
		white = new JRadioButton("Èò»ö");
		ButtonGroup rb = new ButtonGroup();
		rb.add(black); rb.add(white);
		this.add(black); this.add(white);
		black.addItemListener(new SelectListener());
		white.addItemListener(new SelectListener());
		this.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {}
			public void mouseDragged(MouseEvent e) {
				curX = e.getX();
				curY = e.getY();
				tmp.add(new point(curX, curY));
				FreeDraw.this.getGraphics().drawLine(oldX, oldY, curX, curY);
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
				tmp.add(new point(oldX, oldY));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				list.add(tmp);
				tmp = new Vector<point>();
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for (Vector vs : list) {
			Iterator it = vs.iterator();
			a = new int[vs.size()];
			b = new int[vs.size()];
			int k = 0;
			while (it.hasNext()) {
				point pt = (point) it.next();
				a[k] = pt.x;
				b[k] = pt.y;
				k++;
			}
			g.drawPolyline(a, b, a.length);
		}
	}
	public static void main(String[] args) {
		FreeDraw a = new FreeDraw();
		JFrame frame = new JFrame();
		frame.add(a);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	class point {
		int x, y;
		public point(int a, int b) {
			x = a;
			y = b;
		}
	}
	class SelectListener implements ItemListener {
	
		@Override
		public void itemStateChanged(ItemEvent e) {
			AbstractButton a = (AbstractButton) e.getItemSelectable();
			if(e.getStateChange()==ItemEvent.SELECTED){
				if(a.getText().equals("°ËÁ¤»ö")) {
					color = Color.RED;
				}
				else if(a.getText().equals("Èò»ö")) {
					color = Color.BLUE;
				}
			}
		}
	}
}
