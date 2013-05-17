/**
 * 
 */
package com.tw.merchant;

import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author vishalshu
 * 
 */
public class InputParser {

	String[] queryKeywords = { "how many", "how much" };
	String[] keywords = { "credits", "is" };

	public Input parseInput(String inputStr) {
		Input input = null;

		inputStr = inputStr.toLowerCase();

		if(!startsWith(inputStr, keywords) && !startsWith(inputStr, queryKeywords)){
			StringTokenizer tokenizer = new StringTokenizer(inputStr);
			if(tokenizer.countTokens()==3){
				// Vocab Input
			}
			
			else{
				// Material Credit Input
				
				
			}
		}
		
		if (inputStr.toLowerCase().startsWith("how many")
				|| inputStr.toLowerCase().startsWith("how much")) {
			input = new QueryInput();
			inputStr = inputStr.substring(8).trim();

		} else {
			inputStr.split(" is ");

		}

		return input;

	}
	
	void parse(String input){
		StringTokenizer tokenizer = new StringTokenizer(input);
		Queue<String> tokens = new LinkedBlockingQueue<String>();
		String adj = null;
		String verb = null;
		String subject = null;
		String object = null;
		
		while(tokenizer.hasMoreTokens()){
			tokenizer.nextToken();
		}
	}

	private boolean startsWithCommonKeyword(String inputStr) {
		return startsWith(inputStr, keywords);
	}

	private boolean startsWithQueryKeyword(String inputStr) {
		return startsWith(inputStr, queryKeywords);
	}

	private boolean startsWith(String inputStr, String[] keywords) {
		for (String keyword : keywords) {
			if (inputStr.startsWith(keyword)) {
				return true;
			}
		}
		return false;
	}
}
