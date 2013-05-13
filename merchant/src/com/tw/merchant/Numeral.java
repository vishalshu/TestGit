package com.tw.merchant;

public enum Numeral {
	I(1), V(5), X(10), C(50), L(100), D(500), M(1000);
	
	private Integer number;
	
	private Numeral(Integer number) {
		this.number = number;
	}
	
	
}
