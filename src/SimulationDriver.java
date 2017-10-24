import hw3_model.HW3NetworkModel;
import framework.model.Simulation;


public class SimulationDriver {

	public static void main(String[] args) {
		//Simulation sim = new Simulation(new VendingMachine().build());
		Simulation sim = new Simulation(new HW3NetworkModel().build());
		sim.run();
	}

}
