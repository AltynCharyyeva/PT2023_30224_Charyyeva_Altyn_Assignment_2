package BusinessLogic;

public class DemoSimulation {


    public static void main(String[] args) {
        SimulationManager gen = new SimulationManager();
        Thread thread = new Thread(gen);
        thread.start();

    }
}
