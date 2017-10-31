package hw3_model.models.memory_model.functions;

import hw3_model.tokens.Bit;

import java.util.Queue;

import framework.model.functions.Lambda;
import framework.model.persistence.ReadableBag;
import framework.model.token.output.OutputToken;

public class MemLambda extends Lambda {

	public MemLambda(ReadableBag<?> state) {
		super(state);
	}

	@Override
	public OutputToken[] execute() {
		Queue<Bit> queue = STATE.get("queue");
		return new OutputToken[]{queue.peek()};
	}

}
