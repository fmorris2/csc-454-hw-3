package hw3_model.models.memory_model;

import hw3_model.models.memory_model.functions.MemDelta;
import hw3_model.models.memory_model.functions.MemLambda;
import hw3_model.tokens.Bit;
import hw3_model.tokens.Zero;

import java.util.ArrayDeque;
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

public class MemoryModel extends AtomicModel {
	
	public MemoryModel() {
		STATE.add("queue", new ArrayDeque<>(Arrays.asList(new Zero(), new Zero())));
	}

	@Override
	protected Bag<?> getBag() {
		return new Bag<MemoryModel>(this);
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
		return new MemLambda(STATE);
	}

	@Override
	protected Delta delta() {
		return new MemDelta(STATE);
	}

	@Override
	protected InputParser inputParser() {
		return null;
	}

	@Override
	protected String getModelName() {
		return "M";
	}

	@Override
	protected String getStateString() {
		ArrayDeque<Bit> queue = STATE.get("queue");
		return "("+queue.getFirst().getName() + ", " + queue.getLast().getName() +")";
	}

}
