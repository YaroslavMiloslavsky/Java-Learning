
public class TransactionThread implements Runnable {
	private Account account;
	
	public TransactionThread(Account account) {
		this.account = account;
	}
	
	public void run() {
		account.transaction(10000000);
	}
}
