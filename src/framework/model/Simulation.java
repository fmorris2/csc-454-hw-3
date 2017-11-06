package framework.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import framework.model.token.output.OutputToken;

public class Simulation {
	private List<Model> models;
	private int tick = 1;
	
	public Simulation(Model... models) {
		this.models = new ArrayList<>(Arrays.asList(models));
	}
	
	public void run() {
		while(true) {
			System.out.println("[Simulation] Executing tick #" + tick);
			for(Model m : models) {
				OutputToken[] output = m.tick();
				m.resetInputAndOutput();
				System.out.println(m.getModelName() + " output at simulation tick #"+tick+": ");
				Arrays.stream(output).forEach(o -> System.out.println("\t"+o.getName()));
			}
			tick++;
		}
	}
	
	public void addModels(Model... models) {
		this.models.addAll(Arrays.asList(models));
	}
}
