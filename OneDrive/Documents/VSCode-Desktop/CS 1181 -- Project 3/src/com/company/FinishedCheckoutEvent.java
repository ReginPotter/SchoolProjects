package com.company;

public class FinishedCheckoutEvent extends Event {

    // this creates the needed variables
    private CheckoutLane checkout;

    // this creates the finished checkout event
    public FinishedCheckoutEvent(Customer customer, double time, CheckoutLane checkout) {
        super(customer, time);

        // if the checkout is a regular checkout it sets the time this way
        if(checkout instanceof RegularCheckout) {
            this.setTime(time + (.05 * customer.getItems() + 2.0));
        }

        // if the checkout is an express checkout it sets the time this way
        else if (checkout instanceof ExpressCheckout) {
            this.setTime(time + (.1 * customer.getItems() + 1.0));
        }
        this.checkout = checkout;
    }

    // this returns the checkout
    public CheckoutLane getCheckout() {
        return checkout;
    }

    // this returns the average wait time
    public double getAvgWait() {
        return this.getCustomer().getEndWait() - this.getCustomer().getStartWait();
    }
}
