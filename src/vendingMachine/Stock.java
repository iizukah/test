package vendingMachine;

import vendingMachine.item.Item;

/**
 * ���i�݌ɁB���鏤�i�������݌ɂ���Ă��邩���Ǘ�����B
 * @author satohisa
 *
 */
public class Stock {
	private Item item;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private int quantity;
}
