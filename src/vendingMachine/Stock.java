package vendingMachine;

import vendingMachine.item.Item;

/**
 * 商品在庫。ある商品がいくつ在庫されているかを管理する。
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
