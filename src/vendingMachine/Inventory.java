package vendingMachine;

import java.util.ArrayList;
import java.util.List;

import vendingMachine.item.Item;

public class Inventory {
	/*
	 * フィールド
	 */
	private List<Item> items = new ArrayList<>(); // 飲み物リスト

	private List<Stock> stocks = new ArrayList<>();

	/*
	 * メソッド
	 */

	// コンストラクタ
	public Inventory() {
	}

	public List<Item> getItems() {
		// 変更不可のコレクションとして返却する
		return java.util.Collections.unmodifiableList(items);
	}

	public List<Stock> getStocks() {
		// 変更不可のコレクションとして返却する
		return java.util.Collections.unmodifiableList(stocks);
	}

	/**
	 * 指定の商品名の在庫情報を取得する。
	 * @param itemName 商品名
	 * @return 在庫情報。指定する名称の商品が無ければnull。
	 */
	public Stock findStock(String itemName) {
		for(Stock stock : stocks) {
			if(stock.getItem().getName().equals(itemName)) return stock;
		}
		return null;
	}

	/**
	 * 指定のインデックス(0始まり)の在庫情報を取得する。
	 * @param index インデックス
	 * @return 在庫情報
	 */
	public Stock getStock(int index) {
		try {
			return stocks.get(index);
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 *  商品情報登録
	 * @param item 登録する商品
	 * @return 登録に成功すればtrue。既に登録されている場合falseを返す。
	 */
	public boolean register(Item item) {
		for(Item elem : items) {
			if(elem.getName().equals(item.getName())) return false;
		}

		items.add(item);
		Stock stock = new Stock();
		stock.setItem(item);
		stock.setQuantity(0);
		stocks.add(stock);

		return true;
	}

	/**
	 *  在庫数設定
	 * @param itemName 商品名
	 * @param quantity 在庫数
	 * @return 成功すればtrue。指定の商品が存在しないなど失敗すればfalse。
	 */
	public boolean setStock(String itemName, int quantity) {
		Stock stock = findStock(itemName);
		if(stock == null) return false;
		stock.setQuantity(quantity);
		return true;
	}

	/**
	 *  初期在庫数設定
	 * @param amount 初期在庫数
	 */
	public void setInitStock(int amount) {
		for(Item item : items) {
			setStock(item.getName(), amount);
		}
	}

	/**
	 * 在庫がある商品のうち最低価格の商品を取得する。
	 * @return 最低価格の商品があればその商品を、なければnullを返す。
	 */
	public Item getMinPriceItemInInventory() {
		int minPrice = Integer.MAX_VALUE;
		Item minPriceItem = null;

		for(Stock inventory : stocks) {
			if(0 < inventory.getQuantity()) {
				if(inventory.getItem().getPrice() < minPrice) {
					minPrice = inventory.getItem().getPrice();
					minPriceItem = inventory.getItem();
				}
			}
		}

		if(minPrice != Integer.MAX_VALUE) {
			return minPriceItem;
		} else {
			return null;
		}
	}

}
