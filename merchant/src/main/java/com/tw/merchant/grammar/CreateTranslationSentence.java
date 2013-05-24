/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.vocab.UserDefinedVocab;

/**
 * <b>Sentence</b> representation for create translation sentence. <br>
 * e.g. glob is I. where glob represents a new <UserDefinedQuantifier> to be
 * mapped, I represents a valid <PrimaryNumeralQuantifier>
 * 
 * @author vishalshu
 * 
 */
public class CreateTranslationSentence extends Sentence {
	private String assignee;
	private String assigner;

	public CreateTranslationSentence(String assignee, String assigner) {
		this.assignee = assignee.trim();
		this.assigner = assigner.trim();
	}

	@Override
	public Command getCommand() {
		return new CreateTranslationCommand();
	}

	private class CreateTranslationCommand implements Command {
		public CommandResult execute() throws InvalidSyntaxException {
			
			UserDefinedVocab userDefinedVocab = UserDefinedVocab.getInstance();
			
			try {
				userDefinedVocab.addTranslation(assignee,
							assigner);
			} catch (InvalidNumeralException e) {
				throw new InvalidSyntaxException(e);
			}
			
			CommandResult result = new CommandResult();
			result.setResult(Boolean.toString(true));
			return result;
		}
	}

	@Override
	public boolean isQuery() {
		return false;
	}

	@Override
	public String toString() {
		return assignee + " " + KeyWord.IS + " " + assigner;
	}

}
