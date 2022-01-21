package vendingMachine.item;

/**
 * 商品情報。商品名や価格などの情報を保持。
 * @author satohisa
 *
 */
public class Item {

	/*
	 * フィールド
	 */
	private String name; // 名前
	private String maker; // メーカー
	private int price; // 価格
	private boolean cold; // 冷温（true:Cold,false:Hot）
	private int volume; // 内容量（ml）

	/*
	 * メソッド
	 */
	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isCold() {
		return cold;
	}

	public void setCold(boolean cold) {
		this.cold = cold;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getNote() {
		return "";
	}
}
