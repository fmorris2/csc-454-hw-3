package hw3_model.models.memory_model.functions;

import hw3_model.tokens.Bit;

import java.util.Queue;

import framework.model.functions.Delta;
import framework.model.persistence.Bag;
import framework.model.token.input.InputToken;

public class MemDelta extends Delta {

	public MemDelta(Bag<?> state) {
		super(state);
	}

	@Override
	protected void processPreviousState() {
	}

	@Override
	protected void updateStateWithInput(InputToken[] input) {
		Queue<Bit> queue = STATE.get("queue");
		System.out.println("MemDelta input length: " + input.length + ", queue size: " + queue.size());
		queue.poll();
		if(input.length > 0)
			queue.add((Bit)input[0]);
	}

}
