package vending_machine.functions;

import java.util.Arrays;

import vending_machine.VendingMachine;
import vending_machine.utils.ChangeUtils;
import framework.model.functions.Delta;
import framework.model.persistence.Bag;
import framework.model.token.input.InputToken;

public class VMDelta extends Delta {

	public VMDelta(Bag<?> state) {
		super(state);
	}

	@Override
	protected void processPreviousState() {
		if(STATE.getBool("isChangePressed")) {
			Arrays.stream(ChangeUtils.calculateChange(STATE))
				.forEach(coin -> {
					STATE.addOrUpdate(coin.getName(), STATE.getInt(coin.getName()) - 1);
					STATE.addOrUpdate("value", STATE.getInt("value") - coin.getValue());
				});
			STATE.addOrUpdate("isChangePressed", false);
		}
		if(STATE.getInt("value") >= VendingMachine.COFFEE_PRICE) {
			STATE.addOrUpdate("value", STATE.getInt("value") % VendingMachine.COFFEE_PRICE);
		}
	}

	@Override
	protected void updateStateWithInput(InputToken[] input) {
		Arrays.stream(input).forEach(i -> i.stateChangeFunction().accept(STATE));
	}

}
