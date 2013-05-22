/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.MaterialNotFoundException;
import com.tw.merchant.AppConfig;
import com.tw.merchant.dao.MaterialDao;

/**
 * <b>Sentence</b> representation for material credit query sentence. <br>
 * e.g. How many credits is glob glob Silver? where glob represents a valid
 * <Quantifier> and Silver represents an existing <Material> name, earlier
 * defined using <MaterialDefinitionSentence>
 * 
 * @author vishalshu
 */
public class MaterialCreditsQuerySentence extends Sentence {

	private QuantifiedNoun materialNoun;

	@Override
	public Command getCommand() {
		return new MaterialCreditsQueryExecutorCommand();
	}

	@Override
	public boolean isQuery() {
		return true;
	}

	public QuantifiedNoun getMaterialNoun() {
		return materialNoun;
	}

	public void setMaterialNoun(QuantifiedNoun materialNoun) {
		this.materialNoun = materialNoun;
	}

	private class MaterialCreditsQueryExecutorCommand implements Command {

		@Override
		public CommandResult execute() throws InvalidNumeralException {
			CommandResult result = new CommandResult();

			String msg = Boolean.toString(false);
			try {
				Double quantity = materialNoun.getQuantifier().getValue();
				MaterialDao materialDao = AppConfig.getInstance()
						.getDaoFactory().getMaterialDao();
				Double credits = materialDao.getCredits(materialNoun.getNoun()
						.getSymbol(), quantity);

				msg = materialNoun.toString() + " " + KeyWord.IS + " "
						+ credits + " " + KeyWord.CREDITS;

			} catch (MaterialNotFoundException e) {
				throw new RuntimeException(
						ErrorMessage.MATERIAL_NOT_FOUND_ERROR
								.getMessage(materialNoun.getNoun().getSymbol()));
			}
			result.setResult(msg);
			return result;
		}

	}

	@Override
	public String toString() {
		return "credits of " + materialNoun.getSymbol();
	}

}
