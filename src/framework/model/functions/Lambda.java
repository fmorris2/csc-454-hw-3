package framework.model.functions;

import framework.model.persistence.ReadableBag;
import framework.model.token.output.OutputToken;

public abstract class Lambda {
	protected final ReadableBag<?> STATE;
	
	public Lambda(ReadableBag<?> state) {
		STATE = state;
	}
	
	public abstract OutputToken[] execute();
	
}
