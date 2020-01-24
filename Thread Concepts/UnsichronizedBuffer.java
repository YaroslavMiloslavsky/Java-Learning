import javax.swing.JTextArea;

public class UnsichronizedBuffer implements Buffer {
	private JTextArea txtArea;
	private int buffer = -1;
	
	public UnsichronizedBuffer(JTextArea area) {
		this.txtArea = area;
	}
	@Override
	public void blockingPut(int value, JTextArea area) throws InterruptedException {
		txtArea.append("Producer writes " + value+ "\n");
		buffer = value;
	}

	@Override
	public int blockingGet() throws InterruptedException {
		txtArea.append("Consumer reads " + buffer + "\n");
		return buffer;
	}
	
}
