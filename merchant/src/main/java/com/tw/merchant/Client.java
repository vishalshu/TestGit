package com.tw.merchant;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tw.merchant.grammar.CommandResult;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.Sentence;
import com.tw.merchant.grammar.SentenceParser;

public class Client {

	private static Logger logger = LogManager.getRootLogger();

	public static void main(String[] args) {
		InputReader reader = new ConsoleInputReader();

		do {
			logger.info("merchant-guide>");
			String input = reader.next();

			SentenceParser parser = new SentenceParser();
			try {
				Sentence sentence = parser.parse(input);
				CommandResult result = sentence.getCommand().execute();
				String resultStr = result.getResult();

				if (sentence.isQuery()) {
					logger.info(resultStr);
				}

				logger.debug("Result of '" + input + "' sentence is : "
						+ resultStr);
			} catch (InvalidSyntaxException e) {
				logger.error(e.getMessage());
			}
		} while (reader.hasNext());

	}
}
