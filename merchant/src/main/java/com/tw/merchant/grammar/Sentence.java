/**
 * 
 */
package com.tw.merchant.grammar;

/**
 * <b>Sentence</b> representation of the input parsed by the parser
 * 
 * @author vishalshu
 * 
 */
public abstract class Sentence {
	/**
	 * Executable command to perform action for the sentence
	 * 
	 * @return the command
	 */
	public abstract Command getCommand();

	/**
	 * If the sentence instance is query or simple action sentence
	 * 
	 * @return boolean isQuery
	 */
	public abstract boolean isQuery();
}
