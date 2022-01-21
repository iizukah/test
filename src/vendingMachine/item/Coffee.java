package vendingMachine.item;

public class Coffee extends Item {
	/*
	 * フィールド
	 */
	private String areaOfOrigin; // コーヒー豆原産地("area of origin": BMS仕様より)

	/*
	 * メソッド
	 */
	public Coffee(String name, int price, String areaOfOrigin) {
		super(name, price);
		this.areaOfOrigin = areaOfOrigin;
	}

	@Override
	public String getNote() {
		return "豆原産地：" + areaOfOrigin;
	}
}
