package hw3_model.tokens;

import java.util.function.Consumer;

import framework.model.persistence.Bag;
import framework.model.token.input.InputToken;
import framework.model.token.output.OutputToken;

public class Bit implements InputToken, OutputToken {
	protected boolean value;
	
	@Override
	public Consumer<Bag<?>> stateChangeFunction() {
		return null;
	}

	@Override
	public String getName() {
		return "Bit: " + (value ? 1 : 0);
	}
	
	public void flip() {
		value = !value;
	}
	
	public boolean xor(Bit other) {
		return Boolean.logicalXor(value, other.value);
	}
	
	public static Bit getForBoolean(boolean b) {
		return b ? new One() : new Zero();
	}
}
