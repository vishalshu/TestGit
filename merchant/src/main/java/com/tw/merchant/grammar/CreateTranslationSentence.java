/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;

/**
 * @author vishalshu
 * 
 */
public class CreateTranslationSentence extends Sentence {

	private TranslatedRomanQuantifier assignee;
	private Quantifier assigner;

	public CreateTranslationSentence() {
	}

	public TranslatedRomanQuantifier getAssignee() {
		return assignee;
	}

	public void setAssignee(TranslatedRomanQuantifier assignee) {
		this.assignee = assignee;
	}

	public Quantifier getAssigner() {
		return assigner;
	}

	public void setAssigner(Quantifier assigner) {
		this.assigner = assigner;
	}

	@Override
	public Command getCommand() {
		return new CreateTranslationCommand();
	}

	private class CreateTranslationCommand implements Command {
		public CommandResult execute() {
			boolean assigned = false;
			try {
				Integer value = getAssigner().getValue();
				boolean isValidAssignment = false;
				switch (value) {
				case 1:
				case 5:
				case 10:
				case 50:
				case 100:
				case 500:
				case 1000:
					isValidAssignment = true;
					assignee.getTranslation().addTranslation(
							assignee.getSymbol(), assigner.getSymbol());
					break;
				}

				if (!isValidAssignment) {
					throw new RuntimeException(
							"Only basic roman numerals can be used for assignment.");
				}

			} catch (InvalidNumeralException e) {
				// TODO This shouldn't come.... Parser should know about invalid
				// syntax
			}
			CommandResult result = new CommandResult();
			result.setResult(Boolean.toString(assigned));
			return result;
		}
	}

	@Override
	public boolean isQuery() {
		return false;
	}

}
