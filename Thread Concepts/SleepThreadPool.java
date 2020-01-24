import javax.swing.JTextArea;

public class SleepThreadPool implements Runnable{
	private String name;
	private int sleepTime;
	private JTextArea txtArea;
	
	
	public SleepThreadPool(String name, int sleepTime, JTextArea txtArea) {
		this.name = name;
		this.sleepTime = sleepTime;
		this.txtArea = txtArea;
	}
	
	@Override
	public void run() {
		try {
			txtArea.append(name+" is going to sleep for " + sleepTime/1000 + " seconds.\n");
			Thread.sleep(sleepTime);
		} catch(InterruptedException e) {}
		
		txtArea.append(name + " woke up!\n");
	}
	
}
