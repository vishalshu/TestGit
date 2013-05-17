package com.tw.merchant.test;

public class HundredExpression implements Expression {

	public String one() {
		return "C";
	}

	public String four() {
		return "CD";
	}

	public String five() {
		return "D";
	}

	public String nine() {
		return "CM";
	}

	public int multiplier() {
		return 100;
	}
}
