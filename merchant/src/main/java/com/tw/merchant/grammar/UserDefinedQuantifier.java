/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.AppConfig;
import com.tw.merchant.dao.VocabTranslationDao;
import com.tw.merchant.vocab.PrimaryVocab;

/**
 * Quantifier which follows Roman numerals convention. Symbols are
 * defined by User and mapped with valid <PrimaryVocab> symbols.
 * 
 * @author vishalshu
 * 
 */
public class UserDefinedQuantifier extends Quantifier {

	@Override
	public Double getValue() throws InvalidNumeralException {
		Double value = 0d;
		AppConfig config = AppConfig.getInstance();
		PrimaryVocab vocab = config.getPrimaryVocab();
		PrimaryNumeralQuantifier romanQuantifier = new PrimaryNumeralQuantifier();
		VocabTranslationDao translationDao = config.getDaoFactory()
				.getVocabTranslationDao(vocab);
		String translatedSymbol = translationDao.translate(getSymbol());
		romanQuantifier.setSymbol(translatedSymbol);
		try {
			value = romanQuantifier.getValue();
		} catch (InvalidNumeralException e) {
			throw new InvalidNumeralException(
					ErrorMessage.INVALID_NUMERAL_SYMBOL.getMessage(getSymbol()));
		}
		return value;
	}

}
