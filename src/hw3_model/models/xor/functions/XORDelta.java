package hw3_model.models.xor.functions;

import hw3_model.tokens.Bit;
import hw3_model.tokens.Zero;
import framework.model.functions.Delta;
import framework.model.persistence.Bag;
import framework.model.token.input.InputToken;

public class XORDelta extends Delta {

	public XORDelta(Bag<?> state) {
		super(state);
		STATE.add("bit", new Zero());
	}

	@Override
	protected void processPreviousState() {}

	@Override
	protected void updateStateWithInput(InputToken[] input) {
		debug("XORDelta input length: " + input.length);
		if(input.length >= 2) {
			Bit firstBit = (Bit)input[0];
			Bit secondBit = (Bit)input[1];
			STATE.addOrUpdate("bit", Bit.getForBoolean(firstBit.xor(secondBit)));
		}
	}

}
