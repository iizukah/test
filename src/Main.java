import vendingMachine.Inventory;
import vendingMachine.VendingMachine;
import vendingMachine.item.Coffee;
import vendingMachine.item.Item;
import vendingMachine.item.Tea;
import vendingMachine.item.Water;

public class Main {

	private static final int INIT_STOCK = 30; // 各初期在庫数

	public static void main(String[] args) {
		VendingMachine machine = new VendingMachine();
		System.out.print("test");
		// 在庫初期化
		tryRegisterItem(machine.getInventory(), new Coffee("BOSS RAINBOW", 130, "コロンビア"));
		tryRegisterItem(machine.getInventory(), new Coffee("BOSS PREMIUM", 130, "グアテマラ"));
		tryRegisterItem(machine.getInventory(), new Water("おいしい天然水", 120, "富士山麓"));
		tryRegisterItem(machine.getInventory(), new Tea("綾鷹 あやたか ", 180, "緑茶"));
		tryRegisterItem(machine.getInventory(), new Tea("爽 健 美 茶", 180, "ブレンド茶"));
		machine.getInventory().setInitStock(INIT_STOCK); // 初期在庫設定

		machine.mainLoop();
	}

	private static void tryRegisterItem(Inventory inventory, Item item) {
		if(!inventory.register(item)) {
			System.out.println(item.getName() + "は既に登録されています。");
		}
	}
}
