import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MyCross extends JPanel {
	
	private int x,y;
	private final int SIZE = 5;
	private JButton[] ButtonArray;

	public MyCross() {
		x=y=0;
		ButtonArray = new JButton[SIZE];
		for(int i=0; i<ButtonArray.length ; i++)
			ButtonArray[i] = new JButton("Button " + i);
			
		//BorderLayout
//		this.setLayout(new BorderLayout(100,100));
//		
//		this.add(ButtonArray[0],BorderLayout.NORTH);
//		this.add(ButtonArray[1],BorderLayout.WEST);
//		this.add(ButtonArray[2],BorderLayout.SOUTH);
//		this.add(ButtonArray[3],BorderLayout.EAST);
//		this.add(ButtonArray[4],BorderLayout.CENTER);
		
		//Grid Layout
		this.setLayout(new GridLayout(2,3));
		for(JButton but: ButtonArray)
			add(but);
		
		//Anonymous class
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				repaint();
			}
		});
		
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int height = this.getHeight();
		int width = this.getWidth();
		
		g.drawLine(0, y, width, y);
		g.drawLine(x, 0, x, height);
	}
}
