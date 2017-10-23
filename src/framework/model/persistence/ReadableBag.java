package framework.model.persistence;

import java.util.HashMap;

public class ReadableBag {

    protected final HashMap<String, Object> BAG = new HashMap<>();

    @SuppressWarnings("unchecked")
	public <T> T get(String name) {
        if (BAG.containsKey(name))
            return null;

        return (T) BAG.get(name);
    }

    @SuppressWarnings("unchecked")
	public <T> T get(String name, T defaultValue) {
        Object obj;
        return (obj = get(name)) != null ? (T) obj : defaultValue;
    }
}