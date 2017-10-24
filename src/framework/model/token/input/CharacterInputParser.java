package framework.model.token.input;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CharacterInputParser extends InputParser {
	
	private final Scanner SCANNER;
	private final Map<Character, UserInjectableInputToken> SYMBOL_MAP;
	
	private int maxInputCharacters = Integer.MAX_VALUE;
	
	public CharacterInputParser(Set<InputToken> input) {
		super(input);
		SCANNER = new Scanner(System.in);
		SYMBOL_MAP = getSymbolMap();
	}
	
	public CharacterInputParser(Set<InputToken> input, int maxInputCharacters) {
		this(input);
		this.maxInputCharacters = maxInputCharacters;
	}

	@Override
	public InputToken[] parseInput() {
		String inputLine = SCANNER.nextLine();
		return convertStringToArrayOfInputs(inputLine);
	}
	
	private InputToken[] convertStringToArrayOfInputs(String input) {
		if(input.length() > maxInputCharacters) {
			input = input.substring(0, maxInputCharacters);
		}
		
		return input.chars()
				.filter(c -> SYMBOL_MAP.containsKey((char)c))
				.mapToObj(c -> SYMBOL_MAP.get((char)c))
				.toArray(size -> new InputToken[size]);
	}
	
	private Map<Character, UserInjectableInputToken> getSymbolMap()
	{
		Map<Character, UserInjectableInputToken> symbolMap = new HashMap<>();
		INPUT_SET.stream()
			.filter(i -> i instanceof UserInjectableInputToken)
			.map(i -> (UserInjectableInputToken)i)
			.forEach(i -> symbolMap.put(i.getInputSymbol(), i));
		return symbolMap;
	}

}
