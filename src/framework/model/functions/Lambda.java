package framework.model.functions;

import framework.model.output.OutputToken;
import framework.model.persistence.ReadableBag;

public abstract class Lambda {
	protected final ReadableBag STATE;
	
	public Lambda(ReadableBag state) {
		STATE = state;
	}
	
	public abstract OutputToken[] execute();
	
}
