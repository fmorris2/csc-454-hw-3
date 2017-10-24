package framework.model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import framework.model.coupling.Coupling;
import framework.model.functions.Delta;
import framework.model.functions.NetworkLambda;
import framework.model.token.input.InputToken;

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
	protected abstract NetworkLambda lambda();

	@Override
	protected abstract Delta delta();
	
	private boolean verifyCoupling(Coupling c) {
		switch(c.TYPE) {
		case INPUT_TO_INPUT:
		case OUTPUT_TO_INPUT:
			return c.FIRST.INPUT_SET.stream().allMatch(i -> containsType(i, c.LAST.INPUT_SET));
		default:
			return true;
		}
	}
	
	private boolean containsType(InputToken type, Set<InputToken> set)
	{
		for(InputToken t : set) {
			if(type.getClass().equals(t.getClass()))
				return true;
		}
		
		return false;
	}
}
