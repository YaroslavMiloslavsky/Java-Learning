import javax.swing.JTextArea;

public class GetBalanceThread implements Runnable {
	private Account account;
	private JTextArea txtArea;
	
	public GetBalanceThread(Account a, JTextArea area) {
		this.account = a;
		this.txtArea = area;
	}
	@Override
	public void run() {
		txtArea.append(String.format("%d%n", account.getBalance()));
	}
}	
