/**
 * 
 */
package com.tw.merchant;

import java.util.StringTokenizer;

/**
 * @author vishalshu
 * 
 */
public class MaterialCreditExpression implements AbstractExpression {

	private String expression;

	public MaterialCreditExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public void interprete(Context context) {
		// TODO: use expression & context to identify Material & calculate
		// credit
		MaterialCreditExpressionParser parser = new MaterialCreditExpressionParser(
				expression, context);
		Material.createOrUpdateMaterial(parser.getMaterialName(),
				parser.getCredits() / parser.getNoOfUnits());
	}

	private class MaterialCreditExpressionParser {
		Integer noOfUnits = 1;
		String materialName;
		Double credits;

		public MaterialCreditExpressionParser(String expression,
				Context context) {
			StringBuilder langNumeral = new StringBuilder();
			boolean isLeftOperandProcessed = false;
			StringTokenizer tokenizer = new StringTokenizer(expression);
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				TokenType tokenType = TokenType.parse(context, token);
				switch (tokenType) {
				case LANGUAGE_NUMERAL:
					langNumeral.append(" " + token);
					break;
				case EQUAL_OPERATOR:
					isLeftOperandProcessed = true;
					noOfUnits = context.getVocab().resolveLanguageNumeral(
							langNumeral.toString());
					break;
				case NUMBER:
					credits = Double.parseDouble(token);
					break;
				case UNKNOWN:
					if (!isLeftOperandProcessed)
						materialName = token;
					break;
				}
			}
		}

		public String getMaterialName() {
			return materialName;
		}

		public Integer getNoOfUnits() {
			return noOfUnits;
		}

		public Double getCredits() {
			return credits;
		}
	}

}
