import vendingMachine.Inventory;
import vendingMachine.VendingMachine;
import vendingMachine.item.Coffee;
import vendingMachine.item.Item;
import vendingMachine.item.Tea;
import vendingMachine.item.Water;

public class Main {

	private static final int INIT_STOCK = 30; // �e�����݌ɐ�

	public static void main(String[] args) {
		VendingMachine machine = new VendingMachine();
		System.out.print("test");
		// �݌ɏ�����
		tryRegisterItem(machine.getInventory(), new Coffee("BOSS RAINBOW", 130, "�R�����r�A"));
		tryRegisterItem(machine.getInventory(), new Coffee("BOSS PREMIUM", 130, "�O�A�e�}��"));
		tryRegisterItem(machine.getInventory(), new Water("���������V�R��", 120, "�x�m�R�["));
		tryRegisterItem(machine.getInventory(), new Tea("���� ���₽�� ", 180, "�Β�"));
		tryRegisterItem(machine.getInventory(), new Tea("�u �� �� ��", 180, "�u�����h��"));
		machine.getInventory().setInitStock(INIT_STOCK); // �����݌ɐݒ�

		machine.mainLoop();
	}

	private static void tryRegisterItem(Inventory inventory, Item item) {
		if(!inventory.register(item)) {
			System.out.println(item.getName() + "�͊��ɓo�^����Ă��܂��B");
		}
	}
}
