package com.company;

public class Item {

    // this initalizes all the variables needed
    private final String name;
    private final double weight;
    private final int value;
    private boolean included;

    // this constructs the item
    public Item (String name, double weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    // this creates a copy of another item
    public Item (Item other) {
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        this.included = false;
    }

    // this returns the weight value
    public double getWeight () {
        return weight;
    }

    // this returns the value
    public int getValue () {
        return value;
    }

    // this returns the included value
    public boolean isIncluded () {
        return included;
    }

    // this sets the included value
    public void setIncluded (boolean included) {
        this.included = included;
    }

    // this prints out all valuble information about the item
    public String toString () {
        return name + " (" + weight + " lbs, $" + value + ")";
    }
}
