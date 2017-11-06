package framework.model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import framework.model.functions.Delta;
import framework.model.functions.Lambda;
import framework.model.persistence.Bag;
import framework.model.token.Token;
import framework.model.token.input.InputParser;
import framework.model.token.input.InputToken;
import framework.model.token.output.OutputToken;

public abstract class Model {
	public final Set<InputToken> INPUT_SET;
	public final Set<OutputToken> OUTPUT_SET;
	protected final Bag<?> STATE;
	
	public static int numModels = 0;
	
	protected InputParser inputParser;
	protected int internalTicks;
	protected InputToken[] input;
	protected OutputToken[] output;
	protected Lambda lambda;
	protected Delta delta;
	protected int tick;
	private int id;
	private boolean receivesExternalInput;
	
	public Model() {
		INPUT_SET = inputSet();
		OUTPUT_SET = outputSet();
		STATE = getBag();
		STATE.add("debugMode", false);
	}
	
	public Model build() {
		inputParser = inputParser();
		internalTicks = internalTicks();
		lambda = lambda();
		delta = delta();
		tick = 1;
		numModels++;
		id = numModels;
		return this;
	}
	
	public Model receivesExternalInput(boolean b) {
		receivesExternalInput = b;
		return this;
	}

	protected abstract Bag<?> getBag();
	protected abstract Set<InputToken> inputSet();
	protected abstract Set<OutputToken> outputSet();
	protected abstract InputParser inputParser();
	protected abstract int internalTicks();
	protected abstract String getModelName();
	protected abstract String getStateString();
	protected abstract Lambda lambda();
	protected abstract Delta delta();
	
	public OutputToken[] tick() {
		if(receivesExternalInput && inputParser != null) {
			input = inputParser.parseInput();
		}
		
		for(int i = 0; i < internalTicks; i++) {
			executeLambda();
			executeDeltaOnce();
		}
		
		return output;
	}
	
	public void executeDelta() {
		for(int i = 0; i < internalTicks; i++) {
			executeDeltaOnce();
		}
	}
	
	private void executeDeltaOnce() {
		debug("delta is being executed for " + getModelName());
		delta.execute(input);
		tick++;
	}
	
	public OutputToken[] executeLambda() {
		debug("lambda is being executed for " + getModelName());
		output = lambda.execute();
		if(tick % internalTicks == 0) {
			debug("State at internal tick #"+tick+": "+getStateString());
			debug("Output at internal tick #"+tick+": ");
			Arrays.stream(output).forEach(o -> debug("\t"+o.getName()));
		}
		return output;
	}
	
	public void resetInputAndOutput() {
		output = null;
		input = null;
	}
	
	public void addToInputBag(Token[] tokens) {
		if(input == null)
		{
			input = new InputToken[tokens.length];
			for(int i = 0; i < input.length; i++)
				input[i] = (InputToken)tokens[i]; 
		}
		else
		{
			input = Stream.concat(Arrays.stream(input), Arrays.stream(tokens)).toArray(InputToken[]::new);
		}
		debug("adding " + tokens.length + " tokens to input bag... input bag is now size " + input.length);
	}
	
	public InputToken[] getInput() {
		return input;
	}
	
	protected void log(String str) {
		System.out.println("["+getModelName()+ " " + id +"] - " + str);
	}
	
	protected void debug(String str) {
		if(STATE.getBool("debugMode"))
			log(str);
	}
	
	public void setDebugMode(boolean debug) {
		STATE.addOrUpdate("debugMode", debug);
	}
}
