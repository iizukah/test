package vendingMachine.item;

public class Tea extends Item {
	/*
	 * フィールド
	 */
	private String kind; // 種類

	/*
	 * メソッド
	 */
	public Tea(String name, int price, String kind) {
		super(name, price);
		this.kind = kind;
	}

	@Override
	public String getNote() {
		return "種類：" + kind;
	}

}
