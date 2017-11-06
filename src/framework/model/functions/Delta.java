package framework.model.functions;

import framework.model.persistence.Bag;
import framework.model.token.input.InputToken;

public abstract class Delta {
	protected final Bag<?> STATE;
	
	public Delta(Bag<?> state) {
		STATE = state;
	}
	
	protected abstract void processPreviousState();
	protected abstract void updateStateWithInput(InputToken[] input);
	
	public void execute(InputToken[] input) {
		processPreviousState();
		updateStateWithInput(input);
	}
	
	public void debug(String str) {
		if(STATE.getBool("debugMode"))
			System.out.println("[Debug]["+getClass().getSimpleName()+"]: " + str);
	}
}
