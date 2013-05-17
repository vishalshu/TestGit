/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidSyntaxException;

/**
 * @author vishalshu
 *
 */
public interface Parser<T> {
	T parse() throws InvalidSyntaxException;
}
