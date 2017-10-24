package hw3_model.tokens;

import framework.model.token.input.UserInjectableInputToken;

public class One extends Bit implements UserInjectableInputToken {
	
	public One() {
		value = true;
	}
	
	@Override
	public Character getInputSymbol() {
		return '1';
	}

}
