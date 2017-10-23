package framework.model;

public abstract class AtomicModel extends Model {
	@Override
	protected int internalTicks() {
		return 1;
	}
}
