package vendingMachine.item;

public class Tea extends Item {
	/*
	 * �t�B�[���h
	 */
	private String kind; // ���

	/*
	 * ���\�b�h
	 */
	public Tea(String name, int price, String kind) {
		super(name, price);
		this.kind = kind;
	}

	@Override
	public String getNote() {
		return "��ށF" + kind;
	}

}
