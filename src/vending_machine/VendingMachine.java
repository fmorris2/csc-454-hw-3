package vending_machine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import vending_machine.functions.VMDelta;
import vending_machine.functions.VMLambda;
import vending_machine.tokens.input.ChangeButton;
import vending_machine.tokens.mixed.coins.Dime;
import vending_machine.tokens.mixed.coins.Nickel;
import vending_machine.tokens.mixed.coins.Quarter;
import vending_machine.tokens.output.Coffee;
import framework.model.AtomicModel;
import framework.model.functions.Delta;
import framework.model.functions.Lambda;
import framework.model.persistence.Bag;
import framework.model.token.input.CharacterInputParser;
import framework.model.token.input.InputParser;
import framework.model.token.input.InputToken;
import framework.model.token.output.OutputToken;

public class VendingMachine extends AtomicModel {
	public static final int COFFEE_PRICE = 100;
	
	public VendingMachine() {
		STATE.add("isChangePressed", false);
		STATE.add("value", 0);
		STATE.add("Nickel", 0);
		STATE.add("Dime", 0);
		STATE.add("Quarter", 0);
	}
	
	@Override
	protected Set<InputToken> inputSet() {
		return new HashSet<>(Arrays.asList(
			new ChangeButton(), new Nickel(),
			new Dime(), new Quarter()
		));
	}

	@Override
	protected Set<OutputToken> outputSet() {
		return new HashSet<>(Arrays.asList(
			new Nickel(), new Dime(),
			new Quarter(), new Coffee()
		));
	}

	@Override
	protected Lambda lambda() {
		return new VMLambda(STATE);
	}

	@Override
	protected Delta delta() {
		return new VMDelta(STATE);
	}

	@Override
	protected InputParser inputParser() {
		return new CharacterInputParser(INPUT_SET);
	}

	@Override
	protected String getModelName() {
		return "Vending Machine";
	}

	@Override
	protected String getStateString() {
		return "v: " + STATE.get("value") + ", c: " + STATE.get("isChangePressed")
				+ ", q: " + STATE.get("Quarter") + ", d: " + STATE.get("Dime")
				+ ", n: " + STATE.get("Nickel");
	}

	@Override
	protected Bag<?> getBag() {
		return new Bag<VendingMachine>(this);
	}

}
