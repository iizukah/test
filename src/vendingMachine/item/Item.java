package vendingMachine.item;

/**
 * ���i���B���i���≿�i�Ȃǂ̏���ێ��B
 * @author satohisa
 *
 */
public class Item {

	/*
	 * �t�B�[���h
	 */
	private String name; // ���O
	private String maker; // ���[�J�[
	private int price; // ���i
	private boolean cold; // �≷�itrue:Cold,false:Hot�j
	private int volume; // ���e�ʁiml�j

	/*
	 * ���\�b�h
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
