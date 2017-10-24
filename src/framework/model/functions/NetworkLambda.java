package framework.model.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import framework.model.Model;
import framework.model.coupling.Coupling;
import framework.model.coupling.CouplingType;
import framework.model.persistence.ReadableBag;
import framework.model.token.output.OutputToken;

public abstract class NetworkLambda extends Lambda {
	
	private final List<Model> SUB_MODELS;
	private final List<Coupling> COUPLINGS;
	
	public NetworkLambda(ReadableBag<?> state, List<Model> subModels, List<Coupling> couplings) {
		super(state);
		SUB_MODELS = subModels;
		COUPLINGS = couplings;
	}

	@Override
	public OutputToken[] execute() {
		List<OutputToken> networkOutput = new ArrayList<>();
		
		//execute lambdas for all sub models
		for(Model m : SUB_MODELS) {
			OutputToken[] output = m.executeLambda();
			
			//route output in accordance with couplings
			COUPLINGS.stream()
				.filter(coupling -> coupling.FIRST == m && coupling.TYPE != CouplingType.INPUT_TO_INPUT)
				.forEach(coupling -> {
					if(coupling.LAST == null) //if the coupling leads to the network output
						networkOutput.addAll(Arrays.asList(output));
					else //if the coupling leads to another submodel
					{
						coupling.LAST.addToInputBag(output);
					}
				});
			
		}
		
		return networkOutput.toArray(new OutputToken[networkOutput.size()]);
	}
	
	protected abstract OutputToken[] networkSpecificLambda();

}
