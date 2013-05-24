/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.Material;
import com.tw.merchant.StringUtils;
import com.tw.merchant.vocab.UserDefinedVocab;

/**
 * <b>Sentence</b> representation for material definition sentence. <br>
 * e.g. glob glob Silver is 300 credits. where glob represents a valid
 * <Quantifier> and Silver represents a new <Material> to be defined.
 * 
 * @author vishalshu
 * 
 */
public class MaterialDefinitionSentence extends Sentence {

	private String materialNounStr;
	private String creditsQuantifierStr;

	public MaterialDefinitionSentence(String materialsNoun,
			String creditsQuantifier) {
		this.materialNounStr = materialsNoun.trim();
		this.creditsQuantifierStr = creditsQuantifier.trim();
	}

	@Override
	public Command getCommand() {
		return new CreateMaterialDefinitionCommand();
	}

	@Override
	public boolean isQuery() {
		return false;
	}

	private class CreateMaterialDefinitionCommand implements Command {

		@Override
		public CommandResult execute() throws InvalidSyntaxException {
			CommandResult result = new CommandResult();

			String materialName = StringUtils.extractLastWord(materialNounStr);

			boolean isValid = validateMaterialName(materialName);
			if (!isValid) {
				throw new InvalidSyntaxException(
						ErrorMessage.MATERIAL_NOT_FOUND_ERROR
								.getMessage(materialName));
			}

			String materialQuantifierStr = materialNounStr.replace(
					materialName, "").trim();
			Quantifier creditsQuantifier = new Quantifier(creditsQuantifierStr);

			UserDefinedQuantifier materialQuantifier = new UserDefinedQuantifier(
					materialQuantifierStr);

			try {
				Material.createOrUpdateMaterial(materialName, creditsQuantifier
						.getValue(), materialQuantifier.getValue().intValue());
			} catch (InvalidNumeralException e) {
				throw new InvalidSyntaxException(e);
			}

			result.setResult(Boolean.toString(true));
			return result;
		}

		private boolean validateMaterialName(String materialName) {
			boolean isValid = true;
			if (materialName.isEmpty()
					|| UserDefinedVocab.getInstance()
							.isValidUserDefinedVocabSymbol(materialName)) {
				isValid = false;
			}
			return isValid;
		}
	}

	@Override
	public String toString() {
		return materialNounStr + " is " + creditsQuantifierStr+" "+KeyWord.CREDITS.toString();
	}

}
