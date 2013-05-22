/**
 * 
 */
package com.tw.merchant;

/**
 * Represents exceptional condition when the numeral symbol passed is invalid
 * 
 * @author vishalshu
 * 
 */
public class InvalidNumeralException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3042477092881140910L;

	public InvalidNumeralException(String msg) {
		super(msg);
	}

}
