/**
 * 
 */
package com.tw.merchant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vishalshu
 * 
 */
public class IntergalacticNumeralsVocab implements NumeralsVocab {

	private Map<Integer, String> intergalacticNumeralsRepresentation = new HashMap<Integer, String>();
	private Map<String, Integer> inverseIntergalacticNumeralsRepresentation = new HashMap<String, Integer>();

	public IntergalacticNumeralsVocab() {
		intergalacticNumeralsRepresentation.put(5, "glob");
		inverseIntergalacticNumeralsRepresentation.put("glob", 5);
		
/*		intergalacticNumeralsRepresentation.put(5, "prok");
		inverseIntergalacticNumeralsRepresentation.put("glob", 1);
		intergalacticNumeralsRepresentation.put(10, "pish");
		inverseIntergalacticNumeralsRepresentation.put("glob", 1);
		intergalacticNumeralsRepresentation.put(50, "tegj");
		inverseIntergalacticNumeralsRepresentation.put("glob", 1);
		intergalacticNumeralsRepresentation.put(100, "cigv");
		inverseIntergalacticNumeralsRepresentation.put("glob", 1);
		intergalacticNumeralsRepresentation.put(500, "dolg");
		inverseIntergalacticNumeralsRepresentation.put("glob", 1);
		intergalacticNumeralsRepresentation.put(1000, "mong");
		inverseIntergalacticNumeralsRepresentation.put("glob", 1);*/
	}

	@Override
	public String getLanguageNumeral(Integer number) {
		return intergalacticNumeralsRepresentation.get(number);
	}

	@Override
	public Integer resolveLanguageNumeral(String languageNumeral) {
		return inverseIntergalacticNumeralsRepresentation.get(languageNumeral.trim());
	}

	@Override
	public void addToVocab(Integer number, String languageNumeral) {
		intergalacticNumeralsRepresentation.put(number, languageNumeral);
	}

}
