/**
 * 
 */
package com.tw.merchant.vocab;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;

/**
 * Roman numeral definition of <PrimaryVocab>
 * 
 * @author vishalshu
 * 
 */
public enum RomanVocab {
	M(1000), CM(900), D(500), CD(400), C(100), XC(90), L(50), XL(40), X(10), IX(
			9), V(5), IV(4), I(1);

	private Integer number;

	private RomanVocab(Integer number) {
		this.number = number;
	}

	public static RomanVocab primitiveValueOf(String primitiveRomanSymbol)
			throws InvalidNumeralException {
		RomanVocab symbol = null;

		InvalidNumeralException ex = new InvalidNumeralException(
				ErrorMessage.INVALID_PRIMITIVE_NUMERAL
						.getMessage(primitiveRomanSymbol));

		try {
			symbol = RomanVocab.valueOf(primitiveRomanSymbol.toUpperCase());
			switch (symbol) {
				case CM:
				case CD:
				case XC:
				case XL:
				case IX:
				case IV:
					throw ex;
			}
		} catch (IllegalArgumentException e) {
			throw ex;
		}
		return symbol;
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

	public Integer getNumber() {
		return number;
	}

}
