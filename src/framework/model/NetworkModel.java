package framework.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import framework.model.coupling.Coupling;
import framework.model.coupling.CouplingType;
import framework.model.functions.Delta;
import framework.model.functions.Lambda;

public abstract class NetworkModel extends Model {
	private List<Model> subModels;
	private List<Coupling> couplings;
	
	public NetworkModel addSubModel(Model subModel, Coupling... couplings) {
		subModels.add(subModel);
		Arrays.stream(couplings)
			.filter(c -> verifyCoupling(c))
			.forEach(c -> this.couplings.add(c));
		return this;
	}
	
	@Override
	protected Lambda lambda() {
		
		
		return null;
	}

	@Override
	protected Delta delta() {
		return null;
	}
	
	private boolean verifyCoupling(Coupling c) {
		return true;
	}
}
