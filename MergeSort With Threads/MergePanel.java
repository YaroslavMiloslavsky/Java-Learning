import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MergePanel extends JPanel {
	private ThreadMonitor monitor;
	private JTextField txtN, txtM;
	private JButton btnGenerate, btnMerge, btnClear;
	private JLabel lblN, lblM;
	private JTextArea mainArea;
	private Integer[] unsortedArray;
	private int numberOfThreads;
	
	public MergePanel() {
		txtN = new JTextField(10);
		lblN = new JLabel("Array size: ");
		lblM = new JLabel("Threads number: ");
		txtM = new JTextField(10);
		mainArea = new JTextArea();
		mainArea.setEditable(false);
		mainArea.setFont(mainArea.getFont().deriveFont(Font.BOLD, 14f));
		JScrollPane scrollPanel = new JScrollPane(mainArea);
		btnGenerate = new JButton("Generate array");
		btnMerge = new JButton("Merge-sort");
		btnClear = new JButton("Clear");
		
		ButtonListener listener = new ButtonListener();
		btnGenerate.addActionListener(listener);
		btnMerge.addActionListener(listener);
		btnClear.addActionListener(listener);
		
		//south panel
		JPanel southPanel = new JPanel();
		southPanel.add(lblN);
		southPanel.add(txtN);
		southPanel.add(lblM);
		southPanel.add(txtM);
		
		// north panel
		JPanel northPanel = new JPanel();
		northPanel.add(btnGenerate);
		northPanel.add(btnMerge);
		northPanel.add(btnClear);
		
		this.setLayout(new BorderLayout());
		this.add(scrollPanel , BorderLayout.CENTER);
		this.add(northPanel , BorderLayout.NORTH);
		this.add(southPanel , BorderLayout.SOUTH);
		
		mainArea.append("Please enter array size and threads number\n");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnGenerate) {
				mainArea.setText(null);
				if(txtN.getText().length()<1 || txtM.getText().length()<1) {
					JOptionPane.showMessageDialog(MergePanel.this, "Please fill the fields on the buttom of the panel");
					return;
				}
				try {
					Integer n = Integer.parseInt(txtN.getText());
					Integer m = Integer.parseInt(txtM.getText());
					
					unsortedArray = new Integer[n];
					numberOfThreads = m ;
					// Generate array with random values from 1 .. 100
					for(int i=0; i<n ;i++)
						unsortedArray[i] = (int)(1 + Math.random()*100);
					// Print the array
					for(Integer x : unsortedArray)
						mainArea.append(String.format("%d ", x));
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(MergePanel.this, "The fields must contain numbers");
				}
			}
			if(e.getSource() == btnMerge) {
				mainArea.append("\n");
				monitor = new ThreadMonitor(unsortedArray, numberOfThreads);
				for(int i=0; i<numberOfThreads; i++)
		            (new Compute(monitor)).start();
				
				Integer[] temp = monitor.sortedArray();
				for(Integer x : temp)
					mainArea.append(String.format("%d ", x));
			}
			if(e.getSource() == btnClear) {
				mainArea.setText(null);
			}
		}
	}
}
