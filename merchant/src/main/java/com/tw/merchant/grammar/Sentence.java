/**
 * 
 */
package com.tw.merchant.grammar;

/**
 * @author vishalshu
 * 
 */
public abstract class Sentence {
	public abstract Command getCommand();
	public abstract boolean isQuery();
}
