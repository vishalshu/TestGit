/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.AppConfig;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.Material;
import com.tw.merchant.dao.MaterialDao;

/**
 * <b>Sentence</b> representation for material definition sentence. <br>
 * e.g. glob glob Silver is 300 credits. where glob represents a valid
 * <Quantifier> and Silver represents a new <Material> to be defined.
 * 
 * @author vishalshu
 * 
 */
public class MaterialDefinitionSentence extends Sentence {

	private QuantifiedNoun materialNoun;
	private QuantifiedNoun creditsNoun;

	@Override
	public Command getCommand() {
		return new CreateMaterialDefinitionCommand();
	}

	@Override
	public boolean isQuery() {
		return false;
	}

	public QuantifiedNoun getMaterialNoun() {
		return materialNoun;
	}

	public void setMaterialNoun(QuantifiedNoun materialNoun) {
		this.materialNoun = materialNoun;
	}

	public QuantifiedNoun getCreditsNoun() {
		return creditsNoun;
	}

	public void setCreditsNoun(QuantifiedNoun creditsNoun) {
		this.creditsNoun = creditsNoun;
	}

	private class CreateMaterialDefinitionCommand implements Command {

		@Override
		public CommandResult execute() throws InvalidNumeralException {
			CommandResult result = new CommandResult();
			String msg = Boolean.toString(false);
			MaterialDao dao = AppConfig.getInstance().getDaoFactory()
					.getMaterialDao();
			Material material = dao.createOrUpdateMaterial(materialNoun
					.getNoun().getSymbol(), materialNoun.getQuantifier()
					.getValue(), creditsNoun.getQuantifier().getValue());
			msg = material.getName() + " is " + material.getCreditsForUnit()
					+ " Credits.";

			result.setResult(msg);
			return result;
		}
	}

	@Override
	public String toString() {
		return materialNoun.getSymbol() + " is " + creditsNoun.getSymbol();
	}

}
