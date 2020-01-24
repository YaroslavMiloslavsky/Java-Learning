import javax.swing.JTextArea;

public class PrintThread extends Thread {
	private final int MAX = 100;
	private String ch;
	private JTextArea txtArea;
	
	public PrintThread(String ch, JTextArea area) {
		this.ch = ch;
		this.txtArea = area;
	}
	
	public void run() {
		
		printChar();
	}

	private synchronized void printChar() {
		for(int i=0 ; i<MAX ; i++)
			txtArea.append("\t"+ch+"\n");
	}
	
}
