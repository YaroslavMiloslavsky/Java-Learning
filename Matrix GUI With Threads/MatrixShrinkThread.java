import java.awt.Color;

import javax.swing.JButton;

public class MatrixShrinkThread implements Runnable {
	private JButton[][] buttonArray;
	private boolean[][] booleanArray;
	private int rowIndex; // The row to shrink
	
	public MatrixShrinkThread(JButton[][] buttons, int rowIndex, boolean[][] boolarr) {
		this.buttonArray = buttons;
		this.rowIndex = rowIndex;
		this.booleanArray = boolarr;
	}
	
	@Override
	public void run() {
		task();
	}

	private synchronized void task() {
		
		for(int j=0, i =rowIndex; j<booleanArray[1].length;j++) {
			if(booleanArray[i][j] == true) {
				buttonArray[i][j].setBackground(Color.WHITE);
				buttonArray[i][j].setOpaque(true);
			}
		}
	}
}
