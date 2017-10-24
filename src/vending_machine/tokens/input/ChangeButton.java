package vending_machine.tokens.input;

import java.util.function.Consumer;

import framework.model.persistence.Bag;
import framework.model.token.input.UserInjectableInputToken;

public class ChangeButton implements UserInjectableInputToken {

	@Override
	public Character getInputSymbol() {
		return 'c';
	}

	@Override
	public Consumer<Bag<?>> stateChangeFunction() {
		return (b) -> b.addOrUpdate("isChangePressed", true);
	}

}
