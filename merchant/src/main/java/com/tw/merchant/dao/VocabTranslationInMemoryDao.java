/**
 * 
 */
package com.tw.merchant.dao;

import java.util.StringTokenizer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.vocab.PrimaryVocab;
import com.tw.merchant.vocab.UserDefinedVocab;

/**
 * InMemory implementation of <VocabTranslationDao>
 * 
 * @author vishalshu
 * 
 */
class VocabTranslationInMemoryDao implements VocabTranslationDao {
	private Logger logger = LogManager.getRootLogger();
	private UserDefinedVocab vocab = new UserDefinedVocab();
	private PrimaryVocab primaryVocab;
	private static VocabTranslationInMemoryDao instance;

	private VocabTranslationInMemoryDao(PrimaryVocab primaryVocab) {
		this.primaryVocab = primaryVocab;
	}

	@Override
	public boolean addTranslation(String newSymbol, String primarySymbol)
			throws InvalidNumeralException {

		if (!primaryVocab.isPrimitiveSymbol(primarySymbol)) {
			throw new InvalidNumeralException(
					ErrorMessage.INVALID_PRIMITIVE_NUMERAL.getMessage(
							primarySymbol, primaryVocab.getPrimitiveSymbols()
									.toString()));
		}
		vocab.addTranslation(newSymbol.toLowerCase(),
				primarySymbol.toLowerCase());

		logger.debug("Added translation : " + newSymbol.toLowerCase() + "="
				+ vocab.getTranslation(newSymbol.toLowerCase()));

		return true;
	}

	@Override
	public String getPrimitiveSymbolTranslation(String symbol)
			throws InvalidNumeralException {
		symbol = symbol.toLowerCase();
		String translatedSymbol = vocab.getTranslation(symbol);
		if (translatedSymbol == null) {
			throw new InvalidNumeralException(
					ErrorMessage.SYMBOL_NOT_MAPPED.getMessage(symbol));
		}

		logger.debug("Found translation : " + symbol.toLowerCase() + "="
				+ translatedSymbol);

		return translatedSymbol;
	}

	@Override
	public String translate(String symbol) throws InvalidNumeralException {
		symbol = symbol.toLowerCase();
		StringTokenizer tokenizer = new StringTokenizer(symbol);
		StringBuilder translatedSymbolBuilder = new StringBuilder();
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			translatedSymbolBuilder
					.append(getPrimitiveSymbolTranslation(token));
		}

		logger.debug("Translation : " + symbol.toLowerCase() + "="
				+ translatedSymbolBuilder.toString());

		return translatedSymbolBuilder.toString();
	}

	/**
	 * @return Singleton instance of the class
	 */
	public static VocabTranslationInMemoryDao getInstance(
			PrimaryVocab primaryVocab) {
		if (instance == null) {
			instance = new VocabTranslationInMemoryDao(primaryVocab);
		}
		return instance;
	}

}
