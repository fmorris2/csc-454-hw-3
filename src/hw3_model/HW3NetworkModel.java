package hw3_model;

import hw3_model.models.xor.XORModel;
import hw3_model.tokens.Bit;
import hw3_model.tokens.One;
import hw3_model.tokens.Zero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import framework.model.Model;
import framework.model.NetworkModel;
import framework.model.coupling.Coupling;
import framework.model.coupling.CouplingType;
import framework.model.functions.DefaultNetworkDelta;
import framework.model.functions.DefaultNetworkLambda;
import framework.model.functions.NetworkDelta;
import framework.model.functions.NetworkLambda;
import framework.model.persistence.Bag;
import framework.model.token.input.CharacterInputParser;
import framework.model.token.input.InputParser;
import framework.model.token.input.InputToken;
import framework.model.token.output.OutputToken;

public class HW3NetworkModel extends NetworkModel {
	HW3NetworkModelInfo INFO = new HW3NetworkModelInfo();
	
	@Override
	protected NetworkLambda lambda() {
		return new DefaultNetworkLambda(STATE, INFO.MODELS, INFO.COUPLINGS);
	}
	
	@Override
	protected NetworkDelta delta() {
		return new DefaultNetworkDelta(STATE, INFO.MODELS, INFO.COUPLINGS);
	}

	@Override
	protected Bag<?> getBag() {
		return new Bag<HW3NetworkModel>(this);
	}

	@Override
	protected Set<InputToken> inputSet() {
		return new HashSet<>(Arrays.asList(
				new Zero(), new One()
		));
	}

	@Override
	protected Set<OutputToken> outputSet() {
		return new HashSet<>(Arrays.asList(new Bit()));
	}

	@Override
	protected InputParser inputParser() {
		return new CharacterInputParser(INPUT_SET);
	}

	@Override
	protected int internalTicks() {
		return 3;
	}

	@Override
	protected String getModelName() {
		return "HW3 Network Model";
	}

	@Override
	protected String getStateString() {
		return "N/A";
	}
	
	private class HW3NetworkModelInfo {
		private final Model XOR1 = new XORModel("XOR1").build();
		private final Model XOR2 = new XORModel("XOR2").build();
		
		public final List<Model> MODELS = new ArrayList<>(Arrays.asList(
				XOR1, XOR2
		));
		
		public final List<Coupling> COUPLINGS = new ArrayList<>(Arrays.asList(
				new Coupling(null, XOR1, CouplingType.INPUT_TO_INPUT),
				new Coupling(XOR1, null, CouplingType.OUTPUT_TO_OUTPUT),
				new Coupling(null, XOR2, CouplingType.INPUT_TO_INPUT),
				new Coupling(XOR2, null, CouplingType.OUTPUT_TO_OUTPUT)
		));
	}

}
