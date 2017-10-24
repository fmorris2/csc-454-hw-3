package framework.model.functions;

import java.util.List;

import framework.model.Model;
import framework.model.coupling.Coupling;
import framework.model.persistence.Bag;
import framework.model.token.input.InputToken;

public abstract class NetworkDelta extends Delta {
	
	private final List<Model> SUB_MODELS;
	private final List<Coupling> COUPLINGS;

	public NetworkDelta(Bag<?> state, List<Model> subModels, List<Coupling> couplings) {
		super(state);
		SUB_MODELS = subModels;
		COUPLINGS = couplings;
	}

	@Override
	protected void updateStateWithInput(InputToken[] input) {
		//execute deltas for all sub models
		for(Model m : SUB_MODELS) {
			
			COUPLINGS.stream() //find couplings that are network input -> model input
				.filter(coupling -> coupling.FIRST == null && coupling.LAST == m)
				.forEach(coupling -> coupling.LAST.addToInputBag(input)); //add network input to model's input bag
			
			m.executeDelta();
		}
	}

}
