package hw3_model;

import hw3_model.models.memory_model.MemoryModel;
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
import framework.model.persistence.Bag;
import framework.model.token.input.CharacterInputParser;
import framework.model.token.input.InputParser;
import framework.model.token.input.InputToken;
import framework.model.token.output.OutputToken;

public class HW3NetworkModel extends NetworkModel {

	private final HW3NetworkModelInfo INFO = new HW3NetworkModelInfo();
	
	public HW3NetworkModel() {
		this.receivesExternalInput(true);
	}
	
	@Override
	protected List<Model> getSubModels() {
		return INFO.MODELS;
	}

	@Override
	protected List<Coupling> getCouplings() {
		return INFO.COUPLINGS;
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
		private final Model XOR1 = new XORModel().build();
		private final Model XOR2 = new XORModel().build();
		private final Model MEM_MODEL = new MemoryModel().build();
		
		public final List<Model> MODELS = new ArrayList<>(Arrays.asList(
				XOR1, XOR2, MEM_MODEL
		));
		
		public final List<Coupling> COUPLINGS = new ArrayList<>(Arrays.asList(
				//XOR1 COUPLINGS
				new Coupling(null, XOR1, CouplingType.INPUT_TO_INPUT),
				new Coupling(XOR1, XOR2, CouplingType.OUTPUT_TO_INPUT),
				
				//XOR2 COUPLINGS
				new Coupling(XOR2, null, CouplingType.OUTPUT_TO_OUTPUT),
				new Coupling(XOR2, MEM_MODEL, CouplingType.OUTPUT_TO_INPUT),
				
				//MEM MODEL COUPLINGS
				new Coupling(MEM_MODEL, XOR2, CouplingType.OUTPUT_TO_INPUT)
		));
	}
}
