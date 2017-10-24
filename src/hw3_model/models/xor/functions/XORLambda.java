package hw3_model.models.xor.functions;

import framework.model.functions.Lambda;
import framework.model.persistence.ReadableBag;
import framework.model.token.output.OutputToken;

public class XORLambda extends Lambda {

	public XORLambda(ReadableBag<?> state) {
		super(state);
	}

	@Override
	public OutputToken[] execute() {
		return new OutputToken[]{STATE.get("bit")};
	}

}
