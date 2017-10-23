package framework.model.functions;

import framework.model.input.InputToken;
import framework.model.persistence.Bag;

public abstract class Delta {
	protected final Bag STATE;
	
	public Delta(Bag state) {
		STATE = state;
	}
	
	protected abstract void processPreviousState();
	protected abstract void updateStateWithInput(InputToken[] input);
	
	public void execute(InputToken[] input) {
		processPreviousState();
		updateStateWithInput(input);
	}
}
