
public class Compute extends Thread {
	private ThreadMonitor monitor;
	
	public Compute(ThreadMonitor monitor) {
		this.monitor = monitor;
	}
	
	public void run() {
		Integer[] couple = monitor.removeCouple();
		while(couple!=null) {
			monitor.addArray(couple);
			couple = monitor.removeCouple();
		} 
	}
}
