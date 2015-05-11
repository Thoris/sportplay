package com.tap.sportplay.domain;

public enum Position {
	GOALKEEPER(1), DEFENCE(2), MIDDLE(3), FORWARD(4);
	private final int value;

	private Position(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
