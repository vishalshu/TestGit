/**
 * 
 */
package com.tw.merchant;

import java.util.Scanner;

/**
 * @author vishalshu
 *
 */
public class ConsoleInputReader implements InputReader {

	String lastLine;
	
	@Override
	public boolean hasNext() {
		if(lastLine.equalsIgnoreCase("done")){
			return false;
		}
		return true;
	}

	@Override
	public String next() {
		scanInput();
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
