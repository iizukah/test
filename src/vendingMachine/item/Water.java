package vendingMachine.item;

public class Water extends Item {
	/*
	 * �t�B�[���h
	 */
	private String samplingSite; //�̐��n

	/*
	 * ���\�b�h
	 */
	public Water(String name, int price, String samplingSite) {
		super(name, price);
		this.samplingSite = samplingSite;
	}

	@Override
	public String getNote() {
		return "�̐��n�F" + samplingSite;
	}

}
