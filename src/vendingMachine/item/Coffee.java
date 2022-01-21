package vendingMachine.item;

public class Coffee extends Item {
	/*
	 * �t�B�[���h
	 */
	private String areaOfOrigin; // �R�[�q�[�����Y�n("area of origin": BMS�d�l���)

	/*
	 * ���\�b�h
	 */
	public Coffee(String name, int price, String areaOfOrigin) {
		super(name, price);
		this.areaOfOrigin = areaOfOrigin;
	}

	@Override
	public String getNote() {
		return "�����Y�n�F" + areaOfOrigin;
	}
}
