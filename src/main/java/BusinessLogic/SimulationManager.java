package BusinessLogic;

import Model.Client;
import Model.Server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable {
    public int numberOfClients = 50;
    public int numberOfServers = 5;
    public int timeLimit = 60;
    public int minArrivalTime = 2;
    public int maxArrivalTime = 40;
    public int minProcessingTime = 1;
    public int maxProcessingTime = 7;


    private Scheduler scheduler;

    private List<Client> generatedClients;
    private static FileWriter writer;

    public static FileWriter getWriter() {
        return writer;
    }

    public SimulationManager() {
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        try {
            writer = new FileWriter("output.txt");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        for (int i = 0; i < numberOfServers; i++) {
            Thread serverThread = new Thread(scheduler.getServers().get(i));
            serverThread.start();
        }

        generateNRandomClients(numberOfClients);
    }


    private void generateNRandomClients(int numberOfClients) {
        generatedClients = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            Client cl = generateSingleRandomClient();
            cl.setID(i+1);
            generatedClients.add(cl);
        }
        Collections.sort(generatedClients);

    }

    private Client generateSingleRandomClient() {
        Client c = new Client();
        int randomIntSer = 2 + (int) (Math.random() * ((maxProcessingTime - minProcessingTime) + 1));
        c.setServiceTime(randomIntSer);
        int randomIntArr = 2 + (int) (Math.random() * ((maxArrivalTime - minArrivalTime) + 1));;
        c.setArrivalTime(randomIntArr);
        return c;

    }

    @Override
    public void run() {
        int currentTime = 0;
        int peakHour=3;
        int peakHourQueue=0;
            while (currentTime < timeLimit) {
            Iterator<Client> itr = generatedClients.iterator();
            while (itr.hasNext()) {
                Client c = itr.next();
                if (c.getArrivalTime() == currentTime) {
                    if(scheduler.dispatchClient(c)) {
                        itr.remove();
                    }
                }
            }
            try {
                writer.append("\nTime " + currentTime + "\nWaiting Clients: ");
                System.out.print("\nTime " + currentTime + "\nWaiting Clients: ");
                for (Client client : generatedClients) {
                    writer.append(""+client);
                    System.out.print(client);
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            scheduler.printServers();
            currentTime++;
            for(Server server: scheduler.getServers()){
                if(server.getClients().size()>=peakHour){
                    peakHourQueue=peakHour;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            try {
                writer.append("\nAfter: " + generatedClients);
                System.out.print("\nAfter: " + generatedClients);
                writer.append("\nAverage waiting time: "+scheduler.calculateAverageWaitingTime());
                writer.append("\nAverage Service time: "+scheduler.calcalateAverageServiceTime());
                writer.append("\nPeak hour: "+peakHourQueue);
                writer.close();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            System.exit(0);
    }
}
