package hw3_model.models.xor;

import hw3_model.models.xor.functions.XORDelta;
import hw3_model.models.xor.functions.XORLambda;
import hw3_model.tokens.Bit;
import hw3_model.tokens.Zero;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import framework.model.AtomicModel;
import framework.model.functions.Delta;
import framework.model.functions.Lambda;
import framework.model.persistence.Bag;
import framework.model.token.input.InputParser;
import framework.model.token.input.InputToken;
import framework.model.token.output.OutputToken;

public class XORModel extends AtomicModel {
	public XORModel() {
		STATE.add("bit", new Zero());
	}
	
	@Override
	protected Bag<?> getBag() {
		return new Bag<XORModel>(this);
	}

	@Override
	protected Set<InputToken> inputSet() {
		return new HashSet<>(Arrays.asList(new Bit()));
	}

	@Override
	protected Set<OutputToken> outputSet() {
		return new HashSet<>(Arrays.asList(new Bit()));
	}

	@Override
	protected Lambda lambda() {
		return new XORLambda(STATE);
	}

	@Override
	protected Delta delta() {
		return new XORDelta(STATE);
	}

	@Override
	protected InputParser inputParser() {
		return null;
	}

	@Override
	protected String getModelName() {
		return "XOR";
	}

	@Override
	protected String getStateString() {
		Bit bit = STATE.get("bit");
		return "b: " + bit.getName();
	}

}
