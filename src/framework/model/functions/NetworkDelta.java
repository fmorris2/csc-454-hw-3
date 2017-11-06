package framework.model.functions;

import java.util.List;

import framework.model.Model;
import framework.model.coupling.Coupling;
import framework.model.persistence.Bag;
import framework.model.token.input.InputToken;

public class NetworkDelta extends Delta {
	private final List<Model> SUB_MODELS;
	private final List<Coupling> COUPLINGS;
	public NetworkDelta(Bag<?> state, List<Model> models, List<Coupling> couplings) {
		super(state);
		SUB_MODELS = models;
		COUPLINGS = couplings;
	}

	@Override
	protected void processPreviousState() {}

	@Override
	protected void updateStateWithInput(InputToken[] input) {
		//route network input to necessary sub models
		COUPLINGS.stream().filter(c -> c.FIRST == null).forEach(c -> c.LAST.addToInputBag(input));
		
		//call everybodies delta
		SUB_MODELS.stream().forEach(m -> {
			m.executeDelta();
			m.resetInputAndOutput();
		});
	}

}
