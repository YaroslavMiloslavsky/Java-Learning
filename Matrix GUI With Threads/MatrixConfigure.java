import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MatrixConfigure extends JPanel {
	private JButton btnStart;
	private JTextField txtNSize, txtThreadNumber, txtLapsNumber;
	private JLabel lblNsize, lblThreadNumber, lblLapsNumber;
	private JTextArea txtArea;
	
	public MatrixConfigure() {
		ControlListener cmd = new ControlListener();
		JPanel startPanel = new JPanel(); // panel for start button
		JPanel labelPanel = new JPanel(); // panel for all labels and text fields
		
		btnStart = new JButton("Start");
		txtNSize = new JTextField(10);
		txtThreadNumber = new JTextField(10);
		txtLapsNumber = new JTextField(10);
		lblNsize = new JLabel("Matrix size (n)");
		lblThreadNumber = new JLabel("Threads number (m)");
		lblLapsNumber = new JLabel("Laps number (t)");
		txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setFont(txtArea.getFont().deriveFont(Font.BOLD, 14f));
		JScrollPane scroll = new JScrollPane(txtArea);
		
		// south panel
		startPanel.setLayout(new GridLayout());
		btnStart.addActionListener(cmd);
		startPanel.add(btnStart);
		
		// north panel
		labelPanel.setLayout(new GridLayout(3,2,2,2));
		labelPanel.add(lblNsize);
		labelPanel.add(txtNSize);
		labelPanel.add(lblThreadNumber);
		labelPanel.add(txtThreadNumber);
		labelPanel.add(lblLapsNumber);
		labelPanel.add(txtLapsNumber);
		
		// Center
		txtArea.append(String.format("Hello, this is configuration of the matrix\n\n"
				+ "Please enter the matrix dim n\n"
				+ "The number of threads you wish to execute m\n"
				+ "And the number of laps (runs in a single go)\nthat you wish to perform each round t"
				+ "\n\nPlease fill the fields as needed and press start"));
		
		// final 
		this.setLayout(new BorderLayout());
		this.add(labelPanel , BorderLayout.NORTH);
		this.add(startPanel , BorderLayout.SOUTH);
		this.add(scroll , BorderLayout.CENTER);
	}
	
	private class ControlListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == btnStart) {		
				try {
					int n = Integer.parseInt(txtNSize.getText());
					int m = Integer.parseInt(txtThreadNumber.getText());
					int t = Integer.parseInt(txtLapsNumber.getText());
					
					JFrame frame = new JFrame("Shrink");
					frame.setSize(500,500);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.add(new ShrinkGUI(n,m,t));
					frame.setVisible(true);
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(txtArea, "Fields cant be blank and must contain digits only");
				}
			}
		}
		
	}
}
