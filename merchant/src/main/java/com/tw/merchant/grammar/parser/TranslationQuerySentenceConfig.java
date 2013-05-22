/**
 * 
 */
package com.tw.merchant.grammar.parser;

import com.tw.merchant.grammar.UserDefinedQuantifier;

/**
 * Config object for <MaterialCreditsQuerySentenceParser>
 * @author vishalshu
 * 
 */
class TranslationQuerySentenceConfig {
	public UserDefinedQuantifier getSourceQuantifier() {
		UserDefinedQuantifier quantifier = new UserDefinedQuantifier();
		return quantifier;
	}

}
