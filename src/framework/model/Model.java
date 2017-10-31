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
	
	protected Lambda lambda;
	protected Delta delta;
	protected InputParser inputParser;
	protected int internalTicks;
	protected InputToken[] input;
	protected OutputToken[] output;
	protected int tick;
	
	public Model() {
		INPUT_SET = inputSet();
		OUTPUT_SET = outputSet();
		STATE = getBag();
	}
	
	public Model build() {
		lambda = lambda();
		delta = delta();
		inputParser = inputParser();
		internalTicks = internalTicks();
		tick = 1;
		return this;
	}
	
	protected abstract Bag<?> getBag();
	protected abstract Set<InputToken> inputSet();
	protected abstract Set<OutputToken> outputSet();
	protected abstract Lambda lambda();
	protected abstract Delta delta();
	protected abstract InputParser inputParser();
	protected abstract int internalTicks();
	protected abstract String getModelName();
	protected abstract String getStateString();
	
	public OutputToken[] tick() {
		output = null;
		input = null;
		if(inputParser != null) {
			input = inputParser.parseInput();
		}
		
		for(int i = 0; i < internalTicks; i++) {
			executeLambda();
			executeDelta();
		}
		
		return output;
	}
	
	public OutputToken[] executeLambda() {
		output = lambda.execute();
		if(tick % internalTicks == 0) {
			log("State at internal tick #"+tick+": "+getStateString());
			log("Output at internal tick #"+tick+": ");
			Arrays.stream(output).forEach(o -> System.out.println("\t"+o.getName()));
		}
		return output;
	}
	
	public void executeDelta() {
		delta.execute(input);
		tick++;
		if(internalTicks == 1) {
			output = null;
			input = null;
		}
	}
	
	public void addToInputBag(Token[] tokens) {
		if(input == null)
		{
			log("addToInputBag - input is null");
			input = new InputToken[tokens.length];
			for(int i = 0; i < input.length; i++)
				input[i] = (InputToken)tokens[i]; 
		}
		else
		{
			log("addToInputBag - input is not null");
			input = Stream.concat(Arrays.stream(input), Arrays.stream(tokens)).toArray(InputToken[]::new);
		}
		log("adding " + tokens.length + " tokens to input bag... input bag is now size " + input.length);
	}
	
	public InputToken[] getInput() {
		return input;
	}
	
	private void log(String str) {
		System.out.println("["+getModelName()+"] - " + str);
	}
}
