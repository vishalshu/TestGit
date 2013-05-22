/**
 * 
 */
package com.tw.merchant.grammar.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class to get <SentenceParser> instance, which can process all valid
 * sentence types
 * 
 * @author vishalshu
 * 
 */
public class SentenceParserFactory {

	private CreateTranslationSentenceParser createTranslationSentenceParser;
	private MaterialDefinitionSentenceParser materialDefinitionSentenceParser;
	private TranslationQuerySentenceParser translationQuerySentenceParser;
	private MaterialCreditsQuerySentenceParser creditsQuerySentenceParser;

	private static SentenceParserFactory sentenceParserFactory = new SentenceParserFactory();

	private SentenceParserFactory() {
		createTranslationSentenceParser = new CreateTranslationSentenceParser();

		MaterialDefinitionSentenceConfig materialDefinitionConfig = new MaterialDefinitionSentenceConfig();
		materialDefinitionSentenceParser = new MaterialDefinitionSentenceParser();
		materialDefinitionSentenceParser.setConfig(materialDefinitionConfig);

		TranslationQuerySentenceConfig translationQueryConfig = new TranslationQuerySentenceConfig();
		translationQuerySentenceParser = new TranslationQuerySentenceParser();
		translationQuerySentenceParser.setConfig(translationQueryConfig);

		MaterialCreditsQuerySentenceConfig creditsQuerySentenceConfig = new MaterialCreditsQuerySentenceConfig();
		creditsQuerySentenceParser = new MaterialCreditsQuerySentenceParser();
		creditsQuerySentenceParser.setConfig(creditsQuerySentenceConfig);

	}

	/**
	 * @return Singleton instance of the class
	 */
	public static SentenceParserFactory getInstance() {
		return sentenceParserFactory;
	}

	public SentenceParser getSentenceParser() {
		List<SentenceParser> parsers = new ArrayList<SentenceParser>();
		parsers.add(createTranslationSentenceParser);
		parsers.add(materialDefinitionSentenceParser);
		parsers.add(creditsQuerySentenceParser);
		parsers.add(translationQuerySentenceParser);

		MainParser mainParser = new MainParser(parsers);
		return mainParser;
	}
}
