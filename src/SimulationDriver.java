import hw3_model.ExampleNetwork;
import framework.model.Model;
import framework.model.Simulation;


public class SimulationDriver {

	public static void main(String[] args) {
		Model model = new ExampleNetwork().build();//new HW3NetworkModel().build(); //new VendingMachine().build();
		model.setDebugMode(true);
		
		Simulation sim = new Simulation(model);
		sim.run();
	}

}
