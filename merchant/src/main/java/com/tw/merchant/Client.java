package com.tw.merchant;

import java.util.ArrayList;
import java.util.List;

import com.tw.merchant.grammar.CommandResult;
import com.tw.merchant.grammar.CreateTranslationSentenceConfig;
import com.tw.merchant.grammar.CreateTranslationSentenceParser;
import com.tw.merchant.grammar.MaterialCreditsQuerySentenceConfig;
import com.tw.merchant.grammar.MaterialCreditsQuerySentenceParser;
import com.tw.merchant.grammar.MaterialDefinitionSentenceConfig;
import com.tw.merchant.grammar.MaterialDefinitionSentenceParser;
import com.tw.merchant.grammar.Sentence;
import com.tw.merchant.grammar.SentenceParser;
import com.tw.merchant.grammar.TranslationQueryConfig;
import com.tw.merchant.grammar.TranslationQuerySentenceParser;

public class Client {

	public static void main(String[] args) {
		InputReader reader = new ConsoleInputReader();
		System.out
				.println("Enter credits for materials. e.g. glob GOLD is 100");

		CreateTranslationSentenceConfig assignmentConfig = new CreateTranslationSentenceConfig();
		MaterialDefinitionSentenceConfig materialDefinitionConfig = new MaterialDefinitionSentenceConfig();
		TranslationQueryConfig translationQueryConfig = new TranslationQueryConfig();
		MaterialCreditsQuerySentenceConfig creditsQuerySentenceConfig = new MaterialCreditsQuerySentenceConfig();
		do {
			String sentence = reader.next();

			List<SentenceParser> parsers = new ArrayList<SentenceParser>();

			CreateTranslationSentenceParser createTranslationSentenceParser = new CreateTranslationSentenceParser(
					sentence);
			createTranslationSentenceParser.setConfig(assignmentConfig);
			
			MaterialDefinitionSentenceParser materialDefinitoinSentenceParser = new MaterialDefinitionSentenceParser(sentence);
			materialDefinitoinSentenceParser.setConfig(materialDefinitionConfig);
			
			TranslationQuerySentenceParser translationQuerySentenceParser = new TranslationQuerySentenceParser(sentence);
			translationQuerySentenceParser.setConfig(translationQueryConfig);
			
			MaterialCreditsQuerySentenceParser creditsQuerySentenceParser = new MaterialCreditsQuerySentenceParser(sentence);
			creditsQuerySentenceParser.setConfig(creditsQuerySentenceConfig);
			
			parsers.add(createTranslationSentenceParser);
			parsers.add(materialDefinitoinSentenceParser);
			parsers.add(creditsQuerySentenceParser);
			parsers.add(translationQuerySentenceParser);
			
			boolean sentenceInterpreted = false;

			for (SentenceParser parser : parsers) {
				try {
					Sentence sent = parser.parse();
					CommandResult result = sent.getCommand().execute();
					sentenceInterpreted = true;
					
					// if(sent.isQuery()){
					System.out.println(result.getResult());
					break;
					// }
				} catch (InvalidSyntaxException e) {
					// Fallback to next parser in the list
				}
			}
			if (!sentenceInterpreted) {
				System.out.println("I've no idea what you're talking about.");
			}
		} while (reader.hasNext());

	}
}
