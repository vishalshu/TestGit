/**
 * 
 */
package com.tw.merchant;

/**
 * @author vishalshu
 * 
 */
public class VocabInputParser {

	private String subject;
	private String object;
	private String input;
	public VocabInputParser(String input){
		this.input = input;
	}

	public void parse() {
		String[] expressions = input.split(" is ");
		subject = expressions[0];
		object = expressions[1];
	}
	
	public boolean isValidExpression(){
		/*if(input.split(" is ").length==2)*/
		return true;
	}

	public String getSubject() {
		return subject;
	}

	public String getObject() {
		return object;
	}

}
