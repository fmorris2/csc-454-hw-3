package framework.model.input;

import java.util.Set;

public abstract class InputParser {
	protected final Set<InputToken> INPUT_SET;
	
	public InputParser(Set<InputToken> input) {
		INPUT_SET = input;
	}
	
	public abstract InputToken[] parseInput();
}
