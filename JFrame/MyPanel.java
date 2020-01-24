import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.drawRect(20, 30, 100, 80);
		g.setColor(Color.YELLOW);
		g.fillOval(50, 50, 100, 80);
		
		Color MyColor = new Color(100,20,60);
		g.setColor(MyColor);
		g.fillArc(60, 60, 200, 200, 78, 45);
		
	}
}
