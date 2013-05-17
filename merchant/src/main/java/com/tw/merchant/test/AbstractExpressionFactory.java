/**
 * 
 */
package com.tw.merchant.test;

/**
 * @author vishalshu
 * 
 */
public interface AbstractExpressionFactory {
	Expression createOneExpression();

	Expression createTenExpression();

	Expression createHundredExpression();

	Expression createThousandExpression();

}
