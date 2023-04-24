package Model;

public class Client implements Comparable<Client>{

    private int ID;

    public int getID() {
        return ID;
    }

    private int arrivalTime;

    public int getArrivalTime() {
        return arrivalTime;
    }


    private int serviceTime;
    public int getServiceTime() {
        return serviceTime;
    }
    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    @Override
    public int compareTo(Client c) {
        if(this.arrivalTime < c.arrivalTime){
            return -1;
        }
        else if(this.arrivalTime==c.arrivalTime){
            return 0;
        }
        else {
            return 1;
        }
    }
    public String toString(){
        return ("("+this.getID()+","+ this.getArrivalTime()+","+ this.getServiceTime()+"); ");
    }
    public void decrementServiceTime(){
        if(this.serviceTime>0) {
            this.serviceTime = this.serviceTime - 1;
        }
    }
}
