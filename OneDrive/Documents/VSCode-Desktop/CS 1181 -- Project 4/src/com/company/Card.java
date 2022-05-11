package com.company;

public class Card /*implements Comparable<Card>*/{

    // this creates the needed variables
    String value;
    String suit;

    // this creates the card
    public Card (String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    // this sets the value of the card
    public void setValue (String value) {
        this.value = value;
    }

    // this sets the suit of the card
    public void setSuit (String suit) {
        this.suit = suit;
    }

    // this gets the value of the card
    public String getValue() {
        return value;
    }

    // this gets the suit of the card
    public String getSuit() {
        return suit;
    }

    /*public int compareTo(Card other) {
        int thisValue = 0;
        int otherValue = 0;

        if (this.getValue().equals("King")) {
            thisValue = 13;
        } else if (this.getValue().equals("Queen")) {
            thisValue = 12;
        } else if (this.getValue().equals("Jack")) {
            thisValue = 11;
        } else if (this.getValue().equals("Ace")) {
            thisValue = 1;
        } else {
            thisValue = Integer.parseInt(this.getValue());
        }

        if (other.getValue().equals("King")) {
            otherValue = 13;
        } else if (other.getValue().equals("Queen")) {
            otherValue = 12;
        } else if (other.getValue().equals("Jack")) {
            otherValue = 11;
        } else if (other.getValue().equals("Ace")) {
            otherValue = 1;
        } else {
            otherValue = Integer.parseInt(other.getValue());
        }

        if (thisValue > otherValue) {
            return -1;
        } else if (thisValue < otherValue) {
            return 1;
        } else {
            return 0;
        }
    }*/

    // this prints out the value and the suit of the card to the console
    public String toString() {
        return value + " of " + suit;
    }
}
