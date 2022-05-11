package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // this creates the average wait and event clock variablese
        double eventClock = 0.0;
        double avgWait = 0.0;

        // this creates all of the checkout lanes, all of the customers, and the arrival events for the customers
        PriorityQueue<CheckoutLane> regLanes = createRegLanes();
        PriorityQueue<CheckoutLane> expLanes = createExpLanes(regLanes.size());
        ArrayList<Customer> customers = readData();
        PriorityQueue<Event> events = createEvents(customers);

        // this loops until there are no more events
        while(events.peek() != null) {

            // this gets the first event in the list and sets the event clock to that time
            Event currentEvent = events.poll();
            eventClock = currentEvent.getTime();



            // this checks if the event is an arrival event
            if (currentEvent instanceof ArrivalEvent) {
                //System.out.println("ARRIVED");

                // if the event is an arrival event it creates a finished shopping event and offers it to the events priority queue
                FinishedShoppingEvent doneShop = new FinishedShoppingEvent(currentEvent.getCustomer(), currentEvent.getTime());
                events.offer(doneShop);

                // this prints out the needed info about the arrival event and customer
                //System.out.println(eventClock + ": Arrival Event Customer " + currentEvent.getCustomer().getCusomerNum());
            }

            // this checks if the event is a finished shopping event
            else if (currentEvent instanceof FinishedShoppingEvent) {

                CheckoutLane cl = regLanes.poll();
                CheckoutLane cl2 = expLanes.poll();
                // this checks if the customer's items are less than or equal to 12
                if (currentEvent.getCustomer().getItems() <= 12) {

                    // this checks if the first of the regular lanes and the first of the express lanes are equal
                    if (cl.lineSize() == cl2.lineSize()) {

                        // this prints out the valid information
                        System.out.println(eventClock + ": Finished Shopping Event Customer " + currentEvent.getCustomer().getCustomerNum() + " added to Express Checkout Line " + cl2.getCheckoutNum() + " (the lane has " + (cl2.lineSize()+1) + " people in line now)");

                        // if there is at least one person in the line it starts the wait of the customer
                        if (cl2.lineSize() > 0) {
                            currentEvent.getCustomer().setStartWait(eventClock);
                        }

                        // if there is no one in the line it creates the finished checkout event for the customer and adds it to the events queue
                        else if (cl2.lineSize() == 0) {
                            //System.out.println("event made");
                            FinishedCheckoutEvent checkOut = new FinishedCheckoutEvent(currentEvent.getCustomer(), currentEvent.getTime(), cl2);
                            events.offer(checkOut);
                        }

                        // this adds the customer to the express lane
                        cl2.add(currentEvent.getCustomer());
                    }

                    // this checks if the regular lane is shorter than the shortest express lane
                    else if (cl.lineSize() > cl2.lineSize()) {

                        // this prints out all the valid information
                        System.out.println(eventClock + ": Finished Shopping Event Customer " + currentEvent.getCustomer().getCustomerNum() + " added to Regular Checkout Line " + cl.getCheckoutNum() + " (the lane has " + (cl.lineSize()+1) + " people in line now)");

                        // if there is at least one person in the line it starts the customer's wait time
                        if (cl.lineSize() > 0) {
                            currentEvent.getCustomer().setStartWait(eventClock);
                        }

                        // if there is no one in the line it creates a new finished checkout event for the customer and offers it to the events priority queue
                        else if (cl.lineSize() == 0) {
                            //System.out.println("event made");
                            FinishedCheckoutEvent checkOut = new FinishedCheckoutEvent(currentEvent.getCustomer(), currentEvent.getTime(), cl);
                            events.offer(checkOut);
                        }

                        // this adds the customer to the checkout lane
                        cl.add(currentEvent.getCustomer());
                    }

                    // this checks if the express lane is the shortest lane
                    else {

                        // this prints out all the valid information
                        System.out.println(eventClock + ": Finished Shopping Event Customer " + currentEvent.getCustomer().getCustomerNum() + " added to Express Checkout Line " + cl2.getCheckoutNum() + " (the lane has " + (cl2.lineSize()+1) + " people in line now)");

                        // if there is at least one person in the lane it sets the start wait of the customer
                        if (cl2.lineSize() > 0) {
                            currentEvent.getCustomer().setStartWait(eventClock);

                        }

                        // if there is no one in the lane it creates a new finished checkout event for the customer and adds it to the events priority queue
                        else if (cl2.lineSize() == 0) {
                            FinishedCheckoutEvent checkOut = new FinishedCheckoutEvent(currentEvent.getCustomer(), currentEvent.getTime(), cl2);
                            events.offer(checkOut);
                        }

                        // this adds the customer to the checkout lane
                        cl2.add(currentEvent.getCustomer());
                    }
                }

                // if the customer has more than 12 items it prints out all the valid information
                else {
                    System.out.println(eventClock + ": Finished Shopping Event Customer " + currentEvent.getCustomer().getCustomerNum() + " added to Regular Checkout Line " + cl.getCheckoutNum() + " (the lane has " + (cl.lineSize()+1) + " people in line now)");

                    // if there is at least one person in the lane it sets the start wait of the customer
                    if (cl.lineSize() > 0) {
                        currentEvent.getCustomer().setStartWait(eventClock);
                    }

                    // if there is no one in the lane it creates a new finished checkout event for the customer and adds it to the events priority queue
                    else if (cl.lineSize() == 0) {
                        //System.out.println("event made");
                        FinishedCheckoutEvent checkOut = new FinishedCheckoutEvent(currentEvent.getCustomer(), currentEvent.getTime(), cl);
                        events.offer(checkOut);
                    }

                    // this adds the customer to the shortest regular lane
                    cl.add(currentEvent.getCustomer());
                }
                regLanes.offer(cl);
                expLanes.offer(cl2);
            }

            // this checks if the event is a finished checkout event and prints out the valid information and takes the first customer out of the checkout lane
            else if (currentEvent instanceof FinishedCheckoutEvent) {

                // this prints out all the valid information and takes the customer out of the checkout lane
                System.out.println(eventClock + ": Finished Checkout Event Customer " + currentEvent.getCustomer().getCustomerNum() + " had a wait time of " + ((FinishedCheckoutEvent) currentEvent).getAvgWait() + " minutes in line " + ((FinishedCheckoutEvent) currentEvent).getCheckout().getCheckoutNum());
                ((FinishedCheckoutEvent) currentEvent).getCheckout().poll();


                // if the checkout lane has one or more people left in it it creates a finished checkout event for the next customer in line and sets the end wait for the customer and offers the event to the events priority queue
                if(((FinishedCheckoutEvent) currentEvent).getCheckout().lineSize() > 0) {
                    FinishedCheckoutEvent checkOut = new FinishedCheckoutEvent(((FinishedCheckoutEvent) currentEvent).getCheckout().peek(), currentEvent.getTime(), ((FinishedCheckoutEvent) currentEvent).getCheckout());
                    checkOut.getCustomer().setEndWait(eventClock);
                    events.offer(checkOut);
                }

                // this adds the wait time of the current event's customer to the average wait
                avgWait += ((FinishedCheckoutEvent) currentEvent).getAvgWait();
            }
        }

        // once there is no more events left to use it divides the average wait by the amount of customers and prints it out
        System.out.println(avgWait/customers.size() + " = wait time");
    }

    //
    public static ArrayList<Customer> readData() throws FileNotFoundException {
        Scanner in = new Scanner(new File("arrival_medium.txt"));
        ArrayList<Customer> customers = new ArrayList<>();
        int customerNum = 1;

        while(in.hasNext()) {

            String line = in.nextLine();
            Scanner splitString = new Scanner(line);
            splitString.useDelimiter("\\s");

            Customer customer = new Customer(splitString.nextDouble(), splitString.nextInt(), splitString.nextDouble(), customerNum);
            customers.add(customer);
            customerNum++;
        }
        return customers;
    }

    public static PriorityQueue<Event> createEvents(ArrayList<Customer> customers) {
        PriorityQueue<Event> events = new PriorityQueue<>();

        for(Customer c: customers) {
            ArrivalEvent arrive = c.makeArrivalEvent();

            events.offer(arrive);
        }
        return events;
    }

    public static PriorityQueue<CheckoutLane> createRegLanes() {
        PriorityQueue<CheckoutLane> lanes = new PriorityQueue<>();

        for(int i = 0; i < 8; i++) {
            RegularCheckout lane = new RegularCheckout(i);
            lanes.offer(lane);
        }

        return lanes;
    }

    public static PriorityQueue<CheckoutLane> createExpLanes(int index) {
        PriorityQueue<CheckoutLane> lanes = new PriorityQueue<>();
        int laneNumber = index;

        for(int i = 0; i < 3; i++) {
            laneNumber ++;
            ExpressCheckout lane = new ExpressCheckout(laneNumber);
            lanes.offer(lane);

        }

        return lanes;
    }
}
