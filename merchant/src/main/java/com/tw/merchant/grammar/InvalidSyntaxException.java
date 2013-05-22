/**
 * 
 */
package com.tw.merchant.grammar;

/**
 * Represents condition when Syntax of the token parsed is invalid
 * 
 * @author vishalshu
 * 
 */
public class InvalidSyntaxException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8625970058401153368L;

	public InvalidSyntaxException(Throwable t) {
		super(t);
	}

	public InvalidSyntaxException(String msg) {
		super(msg);
	}

}
