/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;

/**
 * @author vishalshu
 *
 */
public class Quantifier extends SentenceToken {
	public Quantifier() {
	}
	
	public Integer getValue() throws InvalidNumeralException{
		try{
			int value = Integer.parseInt(symbol.trim());
			return value;
		}catch(NumberFormatException nfe){
			throw new InvalidNumeralException(symbol+" is not a valid number.");
		}
		
	}
	
	
}
