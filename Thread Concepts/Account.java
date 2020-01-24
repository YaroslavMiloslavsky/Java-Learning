
public class Account {
	private int balance;
	
	public Account() {
		balance = 0;
	}
	
	public synchronized void transaction(int amountOfMoney) {
		int add = (amountOfMoney>0)? 1:-1;
		for(int i=0; i<Math.abs(amountOfMoney);i++)
			balance += add;
	}
	
	public synchronized int getBalance() {
		return this.balance;
	}
}
