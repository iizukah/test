package vendingMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
	/**
	 * 文字列を入力する
	 * @param prompt 入力待ちメッセージ
	 * @return 入力された文字列
	 * @throws IOException
	 */
	public static String readString(String prompt) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String line;
		do {
			System.out.print(prompt + ">");
			line = br.readLine();

			// Ctrl+C が入力された場合は正常終了する
			if (line == null) {
				System.exit(0);
			}

		} while (line.equals(""));

		return line;
	}

	/**
	 * 整数を入力する
	 * @param prompt 入力待ちメッセージ
	 * @param min 入力値の最小値
	 * @param max 入力値の最大値
	 * @return 入力された整数値
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
	 * [Enter]の入力を待機する
	 * @param prompt 入力待ちメッセージ
	 * @throws IOException
	 */
	public static void readEnter(String prompt) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String line;
		System.out.print(prompt + "[Enter]>");
		line = br.readLine();

		// Ctrl+C が入力された場合は正常終了する
		if (line == null) {
			System.exit(0);
		}
	}

}
