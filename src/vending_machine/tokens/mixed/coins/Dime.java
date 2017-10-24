package vending_machine.tokens.mixed.coins;

import vending_machine.tokens.mixed.Coin;

public class Dime extends Coin {

	@Override
	public Character getInputSymbol() {
		return 'd';
	}

	@Override
	public int getValue() {
		return 10;
	}

	@Override
	public String getName() {
		return "Dime";
	}
}
