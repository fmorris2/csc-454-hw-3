package hw3_model.tokens;

import framework.model.token.input.UserInjectableInputToken;

public class Zero extends Bit implements UserInjectableInputToken {
	
	public Zero() {
		value = false;
	}
	
	@Override
	public Character getInputSymbol() {
		return '0';
	}

}
