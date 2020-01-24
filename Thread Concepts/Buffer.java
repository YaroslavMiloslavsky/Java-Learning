import javax.swing.JTextArea;

public interface Buffer {
	public void blockingPut(int value, JTextArea txtArea) throws InterruptedException;
	public int blockingGet() throws InterruptedException;
}
