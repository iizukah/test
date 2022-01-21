package vendingMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
	/**
	 * ���������͂���
	 * @param prompt ���͑҂����b�Z�[�W
	 * @return ���͂��ꂽ������
	 * @throws IOException
	 */
	public static String readString(String prompt) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String line;
		do {
			System.out.print(prompt + ">");
			line = br.readLine();

			// Ctrl+C �����͂��ꂽ�ꍇ�͐���I������
			if (line == null) {
				System.exit(0);
			}

		} while (line.equals(""));

		return line;
	}

	/**
	 * ��������͂���
	 * @param prompt ���͑҂����b�Z�[�W
	 * @param min ���͒l�̍ŏ��l
	 * @param max ���͒l�̍ő�l
	 * @return ���͂��ꂽ�����l
	 * @throws IOException
	 */
	public static int readInteger(String prompt, int min, int max) throws IOException {
		while(true) {
			try {
				int value = Integer.parseInt(ConsoleInput.readString(prompt + "[" + min + "-" + max + "]"));
				if(value < min || value > max) continue;
				return value;
			} catch(NumberFormatException e) {
				continue;
			}
		}
	}

	/**
	 * [Enter]�̓��͂�ҋ@����
	 * @param prompt ���͑҂����b�Z�[�W
	 * @throws IOException
	 */
	public static void readEnter(String prompt) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String line;
		System.out.print(prompt + "[Enter]>");
		line = br.readLine();

		// Ctrl+C �����͂��ꂽ�ꍇ�͐���I������
		if (line == null) {
			System.exit(0);
		}
	}

}
