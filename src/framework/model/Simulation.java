package framework.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulation {
	private List<Model> models;
	
	public Simulation(Model... models) {
		this.models = new ArrayList<>(Arrays.asList(models));
	}
	
	public void run() {
		while(true) {
			for(Model m : models) {
				m.tick();
			}
		}
	}
	
	public void addModels(Model... models) {
		this.models.addAll(Arrays.asList(models));
	}
}
