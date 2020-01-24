import java.awt.Color;

import javax.swing.JButton;

public class BooleanShrinkThread implements Runnable {
	private JButton[][] buttonArray;
	private boolean[][] booleanArray;
	private int rowIndex; // The row to shrink
	
	/**
	 * Gets array of JButton and array of boolean to correspond to the JButton array
	 * @param buttons
	 * @param rowIndex
	 * @param boolarr
	 */
	public BooleanShrinkThread(JButton[][] buttons, int rowIndex, boolean[][] boolarr) {
		this.buttonArray = buttons;
		this.rowIndex = rowIndex;
		this.booleanArray = boolarr;
	}
	
	@Override
	public void run() {
		task();	
	}
	
	/**
	 * To boolean matrix
	 */
	private synchronized void task(){
		for(int j=0,i=rowIndex; j<buttonArray[0].length;j++) {
			if(isLegalIndex(i+1,j) && isWhite(buttonArray[i+1][j]))
				booleanArray[i][j] = true;
			if(isLegalIndex(i-1,j) && isWhite(buttonArray[i-1][j]))
				booleanArray[i][j] = true;
			if(isLegalIndex(i,j+1) && isWhite(buttonArray[i][j+1]))
				booleanArray[i][j] = true;
			if(isLegalIndex(i,j-1) && isWhite(buttonArray[i][j-1]))
				booleanArray[i][j] = true;
			if(isLegalIndex(i+1,j+1) && isWhite(buttonArray[i+1][j+1]))
				booleanArray[i][j] = true;
			if(isLegalIndex(i+1,j-1) && isWhite(buttonArray[i+1][j-1]))
				booleanArray[i][j] = true;
			if(isLegalIndex(i-1,j+1) && isWhite(buttonArray[i-1][j+1]))
				booleanArray[i][j] = true;
			if(isLegalIndex(i-1,j-1) && isWhite(buttonArray[i-1][j-1]))
				booleanArray[i][j] = true;
		}
		
		try {
			Thread.sleep(250);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	private synchronized boolean isLegalIndex(int i, int j) {
		if(i>buttonArray[0].length-1 || j>buttonArray[1].length-1 || i<0 || j<0)
			return false;
		return true;
	}
	
	private boolean isWhite(JButton button) {
		if(button.getBackground() == Color.WHITE)
			return true;
		return false;
	}

}
