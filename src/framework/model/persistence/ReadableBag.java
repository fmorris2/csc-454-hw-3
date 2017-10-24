package framework.model.persistence;

import java.util.HashMap;

import framework.model.Model;

public class ReadableBag<M extends Model> {

	public final M MODEL;
    protected final HashMap<String, Object> BAG = new HashMap<>();

    public ReadableBag(M model) {
    	MODEL = model;
    }
    
    @SuppressWarnings("unchecked")
	public <T> T get(String name) {
        if (!BAG.containsKey(name))
            return null;

        return (T) BAG.get(name);
    }

    @SuppressWarnings("unchecked")
	public <T> T get(String name, T defaultValue) {
        Object obj;
        return (obj = get(name)) != null ? (T) obj : defaultValue;
    }
    
    public int getInt(String name) {
    	return (int)BAG.get(name);
    }
    
    public boolean getBool(String name) {
    	return (boolean)BAG.get(name);
    }
}