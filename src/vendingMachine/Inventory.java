package vendingMachine;

import java.util.ArrayList;
import java.util.List;

import vendingMachine.item.Item;

public class Inventory {
	/*
	 * �t�B�[���h
	 */
	private List<Item> items = new ArrayList<>(); // ���ݕ����X�g

	private List<Stock> stocks = new ArrayList<>();

	/*
	 * ���\�b�h
	 */

	// �R���X�g���N�^
	public Inventory() {
	}

	public List<Item> getItems() {
		// �ύX�s�̃R���N�V�����Ƃ��ĕԋp����
		return java.util.Collections.unmodifiableList(items);
	}

	public List<Stock> getStocks() {
		// �ύX�s�̃R���N�V�����Ƃ��ĕԋp����
		return java.util.Collections.unmodifiableList(stocks);
	}

	/**
	 * �w��̏��i���̍݌ɏ����擾����B
	 * @param itemName ���i��
	 * @return �݌ɏ��B�w�肷�閼�̂̏��i���������null�B
	 */
	public Stock findStock(String itemName) {
		for(Stock stock : stocks) {
			if(stock.getItem().getName().equals(itemName)) return stock;
		}
		return null;
	}

	/**
	 * �w��̃C���f�b�N�X(0�n�܂�)�̍݌ɏ����擾����B
	 * @param index �C���f�b�N�X
	 * @return �݌ɏ��
	 */
	public Stock getStock(int index) {
		try {
			return stocks.get(index);
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 *  ���i���o�^
	 * @param item �o�^���鏤�i
	 * @return �o�^�ɐ��������true�B���ɓo�^����Ă���ꍇfalse��Ԃ��B
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
	 *  �݌ɐ��ݒ�
	 * @param itemName ���i��
	 * @param quantity �݌ɐ�
	 * @return ���������true�B�w��̏��i�����݂��Ȃ��Ȃǎ��s�����false�B
	 */
	public boolean setStock(String itemName, int quantity) {
		Stock stock = findStock(itemName);
		if(stock == null) return false;
		stock.setQuantity(quantity);
		return true;
	}

	/**
	 *  �����݌ɐ��ݒ�
	 * @param amount �����݌ɐ�
	 */
	public void setInitStock(int amount) {
		for(Item item : items) {
			setStock(item.getName(), amount);
		}
	}

	/**
	 * �݌ɂ����鏤�i�̂����Œቿ�i�̏��i���擾����B
	 * @return �Œቿ�i�̏��i������΂��̏��i���A�Ȃ����null��Ԃ��B
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
