package com.company;

public class Event implements Comparable<Event> {

    // this creates all the needed variables
    private Customer customer;
    private double time;

    // this creates the event
    public Event(Customer customer, double time) {
        this.customer = customer;
        this.time = time;
    }

    // this sets the time of the event
    public void setTime(double time) {
        this.time = time;
    }

    // this returns the customer associated with this event
    public Customer getCustomer() {
        return customer;
    }

    // this returns the event's time
    public double getTime() {
        return time;
    }

    // this compares the event to another event based on time
    public int compareTo(Event other) {
        if (this.time > other.time) {
            return 1;
        } else if (this.time < other.time) {
            return -1;
        } else {
            return 0;
        }
    }

    // this prints out all valid information
    public String toString() {
        return "arrived: " + time + " items: " + customer.getItems() + " time per item: " + customer.getTimeSpent();
    }
}
