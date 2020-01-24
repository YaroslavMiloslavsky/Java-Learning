
public class BallClass implements Runnable{
	private int x,y; // coordinates
	private int delay; // FPS of ball
	private int xInterval, yInterval; // the movement direction of the ball
	private MovingBallzGUI ballzFrame; // the frame for the balls to be balls
	private final int INTERVAL = 10;
	
	public BallClass(int x, int y, MovingBallzGUI frame) {
		this.x = x;
		this.y = y;
		this.ballzFrame = frame;
		this.delay = (int)(Math.random()*100); // random FPS
		this.xInterval = (int)(Math.random()*10) % 2 == 0 ? INTERVAL : -1 * INTERVAL; // random x direction
		this.yInterval = (int)(Math.random()*10) % 2 == 0 ? INTERVAL : -1 * INTERVAL; // random y direction
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	@Override
	public void run() {
		while(true) {
			x += xInterval;
			y += yInterval;
			
			if((x+10)>ballzFrame.getWidth() || x <=0) xInterval *= -1;
			if((y+20)>ballzFrame.getHeight() ||y<=10) yInterval *= -1;
			
			ballzFrame.repaint();
			try {
				Thread.sleep(delay);
			} catch(InterruptedException e) {}
		}
	}

}
