package vending_machine.tokens.mixed.coins;

import vending_machine.tokens.mixed.Coin;

public class Quarter extends Coin {

	@Override
	public Character getInputSymbol() {
		return 'q';
	}

	@Override
	public int getValue() {
		return 25;
	}

	@Override
	public String getName() {
		return "Quarter";
	}

}
