/**
 * 
 */
package com.tw.merchant.grammar.parser;

import com.tw.merchant.grammar.InvalidSyntaxException;

/**
 * Parser interface to be implemented by all classes that parses input string
 * into specific type of object definied by generic type <T>
 * 
 * @author vishalshu
 * 
 */
public interface Parser<T> {
	/**
	 * @param input
	 *            String to be parsed
	 * @return specific type(T) of object to be returned after performing
	 *         parsing on input
	 * @throws InvalidSyntaxException
	 */
	T parse(String input) throws InvalidSyntaxException;
}
