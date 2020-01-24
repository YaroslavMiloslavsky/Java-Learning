import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JTextArea;

public class BlockingBuffer implements Buffer {
	private final ArrayBlockingQueue<Integer> buffer;
	private JTextArea txtArea;
	public BlockingBuffer(JTextArea area) {
		buffer = new ArrayBlockingQueue<Integer>(1);
		txtArea = area;
	}
	
	@Override
	public void blockingPut(int value, JTextArea txtArea) throws InterruptedException {
		buffer.put(value);
		txtArea.append("Producer writes " + value+ "\n");
	}

	@Override
	public int blockingGet() throws InterruptedException {
		int readVal = buffer.take();
		txtArea.append("Consumer reads " + readVal + "\n");
		return readVal;
	}

}
