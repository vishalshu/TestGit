/**
 * 
 */
package com.tw.merchant.test;

/**
 * @author vishalshu
 * 
 */
public class ExpressionFactory implements AbstractExpressionFactory {

	public ExpressionFactory() {
	}

	@Override
	public Expression createOneExpression() {
		return new OneExpression();
	}

	@Override
	public Expression createTenExpression() {
		return new TenExpression();
	}

	@Override
	public Expression createHundredExpression() {
		return new HundredExpression();
	}

	@Override
	public Expression createThousandExpression() {
		return new ThousandExpression();
	}

}
