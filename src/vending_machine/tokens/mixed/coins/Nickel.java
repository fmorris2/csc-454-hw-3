package vending_machine.tokens.mixed.coins;

import vending_machine.tokens.mixed.Coin;

public class Nickel extends Coin {

	@Override
	public Character getInputSymbol() {
		return 'n';
	}

	@Override
	public int getValue() {
		return 5;
	}

	@Override
	public String getName() {
		return "Nickel";
	}

}
