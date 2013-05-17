/**
 * 
 */
package com.tw.merchant;

/**
 * @author vishalshu
 *
 */
public class InvalidSyntaxException extends Exception{
	
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
