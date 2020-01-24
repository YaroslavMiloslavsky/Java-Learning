import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ShrinkGUI extends JPanel {
	private JButton btnGo, btnClear;
	private JButton[][] buttonArray;
	private final int nSize, mSize, tSize;
	private ExecutorService threadPool;
	
	 public ShrinkGUI(int n, int m, int t) {
		 nSize = n; // matrix dim
		 mSize = m; // number of threads
		 tSize = t; // number of runs per thread
		 
		buttonArray = new JButton[nSize][nSize];
		ControlListener cmd = new ControlListener();
		
		btnGo = new JButton("go");
		btnClear = new JButton("clear");
		btnGo.addActionListener(cmd);
		btnClear.addActionListener(cmd);
		
		// Centre panel - the matrix itself
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(nSize,nSize));
		
		// Initialise the matrix of JButtons
		for(int i=0; i<nSize; i++)
			for(int j=0; j<nSize; j++)
			{
				JButton button = new JButton();
				button.setBackground(Color.WHITE);
				button.setOpaque(true);
				button.addActionListener(cmd);
				mainPanel.add(button);
				buttonArray[i][j] = button;
			}
		
		// South panel - go and clear buttons
		JPanel southPanel = new JPanel();
		southPanel.add(btnGo);
		southPanel.add(btnClear);
		
		this.setLayout(new BorderLayout());
		this.add(southPanel , BorderLayout.SOUTH);
		this.add(mainPanel , BorderLayout.CENTER);
	 }
	 
	 private class ControlListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnGo) {
				
				threadPool = Executors.newFixedThreadPool(mSize);   // the thread pool is set to fixed number m, so each time only m threads work parallel
				boolean[][] boolArray = new boolean[nSize][nSize]; // the array of boolean buttons
				
				for(int k=0; k<tSize ; k++) {

					//first we check the matrix and change all the black ones to true if there are white neighbours
					for(int i=0; i<nSize; i++)
						threadPool.execute(new BooleanShrinkThread(buttonArray, i, boolArray));
					
					//then the threads are sent to crop the needed values
					for(int i=0; i<nSize; i++)
						threadPool.execute(new MatrixShrinkThread(buttonArray, i, boolArray));	
				}
				
				threadPool.shutdown();
				
			}
			if(e.getSource() == btnClear) {
				for(int i=0; i<nSize; i++)
					for(int j=0; j<nSize; j++) {
						buttonArray[i][j].setBackground(Color.WHITE);
						buttonArray[i][j].setOpaque(true);
					}
				if(!threadPool.isShutdown())
					threadPool.shutdown();
			}
			else {
				JButton button = (JButton) e.getSource();
				if(button.getBackground() == Color.WHITE) {
					button.setBackground(Color.BLACK);
					button.setOpaque(true);
				}
				else if(button.getBackground() == Color.BLACK) {
					button.setBackground(Color.WHITE);
					button.setOpaque(true);
				}
			}
			
		}
		 
	 }
}

