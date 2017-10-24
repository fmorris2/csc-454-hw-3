package framework.model.functions;

import java.util.List;

import framework.model.Model;
import framework.model.coupling.Coupling;
import framework.model.persistence.ReadableBag;
import framework.model.token.output.OutputToken;

public class DefaultNetworkLambda extends NetworkLambda {

	public DefaultNetworkLambda(ReadableBag<?> state, List<Model> subModels,
			List<Coupling> couplings) {
		super(state, subModels, couplings);
	}

	@Override
	protected OutputToken[] networkSpecificLambda() {
		return null;
	}

}
