package BusinessLogic;

import Model.Client;
import Model.Server;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Scheduler {

    private List <Server> servers;
    private List <Thread> serverThreads;
    private int maxNoServers;
    private int maxClientPerServer;

    public int getMaxClientPerServer() {
        return maxClientPerServer;
    }

    FileWriter writer;

    public Scheduler(int maxNoServers, int maxClientPerServer) {
        try {
            writer = new FileWriter("output.txt");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        this.maxNoServers = maxNoServers;
        this.maxClientPerServer = maxClientPerServer;
        this.servers = new ArrayList<>();
        this.serverThreads = new ArrayList<>();

        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server(maxClientPerServer);
            servers.add(server);
            serverThreads.add(new Thread(server));
        }
    }

    public double calculateAverageWaitingTime(){
        int totalWaitingTime = Server.getTotalWaitingTime();
        int nrTotalClienti = Server.getClientCount();
        return totalWaitingTime/nrTotalClienti;

    }

    public double calcalateAverageServiceTime(){
        int totalServiceTime = Server.getTotalServiceTime();
        int nrTotalClient = Server.getClientCount();
        return totalServiceTime/nrTotalClient;
    }

    public int getPeakHour(){
        int peakHour=3;
        int peakHourQueue=0;
        for(Server server : servers){
            if(server.getClients().size()>=peakHour){
               peakHourQueue=peakHour;
            }
        }
        return peakHourQueue;
    }

    public void printServers(){
        this.writer=SimulationManager.getWriter();
        for(int i=0; i<servers.size(); i++){
            Server server = servers.get(i);
            BlockingQueue<Client> clients = new ArrayBlockingQueue<>(maxClientPerServer);
            clients.addAll(server.getClients());
            try {
                writer.append("\nQueue:" + (i+1) + " " + clients + " size " + clients.size());
                System.out.print("\nQueue: " + (i+1) + " " + clients + " size " + clients.size());
            }catch (IOException  e){
                throw new RuntimeException(e);
            }
        }
        try{
            writer.append("\n");
            System.out.print("\n");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public boolean dispatchClient(Client c){
        int currentMinimum = servers.get(0).getWaitingPeriod().get();
        int index = 0;

        // iterate over each server in the list and compare its waiting period to the current minimum
        for (int i = 1; i < servers.size(); i++) {
            Server server = servers.get(i);
            int waitingPeriod = server.getWaitingPeriod().get();
            if (waitingPeriod < currentMinimum) {
                currentMinimum = waitingPeriod;
                index = i; // update the index to the current position
            }
        }
           if(servers.get(index).addClient(c)){
               return true;
           }else{
               return false;
           }

    }

    public List<Server> getServers(){
        return servers;
    }

    // ...
}

