package Model;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class Server implements Runnable{

    private BlockingQueue<Client> queue;

    private  AtomicInteger waitingPeriod;
    private static int clientCount=0;
    private static int totalWaitingTime=0;
    private static int totalServiceTime=0;

   public AtomicInteger getWaitingPeriod() {
        return this.waitingPeriod;
    }


    public Server(int maxNrClient){
        this.queue = new ArrayBlockingQueue<>(maxNrClient);
        this.waitingPeriod= new AtomicInteger(0);
    }
    public Server(){}

    public boolean addClient(Client c) {
        this.totalServiceTime=totalServiceTime+(c.getServiceTime());
        ArrayList<Client> clienti = new ArrayList<>();
        clienti.addAll(queue);
        for(Client client : clienti){
            totalWaitingTime=totalWaitingTime+(client.getServiceTime());
        }
        boolean success = queue.offer(c);
        if (!success) {
            System.out.println("Queue is full. Client " + c + " was not added.");
            return false;
        }else {
            this.waitingPeriod.incrementAndGet();
            this.clientCount++;
            return true;
        }
    }

    public static int getTotalWaitingTime(){
       return totalWaitingTime;
    }
    public static int getClientCount(){
       return clientCount;
    }

    public static int getTotalServiceTime(){
       return totalServiceTime;
    }

    @Override
    public void run(){
        while(true){
            try {
                if(!queue.isEmpty()) {
                    Client nextClient = queue.peek();
                    if(nextClient.getServiceTime()<=0){
                        nextClient= queue.take();
                    }
                    Thread.sleep(1000);
                    waitingPeriod.set(queue.size());
                    nextClient.decrementServiceTime();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

    }
    public BlockingQueue<Client> getClients(){
        return queue;
    }
}
