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

	private String userDefinedNumeral;

	public TranslationQuerySentence(String userDefinedNumeral) {
		this.userDefinedNumeral = userDefinedNumeral.trim();
	}

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
		public CommandResult execute() throws InvalidSyntaxException {
			CommandResult result = new CommandResult();
			String msg = Boolean.toString(false);
			UserDefinedQuantifier quantifier = new UserDefinedQuantifier(
					userDefinedNumeral);

			try {
				msg = userDefinedNumeral + " is " + quantifier.getValue().intValue();
			} catch (InvalidNumeralException e) {
				throw new InvalidSyntaxException(e);
			}

			result.setResult(msg);
			return result;
		}

	}

	@Override
	public String toString() {
		return userDefinedNumeral.toString().trim();
	}
}
