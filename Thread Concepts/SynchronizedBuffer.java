import javax.swing.JTextArea;

public class SynchronizedBuffer implements Buffer {
	private int buffer = -1;
	private boolean occupied = false;
	private JTextArea area;
	
	public SynchronizedBuffer(JTextArea area) {
		this.area=area;
	}
	@Override
	public synchronized void blockingPut(int value, JTextArea txtArea) throws InterruptedException {
		while(occupied) {
			txtArea.append("Producer tries to write but buffer is full\n");
			wait();
		}
		buffer = value;
		occupied = true;
		txtArea.append("Producer writes"+ buffer + "\n");
		notifyAll();
	}

	@Override
	public synchronized int blockingGet() throws InterruptedException {
		while(!occupied){
			area.append("Consumer tries to read but buffer is empty\n");
			wait();
		}
		occupied= false;
		area.append("Consumer reads from buffer "+ buffer + "\n");
		notifyAll();
		return buffer;
	}

}
