package com.company;

public class Customer implements Comparable<Customer> {

    // this creates all variables
    private int customerNum;
    private double arrivalTime;
    private int items;
    private double timeSpent;
    private double startWait = 0.0;
    private double endWait = 0.0;

    // this creates the customers
    public Customer(double arrivalTime, int items, double timeSpent, int customerNum) {
        this.arrivalTime = arrivalTime;
        this.items = items;
        this.timeSpent = timeSpent;
        this.customerNum = customerNum;
    }

    // this creates the arrival event for the customer
    public ArrivalEvent makeArrivalEvent() {
        ArrivalEvent ae = new ArrivalEvent(this, arrivalTime);

        return ae;
    }

    // this returns how many items the customer needs
    public int getItems() {
        return items;
    }

    // this returns how long the customer spends getting each item
    public double getTimeSpent() {
        return  timeSpent;
    }

    // this returns the start wait time of the customer
    public double getStartWait() {
        return startWait;
    }

    // this returns the end wait time of the customer
    public double getEndWait() {
        return endWait;
    }

    // this returns the unique customer number
    public int getCustomerNum() {
        return customerNum;
    }

    // this sets the start wait time of the customer
    public void setStartWait (double startWait) {
        this.startWait = startWait;
    }

    // this sets the end wait time of the customer
    public void setEndWait (double endWait) {
        this.endWait = endWait;
    }

    // this compares the customer to another customer
    public int compareTo(Customer other) {
        if (this.arrivalTime > other.arrivalTime) {
            return 1;
        } else if (this.arrivalTime < other.arrivalTime) {
            return -1;
        } else {
            return 0;
        }
    }

    // this prints out all the valid information
    public String toString() {
        return "arrived: " + arrivalTime + " items: " + items + " time per item: " + timeSpent;
    }
}
