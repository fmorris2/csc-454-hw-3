package framework.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import framework.model.coupling.Coupling;
import framework.model.functions.Delta;
import framework.model.functions.Lambda;
import framework.model.functions.NetworkDelta;
import framework.model.functions.NetworkLambda;
import framework.model.token.input.InputToken;

public abstract class NetworkModel extends Model {
	private List<Model> subModels = new ArrayList<>();
	private List<Coupling> couplings = new ArrayList<>();
	
	protected abstract List<Model> getSubModels();
	protected abstract List<Coupling> getCouplings();
	
	@Override
	public NetworkModel build() {
		this.subModels = getSubModels();
		getCouplings().stream()
			.filter(c -> verifyCoupling(c))
			.forEach(c -> this.couplings.add(c));
		super.build();
		return this;
	}
	
	@Override
	protected Lambda lambda() {
		return new NetworkLambda(STATE, subModels, couplings);
	}
	
	@Override
	protected Delta delta() {
		return new NetworkDelta(STATE, subModels, couplings);
	}
	
	private boolean verifyCoupling(Coupling c) {
		switch(c.TYPE) {
		case INPUT_TO_INPUT:
		case OUTPUT_TO_INPUT:
			if(c.FIRST == c.LAST)
				return false;
			if(c.FIRST == null || c.LAST == null)
				return true;
			
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
	
	@Override
	public void resetInputAndOutput() {
		super.resetInputAndOutput();
		for(Model m : subModels) {
			m.resetInputAndOutput();
		}
	}
	
	@Override
	public void setDebugMode(boolean debug) {
		super.setDebugMode(debug);
		for(Model m : subModels) {
			m.setDebugMode(debug);
		}
	}
}
