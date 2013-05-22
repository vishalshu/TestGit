/**
 * 
 */
package com.tw.merchant.grammar.parser;

import com.tw.merchant.grammar.QuantifierType;

/**
 * Config object for <MaterialDefinitionSentenceParser>
 * 
 * @author vishalshu
 * 
 */
class MaterialDefinitionSentenceConfig {

	public QuantifierType getMaterialQuantifierType() {
		return QuantifierType.USER_DEFINED;
	}

	public QuantifierType getCreditsQuantifierType() {
		return QuantifierType.NUMERIC;
	}
}
