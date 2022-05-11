package com.company;

public class FinishedShoppingEvent extends Event {

    // this creates the finished shopping event
    public FinishedShoppingEvent(Customer customer, double time) {
        super(customer, time + (customer.getItems() * customer.getTimeSpent()));
    }
}
