package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class CheckoutLane implements Comparable<CheckoutLane> {

    // this creates all variables needed
    private Queue<Customer> line;
    private int checkoutNum;

    // this creates the checkout lane
    public CheckoutLane(int checkoutNum) {
        line = new LinkedList<>();
        this.checkoutNum = checkoutNum;
    }

    // this returns the unique checkout lane number
    public int getCheckoutNum() {
        return checkoutNum;
    }

    // this adds a customer to the lane
    public void add(Customer customer) {
        line.offer(customer);
    }

    // this polls the lane
    public Customer poll() {
        return line.poll();
    }

    // this peeks the lane
    public Customer peek() {
        return line.peek();
    }

    // this gives the lane's size
    public int lineSize() {
        return line.size();
    }

    // this compares a lane to another lane
    public int compareTo(CheckoutLane other) {
        if (this.lineSize() > other.lineSize()) {
            return 1;
        } else if (this.lineSize() < other.lineSize()) {
            return -1;
        } else {
            return 0;
        }
    }

    // this prints out the valid information
    public String toString() {
        String s = "";
        s += line.peek() + " ";
        s += lineSize();
        return s;
    }
}