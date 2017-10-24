package framework.model.persistence;

import java.util.Map;
import java.util.Set;

import framework.model.Model;

public class Bag<M extends Model> extends ReadableBag<M> {

	public Bag(M model) {
		super(model);
	}

	public Set<Map.Entry<String, Object>> getAll() {
		return BAG.entrySet();
	}

	public boolean add(String name, Object value) {

		if (BAG.containsKey(name))
			return false;

		BAG.put(name, value);
		return true;
	}

	public void addOrUpdate(String name, Object value) {
		BAG.put(name, value);
	}

	public boolean remove(String name) {
		if (!BAG.containsKey(name))
			return false;

		BAG.remove(name);
		return true;
	}
}
