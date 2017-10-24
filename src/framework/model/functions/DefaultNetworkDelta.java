package framework.model.functions;

import java.util.List;

import framework.model.Model;
import framework.model.coupling.Coupling;
import framework.model.persistence.Bag;

public class DefaultNetworkDelta extends NetworkDelta {

	public DefaultNetworkDelta(Bag<?> state, List<Model> subModels,
			List<Coupling> couplings) {
		super(state, subModels, couplings);
	}

	@Override
	protected void processPreviousState() {}

}
