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

public class ExampleNetwork extends NetworkModel {
	
	private final ExampleNetworkInfo INFO = new ExampleNetworkInfo();
	
	public ExampleNetwork() {
		receivesExternalInput(true);
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
		return new Bag<ExampleNetwork>(this);
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
		return 1;
	}

	@Override
	protected String getModelName() {
		return "Example Model";
	}

	@Override
	protected String getStateString() {
		return "N/A";
	}
	
	private class ExampleNetworkInfo {
		private final Model HW3_MODEL_1 = new HW3NetworkModel().build();
		private final Model HW3_MODEL_2 = new HW3NetworkModel().build();
		private final Model XOR_MODEL = new XORModel().build();
		private final Model MEM_MODEL = new MemoryModel().build();
		
		public final List<Model> MODELS = new ArrayList<>(Arrays.asList(
				HW3_MODEL_1, HW3_MODEL_2, XOR_MODEL, MEM_MODEL
		));
		
		public final List<Coupling> COUPLINGS = new ArrayList<>(Arrays.asList(
				
				new Coupling(null, HW3_MODEL_1, CouplingType.INPUT_TO_INPUT),
				new Coupling(null, XOR_MODEL, CouplingType.INPUT_TO_INPUT),
				
				new Coupling(HW3_MODEL_1, HW3_MODEL_2, CouplingType.OUTPUT_TO_INPUT),
				new Coupling(XOR_MODEL, MEM_MODEL, CouplingType.OUTPUT_TO_INPUT),
				new Coupling(MEM_MODEL, HW3_MODEL_2, CouplingType.OUTPUT_TO_INPUT),
				
				new Coupling(HW3_MODEL_2, null, CouplingType.OUTPUT_TO_OUTPUT)
		));
	}

}
