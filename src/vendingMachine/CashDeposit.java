package vendingMachine;

/**
 * �ڋq�������Ǘ�
 *
 */
public class CashDeposit {
	private int amount;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void addAmount(int amount) {
		this.amount += amount;
	}
}
