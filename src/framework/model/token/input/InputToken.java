package framework.model.token.input;

import java.util.function.Consumer;

import framework.model.persistence.Bag;
import framework.model.token.Token;

public interface InputToken extends Token {
	public Consumer<Bag<?>> stateChangeFunction();
}
