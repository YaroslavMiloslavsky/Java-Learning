import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MovingBallzGUI extends JPanel {
	private Vector<BallClass> ballsArray; // A vector is used because of synchronisation
	// Vector uses only 1 thread at a time
	private ExecutorService threadPool;
	
	public MovingBallzGUI() {
		ballsArray = new Vector<BallClass>();
		threadPool = Executors.newCachedThreadPool(); // A non fix sized pool of threads
		this.addMouseListener(new Listener());
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Iterator<BallClass> itr = ballsArray.iterator() ; itr.hasNext();) {
			BallClass ball = itr.next();
			g.fillOval(ball.getX(),ball.getY(), 10 , 10);
		}
	}
	private class Listener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(SwingUtilities.isLeftMouseButton(e)) {
				BallClass newBall = new BallClass(e.getX(),e.getY(),MovingBallzGUI.this);
				ballsArray.add(newBall);
				threadPool.execute(newBall); // each ball is a Thread
			}
			if(SwingUtilities.isRightMouseButton(e)) {
				Iterator<BallClass> itr = ballsArray.iterator();
				try {
					ballsArray.remove(itr.next());
				} catch (NoSuchElementException ex){
				}
				
			}
		}
	}
}


