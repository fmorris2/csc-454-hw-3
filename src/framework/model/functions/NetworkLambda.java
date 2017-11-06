package framework.model.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import framework.model.Model;
import framework.model.coupling.Coupling;
import framework.model.persistence.ReadableBag;
import framework.model.token.output.OutputToken;

public class NetworkLambda extends Lambda{
	private final List<Model> SUB_MODELS;
	private final List<Coupling> COUPLINGS;
	public NetworkLambda(ReadableBag<?> state, List<Model> models, List<Coupling> couplings) {
		super(state);
		SUB_MODELS = models;
		COUPLINGS = couplings;
	}

	@Override
	public OutputToken[] execute() {
		List<OutputToken> networkOutput = new ArrayList<>();
		for(Model m : SUB_MODELS) {
			networkOutput.addAll(Arrays.asList(routeOutput(m, m.executeLambda())));
		}
		return networkOutput.toArray(new OutputToken[networkOutput.size()]);
	}
	
	private OutputToken[] routeOutput(Model model, OutputToken[] output) {
		final List<OutputToken> networkOutput = new ArrayList<>();
		COUPLINGS.stream().filter(c -> c.FIRST == model).forEach((c) -> {
			if(c.LAST == null)
				networkOutput.addAll(Arrays.asList(output));
			else {
				debug("Routing output from " + c.FIRST + " to " + c.LAST);
				c.LAST.addToInputBag(output);
			}
		});
		
		return networkOutput.toArray(new OutputToken[networkOutput.size()]);
	}

}
