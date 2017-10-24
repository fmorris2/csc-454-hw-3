package vending_machine.functions;

import vending_machine.VendingMachine;
import vending_machine.tokens.output.Coffee;
import vending_machine.utils.ChangeUtils;
import framework.model.functions.Lambda;
import framework.model.persistence.ReadableBag;
import framework.model.token.output.Nothing;
import framework.model.token.output.OutputToken;

public class VMLambda extends Lambda {

	public VMLambda(ReadableBag<?> state) {
		super(state);
	}

	@Override
	public OutputToken[] execute() {
		final int VALUE = STATE.getInt("value");
		if(STATE.getBool("isChangePressed") && VALUE > 0) {
			return ChangeUtils.calculateChange(STATE);
		}
		
		if(VALUE >= VendingMachine.COFFEE_PRICE) {
			return dispenseCoffee();
		}
		
		return new OutputToken[]{new Nothing()};
	}
	
	private OutputToken[] dispenseCoffee() {
		int numCoffee = (int)(STATE.getInt("value") / VendingMachine.COFFEE_PRICE);
		OutputToken[] coffee = new OutputToken[numCoffee];
		for(int i = 0; i < numCoffee; i++) {
			coffee[i] = new Coffee();
		}
		
		return coffee;
	}

}
