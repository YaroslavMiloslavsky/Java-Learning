import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameMatrix extends JPanel {
	private int x,y;
	private LifeMatrix lifeMatrix;
	
	
	public GameMatrix() {
		lifeMatrix = new LifeMatrix();
		repaint();
	}//end of constuctor
	
	public void begin() {
		while(JOptionPane.showConfirmDialog(null, "Proceed to the next generation?") == JOptionPane.YES_OPTION) {
			lifeMatrix.nextGeneration();
			repaint();
		}
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		
		for(x=0 ; x < lifeMatrix.getMatrixDim() ; x++)
			for(y=0 ; y < lifeMatrix.getMatrixDim() ; y++) {
				if(lifeMatrix.getCoordinateLifeValue(x, y) == false) {
					g.setColor(Color.BLACK);
					g.drawRect(x*50, y*50, 50, 50);
				}
					
				if(lifeMatrix.getCoordinateLifeValue(x, y) == true) {
					
					g.setColor(Color.GREEN);
					g.fillRect(x*50, y*50, 50, 50);
					g.setColor(Color.BLACK);
					g.drawRect(x*50, y*50, 50, 50);
				}
					
			}
	}
		
}
