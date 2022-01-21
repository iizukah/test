package vendingMachine.item;

public class Water extends Item {
	/*
	 * フィールド
	 */
	private String samplingSite; //採水地

	/*
	 * メソッド
	 */
	public Water(String name, int price, String samplingSite) {
		super(name, price);
		this.samplingSite = samplingSite;
	}

	@Override
	public String getNote() {
		return "採水地：" + samplingSite;
	}

}
