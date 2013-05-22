/**
 * 
 */
package com.tw.merchant.grammar;

/**
 * Defines available types of <Quantifier>
 * 
 * @author vishalshu
 * 
 */
public enum QuantifierType {

	PRIMARY_NUMERAL, USER_DEFINED, NUMERIC;

	/**
	 * Returns quantifier for the type represented by the instance
	 * @return the quantifier instance
	 */
	public Quantifier createQuantifier() {
		Quantifier quantifier = null;
		switch (this) {
		case PRIMARY_NUMERAL:
			quantifier = new PrimaryNumeralQuantifier();
			break;
		case USER_DEFINED:
			quantifier = new UserDefinedQuantifier();
			break;
		case NUMERIC:
			quantifier = new Quantifier();
			break;
		}
		return quantifier;
	}
}
