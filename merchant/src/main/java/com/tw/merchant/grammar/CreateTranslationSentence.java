/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.AppConfig;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.dao.VocabTranslationDao;
import com.tw.merchant.vocab.PrimaryVocab;

/**
 * <b>Sentence</b> representation for create translation sentence. <br>
 * e.g. glob is I. where glob represents a new <UserDefinedQuantifier> to be
 * mapped, I represents a valid <PrimaryNumeralQuantifier>
 * 
 * @author vishalshu
 * 
 */
public class CreateTranslationSentence extends Sentence {
	private UserDefinedQuantifier assignee;
	private Quantifier assigner;

	public CreateTranslationSentence() {
	}

	public UserDefinedQuantifier getAssignee() {
		return assignee;
	}

	public void setAssignee(UserDefinedQuantifier assignee) {
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
		public CommandResult execute() throws InvalidNumeralException {
			boolean assigned = false;
			AppConfig config = AppConfig.getInstance();
			PrimaryVocab primaryVocab = config.getPrimaryVocab();

			VocabTranslationDao translationDao = config.getDaoFactory()
					.getVocabTranslationDao(primaryVocab);

			assigned = translationDao.addTranslation(assignee.getSymbol(),
					assigner.getSymbol());

			CommandResult result = new CommandResult();
			result.setResult(Boolean.toString(assigned));
			return result;
		}
	}

	@Override
	public boolean isQuery() {
		return false;
	}

	@Override
	public String toString() {
		return assignee.getSymbol() + " " + KeyWord.IS + " "
				+ assigner.getSymbol();
	}

}
