import java.security.SecureRandom;

import javax.swing.JTextArea;

public class Prodeucer implements Runnable {
	private static final SecureRandom generator = new SecureRandom();
	private final Buffer sharedLocation;
	private JTextArea txtArea;
	
	public Prodeucer(Buffer location, JTextArea area) {
		this.sharedLocation = location;
		this.txtArea = area;
	}
	
	@Override
	public void run() { 
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(generator.nextInt(3000));
				sharedLocation.blockingPut(i+1,txtArea);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		txtArea.append("Producer done producing\n");
	}

}
