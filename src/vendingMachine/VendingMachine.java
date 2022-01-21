package vendingMachine;

import java.io.IOException;

import vendingMachine.item.Item;

public class VendingMachine {
	private final Inventory inventory = new Inventory();

	private final CashDeposit deposit = new CashDeposit();

	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * メインループ
	 */
	public void mainLoop() {
		try {
			while(true) {
				System.out.println("◆◆　トップメニュー　◆◆");
				System.out.println("(1) 商品を購入する");
				System.out.println("(2) 在庫を確認する");
				System.out.println("(3) 終了");

				int choice = ConsoleInput.readInteger("番号を入力してください。", 1, 3);

				switch (choice) {
				case 1:
					menuPurchase();
					break;
				case 2:
					menuInventory();
					break;
				case 3:
					System.out.println("アプリケーションを終了します。");
					return;
				default:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商品を購入する
	 */
	private void menuPurchase() throws IOException {
		menuPurchase_displayItems();

		// 商品購入ループ
		while (true) {

			// 操作メニューの表示
			menuPurchase_displayMenu();
			int choice = menuPurchase_selectMenu();

			switch(choice) {
			case 1:
				// 「お金を投入」を選択
				menuPurchase_acceptDeposit();
				break;
			case 2:
				// 「商品を選択する」を選択
				break;
			default:
				//例外処理：選択肢以外は操作画面へ戻る
				continue;
			}

			// 商品選択ループ
			Stock stock = menuPurchase_chooseItem();

			// 決済処理
			boolean paymentSucceeded = menuPurchase_payWithCash(stock);

			// 商品排出＆在庫更新
			if(paymentSucceeded) {
				// 商品排出
				menuPurchase_dispenseItem(stock.getItem());

				// 在庫を1減らす
				menuPurchase_decrementStock(stock);
			} else {
				continue;
			}

			menuPurchase_refundDeposit();
			break;
		} // while
	}

	/**
	 * 商品一覧表示
	 */
	private void menuPurchase_displayItems() {
		System.out.println("◆◆ 商品一覧 ◆◆");
		int index = 1;
		for(Item item : inventory.getItems()) {
			System.out.print("(" + index + ") " + item.getName());
			System.out.print(" \t" + item.getPrice() + "円");
			System.out.println(" （ " + item.getNote() + " ）");
			index++;
		}
	}

	/**
	 * 購入メニューの表示と入力受付
	 * @return 選択結果の整数値
	 */
	private void menuPurchase_displayMenu() {
		System.out.println("◆◆　購入メニュー　◆◆");
		System.out.println("(1) お金を投入する");
		System.out.println("(2) 商品を選択する");
		System.out.println("(3) キャンセル");
	}

	/**
	 * 購入メニューの選択
	 * @return 選択されたメニュー番号
	 * @throws IOException
	 */
	private int menuPurchase_selectMenu() throws IOException {
		return ConsoleInput.readInteger("番号を入力してください。", 1, 3);
	}

	/**
	 * 商品選択
	 * @return 選択された在庫
	 * @throws IOException
	 */
	private Stock menuPurchase_chooseItem() throws IOException {
		Stock stock = null;
		while(true) {
			int itemNumber = ConsoleInput.readInteger("商品を選んでください。", 1, inventory.getItems().size());

			// 在庫確認
			stock = inventory.getStock(itemNumber - 1);
			if(stock == null) {
				System.out.println("指定の商品番号はありません。");
				continue;
			} else if(stock.getQuantity() == 0) {
				System.out.println("在庫がありません。");
				continue;
			} else {
				// 在庫あり
				break;
			}
		} // while
		return stock;
	}

	/**
	 * 顧客からのお金受け入れ。
	 * @throws IOException
	 */
	private void menuPurchase_acceptDeposit() throws IOException {
		int inputAmount = ConsoleInput.readInteger("お金を投入してください。", 1, 10000);
		deposit.addAmount(inputAmount); // 投入金額更新
	}

	/**
	 * 現金支払い
	 * @param stock 購入対象の在庫
	 * @return 購入が成功の場合true。そうでなければfalse。
	 */
	private boolean menuPurchase_payWithCash(Stock stock) {
		// 預かり高が不足している場合メニューに戻る
		if (deposit.getAmount() < stock.getItem().getPrice()) {
			System.out.println("お金が不足しています。");
			return false;
		}

		// 残高を更新
		deposit.addAmount(-stock.getItem().getPrice());

		return true;
	}

	/**
	 * 在庫を1減らす
	 * @param stock 対象の在庫
	 */
	private void menuPurchase_decrementStock(Stock stock) {
		stock.setQuantity(stock.getQuantity() - 1);
	}

	/**
	 * 商品排出（購入した商品の表示）
	 * @param item 排出される商品
	 */
	private void menuPurchase_dispenseItem(Item item) {
		System.out.println("【" + item.getName() + "】を購入しました。");
	}

	/**
	 * 返金、またはお釣りの返却
	 * @throws IOException
	 */
	private void menuPurchase_refundDeposit() throws IOException {
		if(deposit.getAmount() > 0) {
			System.out.print("おつりは" + deposit.getAmount() + "円です。");
			ConsoleInput.readEnter("おつりをお取りください。");

			deposit.setAmount(0);	// 預かり金を更新(リセット)
		}
	}

	/**
	 * 在庫表示メニュー
	 * @throws IOException
	 */
	private void menuInventory() throws IOException {
		menuInventory_displayInventory();
		ConsoleInput.readEnter("表示を確認してください。");
	}

	/**
	 * 在庫表示
	 */
	private void menuInventory_displayInventory() {
		System.out.println("◆◆ 商品在庫 ◆◆");
		int index = 1;
		for(Stock stock : inventory.getStocks()) {
			System.out.println("(" + index + ") " + stock.getItem().getName() + " ・・・・" +
					stock.getQuantity() + "本");
			index++;
		}
	}

}
