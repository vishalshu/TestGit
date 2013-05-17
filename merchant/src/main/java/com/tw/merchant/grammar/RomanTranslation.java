/**
 * 
 */
package com.tw.merchant.grammar;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vishalshu
 * 
 */
public class RomanTranslation {

	private String name;
	private Map<String, String> translationsMap = new HashMap<String, String>();
	private volatile static Map<String, RomanTranslation> translations = new HashMap<String, RomanTranslation>();
	private static final String INTERGALACTIC = "intergalactic";

	public RomanTranslation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addTranslation(String symbol, String romanSymbol) {
		translationsMap.put(symbol, romanSymbol);
	}

	public String getTranslation(String symbol) {
		return translationsMap.get(symbol);
	}

	public static RomanTranslation getIntergalacticTranslation() {
		RomanTranslation translation = translations.get(INTERGALACTIC);
		if (translation == null) {
			synchronized (RomanTranslation.class) {
				if (translation == null) {
					translation = new RomanTranslation(INTERGALACTIC);
					translations.put(INTERGALACTIC, translation);
				}
			}

		}
		return translation;
	}
}
