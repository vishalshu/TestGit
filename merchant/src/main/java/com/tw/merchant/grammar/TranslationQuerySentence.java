/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;

/**
 * <b>Sentence</b> representation for translation query sentence. <br>
 * e.g. how many is glob pork? where glob, pork represents a valid
 * <UserDefinedQuantifier>.
 * 
 * @author vishalshu
 * 
 */
public class TranslationQuerySentence extends Sentence {

	private UserDefinedQuantifier sourceQuantifier;

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
		public CommandResult execute() throws InvalidNumeralException {
			CommandResult result = new CommandResult();
			String msg = Boolean.toString(false);
			msg = sourceQuantifier.getSymbol() + " is "
					+ sourceQuantifier.getValue();

			result.setResult(msg);
			return result;
		}

	}

	public UserDefinedQuantifier getSourceQuantifier() {
		return sourceQuantifier;
	}

	public void setSourceQuantifier(UserDefinedQuantifier sourceQuantifier) {
		this.sourceQuantifier = sourceQuantifier;
	}

	@Override
	public String toString() {
		return sourceQuantifier.toString().trim();
	}

}
