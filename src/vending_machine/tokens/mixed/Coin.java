package vending_machine.tokens.mixed;

import java.util.function.Consumer;

import framework.model.persistence.Bag;
import framework.model.token.input.UserInjectableInputToken;
import framework.model.token.output.OutputToken;

public abstract class Coin implements UserInjectableInputToken, OutputToken {
	public abstract int getValue();
	
	@Override
	public Consumer<Bag<?>> stateChangeFunction() {
		return (b) -> {
			b.addOrUpdate(getName(), b.getInt(getName()) + 1);
			b.addOrUpdate("value", b.getInt("value") + getValue());
		};
	}
}
