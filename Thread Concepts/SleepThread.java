import javax.swing.JTextArea;

public class SleepThread extends Thread {
	private int sleepTime;
	private JTextArea txtArea;
	
	public SleepThread(String name, int sleepTime, JTextArea txtArea) {
		super(name);
		this.sleepTime = sleepTime;
		this.txtArea = txtArea;
	}
	
	public void run() {
		
		try {
			txtArea.append(this.getName() + " is going to sleep\n");
			Thread.sleep(sleepTime);
		} catch(InterruptedException e ) {}
		
		txtArea.append(this.getName() + " is woke AF\n");
	}
}
