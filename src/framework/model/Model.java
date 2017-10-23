package framework.model;

import java.util.Arrays;
import java.util.Set;

import framework.model.functions.Delta;
import framework.model.functions.Lambda;
import framework.model.input.InputParser;
import framework.model.input.InputToken;
import framework.model.output.OutputToken;
import framework.model.persistence.Bag;

public abstract class Model {
	public final Set<InputToken> INPUT_SET;
	public final Set<OutputToken> OUTPUT_SET;
	
	protected final Lambda LAMBDA;
	protected final Delta DELTA;
	protected final InputParser INPUT_PARSER;
	protected final int INTERNAL_TICKS;
	
	protected Bag state;
	protected InputToken[] input;
	protected OutputToken[] output;
	protected int tick;
	
	public Model() {
		INPUT_SET = inputSet();
		OUTPUT_SET = outputSet();
		LAMBDA = lambda();
		DELTA = delta();
		INPUT_PARSER = inputParser();
		INTERNAL_TICKS = internalTicks();
		tick = 0;
	}
	
	protected abstract Set<InputToken> inputSet();
	protected abstract Set<OutputToken> outputSet();
	protected abstract Lambda lambda();
	protected abstract Delta delta();
	protected abstract InputParser inputParser();
	protected abstract int internalTicks();
	protected abstract String getModelName();
	protected abstract String getStateString();
	
	public OutputToken[] tick() {
		if(INPUT_PARSER != null) {
			input = INPUT_PARSER.parseInput();
		}
		
		OutputToken[] output = null;
		for(int i = 0; i < INTERNAL_TICKS; i++) {
			log("State at internal tick #"+tick+": "+getStateString());
			
			output = LAMBDA.execute();
			log("Output at internal tick #"+tick+": ");
			Arrays.stream(output).forEach(o -> System.out.println("\t"+o));
			DELTA.execute(input);
			tick++;
		}
		
		return output;
	}
	
	private void log(String str) {
		System.out.println("["+getModelName()+"] - " + str);
	}
}
