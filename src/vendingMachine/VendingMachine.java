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
	 * ���C�����[�v
	 */
	public void mainLoop() {
		try {
			while(true) {
				System.out.println("�����@�g�b�v���j���[�@����");
				System.out.println("(1) ���i���w������");
				System.out.println("(2) �݌ɂ��m�F����");
				System.out.println("(3) �I��");

				int choice = ConsoleInput.readInteger("�ԍ�����͂��Ă��������B", 1, 3);

				switch (choice) {
				case 1:
					menuPurchase();
					break;
				case 2:
					menuInventory();
					break;
				case 3:
					System.out.println("�A�v���P�[�V�������I�����܂��B");
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
	 * ���i���w������
	 */
	private void menuPurchase() throws IOException {
		menuPurchase_displayItems();

		// ���i�w�����[�v
		while (true) {

			// ���상�j���[�̕\��
			menuPurchase_displayMenu();
			int choice = menuPurchase_selectMenu();

			switch(choice) {
			case 1:
				// �u�����𓊓��v��I��
				menuPurchase_acceptDeposit();
				break;
			case 2:
				// �u���i��I������v��I��
				break;
			default:
				//��O�����F�I�����ȊO�͑����ʂ֖߂�
				continue;
			}

			// ���i�I�����[�v
			Stock stock = menuPurchase_chooseItem();

			// ���Ϗ���
			boolean paymentSucceeded = menuPurchase_payWithCash(stock);

			// ���i�r�o���݌ɍX�V
			if(paymentSucceeded) {
				// ���i�r�o
				menuPurchase_dispenseItem(stock.getItem());

				// �݌ɂ�1���炷
				menuPurchase_decrementStock(stock);
			} else {
				continue;
			}

			menuPurchase_refundDeposit();
			break;
		} // while
	}

	/**
	 * ���i�ꗗ�\��
	 */
	private void menuPurchase_displayItems() {
		System.out.println("���� ���i�ꗗ ����");
		int index = 1;
		for(Item item : inventory.getItems()) {
			System.out.print("(" + index + ") " + item.getName());
			System.out.print(" \t" + item.getPrice() + "�~");
			System.out.println(" �i " + item.getNote() + " �j");
			index++;
		}
	}

	/**
	 * �w�����j���[�̕\���Ɠ��͎�t
	 * @return �I�����ʂ̐����l
	 */
	private void menuPurchase_displayMenu() {
		System.out.println("�����@�w�����j���[�@����");
		System.out.println("(1) �����𓊓�����");
		System.out.println("(2) ���i��I������");
		System.out.println("(3) �L�����Z��");
	}

	/**
	 * �w�����j���[�̑I��
	 * @return �I�����ꂽ���j���[�ԍ�
	 * @throws IOException
	 */
	private int menuPurchase_selectMenu() throws IOException {
		return ConsoleInput.readInteger("�ԍ�����͂��Ă��������B", 1, 3);
	}

	/**
	 * ���i�I��
	 * @return �I�����ꂽ�݌�
	 * @throws IOException
	 */
	private Stock menuPurchase_chooseItem() throws IOException {
		Stock stock = null;
		while(true) {
			int itemNumber = ConsoleInput.readInteger("���i��I��ł��������B", 1, inventory.getItems().size());

			// �݌Ɋm�F
			stock = inventory.getStock(itemNumber - 1);
			if(stock == null) {
				System.out.println("�w��̏��i�ԍ��͂���܂���B");
				continue;
			} else if(stock.getQuantity() == 0) {
				System.out.println("�݌ɂ�����܂���B");
				continue;
			} else {
				// �݌ɂ���
				break;
			}
		} // while
		return stock;
	}

	/**
	 * �ڋq����̂����󂯓���B
	 * @throws IOException
	 */
	private void menuPurchase_acceptDeposit() throws IOException {
		int inputAmount = ConsoleInput.readInteger("�����𓊓����Ă��������B", 1, 10000);
		deposit.addAmount(inputAmount); // �������z�X�V
	}

	/**
	 * �����x����
	 * @param stock �w���Ώۂ̍݌�
	 * @return �w���������̏ꍇtrue�B�����łȂ����false�B
	 */
	private boolean menuPurchase_payWithCash(Stock stock) {
		// �a���荂���s�����Ă���ꍇ���j���[�ɖ߂�
		if (deposit.getAmount() < stock.getItem().getPrice()) {
			System.out.println("�������s�����Ă��܂��B");
			return false;
		}

		// �c�����X�V
		deposit.addAmount(-stock.getItem().getPrice());

		return true;
	}

	/**
	 * �݌ɂ�1���炷
	 * @param stock �Ώۂ̍݌�
	 */
	private void menuPurchase_decrementStock(Stock stock) {
		stock.setQuantity(stock.getQuantity() - 1);
	}

	/**
	 * ���i�r�o�i�w���������i�̕\���j
	 * @param item �r�o����鏤�i
	 */
	private void menuPurchase_dispenseItem(Item item) {
		System.out.println("�y" + item.getName() + "�z���w�����܂����B");
	}

	/**
	 * �ԋ��A�܂��͂��ނ�̕ԋp
	 * @throws IOException
	 */
	private void menuPurchase_refundDeposit() throws IOException {
		if(deposit.getAmount() > 0) {
			System.out.print("�����" + deposit.getAmount() + "�~�ł��B");
			ConsoleInput.readEnter("���������肭�������B");

			deposit.setAmount(0);	// �a��������X�V(���Z�b�g)
		}
	}

	/**
	 * �݌ɕ\�����j���[
	 * @throws IOException
	 */
	private void menuInventory() throws IOException {
		menuInventory_displayInventory();
		ConsoleInput.readEnter("�\�����m�F���Ă��������B");
	}

	/**
	 * �݌ɕ\��
	 */
	private void menuInventory_displayInventory() {
		System.out.println("���� ���i�݌� ����");
		int index = 1;
		for(Stock stock : inventory.getStocks()) {
			System.out.println("(" + index + ") " + stock.getItem().getName() + " �E�E�E�E" +
					stock.getQuantity() + "�{");
			index++;
		}
	}

}
