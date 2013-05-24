/**
 * 
 */
package com.tw.merchant;

import java.util.Scanner;

/**
 * Reads inputs from console
 * @author vishalshu
 * 
 */
public class ConsoleInputReader implements InputReader {

	String lastLine;

	public ConsoleInputReader() {
		System.out.println("Type 'exit' to end the program");
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public String next() {
		scanInput();
		if (lastLine.equalsIgnoreCase("exit")) {
			Runtime.getRuntime().exit(0);
		}
		return lastLine;
	}

	@Override
	public void remove() {

	}

	private void scanInput() {
		Scanner reader = new Scanner(System.in);
		lastLine = reader.nextLine();
	}

}
