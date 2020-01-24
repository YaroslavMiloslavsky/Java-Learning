import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyGui extends JPanel {
	
	private JButton cmdPress, cmdNullify, cmdUnpress;
	private JLabel 	lblCount;
	private int counter , x, y;
	private final int SIZE = 10;
	
	public MyGui() {
		counter = 0;
		x = y = -400;
		
		// Anonymous Class
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				repaint();
			}
		});
		
		
		//Making the obj
		lblCount = new JLabel("Presses: " + counter);
		cmdPress = new JButton("Press");
		cmdUnpress = new JButton("Unpress");
		cmdNullify = new JButton("Nullify");
		
		//Actions listeners
		PressPerformed action = new PressPerformed();
		cmdPress.addActionListener(action);
		cmdNullify.addActionListener(action);
		cmdUnpress.addActionListener(action);
		
		//adding the obj to the panel
		add(lblCount);
		add(cmdPress);
		add(cmdNullify);
		add(cmdUnpress);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(x, y, SIZE, SIZE);
	}
//	private class Listener implements MouseListener{
//		
//		public void mouseClicked(MouseEvent e) {
//			x = e.getX();
//			y = e.getY();
//			repaint();
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//	}
	
//	private class Listener extends MouseAdapter {
//		public void mouseClicked(MouseEvent e) {
//			x = e.getX();
//			y = e.getY();
//			repaint();
//		}
//	}
	// sub class
	private class PressPerformed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cmdPress)
				counter++;
			if(e.getSource() == cmdUnpress)
				counter--;
			if(e.getSource() == cmdNullify)
				counter = 0;	
			
			lblCount.setText("Presses: " + counter);
		}
	}
	

}
