/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;

/**
 * @author vishalshu
 * 
 */
public class TranslationQuerySentence extends Sentence {

	private TranslatedRomanQuantifier sourceQuantifier;

	@Override
	public Command getCommand() {
		return new TranslationQueryExecutorCommand();
	}

	@Override
	public boolean isQuery() {
		return true;
	}

	private class TranslationQueryExecutorCommand implements Command {

		@Override
		public CommandResult execute() {
			CommandResult result = new CommandResult();
			String msg = Boolean.toString(false);
			try {
				msg = sourceQuantifier.getSymbol() + "is "
						+ sourceQuantifier.getValue();
			} catch (InvalidNumeralException e) {
				e.printStackTrace();
				// TODO: Something went wrong.. this should've been identified
				// by parser
			}
			result.setResult(msg);
			return result;
		}

	}

	public TranslatedRomanQuantifier getSourceQuantifier() {
		return sourceQuantifier;
	}

	public void setSourceQuantifier(TranslatedRomanQuantifier sourceQuantifier) {
		this.sourceQuantifier = sourceQuantifier;
	}

}
