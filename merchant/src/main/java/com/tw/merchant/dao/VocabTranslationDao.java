/**
 * 
 */
package com.tw.merchant.dao;

import com.tw.merchant.InvalidNumeralException;

/**
 * Data access interface for user defined vocab translations.
 * 
 * @author vishalshu
 * 
 */
public interface VocabTranslationDao {

	/**
	 * Adds new translation of user-defined symbol to primitive primary vocab
	 * symbol
	 * 
	 * @param newSymbol
	 *            user defined symbol to be added
	 * @param primaryNumeralSymbol
	 *            primitive symbol of primary vocab
	 * @return the status
	 * @throws InvalidNumeralException
	 *             when primaryNumeralSymbol is not a valid primitive primary
	 *             vocab symbol
	 */
	boolean addTranslation(String newSymbol, String primaryNumeralSymbol)
			throws InvalidNumeralException;

	/**
	 * Get primitive vocab symbol mapped with user-defined symbol passed as
	 * input
	 * 
	 * @param userDefinedSymbol
	 * @return the primitiveSymbol mapped
	 * @throws InvalidNumeralException
	 *             when userDefinedSymbol is not mapped
	 */
	String getPrimitiveSymbolTranslation(String userDefinedSymbol)
			throws InvalidNumeralException;

	/**
	 * Translate userDefinedSymbol to primary vocab symbol
	 * 
	 * @param userDefinedSymbol
	 * @return the primaryVocabSymbol
	 * @throws InvalidNumeralException
	 *             when userDefinedSymbol contains invalid user-defined symbols
	 */
	String translate(String symbol) throws InvalidNumeralException;
}
